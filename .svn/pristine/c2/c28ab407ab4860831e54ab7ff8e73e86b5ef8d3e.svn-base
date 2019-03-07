package com.lifeshs.pojo.log;/**
 * Created by Administrator on 2017/4/19.
 */

import java.util.Date;

/**
 * 用户登录记录传输类
 *
 * @author wenxian.cai
 * @create 2017-04-19 9:56
 **/

public class LoginLogDTO {
    /**登录时间*/
    private Date loginTime;

    /**用户类型：1_会员，2_机构员工*/
    private Integer userType;

    /**登录用户ID*/
    private Integer userId;

    /**登录用户名*/
    private String userName;

    /**用户的机构ID(只记录机构员工)*/
    private Integer orgId;

    /**终端类型：ios,android,browse*/
    private String terminalType;

    /**登录IP*/
    private String ip;

    @Override
    public String toString() {
        return "LoginLogDTO{" +
                "loginTime=" + loginTime +
                ", userType=" + userType +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", orgId=" + orgId +
                ", terminalType='" + terminalType + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
