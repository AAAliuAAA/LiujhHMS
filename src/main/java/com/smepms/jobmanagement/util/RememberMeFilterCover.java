package com.smepms.jobmanagement.util;

import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import com.smepms.jobmanagement.service.EmployeeService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/1/25.
 */
public class RememberMeFilterCover extends OncePerRequestFilter{
    @Autowired
    private EmployeeService employeeService;
    private final static Logger logger = Logger.getLogger(RememberMeFilterCover.class);

    //在rememberMe中将当前用户信息通过会话再次注入

    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取当前登录用户会话
        Subject subject = SecurityUtils.getSubject();
        //当前如果没有会话，则返回一个新的会话（防止过期）
        Session session = subject.getSession(false);
        //防止在正常登录时也多次访问当前代码，需要只在使用rememberMe进行登录时并且用户当前没有角色，才进入当前代码
        if(subject.isRemembered()&&!subject.isAuthenticated()&&session.getAttribute("logEmployee")==null){
            String employeeWorkId = subject.getPrincipal().toString();
            EmployeeWithBLOBs logEmployee = employeeService.queryLogEmployee(Integer.parseInt(employeeWorkId));
            if(logEmployee==null) return;
            session.setAttribute("logEmployee",logEmployee);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
