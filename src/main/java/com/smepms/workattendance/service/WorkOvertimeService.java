package com.smepms.workattendance.service;

import com.github.pagehelper.PageInfo;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.workattendance.enums.WorkOverTimeStatusEnum;
import com.smepms.workattendance.pojo.WorkOvertime;

import java.util.Date;
import java.util.List;

public interface WorkOvertimeService {
    /**
     * 插入一条
     * @param workOvertime
     * @return
     */
    Integer checkOne(WorkOvertime workOvertime);
    List<WorkOvertime> findAllByEmployeeId(Integer employeeId);

    /**
     * 查询当前部门下所有下属员工加班请求
     * @param employeeLeaderId
     * @return
     */
    public List<WorkOvertime> findByEmployeeLeaderIdAndStatus(Integer employeeLeaderId, WorkOverTimeStatusEnum status);

    /**
     * 直属上级通过加班请求
     * @return
     */
    public boolean managerPassOvertimeApplication(Integer workOvertimeId);

    boolean managerRejectOvertimeApplication(Integer workOvertimeId);

    public PageInfo<WorkOvertime> findWorkOverTimeByDate(Date startDate,Date endDate,Integer pageNo,Integer pageSize);

    /**
     * 人事部门通过加班请求
     * @param workOvertimeId
     * @return
     */
    public boolean hrPassWorkOvertime(Integer workOvertimeId);

    /**
     * 人事部门拒绝加班请求
     */
    public boolean hrRejectWorkOvertime(Integer workOvertimeId);
}
