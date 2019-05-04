package com.smepms.jobmanagement.service.impl;

import com.smepms.common.util.ShiroUtils;
import com.smepms.jobmanagement.consts.RegularWorkerApplicationStatusConsts;
import com.smepms.jobmanagement.enums.PositionEnum;
import com.smepms.jobmanagement.enums.WorkerApplicationStatus;
import com.smepms.jobmanagement.mapper.EmployeeMapper;
import com.smepms.jobmanagement.mapper.PositionMapper;
import com.smepms.jobmanagement.mapper.RegularWorkerApplicationMapper;
import com.smepms.jobmanagement.mapper.RoleMapper;
import com.smepms.jobmanagement.pojo.*;
import com.smepms.jobmanagement.service.EmployeeService;
import com.smepms.jobmanagement.service.RegularWorkerApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularWorkerApplicationServiceImpl implements RegularWorkerApplicationService{
  private final static Logger logger = LoggerFactory.getLogger(RegularWorkerApplicationService.class);
  private final RegularWorkerApplicationMapper regularWorkerApplicationMapper;
  private final EmployeeService employeeService;
  private final EmployeeMapper employeeMapper;
  private final RoleMapper roleMapper;
  private final PositionMapper positionMapper;
  @Autowired
  public RegularWorkerApplicationServiceImpl(RegularWorkerApplicationMapper regularWorkerApplicationMapper, EmployeeService employeeService,EmployeeMapper employeeMapper,RoleMapper roleMapper,PositionMapper positionMapper){
    this.roleMapper = roleMapper;
    this.employeeMapper = employeeMapper;
    this.regularWorkerApplicationMapper = regularWorkerApplicationMapper;
    this.employeeService = employeeService;
    this.positionMapper = positionMapper;
  }

  public boolean createRegularWorkerApplication(String employeeApplication){
    RegularWorkerApplication regularWorkerApplication = new RegularWorkerApplication();
    regularWorkerApplication.setEmployeeApplication(employeeApplication);
    Employee logEmployee = ShiroUtils.getLogEmployeeWithBLOBs();
    regularWorkerApplication.setEmployeeId(logEmployee.getEmployeeId());
    regularWorkerApplication.setEmployeeLeaderId(logEmployee.getEmployeeLeaderId());
    regularWorkerApplication.setApplicationStatus(WorkerApplicationStatus.CREATED);
    List<EmployeeWithBLOBs> list = employeeService.queryEmployeeDepartmentLeader(logEmployee.getEmployeeId());
    regularWorkerApplication.setEmployeeLeaderId(list.get(0).getEmployeeId());
    return regularWorkerApplicationMapper.insert(regularWorkerApplication)>0;
  }

  public RegularWorkerApplication getRegularWorkerApplication(Integer employeeId) {
    RegularWorkerApplication regularWorkerApplication = null;
    try {
      regularWorkerApplication = regularWorkerApplicationMapper.findOneByEmployeeId(employeeId);
    }catch (Exception e){
      e.printStackTrace();
    }
    return regularWorkerApplication;
  }

  /**
   * 给项目经理处理的转正申请
   * @param leaderId
   * @return
   */
  public List<RegularWorkerApplication> getRegularWorkerApplicationByLeaderId(Integer leaderId) {
    return regularWorkerApplicationMapper.findApplicationsByLeaderIdAndStatus(leaderId,WorkerApplicationStatus.CREATED);
  }

  public Integer passRegularWorkerApplication(Integer regularWorkerApplicationId,String leaderAcception){
    RegularWorkerApplication before = regularWorkerApplicationMapper.findOne(regularWorkerApplicationId);
    before.setApplicationStatus(WorkerApplicationStatus.LEADERPASS);
    before.setLeaderAcception(leaderAcception);
    return regularWorkerApplicationMapper.update(before);
  }

  public Integer dismissApplication(Integer regularWorkerApplicationId) {
    return regularWorkerApplicationMapper.delete(regularWorkerApplicationId);
  }


  public List<RegularWorkerApplication> getAll(WorkerApplicationStatus status) {
      List<RegularWorkerApplication> list = regularWorkerApplicationMapper.findAll(status);
    return list;
  }

  public Integer rejectEmployeeApplication(Integer applicationId) {
    logger.info("执行人事部门转正申请驳回，被驳回的转正申请ID为{}",applicationId);
    RegularWorkerApplication regularWorkerApplication = regularWorkerApplicationMapper.findOne(applicationId);
    regularWorkerApplication.setApplicationStatus(WorkerApplicationStatus.CREATED);
    return regularWorkerApplicationMapper.update(regularWorkerApplication);
  }

  public Integer passEmployeeApplication(Integer applicationId) {
    logger.info("执行人事部门转正申请通过，通过的转正申请ID为{}",applicationId);
    RegularWorkerApplication regularWorkerApplication = regularWorkerApplicationMapper.findOne(applicationId);
    regularWorkerApplication.setApplicationStatus(WorkerApplicationStatus.ALLPASS);
    regularWorkerApplicationMapper.update(regularWorkerApplication);
    //设置当前员工角色为转正
    //查询当前员工的部门和职位信息
    EmployeeWithBLOBs employeeWithBLOBs = employeeMapper.queryEmployeeWithDepartmentAndPosition(regularWorkerApplication.getEmployeeId());
    //修改当前员工角色信息
    Integer departmentId = employeeWithBLOBs.getDepartment().getDepartmentId();
    RoleExample roleExample = new RoleExample();
    PositionExample positionExample = new PositionExample();
    Position position = positionMapper.selectByPrimaryKey(PositionEnum.CLERK.getPositionId());
    roleExample.createCriteria().andDepartmentIdEqualTo(departmentId).andPositionIdEqualTo(position.getPositionId());
    List<Role> roles = roleMapper.selectByExample(roleExample);
    //修改当前员工连接表信息
    return roleMapper.updateEmployeeRole(roles.get(0).getRoleId(),employeeWithBLOBs.getEmployeeId());
  }


}
