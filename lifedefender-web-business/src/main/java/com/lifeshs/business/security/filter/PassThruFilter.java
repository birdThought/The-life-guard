package com.lifeshs.business.security.filter;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.PassThruAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PassThruFilter extends PassThruAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request,
        ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        Subject subject = getSubject(httpRequest, httpResponse);

        if (subject.isAuthenticated()) {
            return super.onAccessDenied(request, response);
        } else {
            if (isAjaxRequest(httpRequest)) {
                WebUtils.toHttp(httpResponse).sendError(401);

                return false;
            } else {
                return super.onAccessDenied(request, response);
            }
        }
    }

    private Boolean isAjaxRequest(HttpServletRequest httpRequest) {
        if (StringUtils.equalsIgnoreCase("XMLHttpRequest",
                    httpRequest.getHeader("X-Requested-With"))) {
            return true;
        }

        return false;
    }
}
