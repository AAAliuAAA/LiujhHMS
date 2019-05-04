package com.smepms.jobmanagement.controller.rest;

import com.github.pagehelper.PageInfo;
import com.smepms.common.util.formatutil.StringFormatUtil;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.jobmanagement.pojo.EmployeeExample;
import com.smepms.jobmanagement.service.EmployeeService;
import org.apache.shiro.authz.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */


@Controller
@RequestMapping("/EmployeeAjaxController")
public class EmployeeRestController {
    @Autowired
    private EmployeeService employeeService;
    @Value("${EMPLOYEE_QUERYALL_DEFAULT_PAGE_SIZE}")
    private Integer DEFAULFT_PAGE_SIZE;



    /**
     * 员工列表主页面查询
     * @param pageSize
     * @param pageNo 当前页
     * @param employeeWorkId 工号查询
     * @param departmentId 部门查询
     * @param statusId 在职状态查询
     * @param employeeName 员工姓名查询
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "/queryEmployees",method = RequestMethod.GET)
    public PageInfo queryEmployeeByexampleAndPage(@RequestParam(required = false) Integer pageSize,
                                                  @RequestParam(required = false) Integer pageNo,
                                                  @RequestParam(required = false) Integer employeeWorkId,
                                                  @RequestParam(required = false) Integer departmentId,
                                                  @RequestParam(required = false) Integer statusId,
                                                  @RequestParam(required = false) String employeeName,
                                                  @RequestParam(required = false) Integer employeeLeaderId){
        if (pageSize == null) {
            pageSize = DEFAULFT_PAGE_SIZE;
        }
        if(pageNo == null){
            pageNo  = 1;
        }
        EmployeeExample employeeExample  = new EmployeeExample();
        EmployeeExample.Criteria criteria = employeeExample.createCriteria();
        if(employeeWorkId != null){
            criteria.andEmployeeWorkIdEqualTo(employeeWorkId);
        }else if(departmentId !=null){
            employeeExample.setDepartmentId(departmentId);
        }else if(employeeName!=null&&!employeeName.equals("")){
            //对当前用户输入的名字进行转译
            employeeName = StringFormatUtil.userInputSearchFormat(employeeName);
            criteria.andEmployeeNameLike("%"+employeeName+"%");
        }
        if(statusId==null||statusId.equals(0)){
            Integer[] integers = {1,3};
            ArrayList<Integer> statusIdaccessList = new ArrayList<Integer>(Arrays.asList(integers));
            criteria.andStatusIdIn(statusIdaccessList);
        }
        if(employeeLeaderId != null){
            criteria.andEmployeeLeaderEqualTo(employeeLeaderId);
        }
        PageInfo pageInfo= employeeService.queryEmployeesWithDepartmentAndPosition(employeeExample,pageNo,pageSize);
        return pageInfo;
    }

    @RequestMapping(value = "/queryMaxWorkId",method = RequestMethod.GET)
    @ResponseBody
    public Integer queryMaxWorkId(){
      return employeeService.queryBestEmployeeWorkId();
    };


    /**
     * 录入或者修改员工信息时选择领导
     * 只查询在职员工，只查询当前部门员工，只查询部门经理和组长员工
     * @return
     */

    @ResponseBody
    @RequestMapping(value = "/queryEmployeeLeaders",method = RequestMethod.GET)
    public List<Employee> queryEmployeeWithPositionAndDepartmentByEmployeeExample(@RequestParam(required = false) Integer employeeWorkId,
                                                                                  @RequestParam(required = true) Integer departmentId){
        EmployeeExample employeeExample = new EmployeeExample();
        employeeExample.setDepartmentId(departmentId);
        EmployeeExample.Criteria criteria  =employeeExample.createCriteria();
        if(employeeWorkId!=null){
            criteria.andEmployeeWorkIdEqualTo(employeeWorkId);
        }
        return employeeService.queryEmployeeLeadersByDeptAndPosition(employeeExample);
    }

    //处理日期请求
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}
