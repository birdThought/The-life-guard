package com.lifeshs.business.security.token;

import org.apache.shiro.authc.UsernamePasswordToken;


/**
 *
 * author: wenxian.cai
 * date: 2017/9/25 11:13
 */
public class UserToken extends UsernamePasswordToken {
    private String validCode;

    public UserToken(String username, String password, boolean rememberMe,
        String validCode, String host) {
        super.setUsername(username);
        super.setPassword(password.toCharArray());
        super.setRememberMe(rememberMe);
        this.validCode = validCode;
        super.setHost(host);
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
