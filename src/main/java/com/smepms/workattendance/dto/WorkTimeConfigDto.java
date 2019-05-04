package com.smepms.workattendance.dto;

import java.util.Date;

public class WorkTimeConfigDto {
  private Date checkInTime;
  private Date checkOutTime;

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
}
