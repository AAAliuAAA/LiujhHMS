package com.smepms.jobmanagement.service.impl;

import com.smepms.jobmanagement.mapper.EmployeeMapper;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.jobmanagement.pojo.EmployeeExample;
import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2018/1/17.
 */
@Service
public class SysEmployeeServiceImpl implements com.smepms.jobmanagement.service.SysEmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;

    /*认证方法，通过用户账号和密码获得用户对象信息*/
    public EmployeeWithBLOBs certification(Integer employeeWorkId) {
       return employeeMapper.queryOneEmployeeWithBLOBsByEmployeeWorkId(employeeWorkId);
}

    public Map<String, Set<String>> authorized(Integer employeeWorkId) {
        Map<String,Set<String>> map = new HashMap<String, Set<String>>(2);
        map.put("roles",employeeMapper.queryEmployeeRolesByEmployeeWorkId(employeeWorkId));
        Set<String> set = employeeMapper.queryEmployeeFunctionByEmployeeWorkId(employeeWorkId);
        //为了防止抛出 Wildcard string cannot be null or empty 异常，在用户没有任何权限时，扔一个字符串进去(数据库操作)
        map.put("functions",set);
        return map;
    }
}
