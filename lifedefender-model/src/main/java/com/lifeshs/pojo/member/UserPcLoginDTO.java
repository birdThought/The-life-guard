package com.lifeshs.pojo.member;

import javax.persistence.Id;

/**
 * app扫一扫登录PC信息传输层
 *
 * @author
 * @create 2017-04-18 10:13
 **/

public class UserPcLoginDTO {

    /**pc登录token*/
    private String loginToken;

    /**用户token*/
    private String userToken;

    /**用户Id*/
    private int userId;

    @Override
    public String toString() {
        return "UserPcLoginDTO{" +
                "loginToken='" + loginToken + '\'' +
                ", userToken='" + userToken + '\'' +
                ", userId=" + userId +
                '}';
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
