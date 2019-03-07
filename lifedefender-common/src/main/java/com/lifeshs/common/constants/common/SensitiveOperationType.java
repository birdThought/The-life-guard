package com.lifeshs.common.constants.common;

/**
 * 敏感操作类型
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年4月7日 上午11:01:10
 */
public enum SensitiveOperationType {

    /** 修改密码 */
    MODIFY_PASSWORD(1, "modifyPassword");

    private int value;

    private String remark;

    private SensitiveOperationType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }
}
