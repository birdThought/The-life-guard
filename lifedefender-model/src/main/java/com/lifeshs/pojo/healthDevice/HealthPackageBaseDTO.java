package com.lifeshs.pojo.healthDevice;

/**
 * 健康包数据传输类（基础）
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月14日 上午10:11:32
 */
public class HealthPackageBaseDTO {
    /** 中文名字 */
    private String name_cn;

    /** 英文名字 */
    private String name_en;

    /** 设备值 */
    private Integer deviceValue;

    /** 描述文字 */
    private String about;

    /** 链接地址 */
    private String shopUrl;

    public String getName_cn() {
        return name_cn;
    }

    public void setName_cn(String name_cn) {
        this.name_cn = name_cn;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public Integer getDeviceValue() {
        return deviceValue;
    }

    public void setDeviceValue(Integer deviceValue) {
        this.deviceValue = deviceValue;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    @Override
    public String toString() {
        return "HealthPackageBaseDTO [name_cn=" + name_cn + ", name_en=" + name_en + ", deviceValue=" + deviceValue
                + ", about=" + about + ", shopUrl=" + shopUrl + "]";
    }
}
