package com.smepms.workattendance.controller;

import com.github.pagehelper.PageInfo;
import com.smepms.common.util.ShiroUtils;
import com.smepms.workattendance.enums.LeaveApplicationStatusEnum;
import com.smepms.workattendance.pojo.Leave;
import com.smepms.workattendance.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * LeaveApplicationController
 *
 * @author liujh
 * @since 2018/5/7
 */
@RestController
@RequestMapping("/leave")
public class LeaveApplicationController {
  @Autowired
  private LeaveService leaveService;
  @Value("${EMPLOYEE_QUERYALL_DEFAULT_PAGE_SIZE}")
  private Integer DEFAULFT_PAGE_SIZE;


  @PostMapping
  public boolean createLeaveApplication(@RequestParam(value = "leaveStartTime",required = true)Date leaveStartTime,
                                        @RequestParam(value = "leaveEndTime",required = true)Date leaveEndTime,
                                        @RequestParam(value = "leaveReason",required = true)String leaveReason){
    Leave leave = new Leave();
    leave.setLeaveStartTime(leaveStartTime);
    leave.setLeaveEndTime(leaveEndTime);
    leave.setLeaveReason(leaveReason);
    return leaveService.createEmployeeLeaveApplication(leave);
  }

  /**
   * 获取自己最近发起的请假请求
   */
  @GetMapping("/self/{employeeId}")
  public List<Leave> getEmployeeApplication(@PathVariable("employeeId")Integer employeeId){
    return leaveService.selectLeaveApplicationByEmployeeId(employeeId);
  }

  @GetMapping("/self/page")
  public PageInfo<Leave> getEmployeeApplication(@RequestParam(required = false,value = "pageNo")Integer pageNo,
                                                @RequestParam(required = false,value = "startDate") Date startDate,
                                                @RequestParam(required = false,value = "endDate")Date endDate){
    if(pageNo == null){
      pageNo = 1;
    }
    return leaveService.selectLeaveApplicationByDate(startDate,endDate,pageNo,DEFAULFT_PAGE_SIZE,null,ShiroUtils.getLogEmployeeWithBLOBs().getEmployeeId());
  }

  /**
   * 获取当前leader需要处理的请假申请
   * @param employeeLeaderId
   * @return
   */
  @GetMapping("/leader/{employeeLeaderId}")
  public List<Leave> getEmployeeApplicationByLeaderId(@PathVariable("employeeLeaderId")Integer employeeLeaderId){
    return leaveService.selectLeaveApplicationByEmployeeLeaderId(employeeLeaderId);
  }

  @PostMapping("/leader/pass/{leaveId}")
  public boolean leaderPassEmployeeLeaveApplication(@PathVariable("leaveId")Integer leaveId){
    return leaveService.updateEmployeeLeaveApplicationStatus(LeaveApplicationStatusEnum.MANAGER_PASS,leaveId);
  }

  @PostMapping("/leader/reject/{leaveId}")
  public boolean leaderRejectEmployeeLeaveApplication(@PathVariable("leaveId")Integer leaveId){
    return leaveService.updateEmployeeLeaveApplicationStatus(LeaveApplicationStatusEnum.DELETE,leaveId);
  }

  @GetMapping("/hr")
  public PageInfo hrGetEmployeeApplication(@RequestParam(required = false)Integer pageNo,
                                           @RequestParam(required = false) Date startDate,
                                           @RequestParam(required = false)Date endDate){
    if(pageNo == null){
      pageNo = 1;
    }
    return leaveService.selectLeaveApplicationByDate(startDate,endDate,pageNo,DEFAULFT_PAGE_SIZE,LeaveApplicationStatusEnum.MANAGER_PASS,null);
  }

  @GetMapping("/hr/check")
  public PageInfo hrCheckEmployeeApplication(@RequestParam(required = false)Integer pageNo,
                                           @RequestParam(required = false) Date startDate,
                                           @RequestParam(required = false)Date endDate){
    if(pageNo == null){
      pageNo = 1;
    }
    return leaveService.selectLeaveApplicationByDate(startDate,endDate,pageNo,DEFAULFT_PAGE_SIZE,LeaveApplicationStatusEnum.HR_PASS,null);
  }


  @PostMapping("/hr/pass")
  public boolean hrPassEmployeeLeaveApplication(@RequestParam(required = true)Integer leaveId){
    return leaveService.updateEmployeeLeaveApplicationStatus(LeaveApplicationStatusEnum.HR_PASS,leaveId);
  }

  @PostMapping("/hr/rejection")
  public boolean hrRejectEmployeeLeaveApplication(@RequestParam(required = true)Integer leaveId){
    return leaveService.updateEmployeeLeaveApplicationStatus(LeaveApplicationStatusEnum.CREATED,leaveId);
  }

  //处理日期请求
  @InitBinder
  public void initBinder(WebDataBinder binder) throws Exception {
    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"), true));
  }

}
