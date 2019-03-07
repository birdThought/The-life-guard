package com.lifeshs.pojo.org.group;

/**
 * 群组课程时间
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年2月28日 下午2:49:41
 */
public class CourseTimeDTO {

    /** 星期设定,从低到高代表星期一到星期日,开启_1,关闭_0' */
    private String weeks;

    /** 开课时间 */
    private String startTime;

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "CourseTimeDTO [weeks=" + weeks + ", startTime=" + startTime + "]";
    }

}
