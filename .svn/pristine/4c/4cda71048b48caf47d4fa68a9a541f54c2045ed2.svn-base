package com.lifeshs.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XuZhanSi on 2016/12/27 0027.
 */
public class ServletUtil {
    /**
     * 获取参数键值对
     *
     * @param request
     * @return
     */
    public static Map<String, Object> getParamsFromRequest(HttpServletRequest request) {
        Enumeration e = request.getParameterNames();
        Map<String, Object> result = new HashMap<String, Object>();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            Object val = request.getParameter(key);
            result.put(key, val);
        }
        return result;
    }

    /**
     * 获取当前线程的httpservlet
     * @return
     */
    public static HttpServletRequest getCurrentServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取当前用户IP(注：在使用反向代理时候getRemoteAddr不生效，要使用nginx带过来的远程ip头部信息获取)
     * @return
     *
     */
    public static String getHost(){
        //return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getRemoteAddr();
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("x-real-ip");
    }

    /**
     * 获取IP
     * @author wenxian.cai
     * @DateTime 2017-04-01
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(!org.springframework.util.StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(!org.springframework.util.StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
