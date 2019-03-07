package com.lifeshs.dto.manager.order;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 查询订单列表的数据返回
 * Created by dengfeng on 2017/6/28 0028.
 */
public class GetOrderListData {
    private int id;			//订单ID
    private int status;		//订单状态，状态：待付款_1, 付款失败_2，有效_3，已完成_4，已退款_5，已取消_6
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date orderTime;		//订单时间
    private String price;			//价格
    private String charge;          //总价
    private int number;			//数量
    private int userId;			//会员ID
    private String realName;			//会员姓名
    private String photo;			//会员图像
    private String subject;		//服务名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
