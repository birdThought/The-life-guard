package com.lifeshs.security.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.security.token.CustomUsernamePasswordToken;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.PasswordUtil;

/**
 *  版权归
 *  TODO 类说明
 *  @author duosheng.mo
 *  @DateTime 2016年4月29日 下午1:59:35
 */
public class CustomSimpleCredentialsMatcher extends SimpleCredentialsMatcher {

    private Cache<String, AtomicInteger> passwordRetryCache;

    private PasswordUtil passwordHelper;
    
    @Autowired
    private ICacheService cacheService;

    public CustomSimpleCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    public void setPasswordHelper(PasswordUtil passwordHelper) {
        this.passwordHelper = passwordHelper;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        CustomUsernamePasswordToken loginToken = (CustomUsernamePasswordToken) token;

        String username = (String) token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        } else {

//          //校验码判断逻辑
//            //取得用户输入的校验码
//            String userInputValidCode = loginToken.getValidCode();
//            //取得真实的正确校验码
//            String realRightValidCode = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_KEY_OF_RAND_CODE);
//            System.out.println("id:" + SecurityUtils.getSubject().getSession().getId());
//            System.out.println("realRightValidCode:" + realRightValidCode);
//            //
//            if (null == userInputValidCode || !userInputValidCode.equalsIgnoreCase(realRightValidCode)) {
//                throw new ValidCodeException("验证码输入不正确");
//            }
        }
        if (retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }
        String password = String.valueOf(loginToken.getPassword());
        if (!password.equals(cacheService.getCacheValue(CacheType.OAUTH_CACHE, "oauth" + username))) {
            password = MD5Utils.encryptPassword(password);
        }
        String userInputLoginPass = password;
//        String userInputLoginPass = MD5Utils.encryptPassword(String.valueOf(loginToken.getPassword()));

        CustomUsernamePasswordToken dbToken= (CustomUsernamePasswordToken) getCredentials(info);

        //Object dbLoginPassword = getCredentials(info);
        //将密码加密与系统加密后的密码校验，内容一致就返回true,不一致就返回false
        boolean matches = equals(userInputLoginPass, dbToken.getEncryPsw());
        if (matches) {
            //clear retry count
            passwordRetryCache.remove(username);
            loginToken.setLut(dbToken.getLut());
        }
        return matches;
    }
}
