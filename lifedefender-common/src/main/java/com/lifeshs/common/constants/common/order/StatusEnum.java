package com.lifeshs.common.constants.common.order;

/**
 *  订单状态枚举类
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年7月12日 下午4:30:52
 */
public enum StatusEnum {

    /** 待付款 */
    PAYABLE(1, "待付款"),
    /** 使用中 */
    USING(2, "使用中"),
    /** 未评价 */
    COMMENTABLE(3, "未评价"),
    /** 已完成 */
    COMPLETED(4, "已完成"),
    /** 待使用 */
    USABLE(5, "待使用"),
    /** 过期 */
    OVERDUE(6, "过期"),
    /** 退款中 */
    REFUNDING(7, "退款中"),
    /** 退款成功 */
    REFUNDED(8, "退款成功"),
    /** 退款失败 */
    REFUND_FAILED(9, "退款失败");

    private int value;
    /** 描述 */
    private String remark;

    private StatusEnum(Integer value, String remark) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public String getRemark() {
        return this.remark;
    }
}
