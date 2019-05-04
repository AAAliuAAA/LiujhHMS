package com.smepms.workattendance.pojo;

import java.util.Date;

public class WorkAttendanceCheck {
  private Integer workAttendanceCheckId;
  private Date checkIn;
  private Date checkOut;
  private Date checkDate;
  private Integer employeeId;
  private Integer workAttendanceId;

  public Integer getWorkAttendanceId() {
    return workAttendanceId;
  }

  public void setWorkAttendanceId(Integer workAttendanceId) {
    this.workAttendanceId = workAttendanceId;
  }

  public Integer getWorkAttendanceCheckId() {
    return workAttendanceCheckId;
  }

  public void setWorkAttendanceCheckId(Integer workAttendanceCheckId) {
    this.workAttendanceCheckId = workAttendanceCheckId;
  }

  public Date getCheckIn() {
    return checkIn;
  }

  public void setCheckIn(Date checkIn) {
    this.checkIn = checkIn;
  }

  public Date getCheckOut() {
    return checkOut;
  }

  public void setCheckOut(Date checkOut) {
    this.checkOut = checkOut;
  }

  public Date getCheckDate() {
    return checkDate;
  }

  public void setCheckDate(Date checkDate) {
    this.checkDate = checkDate;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }
}
