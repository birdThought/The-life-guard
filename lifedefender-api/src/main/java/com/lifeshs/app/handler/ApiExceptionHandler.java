package com.lifeshs.app.handler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.exception.common.BaseException;
import com.lifeshs.utils.ParserParaUtil;
import com.lifeshs.utils.ResourceUtil;

/**
 *  api的全局异常
 *  <p>有关于api的全局异常，目前只对两种类型的异常进行出来。当这个异常继承了BaseException的时候，就会根据异常的具体内容，将信息反馈给用户。另外的，没有继承BaseException统一提示用户服务器异常。
 *  <p>这个文件会使用log4j记录异常日志，保存在error.log中
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月5日 下午3:01:24
 */
public class ApiExceptionHandler implements HandlerExceptionResolver {

    private final Logger logger = Logger.getLogger("E");
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        
        /** 记录错误 */
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        logger.error("错误信息:" + sw.toString());
        
        /** 请求的url */
        String url = ResourceUtil.getRequestPath(request);
        logger.error(String.format("请求url(%s)捕获异常:%s", url, ex.getMessage()));
        logger.error(String.format("请求参数为:%s", ParserParaUtil.getParams(request)));
        
        String message = "";
        String status = "";
        /**
         * 这里只处理继承了BaseException的异常，其余异常都当作服务器异常处理
         */
        if (ex instanceof BaseException) {
            BaseException be = (BaseException) ex;
            message = be.getMessage();
            status = String.valueOf(be.getCode());
        } else {
            message = "服务器异常";
            status = "500";
        }
        
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(Normal.MESSAGE, message);
        resultMap.put(Normal.STATUS, status);
        FastJsonJsonView view = new FastJsonJsonView();
        view.setAttributesMap(resultMap);
        ModelAndView mv = new ModelAndView();
        mv.setView(view);
        return mv;
    }

}
