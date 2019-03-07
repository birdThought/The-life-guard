package com.lifeshs.support.plantform.handler;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

import com.lifeshs.pojo.client.LoginUser;

import com.lifeshs.support.plantform.security.sessionmgr.ClientManager;

import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.ResourceUtil;

import org.apache.commons.lang3.StringUtils;

import org.apache.log4j.Logger;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 版权归
 * TODO 类说明
 *
 * @author zhiguo.lin
 * @DateTime 2016年9月9日 上午10:30:04
 */
public class SystemExceptionHandler implements HandlerExceptionResolver {
    private final Logger logger = Logger.getLogger("E");

    /**
     * 统一处理异常
     *
     * @author zhiguo.lin
     * @DateTime 2016年9月9日 上午10:32:06
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
        HttpServletResponse response, Object handler, Exception ex) {
        /** 请求的url */
        String url = ResourceUtil.getRequestPath(request);

        ModelAndView mv = new ModelAndView();

        /** 记录错误 */
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        com.alibaba.fastjson.JSONObject errorObject = new com.alibaba.fastjson.JSONObject();
        errorObject.put("url", url);
        errorObject.put("msg", ex.getMessage());
        errorObject.put("params", ParserParaUtil.getParams(request));
        errorObject.put("timeStamp", System.currentTimeMillis());
        errorObject.put("stackTrace", sw.toString());

        logger.error(com.alibaba.fastjson.JSONObject.toJSONString(errorObject,
                SerializerFeature.WriteMapNullValue));

        try {
            // ajax请求
            if (isAjaxRequest(request)) {
                FastJsonJsonView view = new FastJsonJsonView();
                String errorMsg = "";

                if ((ex.getMessage() == null) || "".equals(ex.getMessage())) {
                    errorMsg = "请求失败，请重试！";
                } else {
                    errorMsg = ex.getMessage();
                }

                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("code", "10001");
                attributes.put("msg", errorMsg);
                attributes.put("success", false);
                view.setAttributesMap(attributes);
                mv.setView(view);

                return mv;
            }

            LoginUser loginUser = ClientManager.getSessionUser();
            mv.getModel().put("error", ex.getMessage());

            if ((loginUser == null) || (loginUser.getOrgId() == null)) {
                // 普通用户报错页面跳转
                mv.setViewName("common/500");
            } else {
                // 企业用户报错页面跳转
                mv.setViewName("com/QYPart/error/error");
            }

            return mv;
        } catch (Exception e) {
            logger.error("[PrintWriter调用getWriter方法报错]" + e.getMessage());

            return null;
        }
    }

    /**
     * @param httpRequest
     * @return
     * @author zhiguo.lin
     * @DateTime 2016年9月9日 上午10:31:29
     * @serverComment 判断是否是ajax请求
     */
    private Boolean isAjaxRequest(HttpServletRequest httpRequest) {
        if (StringUtils.equalsIgnoreCase("XMLHttpRequest",
                    httpRequest.getHeader("X-Requested-With"))) {
            return true;
        }

        return false;
    }

    /**
     * 判断是否是移动端请求
     *
     * @param request
     * @return
     * @author yuhang.weng
     * @DateTime 2016年9月10日 下午2:02:57
     */
    @SuppressWarnings("unused")
    private Boolean isAppRequest(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");

        if (userAgent.contains("Android") || userAgent.contains("iPhone") ||
                userAgent.contains("iPad")) {
            return true;
        }

        return false;
    }
}
