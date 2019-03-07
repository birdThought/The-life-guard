package com.lifeshs.dto.manager.order;

import com.lifeshs.vo.OrderStatisticsByDayVO;
import lombok.Data;

import java.util.List;

/**
 * 按月统计订单量和金额的返回数据
 * Created by dengfeng on 2017/7/8 0008.
 */
public @Data class GetPaymentByMonthData {
    private Double profit;   //收益,单位(分)
    private Double payment;   //结款,单位(分)
    private List<OrderStatisticsByDayVO> statistics;  //按日统计数据

    /*public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public long getPayment() {
        return payment;
    }

    public void setPayment(long payment) {
        this.payment = payment;
    }

    public List<OrderStatisticsByDayVO> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<OrderStatisticsByDayVO> statistics) {
        this.statistics = statistics;
    }*/
}
