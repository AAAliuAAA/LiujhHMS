package com.smepms.workattendance.mapper;

import com.smepms.workattendance.pojo.WorkOverTimeStatus;
import com.smepms.workattendance.pojo.WorkOvertime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkOverTimeStatusMapper {
    public int insert(WorkOverTimeStatus workOverTimeStatus) throws Exception;

    public WorkOverTimeStatus selectById(@Param("statusId") Integer statusId) throws Exception;

    public List<WorkOvertime> selectOverTimeByStatus(WorkOverTimeStatus workOverTimeStatus) throws Exception;

    /**
     * 根据加班表进行更新
     * @param workOverTimeStatus
     * @return
     */
    public int update(WorkOverTimeStatus workOverTimeStatus) throws Exception;

    public WorkOverTimeStatus selectByOverTimeId(@Param("overtimeId")Integer overtimeId) throws Exception;

    public int deleteByOvertimeId(@Param("overtimeId")Integer overtimeId);
}
