package com.smepms.workattendance.pojo;

import com.smepms.workattendance.enums.HolidayTypeEnum;

import java.util.Date;

public class Holiday {
  private Integer holidayId;
  private Date holidayDate;
  private Integer holidayWeekday;
  private String holidayName;
  private String holidayComment;
  private Integer holidayType;

  public Integer getHolidayId() {
    return holidayId;
  }

  public void setHolidayId(Integer holidayId) {
    this.holidayId = holidayId;
  }

  public Date getHolidayDate() {
    return holidayDate;
  }

  public void setHolidayDate(Date holidayDate) {
    this.holidayDate = holidayDate;
  }

  public Integer getHolidayWeekday() {
    return holidayWeekday;
  }

  public void setHolidayWeekday(Integer holidayWeekday) {
    this.holidayWeekday = holidayWeekday;
  }

  public String getHolidayName() {
    return holidayName;
  }

  public void setHolidayName(String holidayName) {
    this.holidayName = holidayName;
  }

  public String getHolidayComment() {
    return holidayComment;
  }

  public void setHolidayComment(String holidayComment) {
    this.holidayComment = holidayComment;
  }

  public Integer getHolidayType() {
    return holidayType;
  }

  public void setHolidayType(Integer holidayType) {
    this.holidayType = holidayType;
  }
}
