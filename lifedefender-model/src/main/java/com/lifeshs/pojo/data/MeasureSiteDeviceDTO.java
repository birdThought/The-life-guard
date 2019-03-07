package com.lifeshs.pojo.data;

/**
 * 测量点设备
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年5月3日 下午6:13:08
 */
public class MeasureSiteDeviceDTO {

    private String name;

    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "MeasureSiteDevice [name=" + name + ", photo=" + photo + "]";
    }

}
