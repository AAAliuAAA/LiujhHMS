package com.smepms.workattendance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smepms.common.exception.BizRuntimeException;
import com.smepms.common.util.ShiroUtils;
import com.smepms.jobmanagement.mapper.EmployeeMapper;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.jobmanagement.pojo.EmployeeExample;
import com.smepms.workattendance.enums.WorkOverTimeStatusEnum;
import com.smepms.workattendance.mapper.WorkAttendanceMapper;
import com.smepms.workattendance.mapper.WorkOverTimeStatusMapper;
import com.smepms.workattendance.mapper.WorkOvertimeMapper;
import com.smepms.workattendance.pojo.*;
import com.smepms.workattendance.service.WorkAttendanceService;
import com.smepms.workattendance.service.WorkOvertimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class WorkOvertimeServiceImpl implements WorkOvertimeService {
    @Autowired
    private WorkOvertimeMapper workOvertimeMapper;
    @Autowired
    private WorkAttendanceService workAttendanceService;
    @Autowired
    private WorkAttendanceMapper workAttendanceMapper;
    @Autowired
    private WorkOverTimeStatusMapper workOverTimeStatusMapper;
    @Autowired
    private EmployeeMapper employeeMapper;


    private final static Logger logger = LoggerFactory.getLogger(WorkOvertimeService.class);

    public List<WorkOvertime> findAllByEmployeeId(Integer employeeId) {
        try {
            return workOvertimeMapper.queryWorkAttendanceByEmployeeId(employeeId);
        } catch (Exception e) {
            logger.error("查询错误",e);
            throw new RuntimeException("回滚");
        }
    }

    public Integer checkOne(WorkOvertime workOvertime) {
        try{
            Employee logEmployee = ShiroUtils.getLogEmployeeWithBLOBs();
            //根据目标考勤信息时间和员工ID查询出指定的 work_attendance
            WorkAttendanceExample workAttendanceExample = new WorkAttendanceExample();
            workAttendanceExample.createCriteria().andWorkAttendanceDateEqualTo(workOvertime.getWorkOvertimeDate()).andEmployeeIdEqualTo(logEmployee.getEmployeeId());
            List<WorkAttendance> workAttendances = workAttendanceMapper.selectByExample(workAttendanceExample);
            if (workAttendances.size() != 1 || workAttendances == null) {
                logger.info("员工当天没有考勤信息，加班时间选择错误");
                return 3;
            }
            WorkAttendance workAttendance = workAttendances.get(0);
            //如果已经发起加班申请，则不可以再进行发起
            if(workAttendance.getWorkOvertimeId() != null){
                logger.info("已经发起加班申请，不可以再进行发起");
                return 2;
            }
            //返回主键
            workOvertimeMapper.insert(workOvertime);
            WorkOverTimeStatus workOverTimeStatus = new WorkOverTimeStatus();
            //如果一个员工不存在上级，发起加班申请则直接通过然后到人事部门处理
            if(logEmployee.getEmployeeLeader() == null){
                workOverTimeStatus.setStatus(WorkOverTimeStatusEnum.LEADER_PASS.ordinal());
                workOverTimeStatus.setStatusName(WorkOverTimeStatusEnum.LEADER_PASS.getName());
                workOverTimeStatus.setOvertimeId(workOvertime.getWorkOvertimeId());
            }else{
                workOverTimeStatus.setStatus(WorkOverTimeStatusEnum.CREATED.ordinal());
                workOverTimeStatus.setStatusName(WorkOverTimeStatusEnum.CREATED.getName());
                workOverTimeStatus.setOvertimeId(workOvertime.getWorkOvertimeId());
            }
            workOverTimeStatusMapper.insert(workOverTimeStatus);
            //更新考勤信息表
            workAttendance.setWorkOvertimeId(workOvertime.getWorkOvertimeId());
            workAttendanceMapper.updateByPrimaryKeySelective(workAttendance);
        }catch (Exception e){
            logger.error("数据库操作异常",e);
            throw new RuntimeException("回滚");
        }
        return 1;
    }

    public List<WorkOvertime> findByEmployeeLeaderIdAndStatus(Integer employeeLeaderId, WorkOverTimeStatusEnum status) {
        //加班申请是通过当前员工的直属上级进行审核
        //查询所有下属员工信息
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.createCriteria().andEmployeeLeaderEqualTo(employeeLeaderId);
        List<Employee> employeeList = employeeMapper.selectByExample(employeeExample);
        //查询所有下属员工对应的加班申请
        //1.状态为CRETED 2.员工ID为列表中的
        List employeeIdList = new LinkedList();
        if(employeeList == null || employeeList.size() == 0){
            return null;
        }
        for(Employee employee:employeeList){
            employeeIdList.add(employee.getEmployeeId());
        }
        List<WorkOvertime> list = null;
        try {
            list = workOvertimeMapper.queryWorkOvertimeByEmployeeIdListAndStatus(employeeIdList,status.ordinal());
        } catch (Exception e) {
            logger.error("查询错误",e);
            throw new RuntimeException("查询错误");
        }
        return  list;
    }

    public boolean managerPassOvertimeApplication(Integer workOvertimeId) {
        boolean flag = false;
        try {
            WorkOverTimeStatus workOverTimeStatus = workOverTimeStatusMapper.selectByOverTimeId(workOvertimeId);
            workOverTimeStatus.setStatusName(WorkOverTimeStatusEnum.LEADER_PASS.getName());
            workOverTimeStatus.setStatus(WorkOverTimeStatusEnum.LEADER_PASS.ordinal());
            flag =workOverTimeStatusMapper.update(workOverTimeStatus) == 1;
        } catch (Exception e) {
            logger.error("表更新错误",e);
            flag = false;
            throw new RuntimeException("表更新错误");
        }
        return flag;
    }

    public boolean managerRejectOvertimeApplication(Integer workOvertimeId) {
        //删除状态表 状态信息
        //删除加班表 加班信息
        //将当日的考勤信息的加班信息置空
        boolean flag = false;
        try {
            workOverTimeStatusMapper.deleteByOvertimeId(workOvertimeId);
            workOvertimeMapper.deleteByPrimaryKey(workOvertimeId);
            //如果是共用主键就好了，可以直接删
            WorkAttendanceExample workAttendanceExample = new WorkAttendanceExample();
            workAttendanceExample.createCriteria().andWorkOvertimeIdEqualTo(workOvertimeId);
            List<WorkAttendance> workAttendances = workAttendanceMapper.selectByExample(workAttendanceExample);
            WorkAttendance workAttendance = workAttendances.get(0);
            workAttendance.setWorkOvertimeId(null);
            workAttendanceMapper.updateByPrimaryKey(workAttendance);
            flag = true;
        } catch (Exception e) {
            logger.error("数据库操作异常",e);
            throw new RuntimeException("数据库操作异常");
        }
        return flag;
    }

    public PageInfo<WorkOvertime> findWorkOverTimeByDate(Date startDate, Date endDate,Integer pageNo,Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        List<WorkOvertime> list = null;
        try {
            list = workOvertimeMapper.selectByDate(startDate,endDate,WorkOverTimeStatusEnum.LEADER_PASS.ordinal());
        } catch (Exception e) {
            logger.error("查询错误",e);
            throw new RuntimeException("数据库查询错误");
        }
        PageInfo page = new PageInfo(list);
        return page;
    }

    public boolean hrPassWorkOvertime(Integer workOvertimeId){
        boolean flag = false;
        try {
            WorkOverTimeStatus workOverTimeStatus = workOverTimeStatusMapper.selectByOverTimeId(workOvertimeId);
            workOverTimeStatus.setStatus(WorkOverTimeStatusEnum.HR_PASS.ordinal());
            workOverTimeStatus.setStatusName(WorkOverTimeStatusEnum.HR_PASS.getName());
            workOverTimeStatusMapper.update(workOverTimeStatus);
            flag = true;
        } catch (Exception e) {
            logger.error("数据库操作异常",e);
            throw new BizRuntimeException("数据库操作异常");
        }
        return flag;
    }

    public boolean hrRejectWorkOvertime(Integer workOvertimeId) {
        boolean flag = false;
        //如果当前打回的加班申请为经理级别，应该直接删除
        try{
            WorkAttendanceExample workAttendanceExample = new WorkAttendanceExample();
            workAttendanceExample.createCriteria().andWorkOvertimeIdEqualTo(workOvertimeId);
            List<WorkAttendance> workAttendances = workAttendanceMapper.selectByExample(workAttendanceExample);
            WorkAttendance workAttendance = workAttendances.get(0);
            Employee employee =  employeeMapper.selectByPrimaryKey(workAttendance.getEmployeeId());
            if(employee.getEmployeeLeader() == null){
                //如果当前打回的加班申请没有上级，直接删除
                managerRejectOvertimeApplication(workOvertimeId);
                return true;
            }
            WorkOverTimeStatus workOverTimeStatus = workOverTimeStatusMapper.selectByOverTimeId(workOvertimeId);
            workOverTimeStatus.setStatus(WorkOverTimeStatusEnum.CREATED.ordinal());
            workOverTimeStatus.setStatusName(WorkOverTimeStatusEnum.CREATED.getName());
            workOverTimeStatusMapper.update(workOverTimeStatus);
            flag = true;
        }catch (Exception e){
            logger.error("数据库操作异常",e);
            throw new BizRuntimeException("数据库操作异常");
        }
        return flag;
    }
}
