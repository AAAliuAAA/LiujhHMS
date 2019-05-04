package com.smepms.jobmanagement.mapper;

import com.smepms.jobmanagement.enums.WorkerApplicationStatus;
import com.smepms.jobmanagement.pojo.RegularWorkerApplication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegularWorkerApplicationMapper {

  Integer insert(RegularWorkerApplication regularWorkerApplication);

  Integer update(RegularWorkerApplication regularWorkerApplication);

  RegularWorkerApplication findOneByEmployeeId(@Param("employeeId") Integer employeeId);

  List<RegularWorkerApplication> findApplicationsByLeaderIdAndStatus(@Param("leaderId") Integer leaderId, @Param("status")WorkerApplicationStatus status);

  RegularWorkerApplication findOne(Integer regularWorkerApplicationId);

  Integer delete(Integer regularWorkerApplicationId);

  List<RegularWorkerApplication> findAll(@Param("status")WorkerApplicationStatus status);
}
