package com.lifeshs.po.order;

import com.lifeshs.vo.order.customer.OrderVO;

/**
 * 
 *  订单与vip订单
 *  @author NaN
 *  @version 1.0
 *  @DateTime 2018年12月6日 上午11:10:06
 */
public class OrderWithVipPO extends OrderVO{

    private String orderType;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    
}
