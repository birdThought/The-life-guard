package com.lifeshs.pojo.org.profit;

import java.util.Date;

import lombok.Data;

/**
 *  收益
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月6日 下午3:31:39
 */
public @Data class ProfitDTO {

    /** 收益 */
    private Double earning;
    
    /** 订单数量 */
    private int orderCount;
    
    /** 日期 */
    private Date date;
}
