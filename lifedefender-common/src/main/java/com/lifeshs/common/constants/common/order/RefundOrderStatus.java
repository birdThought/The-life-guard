package com.lifeshs.common.constants.common.order;

/**
 * 退款信息装态
 * Created by Administrator on 2017/7/14.
 */
public enum RefundOrderStatus {
    COMPLETE(0, "已完成"),

    TO_REFUND(1, "待退款"),

    AUDITED(2, "已审核"),

    FAIL(3, "退款失败"),

    REFUNDIND(4, "退款中");

    private Integer status;

    private String remark;

    private RefundOrderStatus(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getRemark() {
        return this.remark;
    }
}
