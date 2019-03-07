package com.lifeshs.common.constants.common.reportAnalysis;

/**
 *  分析报告申请人类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月10日 下午1:55:14
 */
public enum RequestorTypeEnum {

    /** 会员 */
    MEMBER(1),
    /** 渠道商 */
    BUSINESS(2);
    
    private int value;
    
    private RequestorTypeEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
