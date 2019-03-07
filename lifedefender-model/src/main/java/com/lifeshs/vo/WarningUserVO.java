package com.lifeshs.vo;

import java.util.Date;

/**
 * 异常用户信息
 * Created by dengfeng on 2017/6/23 0023.
 */
public class WarningUserVO {

    private int userId;		//用户ID
    private String name;		//姓名
    private String photo;		//头像
    private String hasWarning;	//int	是否有异常:没有_0,有：设备的值与和
    //@JSONField(format="yyyy-MM-dd HH:mm")
    private Date measureTime;	//测量时间

    // 添加订单ID
    private int orderId;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHasWarning() {
        return hasWarning;
    }

    public void setHasWarning(String hasWarning) {
        this.hasWarning = hasWarning;
    }

    public Date getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(Date measureTime) {
        this.measureTime = measureTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
