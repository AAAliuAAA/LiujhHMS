package com.smepms.workattendance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.PageObjectUtil;
import com.smepms.common.exception.BizRuntimeException;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.workattendance.dto.WorkAttendanceGroupRestResult;
import com.smepms.workattendance.dto.WorkAttendanceRestResult;
import com.smepms.workattendance.enums.LeaveApplicationStatusEnum;
import com.smepms.workattendance.enums.WorkAttendanceLateOrLeaveEarlyEnum;
import com.smepms.workattendance.enums.WorkOverTimeStatusEnum;
import com.smepms.workattendance.mapper.WorkAttendanceCheckMapper;
import com.smepms.workattendance.mapper.WorkAttendanceMapper;
import com.smepms.workattendance.pojo.WorkAttendance;
import com.smepms.workattendance.pojo.WorkAttendanceCheck;
import com.smepms.workattendance.pojo.WorkAttendanceExample;
import com.smepms.workattendance.pojo.WorkOverTimeStatus;
import com.smepms.workattendance.service.WorkAttendanceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
public class WorkAttendanceServiceimpl implements WorkAttendanceService {
  private final static Logger logger = LoggerFactory.getLogger(WorkAttendanceServiceimpl.class);
  @Autowired
  private WorkAttendanceMapper workAttendanceMapper;
  @Autowired
  private WorkAttendanceCheckMapper workAttendanceCheckMapper;

  /**
   * 根据当前系统时间和当前shiro中用户信息创建一个新的考勤记录
   * 同一天同一个人只能有一条考勤记录
   *
   * @return
   */
  public boolean createWorkAttendance() {
    Subject subject = SecurityUtils.getSubject();
    Employee employee = (Employee) subject.getSession().getAttribute("logEmployee");
    WorkAttendance workAttendance = new WorkAttendance();
    workAttendance.setEmployeeId(employee.getEmployeeId());
    workAttendance.setWorkAttendanceDate(new Date());
    return workAttendanceMapper.insert(workAttendance) > 0;
  }

  public WorkAttendance queryWorkAttendanceIfExists(Employee employee) {
    WorkAttendance workAttendance = workAttendanceMapper.querySameDaySameEmployeeRecord(new Date(), employee.getEmployeeId());
    if (workAttendance == null) {
      WorkAttendance workAttendanceNew = new WorkAttendance();
      workAttendanceNew.setEmployeeId(employee.getEmployeeId());
      workAttendanceNew.setWorkAttendanceDate(new Date());
      workAttendanceMapper.insert(workAttendanceNew);
      workAttendance = workAttendanceNew;
    }
    return workAttendance;
  }

  public WorkAttendanceGroupRestResult getWorkAttendanceGroupByDateAndDepartment(Date date, Integer departmentId, Integer employeeId, Integer pageNo, Integer pageSize, Integer employeeWorkId) {
    PageHelper.startPage(pageNo, pageSize);
    List<WorkAttendanceRestResult> list = null;
    try {
      list = workAttendanceMapper.selectByDateAndDepartmentIdAndEmployeeId(departmentId,WorkOverTimeStatusEnum.HR_PASS.ordinal(), employeeId, date, employeeWorkId);
      PageInfo pageInfo = new PageInfo(list);
      WorkAttendanceGroupRestResult workAttendanceGroupRestResult = new WorkAttendanceGroupRestResult();
      workAttendanceGroupRestResult.setPage(pageInfo);
      workAttendanceGroupRestResult.setWorkAttendanceDate(date);
      return workAttendanceGroupRestResult;
    } catch (Exception e) {
      logger.error("数据库查询异常", e);
      throw new BizRuntimeException("数据库查询异常");
    }
  }

