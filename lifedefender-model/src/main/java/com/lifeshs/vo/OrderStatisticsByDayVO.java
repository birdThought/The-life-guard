package com.lifeshs.vo;

/**
 * 按日统计订单量和金额
 * Created by dengfeng on 2017/7/8 0008.
 */
public class OrderStatisticsByDayVO {
    private String statisticsday;   //统计日期yyyy-MM-dd
    private int statisticsCount;   //统计订单数
    private long statisticsTotal;  //统计订单金额

    public String getStatisticsday() {
        return statisticsday;
    }

    public void setStatisticsday(String statisticsday) {
        this.statisticsday = statisticsday;
    }

    public int getStatisticsCount() {
        return statisticsCount;
    }

    public void setStatisticsCount(int statisticsCount) {
        this.statisticsCount = statisticsCount;
    }

    public long getStatisticsTotal() {
        return statisticsTotal;
    }

    public void setStatisticsTotal(long statisticsTotal) {
        this.statisticsTotal = statisticsTotal;
    }
}
