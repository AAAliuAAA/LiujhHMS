package com.smepms.common.filters.shiro;

import com.smepms.jobmanagement.service.EmployeeService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 重写当前shiro默认的表单登录类，使用ajax请求进行登录反馈
 */
public class LocalFormAuthenticationFilter extends FormAuthenticationFilter{
  @Autowired
  private EmployeeService employeeService;


  private static final Logger log = Logger.getLogger(LocalFormAuthenticationFilter.class);

  //当访问被拒绝时 不需要进行操作
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    if (this.isLoginRequest(request, response)) {
      if (this.isLoginSubmission(request, response)) {
        if (log.isTraceEnabled()) {
          log.trace("Login submission detected.  Attempting to execute login.");
        }
        return this.executeLogin(request, response);
      } else {
        if (log.isTraceEnabled()) {
          log.trace("Login page view.");
        }

        return true;
      }
    } else {
      if (log.isTraceEnabled()) {
        log.trace("Attempting to access a path which requires authentication.  Forwarding to the Authentication url [" + this.getLoginUrl() + "]");
      }

      this.saveRequestAndRedirectToLogin(request, response);
      return false;
    }
  }

  /**
   * 当登录成功时
   */
  protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
    //如果登录成功，就把当前员工的所有信息放到会话当中
    Session session = subject.getSession();
    session.setAttribute("logEmployee",employeeService.queryLogEmployee(Integer.parseInt((String)token.getPrincipal())));
    this.issueSuccessRedirect(request, response);
//    if (!"XMLHttpRequest".equalsIgnoreCase(httpServletRequest
//        .getHeader("X-Requested-With"))) {// 不是ajax请求
//      issueSuccessRedirect(request, response);
//    }
    return false;
  }

  /**
   * 当登录失败时
   * @param token
   * @param e
   * @param request
   * @param response
   * @return
   */
  protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
    if (log.isDebugEnabled()) {
      log.debug("Authentication exception", e);
    }
    try {
      //将错误信息防到request作用域即可，刷新就刷新吧，以后再考虑全程使用ajax
      if (e instanceof IncorrectCredentialsException) {
        request.setAttribute("errormsg", "密码错误");
      } else if (e instanceof UnknownAccountException) {
        request.setAttribute("errormsg", "用户名不存在");
      } else if (e instanceof LockedAccountException) {
        request.setAttribute("errormsg", "账户已被锁定");
      } else {
        request.setAttribute("errormsg", "未知错误");
      }
    }catch (Exception e1){
//      e.printStackTrace(); 当需要在控制台打映登录错误信息时，解开这个注释
    }
    this.setFailureAttribute(request, e);
    return true;
  }
}
