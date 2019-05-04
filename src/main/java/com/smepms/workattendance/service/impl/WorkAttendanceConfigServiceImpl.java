package com.smepms.workattendance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smepms.workattendance.mapper.WorkAttendanceConfigMapper;
import com.smepms.workattendance.pojo.WorkAttendanceConfig;
import com.smepms.workattendance.service.WorkAttendanceConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Administrator on 2018/1/28.
 */
@Service
public class WorkAttendanceConfigServiceImpl implements WorkAttendanceConfigService{
    private Logger logger = Logger.getLogger(WorkAttendanceConfigServiceImpl.class);
    @Autowired
    private WorkAttendanceConfigMapper workAttendanceConfigMapper;

    public Boolean addWorkAttendanceConfig(WorkAttendanceConfig workAttendanceConfig) {
        //补全字段
        workAttendanceConfig.setIsUsing(0);
        WorkAttendanceConfig EndingConfig = workAttendanceConfigMapper.queryWhichWorkAttendanceConfigEndDateMax();
        //将当前使用的日期的截止日期设置为当前所有配置中最晚的日期前一天
        //获取目标日期的开始日期的前一天
        Date targetStartDate = workAttendanceConfig.getWorkAttendanceConfigStartDate();
        Calendar targetStartCalendar = Calendar.getInstance();
        targetStartCalendar.setTime(targetStartDate);
        targetStartCalendar.add(Calendar.DAY_OF_MONTH,-1);
        Date targetStartDateBefore = targetStartCalendar.getTime();
        //将此前一天作为当前使用的时间的截止日期传入到使用的最晚的配置的截止日期中
        EndingConfig.setWorkAttendanceConfigEndDate(targetStartDateBefore);

        //输入到数据库
        Integer flag = workAttendanceConfigMapper.addWorkAttendanceConfig(workAttendanceConfig);
        workAttendanceConfigMapper.updateWorkAttendanceConfigEndDate(EndingConfig);
        //每晚下班后，使用quartz调用isUsing 的update方法对当前使用的时间进行更新
        return flag>0;
    }

    public PageInfo<WorkAttendanceConfig> queryAllWorkAttendanceConfig(WorkAttendanceConfig workAttendanceConfig,Integer pageNo,Integer pageSize) {
        //指定开始的页码和指定当前页数，自动拦截sql
        PageHelper.startPage(pageNo,pageSize);
        List<WorkAttendanceConfig> workAttendanceConfigList = workAttendanceConfigMapper.queryAllWorkAttendanceConfigs();
        PageInfo<WorkAttendanceConfig> pageInfo = new PageInfo<WorkAttendanceConfig>(workAttendanceConfigList);
        return pageInfo;
    }

    public Boolean deleteWorkAttendanceConfig(WorkAttendanceConfig workAttendanceConfig) {
        return null;
    }

    public PageInfo<WorkAttendanceConfig> selectAllWorkAttendanceConfigWithEmployeeWorkId(Integer pageNo,Integer pageSize) {
        //指定开始的页码和指定当前页数，自动拦截sql
        PageHelper.startPage(pageNo,pageSize);
        List<WorkAttendanceConfig> workAttendanceConfigList = workAttendanceConfigMapper.queryAllWorkAttendanceConfigWithCreatorInfo();
        PageInfo<WorkAttendanceConfig> pageInfo = new PageInfo<WorkAttendanceConfig>(workAttendanceConfigList);
        return pageInfo;
    }

    //每天凌晨被调用执行
    public void updateWorkAttendanceConfigIsUsing() {
        //获取当前时间
        Date now = new Date();
        WorkAttendanceConfig workAttendanceConfigNow = workAttendanceConfigMapper.queryWhichWorkAttendanceConfigIsInNow(now);
        //查询出来后，如果有变化，将之前的isUsing设置为0，将当前时间的isUsing设置为1
       WorkAttendanceConfig workAttendanceConfigIsUsing = workAttendanceConfigMapper.queryWhichWorkAttendanceConfigIsUsing();
       //比较两个是否相同，如果相同，则不进行改变，如果不同，则改变
        //判断标准：Id
        if(workAttendanceConfigNow.equals(workAttendanceConfigIsUsing)){
            return;
        }else{
            workAttendanceConfigNow.setIsUsing(1);
            workAttendanceConfigIsUsing.setIsUsing(0);
            //更新到数据库
            workAttendanceConfigMapper.updateWorkAttendanceConfigIsUsingByConfigId(workAttendanceConfigIsUsing);
            workAttendanceConfigMapper.updateWorkAttendanceConfigIsUsingByConfigId(workAttendanceConfigNow);
        }
    }

    public Boolean workAttendanceConfigValidation(Date newStartDate) {
        WorkAttendanceConfig EndingConfig = workAttendanceConfigMapper.queryWhichWorkAttendanceConfigEndDateMax();
        //如果当前传入的日期小于当前最晚配置的日期的开始时间，则返回false
        if(newStartDate.before(EndingConfig.getWorkAttendanceConfigStartDate())){
            return false;
        }else{
         return true;
        }
    }

    public WorkAttendanceConfig queryWorkTimeToday() {
        return workAttendanceConfigMapper.queryWhichWorkAttendanceConfigIsUsing();
    }
}
