package com.smepms.workattendance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smepms.common.exception.BizRuntimeException;
import com.smepms.common.util.ShiroUtils;
import com.smepms.jobmanagement.mapper.EmployeeMapper;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.workattendance.enums.LeaveApplicationStatusEnum;
import com.smepms.workattendance.mapper.LeaveMapper;
import com.smepms.workattendance.pojo.Leave;
import com.smepms.workattendance.pojo.LeaveExample;
import com.smepms.workattendance.service.LeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * LeaveServiceImpl
 *
 * @author liujh
 * @since 2018/5/7
 */
@Service
public class LeaveServiceImpl implements LeaveService{
  private final static Logger logger = LoggerFactory.getLogger(LeaveServiceImpl.class);
  @Autowired
  private  LeaveMapper leaveMapper;
  @Autowired
  private EmployeeMapper employeeMapper;

  public boolean createEmployeeLeaveApplication(Leave leave) {
    Employee employee = ShiroUtils.getLogEmployeeWithBLOBs();
    leave.setEmployeeId(employee.getEmployeeId());
    leave.setLeaveStatus(LeaveApplicationStatusEnum.CREATED.ordinal());
    leave.setLeaveStatusName(LeaveApplicationStatusEnum.CREATED.getStatusName());
    if(employee.getEmployeeLeader() == null){
      leave.setLeaveStatus(LeaveApplicationStatusEnum.MANAGER_PASS.ordinal());
      leave.setLeaveStatusName(LeaveApplicationStatusEnum.MANAGER_PASS.getStatusName());
    }
    try {
      return leaveMapper.insert(leave) == 1;
    } catch (Exception e) {
      logger.error("错误",e);
      throw new BizRuntimeException("");
    }
  }

  public boolean updateEmployeeLeaveApplicationStatus(LeaveApplicationStatusEnum leaveApplicationStatusEnum, Integer leaveId) {
    if(leaveApplicationStatusEnum == LeaveApplicationStatusEnum.DELETE){
      leaveMapper.deleteByPrimaryKey(leaveId);
      return true;
    }
    Leave leave = leaveMapper.selectByPrimaryKey(leaveId);
    //没有leader的用户，是不可以修改为等待leader审核通过，没有leader的人直接执行删除操作
    if(!validateEmployeeLeaderExsits(leave)&&leaveApplicationStatusEnum == LeaveApplicationStatusEnum.CREATED){
      leaveMapper.deleteByPrimaryKey(leaveId);
      return true;
    }
    leave.setLeaveStatus(leaveApplicationStatusEnum.ordinal());
    leave.setLeaveStatusName(leaveApplicationStatusEnum.getStatusName());
    try {
      return leaveMapper.updateByPrimaryKey(leave) == 1;
    } catch (Exception e) {
      logger.error("请假申请状态操作失败",e);
      throw new BizRuntimeException("请假申请操作失败");
    }
  }

  /**
   * 如果当前员工存在Leader，则返回true
   * @param leave
   * @return
   */
  private boolean validateEmployeeLeaderExsits(Leave leave){
    Integer employeeId = leave.getEmployeeId();
    Employee employee = employeeMapper.selectByPrimaryKey(employeeId);
    return employee.getEmployeeLeader() != null;
  }



  public PageInfo<Leave> selectLeaveApplicationByEmployeeWorkId(Integer employeeWorkId, Integer pageNo) {
    return null;
  }

  public List<Leave> selectLeaveApplicationByEmployeeLeaderId(Integer employeeLeaderId) {
    try {
      return  leaveMapper.selectByEmployeeLeader(employeeLeaderId,LeaveApplicationStatusEnum.CREATED.ordinal());
    } catch (Exception e) {
      logger.error("查询错误",e);
      throw new BizRuntimeException("数据库查询错误");
    }
  }

  public List<Leave> selectLeaveApplicationByEmployeeId(Integer employeeId) {
    try {
      return leaveMapper.selectByEmployeeId(employeeId);
    } catch (Exception e) {
      logger.error("数据库查询错误",e);
      throw new BizRuntimeException("数据库查询错误");
    }
  }

  public PageInfo<Leave> selectLeaveApplicationByDate(Date startDate, Date endDate, Integer pageNo,Integer pageSize,LeaveApplicationStatusEnum leaveApplicationStatusEnum,Integer employeeId) {
    PageHelper.startPage(pageNo,pageSize);
    List<Leave> list = null;
    try {
      list = leaveMapper.selectByDateAndStatus(startDate,endDate,leaveApplicationStatusEnum == null?null:leaveApplicationStatusEnum.ordinal(),employeeId);
    } catch (Exception e) {
      logger.error("查询错误",e);
      throw new BizRuntimeException("数据库查询错误");
    }
    PageInfo<Leave> pageInfo = new PageInfo<Leave>(list);
    return pageInfo;
  }
}
