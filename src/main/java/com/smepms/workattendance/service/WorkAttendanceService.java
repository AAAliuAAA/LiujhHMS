package com.smepms.workattendance.service;

import com.smepms.jobmanagement.pojo.Department;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.workattendance.dto.WorkAttendanceGroupRestResult;
import com.smepms.workattendance.dto.WorkAttendanceRestResult;
import com.smepms.workattendance.enums.WorkAttendanceLateOrLeaveEarlyEnum;
import com.smepms.workattendance.pojo.WorkAttendance;

import java.util.Date;
import java.util.List;

public interface WorkAttendanceService {
  public boolean createWorkAttendance();

  WorkAttendance queryWorkAttendanceIfExists(Employee employee);

  /**
   * 考勤总览
   * @param date
   * @param departmentId
   * @param employeeId
   * @param pageNo
   * @param pageSize
   * @return
   */
  WorkAttendanceGroupRestResult getWorkAttendanceGroupByDateAndDepartment(Date date,Integer departmentId,Integer employeeId,Integer pageNo,Integer pageSize,Integer employeeWorkId);

  /**
   * 使用调度更新当前的考勤表，严格模式
   * late leaveEarly
   * @return
   */
  boolean updateWorkAttendanceLateAndLeaveEarly();

  /**
   * 对指定日期执行迟到或早退处理，执行了以后严格调度则不会对当前操作的考勤信息进行操作
   */
  boolean updateWorkAttendance(Integer workAttendanceId, WorkAttendanceLateOrLeaveEarlyEnum workAttendanceLateOrLeaveEarlyEnum);


}
