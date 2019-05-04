package com.smepms.workattendance.controller;

import com.smepms.workattendance.dto.WorkAttendanceCheckRestResult;
import com.smepms.workattendance.service.WorkAttendanceCheckService;
import com.smepms.workattendance.service.WorkAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/workAttendanceCheck")
public class WorkAttendanceCheckController {

  @Value("${CHECKINNOTCHECKOUT}")
  private Integer CHECKINNOTCHECKOUT;
  @Value("${CHECKOUTNOTCHECKIN}")
  private Integer CHECKOUTNOTCHECKIN;
  @Value("${NOTCHECKINNOTCHECKOUT}")
  private Integer NOTCHECKINNOTCHECKOUT;
  @Value("${CHECKINANDCHECKOUT}")
  private Integer CHECKINANDCHECKOUT;
  @Autowired
  private WorkAttendanceService workAttendanceService;
  @Autowired
  private WorkAttendanceCheckService workAttendanceCheckService;


  @RequestMapping(value = "/checkIn",method = RequestMethod.GET)
  public Integer checkIn() {
    boolean checkFlag = workAttendanceCheckService.checkIn();
    if (!checkFlag) {
      return 0;
    }
    return 1;
  }

  @RequestMapping(value = "/queryCheckStatus",method = RequestMethod.GET)
  public WorkAttendanceCheckRestResult queryCheckStatis() {
      WorkAttendanceCheckRestResult workAttendanceCheckRestResult = workAttendanceCheckService.queryCheckStatus();
      return workAttendanceCheckRestResult;
  }

  @RequestMapping(value = "/checkOut",method = RequestMethod.GET)
  public Integer checkOut() {
    boolean checkFlag = workAttendanceCheckService.checkOut();
    if (!checkFlag) {
      return 0;
    }
    return 1;
  }

}
