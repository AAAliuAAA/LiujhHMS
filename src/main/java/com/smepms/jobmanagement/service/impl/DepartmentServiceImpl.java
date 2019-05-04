package com.smepms.jobmanagement.service.impl;

import com.smepms.jobmanagement.mapper.DepartmentMapper;
import com.smepms.jobmanagement.pojo.Department;
import com.smepms.jobmanagement.pojo.DepartmentExample;
import com.smepms.jobmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> queryAllDepartment() {
        List<Department> departmentList = departmentMapper.selectByExample(null);
        return departmentList;
    }

    public Department queryDepartmentById(Integer departmentId) {
        Department department = departmentMapper.selectByPrimaryKey(departmentId);
        return department;
    }


}
