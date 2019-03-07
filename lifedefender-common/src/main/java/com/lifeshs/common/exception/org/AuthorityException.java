package com.lifeshs.common.exception.org;

/**
 *  权限异常
 *  @author yuhang.weng  
 *  @DateTime 2016年9月10日 上午10:11:47
 */
public class AuthorityException extends RuntimeException {

	/**  描述  */    
	private static final long serialVersionUID = 3029839503560798737L;

	private String message;
	
	public AuthorityException(String message){
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
