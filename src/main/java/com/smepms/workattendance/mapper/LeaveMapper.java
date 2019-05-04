package com.smepms.workattendance.mapper;

import com.smepms.workattendance.pojo.Leave;
import com.smepms.workattendance.pojo.LeaveExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LeaveMapper {
  int countByExample(LeaveExample example);

  int deleteByExample(LeaveExample example);

  int deleteByPrimaryKey(Integer leaveId);

  int insert(Leave record) throws Exception;

  int insertSelective(Leave record);

  List<Leave> selectByExampleWithBLOBs(LeaveExample example);

  List<Leave> selectByExample(LeaveExample example) throws Exception;

  Leave selectByPrimaryKey(Integer leaveId);

  int updateByExampleSelective(@Param("record") Leave record, @Param("example") LeaveExample example);

  int updateByExampleWithBLOBs(@Param("record") Leave record, @Param("example") LeaveExample example);

  int updateByExample(@Param("record") Leave record, @Param("example") LeaveExample example);

  int updateByPrimaryKeySelective(Leave record);

  int updateByPrimaryKeyWithBLOBs(Leave record);

  int updateByPrimaryKey(Leave record) throws Exception;

  List<Leave> selectByEmployeeLeader(@Param("employeeLeaderId")Integer employeeLeaderId,@Param("leaveStatus")Integer leaveStatus) throws Exception;

  List<Leave> selectByDateAndStatus(@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("leaveStatus")Integer leaveStatus,@Param("employeeId")Integer employeeId) throws Exception;

  List<Leave> selectByEmployeeId(@Param("employeeId")Integer employeeId);
}