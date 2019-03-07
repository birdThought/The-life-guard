package com.lifeshs.common.constants.common.reportAnalysis;

/**
 *  分析报告内容状态枚举
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月29日 下午2:42:58
 */
public enum ContentStatusEnum {

    /** 正常 */
    NORMAL(0),
    /** 异常 */
    UNUSUAL(1);
    
    private int value;
    
    private ContentStatusEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static ContentStatusEnum getStatus(int value) {
        for (ContentStatusEnum c : ContentStatusEnum.values()) {
            if (c.getValue() == value) {
                return c;
            }
        }
        return null;
    }
}
