package com.smepms.jobmanagement.service;

import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/1/17.
 */
public interface SysEmployeeService {

    public EmployeeWithBLOBs certification(Integer employeeWorkId);

    public Map<String,Set<String>> authorized(Integer employeeWorkId);
}
