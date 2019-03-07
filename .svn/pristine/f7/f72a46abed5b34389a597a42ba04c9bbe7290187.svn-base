package com.lifeshs.customer.aop;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Component
public class CustomerAOP {

    private static final Logger logger = Logger.getLogger(CustomerAOP.class);
    
    /** 客服系统所有方法 */
    @Pointcut(value = "execution(public * com.lifeshs.customer.controller..*.*(..))")
    public void all() {
    }
    
    @Around(value = "all()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long s = System.currentTimeMillis();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        
        Signature signature =  point.getSignature(); 
        Class<?> returnType = ((MethodSignature) signature).getReturnType(); 

        logger.debug("时间戳:" + s);
        logger.debug("方法名:" + methodName);
        
        if (returnType.equals(ModelAndView.class)) {
            
        } else {
            List<Object> requestParam = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof String) {
                    String param = (String) args[i];
                    // URL转义
                    param = URLDecoder.decode(param, "UTF-8");
                    args[i] = param;
                }
                requestParam.add(args[i]);
            }
            logger.debug("param:" + requestParam);
        }
        
        /** 执行原来的任务 */
        Object returnObj = point.proceed(args);
        
        long e = System.currentTimeMillis();
        logger.debug("内容体:" + returnObj);
        logger.debug("总耗时:" + (e - s) + "毫秒\n");
        
        return returnObj;
    }
}
