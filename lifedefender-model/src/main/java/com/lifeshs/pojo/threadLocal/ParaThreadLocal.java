package com.lifeshs.pojo.threadLocal;

import java.util.Map;

/**
 *  版权归
 *  TODO 解析全部参数
 *  @author duosheng.mo  
 *  @DateTime 2016年4月20日 下午5:29:19
 */
@SuppressWarnings("unchecked")
public class ParaThreadLocal {
	
	/***
	 * parathreadLocal 保存的是每次请求的参数信息 ，如果没有使用
	 * entity 则可以解析全部参数 ，参数范围在request 范围有效
	 */
	
	@SuppressWarnings("rawtypes")
	public static  ThreadLocal para = new ThreadLocal();



	
	
	public static Map<String, Object> getPara() {
			
		return (Map<String, Object>) para.get();
	}

	public static void setPara(Map<String, Object> _para) {
		para.set(_para);
	}
	
	public static void remove(){
		para.remove();
	}
	
	
	
	
	
	

}
