package com.lifeshs.vo;

/**
 * 统计出的订单数和合计金额
 * Created by dengfeng on 2017/7/4 0004.
 */
public class OrderCountAndChargeVO {
    private int orderNumber;
    private int charge;

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }
}
