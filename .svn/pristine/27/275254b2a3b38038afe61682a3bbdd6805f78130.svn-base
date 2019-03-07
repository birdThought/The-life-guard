package com.lifeshs.interceptors;

import com.lifeshs.support.plantform.security.sessionmgr.ClientManager;

import com.lifeshs.utils.ResourceUtil;

import org.apache.commons.lang3.StringUtils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 版权归 TODO 权限拦截器
 *
 * @author duosheng.mo
 * @DateTime 2016年4月20日 下午5:52:46
 */
public class AuthInterceptor implements HandlerInterceptor {
    private List<String> excludeUrls;
    private List<String> excludeStartWithUrls;

    public List<String> getExcludeUrls() {
        return excludeUrls;
    }

    public void setExcludeUrls(List<String> excludeUrls) {
        this.excludeUrls = excludeUrls;
    }

    public List<String> getExcludeStartWithUrls() {
        return excludeStartWithUrls;
    }

    public void setExcludeStartWithUrls(List<String> excludeStartWithUrls) {
        this.excludeStartWithUrls = excludeStartWithUrls;
    }

    /**
     * 在controller后拦截
     */
    public void afterCompletion(HttpServletRequest request,
        HttpServletResponse response, Object object, Exception exception)
        throws Exception {
    }

    public void postHandle(HttpServletRequest request,
        HttpServletResponse response, Object object, ModelAndView modelAndView)
        throws Exception {
    }

    /**
     * 在controller前拦截
     */
    public boolean preHandle(HttpServletRequest request,
        HttpServletResponse response, Object object) throws Exception {
        String requestPath = ResourceUtil.getRequestPath(request); // 用户访问的资源地址

        if (excludeUrls.contains(requestPath)) {
            return true;
        }

        for (String excludeStartWithUrl : excludeStartWithUrls) {
            if (requestPath.startsWith(excludeStartWithUrl)) {
                return true;
            }
        }

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            if ("m".equals(ClientManager.getSessionUser().getLut())) {
                return true;
            }

            //ajax请求暂时不做拦截
            if (StringUtils.equalsIgnoreCase("XMLHttpRequest",
                        request.getHeader("X-Requested-With"))) {
                return true;
            }

            List<String> list = ClientManager.getClient().getPermissions();
            //url权限判定
            System.out.println("requestPath" + requestPath);

            boolean bool = false;

            for (String permission : list) {
                if (requestPath.startsWith(permission)) {
                    bool = true;

                    break;
                }
            }

            if (!bool) {
                response.sendRedirect(request.getContextPath() + "/404");

                return false;
            }

            return true;
        }

        response.sendRedirect(request.getContextPath() + "/login");

        return false;
    }
}
