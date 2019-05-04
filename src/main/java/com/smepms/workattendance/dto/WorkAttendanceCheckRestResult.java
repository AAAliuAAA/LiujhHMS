package com.smepms.workattendance.dto;

import com.smepms.workattendance.pojo.WorkAttendanceCheck;

public class WorkAttendanceCheckRestResult {
  private Integer checkStatus;
  private WorkAttendanceCheck workAttendanceCheck;

  public Integer getCheckStatus() {
    return checkStatus;
  }

  public void setCheckStatus(Integer checkStatus) {
    this.checkStatus = checkStatus;
  }

  public WorkAttendanceCheck getWorkAttendanceCheck() {
    return workAttendanceCheck;
  }

  public void setWorkAttendanceCheck(WorkAttendanceCheck workAttendanceCheck) {
    this.workAttendanceCheck = workAttendanceCheck;
  }
}
