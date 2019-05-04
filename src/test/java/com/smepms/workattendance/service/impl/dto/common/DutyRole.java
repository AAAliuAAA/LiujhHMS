package com.smepms.workattendance.service.impl.dto.common;

/**
 * @author liujh
 * @since 2018/3/30
 */
public class DutyRole {
  private Integer roleId;
  private String roleCode;
  private String roleName;

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @Override
  public String toString() {
    return "DutyRole{" +
        "roleId=" + roleId +
        ", roleCode='" + roleCode + '\'' +
        ", roleName='" + roleName + '\'' +
        '}';
  }
}
