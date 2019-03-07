package com.lifeshs.support.plantform.security.token;

import org.apache.shiro.authc.UsernamePasswordToken;


/**
 * Created on 2014/11/11.
 */
public class CustomUsernamePasswordToken extends UsernamePasswordToken {
    /**  描述  */
    private static final long serialVersionUID = 1L;

    /**用于存储用户输入的校验码*/
    private String validCode;

    /**登录用户类型 loginUserType
     * m:普通会员、o:组织用户 */
    private String lut;
    private String encryPsw;

    /**
     * 构造函数
     * @param username 用户名
     * @param password 密码
     * @param rememberMe 是否记住
     * @param host 主机
     * @param validCode 验证码
     * @param lut  登录用户类型(loginUserType)
     */
    public CustomUsernamePasswordToken(String username, char[] password,
        boolean rememberMe, String host, String validCode, String lut) {
        //调用父类的构造函数
        super(username, password, rememberMe, host);
        this.validCode = validCode;
        this.lut = lut;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getLut() {
        return lut;
    }

    public String getEncryPsw() {
        return encryPsw;
    }

    public void setEncryPsw(String encryPsw) {
        this.encryPsw = encryPsw;
    }

    public void setLut(String lut) {
        this.lut = lut;
    }
}
