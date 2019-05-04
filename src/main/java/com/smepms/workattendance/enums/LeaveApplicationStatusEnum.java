package com.smepms.workattendance.enums;

/**
 * LeaveApplicationStatusEnum
 *
 * @author liujh
 * @since 2018/5/7
 */
public enum LeaveApplicationStatusEnum {
  CREATED("等待部门主管审批"),MANAGER_PASS("部门主管审批通过"),HR_PASS("人事部门审批通过"),DELETE("员工直属上级驳回，删除");
  private String statusName;

  public String getStatusName() {
    return statusName;
  }
  LeaveApplicationStatusEnum(String statusName){
    this.statusName = statusName;
  }
}
