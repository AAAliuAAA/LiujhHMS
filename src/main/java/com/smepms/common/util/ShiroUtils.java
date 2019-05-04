package com.smepms.common.util;

import com.smepms.jobmanagement.pojo.Employee;
import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroUtils {
  public static EmployeeWithBLOBs getLogEmployeeWithBLOBs(){
    Subject subject = SecurityUtils.getSubject();
    Session session = subject.getSession();
    return (EmployeeWithBLOBs)session.getAttribute("logEmployee");
  }

}
