package com.lifeshs.customer.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.ResourceUtil;

public class CustomerExceptionHandler implements HandlerExceptionResolver {

    private final Logger logger = Logger.getLogger("E");
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
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

                mv.getModel().put("error", ex.getMessage());
                mv.setViewName("common/error/500");
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
}
