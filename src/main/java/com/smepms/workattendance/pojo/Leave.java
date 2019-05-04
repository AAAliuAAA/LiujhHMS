package com.smepms.workattendance.pojo;

import com.smepms.jobmanagement.pojo.Employee;

import java.util.Date;

public class Leave {
    private Integer leaveId;

    private Date leaveStartTime;

    private Date leaveEndTime;

    private Float leaveTotalDays;

    private Integer leaveStatus;

    private Integer employeeId;

    private String leaveStatusName;

    private String leaveReason;

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public Date getLeaveStartTime() {
        return leaveStartTime;
    }

    public void setLeaveStartTime(Date leaveStartTime) {
        this.leaveStartTime = leaveStartTime;
    }

    public Date getLeaveEndTime() {
        return leaveEndTime;
    }

    public void setLeaveEndTime(Date leaveEndTime) {
        this.leaveEndTime = leaveEndTime;
    }

    public Float getLeaveTotalDays() {
        return leaveTotalDays;
    }

    public void setLeaveTotalDays(Float leaveTotalDays) {
        this.leaveTotalDays = leaveTotalDays;
    }

    public Integer getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(Integer leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getLeaveStatusName() {
        return leaveStatusName;
    }

    public void setLeaveStatusName(String leaveStatusName) {
        this.leaveStatusName = leaveStatusName;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }
}