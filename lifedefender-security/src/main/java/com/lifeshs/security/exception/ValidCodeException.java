package com.lifeshs.security.exception;

import org.apache.shiro.authc.AuthenticationException;

/**
 *  版权归
 *  TODO 自定义异常
 *  @author duosheng.mo  
 *  @DateTime 2016年4月29日 下午2:26:19
 */
public class ValidCodeException extends AuthenticationException {
    /**  描述  */    
	private static final long serialVersionUID = 1L;
	

	public ValidCodeException(String msg){
        super(msg);
    }
}
