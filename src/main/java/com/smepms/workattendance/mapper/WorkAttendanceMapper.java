package com.smepms.workattendance.mapper;

import com.smepms.workattendance.dto.WorkAttendanceRestResult;
import com.smepms.workattendance.pojo.WorkAttendance;
import com.smepms.workattendance.pojo.WorkAttendanceExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkAttendanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int countByExample(WorkAttendanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int deleteByExample(WorkAttendanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int deleteByPrimaryKey(Integer workAttendanceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int insert(WorkAttendance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int insertSelective(WorkAttendance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    List<WorkAttendance> selectByExample(WorkAttendanceExample example) throws Exception;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    WorkAttendance selectByPrimaryKey(Integer workAttendanceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByExampleSelective(@Param("record") WorkAttendance record, @Param("example") WorkAttendanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByExample(@Param("record") WorkAttendance record, @Param("example") WorkAttendanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByPrimaryKeySelective(WorkAttendance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_work_attendance
     *
     * @mbggenerated Wed Jan 10 18:43:08 CST 2018
     */
    int updateByPrimaryKey(WorkAttendance record);


    WorkAttendance querySameDaySameEmployeeRecord(@Param("targetDate")Date targetDate,@Param("employeeId")Integer employeeId);

    Integer queryTargetWorkAttendanceIsInLeave(@Param("leaveStatus")Integer leaveStatus,@Param("workAttendanceId")Integer workAttendanceId);

    List<WorkAttendanceRestResult> selectByDateAndDepartmentIdAndEmployeeId(@Param("departmentId")Integer departmentId,@Param("workOverTimeStatus")Integer workOvertimeStatus,@Param("employeeId")Integer employeeId,@Param("workAttendanceDate")Date workAttendanceDate,@Param("employeeWorkId")Integer employeeWorkId)throws Exception;
}