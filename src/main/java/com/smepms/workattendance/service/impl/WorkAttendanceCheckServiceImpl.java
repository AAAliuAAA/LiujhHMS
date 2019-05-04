package com.smepms.workattendance.service.impl;

import com.smepms.common.util.ShiroUtils;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.workattendance.dto.WorkAttendanceCheckRestResult;
import com.smepms.workattendance.mapper.HolidayMapper;
import com.smepms.workattendance.mapper.WorkAttendanceCheckMapper;
import com.smepms.workattendance.mapper.WorkAttendanceConfigMapper;
import com.smepms.workattendance.mapper.WorkAttendanceMapper;
import com.smepms.workattendance.pojo.WorkAttendance;
import com.smepms.workattendance.pojo.WorkAttendanceCheck;
import com.smepms.workattendance.pojo.WorkAttendanceConfig;
import com.smepms.workattendance.service.HolidayService;
import com.smepms.workattendance.service.WorkAttendanceCheckService;
import com.smepms.workattendance.service.WorkAttendanceService;
import com.smepms.workattendance.util.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WorkAttendanceCheckServiceImpl implements WorkAttendanceCheckService {

  @Autowired
  private WorkAttendanceCheckMapper workAttendanceCheckMapper;
  @Autowired
  private WorkAttendanceMapper workAttendanceMapper;
  @Autowired
  private WorkAttendanceConfigMapper workAttendanceConfigMapper;
  @Autowired
  private WorkAttendanceService workAttendanceService;
  @Autowired
  private HolidayMapper holidayMapper;

  @Value("${CHECKINNOTCHECKOUT}")
  private Integer CHECKINNOTCHECKOUT;
  @Value("${CHECKOUTNOTCHECKIN}")
  private Integer CHECKOUTNOTCHECKIN;
  @Value("${NOTCHECKINNOTCHECKOUT}")
  private Integer NOTCHECKINNOTCHECKOUT;
  @Value("${CHECKINANDCHECKOUT}")
  private Integer CHECKINANDCHECKOUT;



  public boolean checkIn() {
    //查询当前使用的上班时间配置
    WorkAttendanceConfig workAttendanceConfig = workAttendanceConfigMapper.queryWhichWorkAttendanceConfigIsUsing();
    Date now = new Date();
    Date checkInTime = workAttendanceConfig.getLastCheckInTime();
    //如果在holiday当中则不需要进行打卡校验
    boolean passFlag = isHoliday(now);
    if(!passFlag){
      //当前时间大于设定时间则会返回true
      if((DateUtil.compareDate(now,checkInTime))){
        return false;
      }
    }
    Employee employee = ShiroUtils.getLogEmployeeWithBLOBs();
    //查询当前天是否已经存在该员工的考勤记录，如果没有则创建
    //考勤记录应该由调度统一生成，但是开发当中还是直接创建
    WorkAttendance workAttendance = workAttendanceService.queryWorkAttendanceIfExists(employee);
    Integer workAttendanceId = workAttendance.getWorkAttendanceId();
    //因为将checkIn和checkOut都写在了一张表上，所以要先查询是否存在
    WorkAttendanceCheck workAttendanceCheck = queryWorkAttendanceCheckIfExists(employee, workAttendanceId);
    workAttendanceCheck.setCheckIn(new Date());
    return workAttendanceCheckMapper.updateById(workAttendanceCheck) > 0;
  }

  public boolean checkOut() {
    WorkAttendanceConfig workAttendanceConfig = workAttendanceConfigMapper.queryWhichWorkAttendanceConfigIsUsing();
    Date now = new Date();
    Date checkOutTime = workAttendanceConfig.getEarlyCheckOutTime();
    boolean passFlag = isHoliday(now);
    if(!passFlag){
      //当前时间大于设定时间则会返回true
      if(!(DateUtil.compareDate(now,checkOutTime))){
        return false;
      }
    }
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    Employee employee = (Employee) session.getAttribute("logEmployee");
    WorkAttendance workAttendance = workAttendanceService.queryWorkAttendanceIfExists(employee);
    WorkAttendanceCheck workAttendanceCheck = queryWorkAttendanceCheckIfExists(employee,workAttendance.getWorkAttendanceId());
    if(workAttendanceCheck.getCheckIn()==null){
      return false;
    }
    workAttendanceCheck.setCheckOut(new Date());
    return workAttendanceCheckMapper.updateById(workAttendanceCheck)>0;
  }

  public List<WorkAttendanceCheck> queryWorkAttendanceCheckByTargetDate(Date date) {
    return workAttendanceCheckMapper.selectByTargetDate(date);
  }

  public WorkAttendanceCheckRestResult queryCheckStatus() {
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    Employee employee = (Employee) session.getAttribute("logEmployee");

    WorkAttendanceCheck workAttendanceCheck = workAttendanceCheckMapper.selectBySameDateSameEmployee(new Date(), employee.getEmployeeId());
    WorkAttendanceCheckRestResult workAttendanceCheckRestResult = new WorkAttendanceCheckRestResult();
    workAttendanceCheckRestResult.setWorkAttendanceCheck(workAttendanceCheck);
    Integer checkStatus = null;
    if(workAttendanceCheck==null){
      checkStatus = NOTCHECKINNOTCHECKOUT;
      workAttendanceCheckRestResult.setCheckStatus(checkStatus);
      return workAttendanceCheckRestResult;
    }
    if(workAttendanceCheck.getCheckIn()!=null&&workAttendanceCheck.getCheckOut()!=null){
      checkStatus = CHECKINANDCHECKOUT;
      workAttendanceCheckRestResult.setCheckStatus(checkStatus);
      return workAttendanceCheckRestResult;
    }
    if(workAttendanceCheck.getCheckIn()!=null){
      checkStatus = CHECKINNOTCHECKOUT;
      workAttendanceCheckRestResult.setCheckStatus(checkStatus);
    }else{
      checkStatus = CHECKOUTNOTCHECKIN;
      workAttendanceCheckRestResult.setCheckStatus(checkStatus);
    }
    return workAttendanceCheckRestResult;
  }



  private WorkAttendanceCheck queryWorkAttendanceCheckIfExists(Employee employee, Integer workAttendanceId) {
    WorkAttendanceCheck workAttendanceCheck = workAttendanceCheckMapper.selectBySameDateSameEmployee(new Date(), employee.getEmployeeId());
    if (workAttendanceCheck == null) {
      WorkAttendanceCheck workAttendanceCheckNew = new WorkAttendanceCheck();
      workAttendanceCheckNew.setCheckDate(new Date());
      workAttendanceCheckNew.setEmployeeId(employee.getEmployeeId());
      workAttendanceCheckNew.setWorkAttendanceId(workAttendanceId);
      workAttendanceCheckMapper.insert(workAttendanceCheckNew);
      workAttendanceCheck = workAttendanceCheckNew;
    }
    return workAttendanceCheck;
  }

  private boolean isHoliday(Date now) {
    Date nowDay = format2Day(now);
    boolean passFlag = false;
    if(holidayMapper.queryHolidayExists(now)>0){
      passFlag = true;
    }
    return passFlag;
  }

  private Date format2Day(Date targetDate){
    Calendar cal1 = Calendar.getInstance();
    cal1.setTime(targetDate);
    // 将时分秒,毫秒域清零
    cal1.set(Calendar.HOUR_OF_DAY, 0);
    cal1.set(Calendar.MINUTE, 0);
    cal1.set(Calendar.SECOND, 0);
    cal1.set(Calendar.MILLISECOND, 0);
    return cal1.getTime();
  }

}
