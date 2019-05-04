package com.smepms.workattendance.controller;

import com.github.pagehelper.PageInfo;
import com.smepms.common.util.editor.TimeEditor;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.workattendance.pojo.WorkAttendanceConfig;
import com.smepms.workattendance.service.WorkAttendanceConfigService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by acer on 2018/2/5.
 */
@Controller
@RequestMapping("/WorkAttendanceConfig")
public class WorkAttendanceConfigController {
    private Logger logger = Logger.getLogger(WorkAttendanceConfigController.class);

    @Autowired
    WorkAttendanceConfigService workAttendanceConfigService;
    @Value("${WORK_ATTENDANCE_QUERYALL_DEFAULT_PAGE_SIZE}")
    private Integer WORK_ATTENDANCE_QUERYALL_DEFAULT_PAGE_SIZE;

    //根据传入的页数，查询所有工作时间配置
    @RequiresPermissions("hr:update_work_attendance")
    @RequestMapping(value="/queryAllWithCreator",method = RequestMethod.GET)
    public ModelAndView getAllWorkAttendanceConfigAndCreators(@RequestParam(required = false) Integer pageNum){
        ModelAndView md = new ModelAndView();
        md.setViewName("/work_attendance_update.jsp");
        if(pageNum==null||pageNum==0){
            pageNum = 1;
        }
        PageInfo workAttendanceConfigPages = workAttendanceConfigService.selectAllWorkAttendanceConfigWithEmployeeWorkId(pageNum,WORK_ATTENDANCE_QUERYALL_DEFAULT_PAGE_SIZE);
        md.addObject("workAttendanceConfigPages",workAttendanceConfigPages);
        return md;
    }

    @RequiresPermissions("hr:update_work_attendance")
    @RequestMapping(value = "/addWorkAttendanceConfig",method = RequestMethod.POST)
    public String addOneWorkAttendanceConfig(WorkAttendanceConfig workAttendanceConfig){
        ModelAndView md = new ModelAndView();
        //插入一个时间配置数据
        //从页面上获取了上下班时间，开始日期，备注，插入一个当前的创建人工号，在service调用一次isUsing配置的service方法
        Subject subject  = SecurityUtils.getSubject();
        Session session = subject.getSession();
        Employee logEmployee = (Employee) session.getAttribute("logEmployee");
        workAttendanceConfig.setCreator(logEmployee.getEmployeeWorkId());
        workAttendanceConfigService.addWorkAttendanceConfig(workAttendanceConfig);
        return "redirect:/WorkAttendanceConfig/queryAllWithCreator.do";
    }

    @ResponseBody
    @RequestMapping(value = "/addWorkAttendanceConfigValidation",method = RequestMethod.GET)
    public Integer addWorkAttendanceConfigVlidation(Date newStartDate){
        logger.info(newStartDate);
        if(workAttendanceConfigService.workAttendanceConfigValidation(newStartDate)){
            return 1;
        }else{
            return 0;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/queryWorkAttendanceConfigToday",method = RequestMethod.GET)
    public WorkAttendanceConfig queryWorkAttendanceConfigIsUsing(){
        return workAttendanceConfigService.queryWorkTimeToday();
    }

    @RequiresPermissions("hr:update_work_attendance")
    @RequestMapping("/updateWorkAttendanceConfig")
    public String hanleWorkAttendanceConfig(){
        workAttendanceConfigService.updateWorkAttendanceConfigIsUsing();
        return "redirect:/WorkAttendanceConfig/queryAllWithCreator.do";
    }

    //注册 日期请求和sql的Time请求 的解析器
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        binder.registerCustomEditor(Time.class,new TimeEditor());
    }
}
