package com.lifeshs.common.exception.code;

/**
 *  错误码类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月16日 下午3:31:43
 */
public enum TypeEnum {

    WARNING(0, "警告性错误,可以跳过"),

    SERIOUS(1, "严重性错误，需要终止目前的操作");

    private String remark;

    private int value;

    private TypeEnum(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public String getRemark() {
        return remark;
    }

    public int getValue() {
        return value;
    }

}