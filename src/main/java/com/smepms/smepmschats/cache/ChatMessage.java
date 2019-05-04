package com.smepms.smepmschats.cache;

import java.util.Date;

/**
 * ChatMessage
 *
 * @author liujh
 * @since 2018/4/24
 */
public class ChatMessage {
  private String msg;
  private String headSculpture;
  private String name;
  private Integer employeeWorkId;
  private Date now;

  public Date getNow() {
    return now;
  }

  public void setNow(Date now) {
    this.now = now;
  }

  public Integer getEmployeeWorkId() {
    return employeeWorkId;
  }

  public void setEmployeeWorkId(Integer employeeWorkId) {
    this.employeeWorkId = employeeWorkId;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getHeadSculpture() {
    return headSculpture;
  }

  public void setHeadSculpture(String headSculpture) {
    this.headSculpture = headSculpture;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
