package com.lifeshs.common.exception.common;

/**
 *  基础异常
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月1日 下午1:47:35
 */
public class BaseException extends Exception {

    
    /**  描述  */    
    private static final long serialVersionUID = 3720856053872693250L;
    private String message;
    private int code;

    public BaseException(String message, int code) {
        super(message);
        this.message = message;
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *  获取相应的错误码
     *  @author yuhang.weng 
     *  @DateTime 2017年9月1日 下午1:46:09
     *
     *  @return
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
