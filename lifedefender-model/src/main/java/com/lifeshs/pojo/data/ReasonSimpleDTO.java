package com.lifeshs.pojo.data;

import com.lifeshs.common.constants.common.HealthRank;

/**
 *  形成原因精简版
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月15日 上午11:45:33
 */
public class ReasonSimpleDTO {

    /** 健康参数二进制值 */
    private long healthParamBinaryValue;
    /** 状态值 */
    private HealthRank status;

    public long getHealthParamBinaryValue() {
        return healthParamBinaryValue;
    }

    public void setHealthParamBinaryValue(long healthParamBinaryValue) {
        this.healthParamBinaryValue = healthParamBinaryValue;
    }

    public HealthRank getStatus() {
        return status;
    }

    public void setStatus(HealthRank status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReasonSimpleDTO [healthParamBinaryValue=" + healthParamBinaryValue + ", status=" + status + "]";
    }

}
