package com.smepms.workattendance.service;

import com.github.pagehelper.PageInfo;
import com.smepms.workattendance.enums.LeaveApplicationStatusEnum;
import com.smepms.workattendance.pojo.Leave;

import java.util.Date;
import java.util.List;

/**
 * LeaveService
 *
 * @author liujh
 * @since 2018/5/7
 */
public interface LeaveService {

  /**
   * 创建请假申请
   * @param leave
   * @return
   */
  public boolean createEmployeeLeaveApplication(Leave leave);

  /**
   * 更新申请状态 包括删除申请
   * @param leaveApplicationStatusEnum
   * @return
   */
  public boolean updateEmployeeLeaveApplicationStatus(LeaveApplicationStatusEnum leaveApplicationStatusEnum,Integer leaveId);

  /**
   * 人事部门使用,根据用户工号查询当前用户的请假申请，考勤统计可能会使用
   * @param employeeWorkId
   * @return
   */
  public PageInfo<Leave> selectLeaveApplicationByEmployeeWorkId(Integer employeeWorkId,Integer pageNo);

  /**
   * 员工的leader使用，根据当前用户的直属上级查询当前用户的请假申请
   */
  public List<Leave> selectLeaveApplicationByEmployeeLeaderId(Integer employeeLeaderId);

  /**
   * 员工使用，查看自己最近发起的请假申请
   * @param employeeId
   * @return
   */
  public List<Leave> selectLeaveApplicationByEmployeeId(Integer employeeId);

  /**
   * hr使用，查看最近的所有请假申请
   */
  public PageInfo<Leave> selectLeaveApplicationByDate(Date startDate,Date endDate,Integer pageNo,Integer pageSize,LeaveApplicationStatusEnum leaveApplicationStatusEnum,Integer employeeId);
}
