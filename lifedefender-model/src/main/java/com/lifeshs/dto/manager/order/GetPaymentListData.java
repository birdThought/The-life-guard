package com.lifeshs.dto.manager.order;

import java.util.Date;

/**
 * 获取月结算清单的数据
 * Created by dengfeng on 2017/7/11 0011.
 */
public class GetPaymentListData {
    private int id;            //订单ID
    private Date createDate;   //下单时间
    private int charge;         //金额
    private int profitShare;   //费率
    private int orgIncome;      //实收
    private int userId;         //会员ID
    private String realName;    //会员姓名
    private String photo;        //会员图像
    private String subject;     //服务名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(int profitShare) {
        this.profitShare = profitShare;
    }

    public int getOrgIncome() {
        return orgIncome;
    }

    public void setOrgIncome(int orgIncome) {
        this.orgIncome = orgIncome;
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
}
