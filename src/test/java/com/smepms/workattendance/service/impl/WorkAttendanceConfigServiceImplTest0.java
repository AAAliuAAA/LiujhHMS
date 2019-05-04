package com.smepms.workattendance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.smepms.common.consts.HolidayConsts;
import com.smepms.workattendance.dto.HolidayApiDto;
import com.smepms.workattendance.enums.HolidayTypeEnum;
import com.smepms.workattendance.mapper.HolidayMapper;
import com.smepms.workattendance.mapper.WorkAttendanceConfigMapper;
import com.smepms.workattendance.pojo.Holiday;
import com.smepms.workattendance.pojo.WorkAttendanceConfig;
import com.smepms.workattendance.service.WorkAttendanceConfigService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by acer on 2018/2/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-common.xml")
public class WorkAttendanceConfigServiceImplTest0 {
    private final static Logger logger = Logger.getLogger(WorkAttendanceConfigServiceImplTest0.class);
    @Autowired
    private HolidayMapper holidayMapper;
    @Autowired
    private WorkAttendanceConfigMapper workAttendanceConfigMapper;

    @Autowired
    private WorkAttendanceConfigService workAttendanceConfigService;
    @Test
    public void selectAllWorkAttendanceConfigWithEmployeeWorkId() throws Exception {
        PageInfo pageInfo = workAttendanceConfigService.selectAllWorkAttendanceConfigWithEmployeeWorkId(0,5);
        System.out.println(pageInfo);
    }
    @Test
    public void testTimeInit(){
        Date date = new Date();
        long time = date.getTime();
        Time now = new Time(time);
    }
    @Test
    public void addHlidayUtil() throws IOException {
        String address = "http://route.showapi.com/894-2?showapi_appid=55334&showapi_sign=71e4169baee04f748c793081ed8e839f&day=20180324";

        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5*1000);
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        conn.connect();
        InputStream is = conn.getInputStream();
        BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
        StringBuffer bs = new StringBuffer();
        String l = null;
        while((l=buffer.readLine())!=null){
            bs.append(l);
        }
        System.out.println(bs.toString());
        JSONObject jsonObject = JSONObject.parseObject(bs.toString());
        System.out.println(jsonObject);
        HolidayApiDto had = JSONObject.parseObject(jsonObject.getString("showapi_res_body"),HolidayApiDto.class);
        System.out.println(had);
//        String holidayDetail = map.get("showapi_res_body");
//        System.out.println(holidayDetail);
    }

    /**
     * 插入数据
     */
    @Test
    public void insertIntoDatabase() throws InterruptedException, IOException, ParseException {
        StringBuffer address = new StringBuffer("http://route.showapi.com/894-2?showapi_appid=55334&showapi_sign=71e4169baee04f748c793081ed8e839f&day=");
        Calendar targetCalendar = Calendar.getInstance();
        //使用默认时区创建一个Calendar对象
        while (true){
            //防止被封号，每5ms发送一次
            Thread.sleep(5l);
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            Date targetDate =  targetCalendar.getTime();
            address.append(df.format(targetDate));

            URL url = new URL(address.toString());
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5*1000);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
            StringBuffer bs = new StringBuffer();
            String l = null;
            while((l=buffer.readLine())!=null){
                bs.append(l);
            }
            JSONObject jsonObject = JSONObject.parseObject(bs.toString());
            HolidayApiDto holidayApiDto = JSONObject.parseObject(jsonObject.getString("showapi_res_body"),HolidayApiDto.class);

            Holiday holiday = new Holiday();
            //如果为节假日
            if(!holidayApiDto.getHoliday().equals("无")&&!holidayApiDto.equals("")&&holidayApiDto.getHoliday()!=null){
                String holidayName = holidayApiDto.getHoliday().equals("周末")?holidayApiDto.getCn():holidayApiDto.getHoliday();
                holiday.setHolidayName(holidayName);
                holiday.setHolidayDate(targetDate);
                holiday.setHolidayWeekday(Integer.parseInt(holidayApiDto.getWeekDay()));
                String holidaytype = holidayApiDto.getHoliday();
                if(holidaytype.equals("周末")){
                    holiday.setHolidayType(HolidayConsts.WEEKENDS);
                }else{
                    holiday.setHolidayComment(holidayApiDto.getHoliday_remark());
                    holiday.setHolidayType(HolidayConsts.VOCATION);
                }
                holidayMapper.addHoliday(holiday);
            }
            logger.info("插入一条数据完成，当前插入的为["+df.format(targetDate)+"]");
            logger.info("当前请求url["+address+"]");
            address.delete(address.length()-8,address.length());
            targetCalendar.add(Calendar.DAY_OF_MONTH,1);
            //当到达最后一天时，则...
            Date lastdate = df.parse("2019-01-01");
            if(lastdate.equals(targetDate)){
                break;
            }
        }
    }

    @Test
    public void testGetValue(){
//        ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring/applicationContext-common.xml");
//        System.out.println(ac.getBean("${jdbc.password}"));
//        StringBuffer sb = new StringBuffer("http://route.showapi.com/894-2?showapi_appid=55334&showapi_sign=71e4169baee04f748c793081ed8e839f&day=111111");
//        sb.delete(sb.length()-6,sb.length());
//        System.out.println(sb.toString());
//        logger.info("插入一条数据完成，当前插入的为");
        Holiday holiday = new Holiday();
        holiday.setHolidayType(HolidayConsts.VOCATION);
        holiday.setHolidayDate(new Date());
        holiday.setHolidayComment("!!!!");
        holiday.setHolidayName("院旗");
        holiday.setHolidayWeekday(HolidayConsts.MODAY);
        holidayMapper.addHoliday(holiday);
    }
    @Test
    public void testCalendar(){
        Holiday holiday = new Holiday();
        holiday.setHolidayDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(holiday.getHolidayDate());
        holiday.setHolidayWeekday(calendar.get(Calendar.DAY_OF_WEEK)-1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(holiday.getHolidayWeekday());
    }
}