package com.smepms.jobmanagement.realm;

import com.smepms.jobmanagement.pojo.EmployeeWithBLOBs;
import com.smepms.jobmanagement.service.SysEmployeeService;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;


/**
 * Created by Administrator on 2018/1/17.
 */
//让Spring来管理这个Realm
@Component
public class DbRealm extends AuthorizingRealm{
    private static final Logger logger = Logger.getLogger(DbRealm.class);
    //提供getter setter，让Spring来进行注入，让shiro来控制代码执行顺序
    @Autowired
    private SysEmployeeService sysEmployeeService;


    //通过过滤器将页面的提交数据拦截到这个方法体中
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录的用户名
        String employeeWorkId = (String)principalCollection.getPrimaryPrincipal();
        //获得该用户拥有的角色集合，权限集合
        Map<String,Set<String>> map = sysEmployeeService.authorized(Integer.parseInt(employeeWorkId));
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo() ;
        //添加所有的角色名 set 集合
        info.addRoles(map.get("roles"));
        //添加所有权限名 set集合
        info.addStringPermissions(map.get("functions"));
        return info;
    }

    /**
     * 密码验证,令牌验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String employeeWorkIdString = (String) authenticationToken.getPrincipal();
        String employeePassword = new String((char[])authenticationToken.getCredentials());
        //获取到用户名和密码后进行登录查询
        //先查询是否有当前用户
        EmployeeWithBLOBs employeeLogin = sysEmployeeService.certification(Integer.parseInt(employeeWorkIdString));
        if(employeeLogin==null){
            throw new UnknownAccountException("工号["+employeeWorkIdString+"]");
        }
        return new SimpleAuthenticationInfo(employeeWorkIdString,employeeLogin.getEmployeePassword(),this.getName());
    }
}
