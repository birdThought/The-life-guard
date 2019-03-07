package com.lifeshs.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.lifeshs.entity.member.TUser;

import java.util.Date;

/**
 * 用户基本信息及档案信息
 * Created by dengfeng on 2017/7/10 0010.
 */
public class FullUserVO {

    //字段没写全,后补
    private int id;
    private String userName;
    private String realName;
    private String photo;
    private float height;    //身高
    private float weight;    //体重
    private int sex;  //性别
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;    //出生日期
    private String userCode;  //用户编号（环信）
    private String mobile; // 联系方式
    private String hasWarning;

    /**
     * 以下为补充字段,editor:wuj
     */
    private int healthProduct;

    private float waist; // 腰围
    private float bust; // 胸围
    private float hip; // 臀围
    private String wbh; // 三围

    /**
     * 以下字段与orgUserId关联,取的是该用户最近的订单 max(t_order.createDate)
     */
    private int serveId; // 服务ID
    private int orderId; // 订单ID
    private String userRemark; // 用户备注
    private String userDiseasesName; // 病种名

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getServeId() {
        return serveId;
    }

    public void setServeId(int serveId) {
        this.serveId = serveId;
    }

    public String getWbh() {
        return wbh;
    }

    public void setWbh() {
        wbh = "" + waist + "-" + bust + "-" + hip;
    }

    public int getHealthProduct() {
        return healthProduct;
    }

    public String getUserDiseasesName() {
        return userDiseasesName;
    }

    public void setUserDiseasesName(String userDiseasesName) {
        this.userDiseasesName = userDiseasesName;
    }

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
    }

    public float getBust() {
        return bust;
    }

    public void setBust(float bust) {
        this.bust = bust;
    }

    public float getHip() {
        return hip;
    }

    public void setHip(float hip) {
        this.hip = hip;
    }



    public void setHealthProduct(int healthProduct) {
        this.healthProduct = healthProduct;
    }

    public String getHasWarning() {
        return hasWarning;
    }

    public void setHasWarning(String hasWarning) {
        this.hasWarning = hasWarning;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
