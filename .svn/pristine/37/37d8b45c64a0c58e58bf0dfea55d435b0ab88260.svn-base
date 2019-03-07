package com.lifeshs.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

import lombok.Data;

/**
 * 功能描述
 * Created by dengfeng on 2017/7/10 0010.
 */
public @Data class RefundOrderVO  {
    private int id;
    private String orderNumber;    //订单编号
    private String realName;       //下订单人姓名
    private String mobile;         //联系电话
    private String address;         //上门地址
    private String subject;         //服务名称
    private int number;             //购买数量
    private Double charge;             //支付金额
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date createDate;        //下单时间
    private String cause;            //退款原因
    @JSONField(format="yyyy-MM-dd HH:mm")
    private Date refundTime;        //退款申请时间
//    private Integer status;         //状态
    private short refundStatus;     //退款状态 ：0已完成,1待退款,2已审核,3退款失败

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }*/

}
