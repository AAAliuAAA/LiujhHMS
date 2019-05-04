package com.smepms.jobmanagement.service;

import com.github.pagehelper.PageInfo;
import com.smepms.jobmanagement.pojo.Department;
import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.jobmanagement.pojo.EmployeeExample;
import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */

public interface EmployeeService {
    PageInfo queryEmpByExampleAndPage(EmployeeExample employeeExample,Integer pageNo,Integer pageSize);

    boolean saveEmployee(EmployeeWithBLOBs employee);

    EmployeeWithBLOBs queryOneEmpById(Integer employeeId);

    PageInfo queryEmployeesWithDepartmentAndPosition(EmployeeExample employeeExample,Integer pageNo,Integer pageSize);

    Integer updateEmployee(EmployeeWithBLOBs employeeWithBLOBs);

    Integer updateEmployeeHeadSculpture(EmployeeWithBLOBs employeeWithBLOBs);

    Integer queryBestEmployeeWorkId();

    List<Employee> queryEmployeeLeadersByDeptAndPosition(EmployeeExample employeeExample);

    Boolean updateEmployeePassword(String oldPassword,String newPassword,Employee logEmployee);

    EmployeeWithBLOBs queryLogEmployee(Integer employeeWorkId);

    Integer updateEmployeeSelective(EmployeeWithBLOBs employeeWithBLOBs);

    /**
     * 查询当前部门的部门经理
     * @param employeeId
     * @return
     */
    List<EmployeeWithBLOBs> queryEmployeeDepartmentLeader(Integer employeeId);
}
