package com.smepms.workattendance.mapper;

import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.workattendance.pojo.WorkAttendanceCheck;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface WorkAttendanceCheckMapper {
  Integer insert(WorkAttendanceCheck workAttendanceCheck);
  List<WorkAttendanceCheck> selectByEmployeeId(Employee employee);
  List<WorkAttendanceCheck> selectAll();
  List<WorkAttendanceCheck> selectByWorkAttendanceId(@Param("workAttendanceIdList")List<Integer> workAttendanceIdList) throws Exception;
  List<WorkAttendanceCheck> selectByTargetDate(@Param("targetDate")Date targetDate);
  Integer updateByEmployeeId(WorkAttendanceCheck workAttendanceCheck);
  WorkAttendanceCheck selectBySameDateSameEmployee(@Param("targetDate")Date targetDate,@Param("employeeId")Integer employeeId);
  Integer updateById(WorkAttendanceCheck workAttendanceCheck);
}
