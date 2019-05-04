package com.smepms.jobmanagement.controller.rest;

import com.smepms.jobmanagement.mapper.DepartmentMapper;
import com.smepms.jobmanagement.pojo.Department;
import com.smepms.jobmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2018/1/13.
 */
@Controller
@RequestMapping("/Department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取所有部门信息
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<Department> queryAllDepartment(){
        List<Department> list = departmentService.queryAllDepartment();
        return list;
    }



}
