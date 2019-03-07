package com.lifeshs.common.model.map;

/**
 * 版权归
 * TODO 天气实体类
 *
 * @author yuhang.weng
 * @DateTime 2016年7月5日 下午2:34:13
 */
public class Weather {

    /**
     * 最低气温
     */
    private float min;

    /**
     * 最高气温
     */
    private float max;

    /**
     * 天气描述
     */
    private String content;

    /**
     * 天气对应的图片,图片为1.gif
     */
    private String log;

    /**
     * pm2.5
     */
    private float pm;

    /**
     * 城市名
     */
    private String cityName;

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public float getPm() {
        return pm;
    }

    public void setPm(float pm) {
        this.pm = pm;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
