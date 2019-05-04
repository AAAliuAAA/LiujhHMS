package com.smepms.jobmanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.smepms.common.util.formatutil.StringFormatUtil;
import com.smepms.jobmanagement.consts.PositionConsts;
import com.smepms.jobmanagement.mapper.EmployeeMapper;
import com.smepms.jobmanagement.mapper.RoleMapper;
import com.smepms.jobmanagement.pojo.*;
import com.smepms.jobmanagement.service.EmployeeService;
import com.smepms.jobmanagement.util.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/10.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RoleMapper roleMapper;

    public PageInfo queryEmpByExampleAndPage(EmployeeExample employeeExample,Integer pageNo,Integer pageSize) {

        PageHelper.startPage(pageNo,pageSize);
        List<Employee> list = employeeMapper.selectByExample(employeeExample);
        PageInfo page = new PageInfo(list);
        return page;

    }

    //避免并发引起的员工工号重复，添加同步
    public synchronized boolean saveEmployee(EmployeeWithBLOBs employee) {
            //对当前用户的introduction配置转译符号
            employee.setEmployeeIntroduction(StringFormatUtil.userIntroductionFormat(employee.getEmployeeIntroduction()));
            //加盐（最后再解除注释）
            employee.setEmployeePassword(PasswordEncrypt.encryptPassword(employee.getEmployeePassword()));
            //添加入职时间
            employee.setEmployeeCreateTime(new Date());
            //判断当前员工的工号在数据库中还是不是唯一的（有可能在编辑的时候已经有人使用了当前配置的工号）
            //这样做是不合理的，任何时候能避免在循环里面写查询就不要在循环里面写查询，正确的做法是在这里查询一次获取当前所有员工
            //并且根据员工工号进行排序，排序写好以后直接去取第一个然后进行+1操作，
            while(employeeMapper.queryEmployeeWorkIdHasTheSame(employee.getEmployeeWorkId())>0){
                employee.setEmployeeWorkId(employee.getEmployeeWorkId()+1);
            }
            //插入员工记录表，并返回主键！！！！！！！！！！！
            Integer flag = employeeMapper.insertSelective(employee);
            //查询关联的角色信息
            RoleExample roleExample = new RoleExample();
            RoleExample.Criteria criteria =  roleExample.createCriteria();
            criteria.andDepartmentIdEqualTo(employee.getDepartment().getDepartmentId());
            criteria.andPositionIdEqualTo(employee.getPosition().getPositionId());
            List<Role> roleList = roleMapper.selectByExample(roleExample);
            Role targetRole = roleList.get(0);
            //插入角色关联表
            employeeMapper.inserEmployeeRole(targetRole.getRoleId(),employee.getEmployeeId());
            return flag>0;
}

    public EmployeeWithBLOBs queryOneEmpById(Integer employeeId) {
        EmployeeWithBLOBs employeeWithBLOBs =  employeeMapper.queryEmployeeWithDepartmentAndPosition(employeeId);
        return employeeWithBLOBs;
    }

    public PageInfo queryEmployeesWithDepartmentAndPosition(EmployeeExample employeeExample,Integer pageNo,Integer pageSize){
        PageHelper.startPage(pageNo,pageSize);
        List<Employee> employeeList =  employeeMapper.queryEmployeesWithDepartmentAndPosition(employeeExample);
        PageInfo pageInfo  = new PageInfo(employeeList);
        return pageInfo;

    }





    public Integer updateEmployee(EmployeeWithBLOBs employeeWithBLOBs) {
        //把创建时间，密码，头像查找出来set进去
        EmployeeWithBLOBs employee = employeeMapper.selectByPrimaryKey(employeeWithBLOBs.getEmployeeId());
        employeeWithBLOBs.setEmployeeCreateTime(employee.getEmployeeCreateTime());
        employeeWithBLOBs.setEmployeePassword(employee.getEmployeePassword());
        employeeWithBLOBs.setEmployeeHeadSculpture(employee.getEmployeeHeadSculpture());
        //对当前用户的introduction配置转译符号
        employeeWithBLOBs.setEmployeeIntroduction(StringFormatUtil.userIntroductionFormat(employeeWithBLOBs.getEmployeeIntroduction()));
        Integer flag = employeeMapper.updateByPrimaryKeyWithBLOBs(employeeWithBLOBs);

        //修改当前用户角色
        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria criteria =  roleExample.createCriteria();
        criteria.andDepartmentIdEqualTo(employeeWithBLOBs.getDepartment().getDepartmentId());
        criteria.andPositionIdEqualTo(employeeWithBLOBs.getPosition().getPositionId());
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        Role targetRole = roleList.get(0);
        employeeMapper.updateEmployeeRoleByEmployeeId(employeeWithBLOBs.getEmployeeId(),targetRole.getRoleId());
        return flag;
    }

    public Integer updateEmployeeHeadSculpture(EmployeeWithBLOBs employeeWithBLOBs) {
        Integer flag = employeeMapper.updateEmployeeHeadSculptureByPrimaryKey(employeeWithBLOBs);
        return flag;
    }

    public Integer queryBestEmployeeWorkId() {
        Integer employeeWorkId = employeeMapper.selectMaxEmployeeWorkId();
        return employeeWorkId;
    }

    public List<Employee> queryEmployeeLeadersByDeptAndPosition(EmployeeExample employeeExample) {
        return  employeeMapper.queryEmployeeLeadersWithDeptAndPosition(employeeExample);
    }

    public Boolean updateEmployeePassword(String oldPassword, String newPassword,Employee logEmployee) {
        //对当前输入密码进行加盐
        String enryptOldPassword = PasswordEncrypt.encryptPassword(oldPassword);
        if(!logEmployee.getEmployeePassword().equals(enryptOldPassword)){
            //当前密码输入错误
            return false;
        }
        //对当前输入的新密码进行加盐，并存入数据库
        EmployeeWithBLOBs uptemployee = new EmployeeWithBLOBs();
        uptemployee.setEmployeeId(logEmployee.getEmployeeId());
        uptemployee.setEmployeePassword(PasswordEncrypt.encryptPassword(newPassword));
        Integer flag =  employeeMapper.updateByPrimaryKeySelective(uptemployee);
        return flag>0;
    }

    public EmployeeWithBLOBs queryLogEmployee(Integer employeeWorkId) {
        return employeeMapper.queryEmployeeWithDepartmentAndPositionByEmployeeWorkId(employeeWorkId);
    }

    public Integer updateEmployeeSelective(EmployeeWithBLOBs employeeWithBLOBs) {
        //对当前用户的introduction配置转译符号
        employeeWithBLOBs.setEmployeeIntroduction(StringFormatUtil.userIntroductionFormat(employeeWithBLOBs.getEmployeeIntroduction()));
        return employeeMapper.updateByPrimaryKeySelective(employeeWithBLOBs);
    }

    public List<EmployeeWithBLOBs> queryEmployeeDepartmentLeader(Integer employeeId){
        Employee employee = employeeMapper.queryEmployeeWithDepartmentAndPosition(employeeId);
        //获取当前部门的部门经理
        //通常情况java代码关联到表的情况是需要一个code字段来进行匹配，或者说在部门表当中就直接指定了当前项目经理的关系，或者说职位进行树状排布来定义关系
        // 这里没有就直接写死好了
            List<EmployeeWithBLOBs> list = employeeMapper.queryEmployeeByPositionAndDepartment(employee.getDepartment().getDepartmentId(), PositionConsts.POSITION_DEPARTMENT_MANAGER);
        return list;
    }

}
