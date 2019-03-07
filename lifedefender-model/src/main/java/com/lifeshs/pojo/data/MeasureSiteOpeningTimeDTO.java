package com.lifeshs.pojo.data;

/**
 *  测量点开放时间
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月5日 下午4:27:27
 */
public class MeasureSiteOpeningTimeDTO {

    /** 开启时间 */
    private String startTime;
    /** 结束时间 */
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "MeasureSiteOpeningTimeDTO [startTime=" + startTime + ", endTime=" + endTime + "]";
    }

}
