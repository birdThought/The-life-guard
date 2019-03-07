package com.lifeshs.pojo.healthDevice;

/**
 * 健康包数据传输类
 * 
 * @author yuhang.weng
 * @DateTime 2017年2月14日 上午9:55:18
 */
public class HealthPackageDTO extends HealthPackageBaseDTO {

    /** 状态 0_已添加，1_未添加 */
    private int status;

    /** 图片名称 如：feihuoyi.png */
    private String img;
    
    /** 无圆框图片 大小  */
    private String img_no_circle;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg_no_circle() {
        return img_no_circle;
    }

    public void setImg_no_circle(String img_no_circle) {
        this.img_no_circle = img_no_circle;
    }

    @Override
    public String toString() {
        return "HealthPackageDTO [status=" + status + ", img=" + img + ", img_no_circle=" + img_no_circle + "]";
    }
}
