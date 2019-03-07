package com.lifeshs.pojo.member;

import java.util.Date;

/**
 * Created by wenxian.cai on 2017/4/11.
 * 第三方登录信息传输类
 */
public class UserOauthDTO {

    /**主键*/
    private int Id;

    /**用户ID*/
    private int userId;

    /**认证方式*/
    private String oauthType;

    /**openId*/
    private String openId;

    /**uId*/
    private String uId;

    /**创建日期*/
    private Date createDate;

    @Override
    public String toString() {
        return "UserOauthDTO{" +
                "Id=" + Id +
                ", userId=" + userId +
                ", oauthType='" + oauthType + '\'' +
                ", openId='" + openId + '\'' +
                ", uId='" + uId + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOauthType() {
        return oauthType;
    }

    public void setOauthType(String oauthType) {
        this.oauthType = oauthType;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
