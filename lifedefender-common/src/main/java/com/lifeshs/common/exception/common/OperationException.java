package com.lifeshs.common.exception.common;

import com.lifeshs.common.exception.code.ErrorCodeEnum;

/**
 *  操作异常
 *  <p> 添加失败，删除失败，更新失败，查找不到
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月17日 下午4:38:41
 */
public class OperationException extends BaseException {

    /**  描述  */    
    private static final long serialVersionUID = -4880668902344030291L;

    public OperationException(String message, Integer code) {
        super(message, code);
    }
    
    public OperationException(String message, ErrorCodeEnum error) {
        super(message, error.value());
    }
}
