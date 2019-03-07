package com.lifeshs.common.constants.common;

/**
 * 订单状态
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年5月2日 上午11:11:39
 */
public enum OrderStatus {

    PAYABLE(1, "待付款"),

    PAY_FAILED(2, "支付失败"),

    VALID(3, "有效的"),

    COMPLETED(4, "已完成"),

    CANCELED(6, "已取消"),

    REFUNDING(7, "退款中"),

    REFUNDED(8, "退款成功");

    private Integer status;

    private String remark;

    private OrderStatus(Integer status, String remark) {
        this.status = status;
        this.remark = remark;
    }

    public Integer getStatus() {
        return this.status;
    }

    public String getRemark() {
        return this.remark;
    }

    /**
     * 遍历枚举类方法
     *
     * @author wuj
     * @since 2017年7月12日 16:59:11
     * @param status
     * @return
     */
    public static OrderStatus findStatus(Integer status) {
        OrderStatus[] list = OrderStatus.values();
        for (OrderStatus orderStatus : list) {
            if (status == orderStatus.getStatus()) {
                return orderStatus;
            }
        }

        return null;
    }
}
