package com.smepms.jobmanagement.controller.rest;

import com.smepms.common.util.ShiroUtils;
import com.smepms.jobmanagement.enums.WorkerApplicationStatus;
import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import com.smepms.jobmanagement.pojo.RegularWorkerApplication;
import com.smepms.jobmanagement.service.RegularWorkerApplicationService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/RegularWorkerApplication")
@Controller
public class RegularWorkerApplicationController {
  private final static Logger logger = LoggerFactory.getLogger(RegularWorkerApplicationController.class);
  @Autowired
  RegularWorkerApplicationService regularWorkerApplicationService;

  /**
   * RequestParam中的required是指这个参数是否客户端必须提供，
   * defaultValue则是如果没有提供该参数默认值是什么
   * （故required=true, defaultValue="xxx"就没意义了）。
   *
   * @return
   */
  @ResponseBody
  @RequestMapping(value = "/employeeAssignment", method = RequestMethod.POST)
  public Integer postAssignment(String employeeApplication) {
    if (employeeApplication == null || employeeApplication.equals("")) {
      return 0;
    }
    boolean flag = regularWorkerApplicationService.createRegularWorkerApplication(employeeApplication);
    if (flag) {
      return 1;
    }
    return 0;
  }

  @ResponseBody
  @RequestMapping(value = "/employeeApplication", method = RequestMethod.GET)
  public RegularWorkerApplication ApplicationGet() {
    EmployeeWithBLOBs logEmployee = ShiroUtils.getLogEmployeeWithBLOBs();
    RegularWorkerApplication regularWorkerApplication = regularWorkerApplicationService.getRegularWorkerApplication(logEmployee.getEmployeeId());
    return regularWorkerApplication;
  }

  @ResponseBody
  @RequestMapping(value = "/all_employee_application", method = RequestMethod.GET)
  public List<RegularWorkerApplication> leaderApplicationsGet() {
    EmployeeWithBLOBs logEmployee = ShiroUtils.getLogEmployeeWithBLOBs();
    return regularWorkerApplicationService.getRegularWorkerApplicationByLeaderId(logEmployee.getEmployeeId());
  }

  @ResponseBody
  @RequestMapping(value = "/pass_employee_application", method = RequestMethod.POST)
  public Integer passApplication(@RequestParam(required = true, value = "id") Integer id, @RequestParam(required = true, value = "leaderAcception") String leaderAcception) {
    //项目经理通过员工转正申请
    return regularWorkerApplicationService.passRegularWorkerApplication(id, leaderAcception);
  }

  @ResponseBody
  @RequestMapping(value = "/dismiss_employee_application/{id}", method = RequestMethod.POST)
  public Integer dismissApplication(@PathVariable Integer id) {
    //申请不通过，删除
    return regularWorkerApplicationService.dismissApplication(id);
  }

  @RequiresRoles(value = {"hr_worker","hr_manager"},logical = Logical.OR)
  @ResponseBody
  @RequestMapping(value = "/all_application")
  public List<RegularWorkerApplication> allApplication() {
    return regularWorkerApplicationService.getAll(WorkerApplicationStatus.LEADERPASS);
  }

  @ResponseBody
  @RequestMapping(value = "/hr_application_pass/{id}",method = RequestMethod.POST)
  public Integer hrPassApplication(@PathVariable Integer id){
    return regularWorkerApplicationService.passEmployeeApplication(id);
  }

  @ResponseBody
  @RequestMapping(value = "/hr_application_reject/{id}",method = RequestMethod.POST)
  public Integer hrRejectApplication(@PathVariable Integer id){
    return regularWorkerApplicationService.rejectEmployeeApplication(id);
  }

}
