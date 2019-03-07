package com.lifeshs.common.exception.common;

import com.lifeshs.common.exception.code.ErrorCodeEnum;

/**
 * 参数异常
 * <p>格式不正确，缺少
 * 
 * @author zhiguo.lin
 * @DateTime 2016年9月9日 上午10:35:20
 */
public class ParamException extends BaseException {

    /**  描述  */    
    private static final long serialVersionUID = 6498651310846801194L;

    public ParamException(String message, int errorCode) {
        super(message, errorCode);
    }

    public ParamException(String message, ErrorCodeEnum code) {
        super(message, code.value());
    }
    
    /**    
     * 默认错误码为400
     * @param message    
     */ 
    public ParamException(String message) {
        super(message, ErrorCodeEnum.REQUEST.value());
    }
}
