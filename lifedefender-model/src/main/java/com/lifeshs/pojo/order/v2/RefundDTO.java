package com.lifeshs.pojo.order.v2;

import java.util.Date;

import lombok.Data;

/**
 *  退款订单信息
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年7月12日 下午6:10:51
 */
public @Data class RefundDTO {

    private int id;
    private String orderNumber;
    private String cause;
    private Date refundTime;
    private short status;
    private int auditorId;
    private Date completeTime;
}
