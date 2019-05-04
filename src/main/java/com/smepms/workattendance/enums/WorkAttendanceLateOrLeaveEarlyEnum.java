package com.smepms.workattendance.enums;

/**
 * WorkAttendanceLateOrLeaveEarlyEnum
 *
 * @author liujh
 * @since 2018/5/9
 */
public enum WorkAttendanceLateOrLeaveEarlyEnum {
  LEAVE_EARLY,LATE;
  private Integer tableStatus = 1;

  public Integer getTableStatus() {
    return tableStatus;
  }
}
