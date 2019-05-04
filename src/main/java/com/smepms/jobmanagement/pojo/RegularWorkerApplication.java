package com.smepms.jobmanagement.pojo;

import com.smepms.jobmanagement.enums.WorkerApplicationStatus;

public class RegularWorkerApplication {
  private Integer regularWorkerApplicationId;
  private String employeeApplication;
  private String leaderAcception;
  private Integer employeeId;
  private Integer employeeLeaderId;
  private WorkerApplicationStatus applicationStatus;
  private Employee employee;

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public WorkerApplicationStatus getApplicationStatus() {
    return applicationStatus;
  }

  public void setApplicationStatus(WorkerApplicationStatus applicationStatus) {
    this.applicationStatus = applicationStatus;
  }

  public Integer getRegularWorkerApplicationId() {
    return regularWorkerApplicationId;
  }

  public void setRegularWorkerApplicationId(Integer regularWorkerApplicationId) {
    this.regularWorkerApplicationId = regularWorkerApplicationId;
  }

  public String getEmployeeApplication() {
    return employeeApplication;
  }

  public void setEmployeeApplication(String employeeApplication) {
    this.employeeApplication = employeeApplication;
  }

  public String getLeaderAcception() {
    return leaderAcception;
  }

  public void setLeaderAcception(String leaderAcception) {
    this.leaderAcception = leaderAcception;
  }

  public Integer getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(Integer employeeId) {
    this.employeeId = employeeId;
  }

  public Integer getEmployeeLeaderId() {
    return employeeLeaderId;
  }

  public void setEmployeeLeaderId(Integer employeeLeaderId) {
    this.employeeLeaderId = employeeLeaderId;
  }
}
