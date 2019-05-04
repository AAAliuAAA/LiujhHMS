package com.smepms.common.util.jobs;

import com.smepms.workattendance.service.WorkAttendanceConfigService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/2/7.
 */
@Component
public class WorkAttendanceIsUsingUpdateTask {
    @Autowired
    WorkAttendanceConfigService workAttendanceConfigService;
    private Logger logger = Logger.getLogger(WorkAttendanceIsUsingUpdateTask.class);
    public void runTask(){
        //每晚11点检查一次当前的数据库中的isUsing配置为当前日期上班时间配置
        logger.info("开始执行任务调度，查看当前日期配置是否更新");
        workAttendanceConfigService.updateWorkAttendanceConfigIsUsing();
    }
}
