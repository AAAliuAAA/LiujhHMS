package com.smepms.workattendance.dto;

public class HolidayApiDto {
  private String holiday;
  private String ret_code;
  private String weekDay;
  private String cn;

  @Override
  public String toString() {
    return "HolidayApiDto{" +
        "holiday='" + holiday + '\'' +
        ", ret_code='" + ret_code + '\'' +
        ", weekDay='" + weekDay + '\'' +
        ", cn='" + cn + '\'' +
        ", holiday_remark='" + holiday_remark + '\'' +
        ", en='" + en + '\'' +
        ", day='" + day + '\'' +
        ", type='" + type + '\'' +
        ", end='" + end + '\'' +
        ", begin='" + begin + '\'' +
        '}';
  }

  private String holiday_remark;
  private String en;
  private String day;
  private String type;
  private String end;
  private String begin;

  public String getHoliday() {
    return holiday;
  }

  public void setHoliday(String holiday) {
    this.holiday = holiday;
  }

  public String getRet_code() {
    return ret_code;
  }

  public void setRet_code(String ret_code) {
    this.ret_code = ret_code;
  }

  public String getWeekDay() {
    return weekDay;
  }

  public void setWeekDay(String weekDay) {
    this.weekDay = weekDay;
  }

  public String getCn() {
    return cn;
  }

  public void setCn(String cn) {
    this.cn = cn;
  }

  public String getHoliday_remark() {
    return holiday_remark;
  }

  public void setHoliday_remark(String holiday_remark) {
    this.holiday_remark = holiday_remark;
  }

  public String getEn() {
    return en;
  }

  public void setEn(String en) {
    this.en = en;
  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  public String getBegin() {
    return begin;
  }

  public void setBegin(String begin) {
    this.begin = begin;
  }
}
