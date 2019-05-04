package com.smepms.jobmanagement.service;

import com.smepms.jobmanagement.pojo.Department;
import com.smepms.jobmanagement.pojo.DepartmentExample;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */
public interface DepartmentService {
    List<Department> queryAllDepartment();

    Department queryDepartmentById(Integer departmentId);
}
