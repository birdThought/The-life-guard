package com.lifeshs.business.security.credential;


import com.lifeshs.utils.MD5Utils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import com.lifeshs.business.security.token.UserToken;


/**
 *
 * author: wenxian.cai
 * date: 2017/9/25 11:12
 */
public class CustomCredentials extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token,
        AuthenticationInfo info) {
        UserToken userToken = (UserToken) token;
        String pwd = MD5Utils.encryptPassword(String.valueOf(
                    userToken.getPassword()));
        boolean matches = equals(pwd, getCredentials(info));

        return matches;
    }
}
