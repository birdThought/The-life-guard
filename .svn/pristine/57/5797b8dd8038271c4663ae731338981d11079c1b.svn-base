package com.lifeshs.customer.security.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 在验证权限时，如果是超管则给予权限，无需验证permission
 * Created by dengfeng on 2018/7/2 0002.
 */
public class MyPermissionFilter extends PermissionsAuthorizationFilter {

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

//        Subject subject = getSubject(request, response);
//        if(subject.hasRole("super"))
//            return true;

        return super.isAccessAllowed(request, response, mappedValue);
    }
}