  /**
   * 使用调度更新当前的考勤表
   *
   * @return
   */
  public boolean updateWorkAttendanceLateAndLeaveEarly() {
    logger.info("开始执行考勤表更新 leaveEarly和late");
    boolean flag = false;
    try {
      //查询所有未更新的记录
      WorkAttendanceExample workAttendanceExample = new WorkAttendanceExample();
      workAttendanceExample.createCriteria().andLateIsNull().andLeaveEarlyIsNull();
      List<WorkAttendance> workAttendanceList = workAttendanceMapper.selectByExample(workAttendanceExample);
      if (workAttendanceList == null || workAttendanceList.size() == 0) {
        logger.info("无更新内容");
        return true;
      }
      //查询所有对应的打卡记录
      List<Integer> workAttendancePrimaryKeyList = new LinkedList<Integer>();
      for (int i = 0;i<workAttendanceList.size();i++) {
        WorkAttendance workAttendance = workAttendanceList.get(i);
        Integer existFlag = workAttendanceMapper.queryTargetWorkAttendanceIsInLeave(LeaveApplicationStatusEnum.HR_PASS.ordinal(), workAttendance.getWorkAttendanceId());
        if (existFlag > 0) {
          //如果当天员工请假，则没有打卡记录也没有考勤记录
          //如何让最终结果可以改变？
          //如果当天workAttendance被包含在请假信息当中，则不进行操作，如果当天是节假日？
          //迭代器不可以直接操作
          workAttendanceList.remove(i);
          continue;
        }
        workAttendancePrimaryKeyList.add(workAttendance.getWorkAttendanceId());
      }
      List<WorkAttendanceCheck> checkList = workAttendanceCheckMapper.selectByWorkAttendanceId(workAttendancePrimaryKeyList);
      List<Integer> targetWorkAttendanceLate = new LinkedList<Integer>();
      List<Integer> targetWorkAttendanceLeaveEarly = new LinkedList<Integer>();
      for (WorkAttendanceCheck workAttendanceCheck : checkList) {
        //查看打卡上下班是否存在，通过存在判断是否为late和leaveEarly
        if (workAttendanceCheck.getCheckIn() == null) {
          //迟到
          for (WorkAttendance workAttendance : workAttendanceList) {
            if (workAttendance.getWorkAttendanceId() == workAttendanceCheck.getWorkAttendanceId()) {
              targetWorkAttendanceLate.add(workAttendance.getWorkAttendanceId());
            }
          }
        } else {
          if (workAttendanceCheck.getCheckOut() == null) {
            //有签到无签退(早退)
            for (WorkAttendance workAttendance : workAttendanceList) {
              if (workAttendance.getWorkAttendanceId() == workAttendanceCheck.getWorkAttendanceId()) {
                targetWorkAttendanceLeaveEarly.add(workAttendance.getWorkAttendanceId());
              }
            }
          }
        }
      }
      //批量更新模板
      WorkAttendance workAttendanceUpdateLate = new WorkAttendance();
      workAttendanceUpdateLate.setLate(WorkAttendanceLateOrLeaveEarlyEnum.LATE.getTableStatus());

      WorkAttendance workAttendanceUpdateLeaveEarly = new WorkAttendance();
      workAttendanceUpdateLeaveEarly.setLeaveEarly(WorkAttendanceLateOrLeaveEarlyEnum.LEAVE_EARLY.getTableStatus());
      //批量更新选择
      WorkAttendanceExample workAttendanceExampleUpdateLate = new WorkAttendanceExample();
      workAttendanceExampleUpdateLate.createCriteria().andWorkAttendanceIdIn(targetWorkAttendanceLate);
      WorkAttendanceExample workAttendanceExampleUpdateLeaveEarly = new WorkAttendanceExample();
      workAttendanceExampleUpdateLeaveEarly.createCriteria().andWorkAttendanceIdIn(targetWorkAttendanceLeaveEarly);
      //执行
      if(targetWorkAttendanceLate != null && targetWorkAttendanceLate.size() != 0){
        Integer updateNumLate = workAttendanceMapper.updateByExampleSelective(workAttendanceUpdateLate, workAttendanceExampleUpdateLate);
        logger.info("更新完成，本次执行迟到更新{}条", updateNumLate);

      }
      if(targetWorkAttendanceLeaveEarly != null && targetWorkAttendanceLeaveEarly.size() != 0){
        Integer updateNumLeaveEarly = workAttendanceMapper.updateByExampleSelective(workAttendanceUpdateLeaveEarly, workAttendanceExampleUpdateLeaveEarly);
        logger.info("更新完成，本次执行早退更新{}条", updateNumLeaveEarly);
      }
      logger.info("更新完成");
      flag = true;
    } catch (Exception e) {
      logger.error("数据库操作异常", e);
      throw new BizRuntimeException("数据库操作异常");
    }
    return flag;
  }


  public boolean updateWorkAttendance(Integer workAttendanceId, WorkAttendanceLateOrLeaveEarlyEnum workAttendanceLateOrLeaveEarlyEnum) {
    //先将两个字段都置空
    WorkAttendance workAttendance = workAttendanceMapper.selectByPrimaryKey(workAttendanceId);
    //修改为没有异常信息
    workAttendance.setLeaveEarly(null);
    workAttendance.setLate(null);
    if (workAttendanceLateOrLeaveEarlyEnum == WorkAttendanceLateOrLeaveEarlyEnum.LATE) {
      //修改为迟到
      workAttendance.setLeaveEarly(null);
      workAttendance.setLate(workAttendanceLateOrLeaveEarlyEnum.getTableStatus());
    }
    if (workAttendanceLateOrLeaveEarlyEnum == WorkAttendanceLateOrLeaveEarlyEnum.LEAVE_EARLY) {
      //修改为早退
      workAttendance.setLate(null);
      workAttendance.setLeaveEarly(workAttendanceLateOrLeaveEarlyEnum.getTableStatus());
    }
    workAttendanceMapper.updateByPrimaryKey(workAttendance);
    logger.info("修改当前考勤信息成功,id:{}", workAttendance.getWorkAttendanceId());
    return true;
  }
}
