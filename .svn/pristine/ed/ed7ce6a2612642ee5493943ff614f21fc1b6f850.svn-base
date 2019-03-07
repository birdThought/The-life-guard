package com.lifeshs.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *  版权归
 *  TODO 字符集拦截器
 *  @author duosheng.mo
 *  @DateTime 2016年4月20日 下午5:52:21
 */
public class EncodingInterceptor implements HandlerInterceptor {
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
        //		System.out.println("拦截器获取返回参数结果："+modelAndView.getModel());
    }

    /**
     * 在controller前拦截
     */
    public boolean preHandle(HttpServletRequest request,
        HttpServletResponse response, Object object) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        return true;
    }
}
