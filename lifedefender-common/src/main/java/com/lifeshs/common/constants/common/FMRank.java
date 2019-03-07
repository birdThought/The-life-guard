package com.lifeshs.common.constants.common;

/**
 *  阴性阳性级别
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年4月20日 下午4:32:49
 */
public enum FMRank {

    /** 正常 */
    Normal(3, "正常"),
    /** 异常 */
    UNUSUAL(4, "偏高"),
    /** 严重 */
    SERIOUS(5, "严重");
    
    private FMRank(Integer value, String remark) {
        this.value = value;
        this.remark = remark;
    }
    
    private Integer value;
    
    private String remark;

    public Integer getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }
}
