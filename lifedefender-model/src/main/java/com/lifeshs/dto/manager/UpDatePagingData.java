package com.lifeshs.dto.manager;

/**
 * 功能描述
 * Created by dengfeng on 2017/7/1 0001.
 */
public class UpDatePagingData extends UpPagingData {
    private String startDate;
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
