package com.smepms.workattendance.service;

import com.github.pagehelper.PageInfo;
import com.smepms.workattendance.pojo.WorkAttendance;
import com.smepms.workattendance.pojo.WorkAttendanceConfig;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */
public interface WorkAttendanceConfigService {
    public Boolean addWorkAttendanceConfig(WorkAttendanceConfig workAttendanceConfig);

    public PageInfo<WorkAttendanceConfig> queryAllWorkAttendanceConfig(WorkAttendanceConfig workAttendanceConfig,Integer pageNo,Integer pageSize);

    public Boolean deleteWorkAttendanceConfig(WorkAttendanceConfig workAttendanceConfig);

    public PageInfo<WorkAttendanceConfig> selectAllWorkAttendanceConfigWithEmployeeWorkId(Integer pageNo,Integer pageSize);

    public void updateWorkAttendanceConfigIsUsing();

    public Boolean workAttendanceConfigValidation(Date newStartDate);

    public WorkAttendanceConfig queryWorkTimeToday();
}
