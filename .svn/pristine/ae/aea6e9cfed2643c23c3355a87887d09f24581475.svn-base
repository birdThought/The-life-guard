package com.lifeshs.utils;

import org.apache.commons.lang3.Validate;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;

/**
 * 版权归
 * 上下文帮助类
 *
 * @author duosheng.mo
 * @DateTime 2016-2-20 下午03:36:02
 */
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext context;
    private static Logger logger = LogManager.getLogger(SpringContextHolder.class);

    @SuppressWarnings("static-access")
    public void setApplicationContext(ApplicationContext context)
            throws BeansException {
        this.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    /**
     * 从静态变量中取得Bean，自动转换为所复制对象的类型
     * @param name
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }

    /**
     * 从静态变量中取得Bean，自动转换为所复制对象的类型
     * @param requiredType
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> requiredType) {
        return (T) context.getBeansOfType(requiredType);
    }

    @Override
    public void destroy() throws Exception {
        logger.debug("释放ApplicationContextUtil.context");
        SpringContextHolder.clearHolder();
    }

    private  static void assertContextInjected(){
        Validate.validState(context!=null,"context 未注入");
    }
    private static void clearHolder() {
        context = null;
    }
}
