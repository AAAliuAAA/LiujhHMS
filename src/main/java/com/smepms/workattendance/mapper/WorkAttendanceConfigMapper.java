package com.smepms.workattendance.mapper;

import com.smepms.workattendance.pojo.WorkAttendanceConfig;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */
public interface WorkAttendanceConfigMapper {
    //添加一行上班时间配置
   Integer addWorkAttendanceConfig(WorkAttendanceConfig workAttendanceConfig);
    //删除一行
   Integer deleteWorkAttendanceConfigByConfigId(@Param("workAttendanceConfigId") Integer workAttendanceConfigId);
    //查询所有
   List<WorkAttendanceConfig> queryAllWorkAttendanceConfigs();
    //查询所有上下班时间配置和创建人工号
   List<WorkAttendanceConfig> queryAllWorkAttendanceConfigWithCreatorInfo();
    /**
     * 修改当前传入的上班时间配置对象的使用状态
     * 要求封装
     * @param workAttendanceConfig
     * @return
     */
   Integer updateWorkAttendanceConfigIsUsingByConfigId(WorkAttendanceConfig workAttendanceConfig);

   WorkAttendanceConfig queryWhichWorkAttendanceConfigIsUsing();

   Integer updateWorkAttendanceConfigEndDate(WorkAttendanceConfig workAttendanceConfig);

   WorkAttendanceConfig queryWhichWorkAttendanceConfigIsInNow(@Param("targetDate")Date targetDate);

   WorkAttendanceConfig queryWhichWorkAttendanceConfigEndDateMax();
}
