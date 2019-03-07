package com.lifeshs.common.constants.common;

/**
 * Created by Administrator on 2017/7/12 0012.
 */
public enum OrderRefundStatus {
    REFUND_COMPLETE((byte)0,"已完成"),
    REFUND_WAIT((byte)1, "待退款"),
    REFUND_AUDITED((byte)2,"已审核"),
    REFUND_FAILURE((byte)3,"退款失败"),
    REFUND_REFUNDING((byte) 4,"退款中")
    ;
    /**
     * 退款状态：0已完成,1待退款,2已审核,3退款失败
     */

    private final byte status;
    private final String value;

    OrderRefundStatus(byte status, String value) {
        this.status = status;
        this.value = value;
    }

    public byte getStatus() {
        return status;
    }

    public String getValue() {
        return value;
    }
}
