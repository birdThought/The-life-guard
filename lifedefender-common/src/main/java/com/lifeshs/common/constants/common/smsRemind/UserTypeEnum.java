package com.lifeshs.common.constants.common.smsRemind;

/**
 * 短信剩余 - 用户类型
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月31日 上午9:12:55
 */
public enum UserTypeEnum {

    MEMBER(1),

    ORG(2);

    private int value;

    private UserTypeEnum(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
