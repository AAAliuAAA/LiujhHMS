package com.smepms.workattendance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smepms.common.consts.HolidayConsts;
import com.smepms.common.util.PageUtil;
import com.smepms.workattendance.dto.HolidayApiDto;
import com.smepms.workattendance.dto.HolidayGroup;
import com.smepms.workattendance.mapper.HolidayMapper;
import com.smepms.workattendance.pojo.Holiday;
import com.smepms.workattendance.service.HolidayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HolidayServiceImpl implements HolidayService {
  private final static Logger logger = Logger.getLogger(HolidayServiceImpl.class);
  @Autowired
  private HolidayMapper holidayMapper;


  public void queryHolidayTillTargetDate(Date latestDate) {
    StringBuffer address = new StringBuffer("http://route.showapi.com/894-2?showapi_appid=55334&showapi_sign=71e4169baee04f748c793081ed8e839f&day=");
    Calendar targetCalendar = Calendar.getInstance();
    //使用默认时区创建一个Calendar对象
    while (true) {
      try {
        //防止被封号，每5ms发送一次
        Thread.sleep(5l);
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date targetDate = targetCalendar.getTime();
        address.append(df.format(targetDate));
        logger.info("当前请求url[" + address + "]");

        URL url = new URL(address.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.connect();
        InputStream is = conn.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        StringBuffer bs = new StringBuffer();
        String l = null;
        while ((l = buffer.readLine()) != null) {
          bs.append(l);
        }
        JSONObject jsonObject = JSONObject.parseObject(bs.toString());
        buffer.close();
        is.close();
        HolidayApiDto holidayApiDto = JSONObject.parseObject(jsonObject.getString("showapi_res_body"), HolidayApiDto.class);

        Holiday holiday = new Holiday();
        //如果为节假日
        if (!holidayApiDto.getHoliday().equals("无") && !holidayApiDto.equals("") && holidayApiDto.getHoliday() != null) {
          String holidayName = holidayApiDto.getHoliday().equals("周末") ? holidayApiDto.getCn() : holidayApiDto.getHoliday();
          holiday.setHolidayName(holidayName);
          holiday.setHolidayDate(targetDate);
          holiday.setHolidayWeekday(Integer.parseInt(holidayApiDto.getWeekDay()));
          String holidaytype = holidayApiDto.getHoliday();
          if (holidaytype.equals("周末")) {
            holiday.setHolidayType(HolidayConsts.WEEKENDS);
          } else {
            holiday.setHolidayComment(holidayApiDto.getHoliday_remark());
            holiday.setHolidayType(HolidayConsts.VOCATION);
          }
          holidayMapper.addHoliday(holiday);
        }
        logger.info("插入一条数据完成，当前插入的为[" + df.format(targetDate) + "]");
        address.delete(address.length() - 8, address.length());
        targetCalendar.add(Calendar.DAY_OF_MONTH, 1);
        if (df.format(latestDate).equals(df.format(targetDate))) {
          break;
        }
      } catch (InterruptedException e) {
        logger.error("发送请求异常");
        e.printStackTrace();
      } catch (IOException e) {
        logger.error("流读取异常");
        e.printStackTrace();
      }
    }

  }

  public PageUtil<HolidayGroup> queryAllHolidaySelective(Date startDate, Date endDate, Integer pageNo, Integer pageSize) {
    PageUtil pageUtil = new PageUtil();
    Integer totalRecord = holidayMapper.queryCountHolidays(startDate, endDate);
    pageUtil.startPage(pageSize, pageNo, totalRecord);
    List<Holiday> holidayList = holidayMapper.queryAllHolidays(startDate, endDate, pageUtil.getStartRecord(), pageUtil.getPageSize());
    //将每日固定日期转换为假期组格式
    List<HolidayGroup> holidayGroupList = parseHolidays2Group(holidayList);
    pageUtil.setList(holidayGroupList);
    return pageUtil;
  }

  public boolean createNewHolidayConfig(Holiday holiday){
    boolean flag =  holidayMapper.queryHolidayExists(holiday.getHolidayDate()) > 0;
    if(flag){
      return false;
    }

    if(holiday.getHolidayType()==null){
      holiday.setHolidayType(HolidayConsts.SELFDECISION);
    }
    if(holiday.getHolidayWeekday()==null){
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(holiday.getHolidayDate());
      holiday.setHolidayWeekday(calendar.get(Calendar.DAY_OF_WEEK)-1);
    }
      return holidayMapper.addHoliday(holiday) > 0;
  }


  private List<HolidayGroup> parseHolidays2Group(List<Holiday> holidayList) {
    //几种情况
    /**
     * 1.和前后都不相同，则直接添加
     * 2.和后面相同，和前面不同，说明是第一个，创建Group并add，一直添加到和后面不同为止，然后把这些下标都添加到continueList
     */
    //筛选
    List<Integer> continueList = new ArrayList<Integer>();
    List<HolidayGroup> holidayGroupList = new LinkedList<HolidayGroup>();
    //遍历
    for (int i = 0; i < holidayList.size(); i++) {
      if (continueList.contains(i)) {
        continue;
      }
      Holiday currentDay = holidayList.get(i);
      //如果会进行到最后一个，直接放行
      if (i == holidayList.size() - 1) {
        HolidayGroup hg = new HolidayGroup();
        hg.setGroupName(currentDay.getHolidayName());
        hg.setGroupType(parseGroupType(currentDay));
        hg.getHolidayContent().add(currentDay);
        holidayGroupList.add(hg);
        continue;
      }
      Holiday nextDay = holidayList.get(i + 1);
      Holiday beforeDay = null;
      if (i >= 1) {
        beforeDay = holidayList.get(i - 1);
      }
      boolean flagPass = (beforeDay == null && currentDay.getHolidayName().equals(nextDay.getHolidayName())) || (beforeDay != null && !beforeDay.getHolidayName().equals(currentDay.getHolidayName()) && nextDay.getHolidayName().equals(currentDay.getHolidayName()));
      if (flagPass) {
        //从计算的当日开始，计算到结束的所有相同节假日
        HolidayGroup hg = new HolidayGroup();
        hg.setGroupName(currentDay.getHolidayName());
        hg.setGroupType(parseGroupType(currentDay));
        int j = i;
        while (true) {
          //如果到了最后一条数据，直接break?
          if (j == holidayList.size() - 1) {
            Holiday holiday = holidayList.get(j);
            if (!holiday.getHolidayName().equals(currentDay.getHolidayName())) {
              //直接添加新的
              HolidayGroup holidayGroup = new HolidayGroup();
              holidayGroup.setGroupName(holiday.getHolidayName());
              holidayGroup.setGroupType(parseGroupType(holiday));
              holidayGroup.getHolidayContent().add(holiday);
              holidayGroupList.add(holidayGroup);
              continueList.add(j);
              break;
            } else {
              //添加到当前
              hg.getHolidayContent().add(holiday);
            }
            break;
          }
          Holiday holiday = holidayList.get(j);
          if (!holiday.equals(currentDay) && !holiday.getHolidayName().equals(currentDay.getHolidayName())) {
            break;
          }
          hg.getHolidayContent().add(holiday);
          continueList.add(j);
          j++;
        }
        holidayGroupList.add(hg);
      } else {
        HolidayGroup holidayGroup = new HolidayGroup();
        holidayGroup.getHolidayContent().add(currentDay);
        holidayGroup.setGroupType(parseGroupType(currentDay));
        holidayGroup.setGroupName(currentDay.getHolidayName());
        holidayGroupList.add(holidayGroup);
      }
    }
    return holidayGroupList;
  }

  private String parseGroupType(Holiday currentDay) {
    if(currentDay.getHolidayType().equals(HolidayConsts.SELFDECISION)){
      return HolidayConsts.SELFDECISIONSTRING;
    }else{
      return currentDay.getHolidayType().equals(HolidayConsts.WEEKENDS) ? HolidayConsts.WEEKENDSSTRTING : HolidayConsts.VOCATIONSTRING;
    }
  }

  public boolean deleteHolidayById(Integer holidayId){
    return holidayMapper.deleteHolidayById(holidayId) > 0;
  }

}
