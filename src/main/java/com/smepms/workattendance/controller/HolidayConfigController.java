package com.smepms.workattendance.controller;

import com.github.pagehelper.PageInfo;
import com.smepms.common.util.PageUtil;
import com.smepms.workattendance.pojo.Holiday;
import com.smepms.workattendance.service.HolidayService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/holidayConfig")
public class HolidayConfigController {
  @Value("${HOLIDAY_CONFIG_DEFAULT_PAGE_SIZE}")
  private Integer HOLIDAY_CONFIG_DEFAULT_PAGE_SIZE;
  @Autowired
  private HolidayService holidayService;
  private final static Logger logger = Logger.getLogger(HolidayConfigController.class);

  @ResponseBody
  @RequestMapping(value = "/getAllHolidayConfiguration",method = RequestMethod.GET)
  public PageUtil getAllHolidayConfiguration(Date startDate,Date endDate,Integer pageNo,Integer pageSize){
    logger.info("当前查询页面pageSize :"+pageSize+"pageNo :"+pageNo);
    if(pageSize==null){
      pageSize = HOLIDAY_CONFIG_DEFAULT_PAGE_SIZE;
    }
    if(pageNo == null){
      pageNo = 1;
    }
    PageUtil pageUtil = holidayService.queryAllHolidaySelective(startDate,endDate,pageNo,pageSize);
    logger.info("当前传出页面："+pageUtil.getCurrentPage());
    return pageUtil;
  }



  @RequestMapping(value = "/postNewHolidayConfiguration",method = RequestMethod.POST)
  @ResponseBody
  public Integer craeteNewHolidayConfigurtion(Holiday holiday){
    boolean flag = holidayService.createNewHolidayConfig(holiday);
    if(flag){
      return 1;
    }
    return 0;
  }

  @RequestMapping(value = "/deleteHolidayConfiguration/{holidayId}",method = RequestMethod.DELETE)
  @ResponseBody
  public Integer deleteHolidayConfiguration(@PathVariable Integer holidayId){
    return holidayService.deleteHolidayById(holidayId)?1:0;
  }


  //处理日期请求
  @InitBinder
  public void initBinder(WebDataBinder binder) throws Exception {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

}
