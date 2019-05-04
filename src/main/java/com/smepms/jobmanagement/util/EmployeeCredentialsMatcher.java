package com.smepms.jobmanagement.util;

import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import com.smepms.jobmanagement.service.EmployeeService;
import com.smepms.jobmanagement.service.SysEmployeeService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by Administrator on 2018/1/22.
 */
public class EmployeeCredentialsMatcher extends SimpleCredentialsMatcher{
    private static final Logger logger = Logger.getLogger(EmployeeCredentialsMatcher.class);
    @Autowired
    private EmployeeService employeeService;

    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        Object tokenCredentials = PasswordEncrypt.encryptPassword(super.toString(token.getCredentials())).getBytes();
        Object accountCredentials = super.getCredentials(info) ;
        boolean flag = super.equals(tokenCredentials,accountCredentials);
        if(flag){
            //如果登录成功，就把当前员工的所有信息放到会话当中
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute("logEmployee",employeeService.queryLogEmployee(Integer.parseInt((String)token.getPrincipal())));
            return true;
        }else{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession();
            session.setAttribute("errormsg","用户名或密码错误");
            return false;
        }
    }
}
