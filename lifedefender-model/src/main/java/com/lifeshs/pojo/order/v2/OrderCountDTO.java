package com.lifeshs.pojo.order.v2;

import lombok.Data;

/**
 *  订单统计
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月29日 上午9:54:21
 */
public @Data class OrderCountDTO {

    /** 待评价 */
    private int toBePaid;
    /** 待使用 */
    private int toBeUsed;
    /** 未评价 */
    private int unComment;
    /** 退款 */
    private int refund;
}
