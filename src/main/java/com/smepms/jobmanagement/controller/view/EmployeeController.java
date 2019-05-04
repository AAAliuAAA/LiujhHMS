package com.smepms.jobmanagement.controller.view;

import com.github.pagehelper.PageInfo;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.jobmanagement.pojo.EmployeeExample;
import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import com.smepms.jobmanagement.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2018/1/10.
 */
@Controller
@RequestMapping("/Employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Value("${EMPLOYEE_QUERYALL_DEFAULT_PAGE_SIZE}")
    private Integer DEFAULFT_PAGE_SIZE;

    @Value("${User_HeadSculpture_ImageResourcePath}")
    private String User_HeadSculpture_ImageResourcePath;


    /**
     * 同步查询请求，现在已经换为ajax异步查询，可删除
     * @param employeeWorkId 员工工号
     * @param pageNo 当前页面
     * @param pageSize 每一页记录数，默认是2
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequiresPermissions("hr:view_employee")
    @GetMapping
    public ModelAndView queryEmp(@RequestParam(defaultValue = "") String employeeWorkId,
                                 @RequestParam(defaultValue = "1") Integer pageNo,
                                 @RequestParam(required = false) Integer pageSize) throws UnsupportedEncodingException {
        ModelAndView md = new ModelAndView();
        employeeWorkId = new String(employeeWorkId.getBytes("iso-8859-1"), "utf-8");
        md.setViewName("/employee_check_all.jsp");
        EmployeeExample employeeExample = new EmployeeExample();
        //判断
        if (employeeWorkId != null && !employeeWorkId.equals("")) {
            employeeExample.createCriteria().andEmployeeIdEqualTo(Integer.parseInt(employeeWorkId));
        }


        if (pageSize == null) {
            pageSize = DEFAULFT_PAGE_SIZE;
        }

        PageInfo page = employeeService.queryEmpByExampleAndPage(employeeExample, pageNo, pageSize);
        md.addObject("page", page);
        md.addObject("employeeWorkId", employeeWorkId);
        return md;
    }

    /***
     * 只有用户自己能修改自己的头像
     * 上传头像的控制器
     * @param request 用来获取当前绝对路径
     * @param headSculpture 文件
     * @param employeeUptId 员工Id
     * @return
     */
    @RequestMapping(value = "/headSulptureUpload",method = RequestMethod.POST)
    public ModelAndView uptHeadSculpture(
                               HttpServletRequest request,
                               @RequestParam(required = false) MultipartFile headSculpture,
                               @RequestParam(required = false) Integer employeeUptId) {
            ModelAndView md = new ModelAndView();
            /*自动封装在此类表单下，会失效-*/
            EmployeeWithBLOBs employeeWithBLOBsWithHeadSculpture = new EmployeeWithBLOBs();
            employeeWithBLOBsWithHeadSculpture.setEmployeeId(employeeUptId);
            //获取当前tomcat根目录系统绝对路径
            String randomPath = UUID.randomUUID().toString();
            String emppath = "empId_"+employeeUptId+"/";
            String warPath = request.getServletContext().getRealPath("");
            File targetFile = new File(warPath + User_HeadSculpture_ImageResourcePath+emppath+randomPath,headSculpture.getOriginalFilename());
            //没有目录则创建目录
            if (!targetFile.getParentFile().exists()) {
                boolean flag = targetFile.getParentFile().mkdirs();
            }
            try {
                //上传
                headSculpture.transferTo(targetFile);
                //添加了一个UUID，所以需要重新添加一级路径
                employeeWithBLOBsWithHeadSculpture.setEmployeeHeadSculpture(User_HeadSculpture_ImageResourcePath+emppath+randomPath+"/"+headSculpture.getOriginalFilename());
                employeeService.updateEmployeeHeadSculpture(employeeWithBLOBsWithHeadSculpture);
                //上传完后更新会话中的用户信息
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession();
                session.setAttribute("logEmployee",employeeService.queryOneEmpById(employeeWithBLOBsWithHeadSculpture.getEmployeeId()));
                md.setViewName("redirect:/employee_self_detail.jsp");
                return md;
            } catch (IOException e) {
                e.printStackTrace();
            }
        return md;
    }






    /**
     * 新增员工
     * @param employeeWithBLOBs 自动封装员工对象
     * @return
     * 此新增员工页面已经在过滤器链中进行权限过滤
     */
    @RequestMapping(value = "/addEmployee",method = RequestMethod.POST)
    public ModelAndView addEmp(EmployeeWithBLOBs employeeWithBLOBs){
        ModelAndView md = new ModelAndView();
        md.setViewName("redirect:/employee_check_allajax.jsp");
        boolean flag = employeeService.saveEmployee(employeeWithBLOBs);
        if (flag) {
//            request.getSession().setAttribute("flag","0");
            return md;
        }
        return md;
    }
    @RequiresPermissions("hr:update_employee")
    @RequestMapping(value="/updateEmployee",method = RequestMethod.POST)
    public ModelAndView updateEmp(EmployeeWithBLOBs employeeWithBLOBs){
        //修改员工信息
        Integer flag = employeeService.updateEmployee(employeeWithBLOBs);
        ModelAndView md = new ModelAndView();
        md.setViewName("redirect:/employee_check_allajax.jsp");
        return md;
    }



    /**
     * 查询单一员工详情
     * @param employeeId
     * @param method
     * @return
     */
    @RequiresPermissions("hr:view_employee")
    @GetMapping("{employeeId}")
    public ModelAndView queryOneEmp(@PathVariable Integer employeeId, @RequestParam String method) {
        ModelAndView md = new ModelAndView();
        EmployeeWithBLOBs employeeWithBLOBs = employeeService.queryOneEmpById(employeeId);
        md.addObject("emp", employeeWithBLOBs);
        if (method.equals("check")) {
            md.setViewName("/employee_detail.jsp");
        } else {
            md.setViewName("/employee_update.jsp");
        }
        return md;
    }



    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public ModelAndView logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ModelAndView md = new ModelAndView();
        md.setViewName("redirect:/index.jsp");
        return md;
    }


    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    public ModelAndView updatePassword(String oldPassword,
                                       String newPassword){
        ModelAndView md = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        Session logSession = subject.getSession();
        Employee logEmployee =(EmployeeWithBLOBs)logSession.getAttribute("logEmployee");
        Boolean flag = employeeService.updateEmployeePassword(oldPassword,newPassword,logEmployee);
        if(flag){
            md.addObject("errormsg","修改成功，请重新登录");
            subject.logout();
            md.setViewName("/login.jsp");
        }else{
            md.addObject("errormsg","原密码错误，修改失败");
            md.setViewName("/employee_updatePassword.jsp");
        }
        return md;
    }


    @RequestMapping(value = "/updateEmployeeSelf",method = RequestMethod.PUT)
    public ModelAndView updateLogEmployeeProfile(EmployeeWithBLOBs employeeWithBLOBs) throws InterruptedException {
        ModelAndView md = new ModelAndView();
        Subject subject = SecurityUtils.getSubject();
        Session logSession = subject.getSession();
        EmployeeWithBLOBs logEmployee =(EmployeeWithBLOBs) logSession.getAttribute("logEmployee");
        employeeWithBLOBs.setEmployeeId(logEmployee.getEmployeeId());
        employeeService.updateEmployeeSelective(employeeWithBLOBs);
        //修改完成，更新当前登录员工信息
        logSession.setAttribute("logEmployee",employeeService.queryOneEmpById(logEmployee.getEmployeeId()));
        md.setViewName("redirect:/employee_self_detail.jsp");
        return md;
    }

    //处理日期请求
    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }


}
