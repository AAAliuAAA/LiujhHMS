package com.smepms.workattendance.controller;

import com.github.pagehelper.PageInfo;
import com.smepms.workattendance.enums.WorkOverTimeStatusEnum;
import com.smepms.workattendance.pojo.WorkOvertime;
import com.smepms.workattendance.service.WorkOvertimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/work_overtime")
public class WorkOvertimeController {
    @Autowired
    private WorkOvertimeService workOvertimeService;
    @Value("${EMPLOYEE_QUERYALL_DEFAULT_PAGE_SIZE}")
    private Integer DEFAULFT_PAGE_SIZE;

    /**
     * 发起加班请求
     * @param workOvertime
     * @return
     */
    @PostMapping
    public Integer checkInWorkOvertime(WorkOvertime workOvertime){
            Integer status= workOvertimeService.checkOne(workOvertime);
            return status;
    }

    /**
     * 当前员工的加班申请
     * @param employeeId
     * @return
     */
    @GetMapping("/employeeId/{employeeId}")
    public List<WorkOvertime> findWorkOvertimeByEmployeeId(@PathVariable("employeeId")Integer employeeId){
        return workOvertimeService.findAllByEmployeeId(employeeId);
    }



    @PostMapping("/managerPass/{workOvertimeId}")
    public boolean managerPass(@PathVariable("workOvertimeId") Integer workOvertimeId){
        //修改状态
       return workOvertimeService.managerPassOvertimeApplication(workOvertimeId);
    }

    @PostMapping("/managerReject/{workOvertimeId}")
    public boolean mangerReject(@PathVariable("workOvertimeId") Integer workOvertimeId){
        //修改状态
        return workOvertimeService.managerRejectOvertimeApplication(workOvertimeId);
    }

    /**
     * 直属上级需要处理的加班申请
     * @param employeeLeaderId
     * @return
     */
    @GetMapping("/leaderId/{leaderId}")
    public List<WorkOvertime> findWorkOvertimeByEmployeeLeaderId(@PathVariable("leaderId")Integer employeeLeaderId){
        return workOvertimeService.findByEmployeeLeaderIdAndStatus(employeeLeaderId, WorkOverTimeStatusEnum.CREATED);
    }

    /**
     * 人事部门需要处理的加班申请
     * @param startDate
     * @param endDate
     * @return
     */
    @GetMapping("/hr/date")
    public PageInfo<WorkOvertime> findWorkOvertimeByDate(@RequestParam(required = false)Date startDate, @RequestParam(required = false) Date endDate,@RequestParam(required = false)Integer pageNo){
        if(pageNo == null){
            pageNo = 1;
        }
        return workOvertimeService.findWorkOverTimeByDate(startDate,endDate,pageNo,this.DEFAULFT_PAGE_SIZE);
    }

    /**
     * 人事部门通过加班申请
     * @param workOvertimeId
     * @return
     */
    @PostMapping("/hr/pass/{workOvertimeId}")
    public boolean hrPassWorkOvertime(@PathVariable("workOvertimeId")Integer workOvertimeId){
        return workOvertimeService.hrPassWorkOvertime(workOvertimeId);
    }

    /**
     * 人事部门拒绝加班申请
     * @param workOvertimeId
     * @return
     */
    @PostMapping("/hr/reject/{workOvertimeId}")
    public boolean hrRejectWorkOvertime(@PathVariable("workOvertimeId")Integer workOvertimeId){
        return workOvertimeService.hrRejectWorkOvertime(workOvertimeId);
    }


    //处理日期请求
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

}
