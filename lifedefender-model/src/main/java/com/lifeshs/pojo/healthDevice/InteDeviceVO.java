package com.lifeshs.pojo.healthDevice;

/**
 *  智能设备页面数据展示类
 *  @author yuhang.weng  
 *  @DateTime 2017年2月14日 上午10:29:21
 */
public class InteDeviceVO {

    /** 设备名 */
    private String name;

    /** 图片名称 如：feihuoyi.png */
    private String image;

    /** 购买URL链接 */
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "InteDeviceVO [name=" + name + ", image=" + image + ", url=" + url + "]";
    }

}
