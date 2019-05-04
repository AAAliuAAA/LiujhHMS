package com.smepms.jobmanagement.service;

import com.smepms.jobmanagement.enums.WorkerApplicationStatus;
import com.smepms.jobmanagement.pojo.RegularWorkerApplication;

import java.util.List;

public interface RegularWorkerApplicationService {
  boolean createRegularWorkerApplication(String employeeApplication);

  RegularWorkerApplication getRegularWorkerApplication(Integer employeeId);

  /**
   * 当前主管可以处理的转正申请
   * @param leaderId
   * @return
   */
  List<RegularWorkerApplication> getRegularWorkerApplicationByLeaderId(Integer leaderId);

  Integer passRegularWorkerApplication(Integer regularWorkerApplicationId,String leaderAcception);

  Integer dismissApplication(Integer regularWorkerApplicationId);

  /**
   * 查询所有目标状态的转正申请
   * @return
   */
  List<RegularWorkerApplication> getAll(WorkerApplicationStatus workerApplicationStatus);

  /**
   * 人事部门通过员工转正申请
   * @param applicationId
   * @return
   */
  Integer passEmployeeApplication(Integer applicationId);

  /**
   * 人事部门驳回员工转正申请
   */
  Integer rejectEmployeeApplication(Integer applicationId);
}
