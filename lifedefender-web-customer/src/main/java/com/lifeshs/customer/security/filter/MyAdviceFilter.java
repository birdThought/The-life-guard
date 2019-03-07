package com.lifeshs.customer.security.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;


public class MyAdviceFilter extends AccessControlFilter {

	private String unauthorizedUrl = "/login";

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject.isAuthenticated()) {
			return true;
		}
		return false;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {//表示没有登录，重定向到登录页面
			saveRequest(request);
			WebUtils.issueRedirect(request, response, unauthorizedUrl);
		} else {
			if (StringUtils.hasText(unauthorizedUrl)) {//如果有未授权页面跳转过去
				WebUtils.issueRedirect(request, response, unauthorizedUrl);
			} else {//否则返回401未授权状态码
				WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return false;
	}
}
