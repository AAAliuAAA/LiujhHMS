package com.smepms.workattendance.pojo;

import com.smepms.jobmanagement.pojo.Employee;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/28.
 */
public class WorkAttendanceConfig {
    private Integer workAttendanceConfigId;
    private Time lastCheckInTime;
    private Time earlyCheckOutTime;
    private String configComment;
    private Integer isUsing;
    private Date workAttendanceConfigStartDate;
    private Date workAttendanceConfigEndDate;
    private Integer creator;
    private Employee employeeCreator;

    public Integer getWorkAttendanceConfigId() {
        return workAttendanceConfigId;
    }

    public void setWorkAttendanceConfigId(Integer workAttendanceConfigId) {
        this.workAttendanceConfigId = workAttendanceConfigId;
    }

    public Time getLastCheckInTime() {
        return lastCheckInTime;
    }

    public void setLastCheckInTime(Time lastCheckInTime) {
        this.lastCheckInTime = lastCheckInTime;
    }

    public Time getEarlyCheckOutTime() {
        return earlyCheckOutTime;
    }

    public void setEarlyCheckOutTime(Time earlyCheckOutTime) {
        this.earlyCheckOutTime = earlyCheckOutTime;
    }

    public String getConfigComment() {
        return configComment;
    }

    public void setConfigComment(String configComment) {
        this.configComment = configComment;
    }

    public Integer getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(Integer isUsing) {
        this.isUsing = isUsing;
    }

    public Date getWorkAttendanceConfigStartDate() {
        return workAttendanceConfigStartDate;
    }

    public void setWorkAttendanceConfigStartDate(Date workAttendanceConfigStartDate) {
        this.workAttendanceConfigStartDate = workAttendanceConfigStartDate;
    }

    public Date getWorkAttendanceConfigEndDate() {
        return workAttendanceConfigEndDate;
    }

    public void setWorkAttendanceConfigEndDate(Date workAttendanceConfigEndDate) {
        this.workAttendanceConfigEndDate = workAttendanceConfigEndDate;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Employee getEmployeeCreator() {
        return employeeCreator;
    }

    public void setEmployeeCreator(Employee employeeCreator) {
        this.employeeCreator = employeeCreator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkAttendanceConfig that = (WorkAttendanceConfig) o;

        return workAttendanceConfigId.equals(that.workAttendanceConfigId);
    }

    @Override
    public int hashCode() {
        return workAttendanceConfigId.hashCode();
    }
}
