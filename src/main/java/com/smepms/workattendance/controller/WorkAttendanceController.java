package com.smepms.workattendance.controller;

import com.smepms.common.util.ShiroUtils;
import com.smepms.workattendance.dto.WorkAttendanceGroupRestResult;
import com.smepms.workattendance.dto.WorkAttendanceRestResult;
import com.smepms.workattendance.enums.WorkAttendanceLateOrLeaveEarlyEnum;
import com.smepms.workattendance.service.WorkAttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * WorkAttendanceController
 *
 * @author liujh
 * @since 2018/5/9
 */
@RequestMapping("/workAttendance")
@RestController
public class WorkAttendanceController {
  private final static Logger logger = LoggerFactory.getLogger(WorkAttendanceController.class);

  @Autowired
  private WorkAttendanceService workAttendanceService;
  @Value("${EMPLOYEE_QUERYALL_DEFAULT_PAGE_SIZE}")
  private Integer DEFAULT_PAGE_SIZE;

  /**
   * hr 查询指定日期的考勤信息
   * @param targetDate
   * @return
   */
  @GetMapping("/hr")
  public WorkAttendanceGroupRestResult getEmployeeWorkAttendanceByTargetDate(@RequestParam(value = "workAttendanceDate",required = true)Date targetDate,
                                                                             @RequestParam(value = "departmentId",required = false) Integer departmentId,
                                                                             @RequestParam(value = "employeeWorkId",required = false)Integer employeeWorkId,
                                                                             @RequestParam(value = "pageNo",required = false)Integer pageNo,
                                                                             @RequestParam(value = "pageSize",required = false)Integer pageSize) {

    if(pageSize == null){
      pageSize = DEFAULT_PAGE_SIZE;
    }
    if(pageNo == null){
      pageNo = 1;
    }
    return workAttendanceService.getWorkAttendanceGroupByDateAndDepartment(targetDate,departmentId,null,pageNo,pageSize,employeeWorkId);
  }


  /**
   * 员工自己查询自己的考勤信息
   */
  @GetMapping("/self")
  public WorkAttendanceGroupRestResult getEmployeeWorkAttendanceByTargetEmployeeId(@RequestParam(value = "workAttendanceDate",required = false)Date targetDate,
                                                                                   @RequestParam(value = "pageNo",required = false)Integer pageNo,
                                                                                   @RequestParam(value = "pageSize",required = false)Integer pageSize){
    if(pageNo == null){
      pageNo = 1;
    }
    if(pageSize == null){
      pageSize = DEFAULT_PAGE_SIZE;
    }
    Integer employeeId = ShiroUtils.getLogEmployeeWithBLOBs().getEmployeeId();
    WorkAttendanceGroupRestResult workAttendanceGroupRestResult = workAttendanceService.getWorkAttendanceGroupByDateAndDepartment(targetDate,null,employeeId,pageNo,pageSize,null);
    return workAttendanceGroupRestResult;
  }


  /**
   * 更新考勤表早退和迟到状态（慎用）
   */
  @GetMapping("/updateWorkAttendanceLateAndLeaveEarly")
  public boolean updateWorkAttendanceLateAndLeaveEarly(){
    return workAttendanceService.updateWorkAttendanceLateAndLeaveEarly();
  }



  //处理日期请求
  @InitBinder
  public void initBinder(WebDataBinder binder) throws Exception {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  /**
   * 获取迟到早退的修改字段信息
   */
  @GetMapping("/late_leave_early_table_status")
  public Integer getWorkAttendanceLateOrLeaveEarlyTableStatus(){
    return WorkAttendanceLateOrLeaveEarlyEnum.LEAVE_EARLY.getTableStatus();
  }

  /**
   * 修改当前考勤记录的迟到信息
   */
  @PostMapping("/update/late/{workAttendanceId}")
  public boolean updateWorkAttendanceLate(@PathVariable("workAttendanceId")Integer workAttendanceId){
    return workAttendanceService.updateWorkAttendance(workAttendanceId,WorkAttendanceLateOrLeaveEarlyEnum.LATE);
  }

  /**
   * 修改当前考勤记录的早退信息
   */
  @PostMapping("/update/leave_early/{workAttendanceId}")
  public boolean updateWorkAttendanceLeaveEarly(@PathVariable("workAttendanceId")Integer workAttendanceId){
    return workAttendanceService.updateWorkAttendance(workAttendanceId,WorkAttendanceLateOrLeaveEarlyEnum.LATE);
  }

  /**
   * 删除当前考勤记录的迟到早退信息
   */
  @PostMapping("/delete/status/{workAttendanceId}")
  public boolean updateWorkAttendanceStatus(@PathVariable("workAttendanceId")Integer workAttendanceId){
    return workAttendanceService.updateWorkAttendance(workAttendanceId,null);
  }

}
