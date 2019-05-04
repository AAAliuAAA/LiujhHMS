package com.smepms.workattendance.dto;

import java.util.Date;

/**
 * WorkAttendanceRestResult
 *
 * @author liujh
 * @since 2018/5/9
 */
public class WorkAttendanceRestResult {
  private Integer workAttendanceId;
  private Integer employeeId;
  private Integer employeeWorkId;
  private String employeeName;
  private Integer employeeLeaderWorkId;
  private String employeeLeaderName;
  //上下班打卡时间
  private Date checkInTime;
  private Date checkOutTime;
  //当前日期
  private Date workAttendanceDate;
  //加班时间
  private Integer workOverTimeHour;
  private Integer late;
  private Integer leaveEarly;
  //再一次ajax请求，不要写死在页面上


  public Integer getWorkAttendanceId() {
    return workAttendanceId;
  }

  public void setWorkAttendanceId(Integer workAttendanceId) {
    this.workAttendanceId = workAttendanceId;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public Integer getEmployeeWorkId() {
    return employeeWorkId;
  }

  public void setEmployeeWorkId(Integer employeeWorkId) {
    this.employeeWorkId = employeeWorkId;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public Integer getEmployeeLeaderWorkId() {
    return employeeLeaderWorkId;
  }

  public void setEmployeeLeaderWorkId(Integer employeeLeaderWorkId) {
    this.employeeLeaderWorkId = employeeLeaderWorkId;
  }

  public String getEmployeeLeaderName() {
    return employeeLeaderName;
  }

  public void setEmployeeLeaderName(String employeeLeaderName) {
    this.employeeLeaderName = employeeLeaderName;
  }

  public Date getCheckInTime() {
    return checkInTime;
  }

  public void setCheckInTime(Date checkInTime) {
    this.checkInTime = checkInTime;
  }

  public Date getCheckOutTime() {
    return checkOutTime;
  }

  public void setCheckOutTime(Date checkOutTime) {
    this.checkOutTime = checkOutTime;
  }

  public Date getWorkAttendanceDate() {
    return workAttendanceDate;
  }

  public void setWorkAttendanceDate(Date workAttendanceDate) {
    this.workAttendanceDate = workAttendanceDate;
  }

  public Integer getWorkOverTimeHour() {
    return workOverTimeHour;
  }

  public void setWorkOverTimeHour(Integer workOverTimeHour) {
    this.workOverTimeHour = workOverTimeHour;
  }

  public Integer getLate() {
    return late;
  }

  public void setLate(Integer late) {
    this.late = late;
  }

  public Integer getLeaveEarly() {
    return leaveEarly;
  }

  public void setLeaveEarly(Integer leaveEarly) {
    this.leaveEarly = leaveEarly;
  }
}
