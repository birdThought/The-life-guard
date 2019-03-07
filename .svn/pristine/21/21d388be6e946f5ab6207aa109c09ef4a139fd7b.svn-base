package com.lifeshs.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *  版权归
 *  上下文帮助类
 *  @author duosheng.mo  
 *  @DateTime 2016-2-20 下午03:36:02
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	
	@SuppressWarnings("static-access")
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}
}
