package com.lifeshs.security.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lifeshs.common.constants.common.BaseDefine;
import com.lifeshs.common.constants.common.CacheType;
import com.lifeshs.common.constants.common.UserStatus;
import com.lifeshs.entity.data.TDataImei;
import com.lifeshs.entity.member.TUser;
import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.security.exception.ValidCodeException;
import com.lifeshs.security.sessionmgr.ClientManager;
import com.lifeshs.security.token.CustomUsernamePasswordToken;
import com.lifeshs.service.common.impl.transform.CommonTransImpl;
import com.lifeshs.service.member.IMemberService;
import com.lifeshs.service.org.user.IOrgUserService;
import com.lifeshs.service.terminal.ITerminalService;
import com.lifeshs.service.tool.ICacheService;
import com.lifeshs.utils.MD5Utils;
import com.lifeshs.utils.PasswordUtil;
import com.lifeshs.utils.Toolkits;

/**
 * 继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo ()，重写获取用户信息的方法。
 */
public class UserRealm extends AuthorizingRealm {
//    private static Logger logger = Logger.getLogger(UserRealm.class);

    //缓存接口
    private CacheManager cacheManager;

    @Autowired
    private IMemberService memberService;

    @Autowired
    private ICacheService cacheService;
    
    @Autowired
    private IOrgUserService orgUserService;  //临时使用，替换旧平台密码完成后清除 dengfeng

    @Autowired
    private CommonTransImpl commonTrans;
    
    @Autowired
    private ITerminalService terminalService;
    
    public void setCacheManager(CacheManager cacheManager) {
        super.setCacheManager(cacheManager);
        this.cacheManager = cacheManager;
    }

    /**
     * 授权
     * 重载doGetAuthorizationInfo()方法，重写获取用户权限的方法。
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        LoginUser user = ClientManager.getSessionUser();
        if (user == null) {
            return null;
        }
        Set<String> roleSet = new HashSet<>();
        roleSet.add("user:" + user.getUserType());
        roleSet.add("orgType:" + user.getType());
        /*authorizationInfo.setRoles(roleSet);
        authorizationInfo.setStringPermissions(roleSet);*/
        authorizationInfo.addRole("user:" + user.getUserType());
        authorizationInfo.addRole("orgType:" + user.getType());
        authorizationInfo.addStringPermission("orgType:" + user.getType());
        /*List<String> permissions = listPermissions(user.getUserType());
        for (String p : permissions) {
            authorizationInfo.addStringPermission(p);
        }*/
//        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 身份验证
     * 获取身份验证相关信息
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomUsernamePasswordToken loginToken = (CustomUsernamePasswordToken) token;
        String username = (String) token.getPrincipal();//得到用户名 
        //获取错误的次数
        Cache<String, AtomicInteger> passwordRetryCache = cacheManager.getCache("passwordRetryCache");
        AtomicInteger retryCount = passwordRetryCache.get(username);
        //第一次错误，第二次开始校验验证码
        if (retryCount != null) {
            //校验码判断逻辑
            //取得用户输入的校验码
            String userInputValidCode = loginToken.getValidCode();
            //取得真实的正确校验码
            String realRightValidCode = (String) SecurityUtils.getSubject().getSession().getAttribute(BaseDefine.SESSION_KEY_OF_RAND_CODE);
            //
            if (null == userInputValidCode || !userInputValidCode.equalsIgnoreCase(realRightValidCode)) {
                throw new ValidCodeException("验证码输入不正确");
            }
        }
        //登录用户类型
        String userName = "";
        String pwd = "";
        String userId = "";  //临时使用，替换旧平台密码完成后清除 dengfeng
        //以上校验码验证通过以后,查数据库

        // Start 临时使用，替换旧平台密码完成后清除 dengfeng
        char[] passwords = (char[]) token.getCredentials();  //密码
        String password = String.valueOf(passwords);
        if (Toolkits.custom(username, "^\\d{15}$")) {
            TDataImei dataImei = commonTrans.findUniqueByProperty(TDataImei.class, "imei", username);
            if (dataImei != null) {
                if (dataImei.status == 0) {
                    throw new DisabledAccountException("设备尚未卖出");
                }
                if (dataImei.status == 2) {
                    // 设备已绑定，可正常登录
                }
                if (dataImei.status == 1) {
                    if (!StringUtils.equals(password, dataImei.getPassword())) {
                        // 抛出密码错误异常
                        throw new IncorrectCredentialsException();
                    }
                    // 设备已卖出，注册账号登录
                    int nwUserId = memberService.registMember(username, password);
                    if (nwUserId != -1) {
                        try {
                            terminalService.bindTerminal(nwUserId, username, "设备备注名", null, "C3");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        
        TUser user = memberService.findByUsername(TUser.class, username);
        if (user != null) {
            int status = user.getStatus();
            if (status == UserStatus.disenable.value()) {
                throw new DisabledAccountException("帐号已被禁用.");
            }
            userName = user.getUserName();
            pwd = user.getPassword();
            userId = user.getId().toString();
            loginToken.setLut("m"); // 普通用户

        } else {
            TOrgUser orgUser = memberService.findByUsername(TOrgUser.class, username);
            if (orgUser == null) {
                throw new UnknownAccountException();    // 没有找到该账号
            }
            int status = orgUser.getStatus();
            if (status == UserStatus.disenable.value()) {
                throw new DisabledAccountException("帐号已被禁用.");
            }
            if (status == UserStatus.leaveoff.value()) {
                throw new DisabledAccountException("该员工已离职.");
            }
            userName = orgUser.getUserName();
            pwd = orgUser.getPassword();
            userId = orgUser.getId().toString();
            loginToken.setLut("o");    // 企业用户
        }

        if (!password.equals(cacheService.getCacheValue(CacheType.OAUTH_CACHE, "oauth" + userName))) {
            password = MD5Utils.encryptPassword(password);
        }
//        String enpwdNew = MD5Utils.encryptPassword(password);
        if (!password.equals(pwd)) {
            String enpwdOld = PasswordUtil.encrypt("", password, PasswordUtil.getStaticSalt());
            if (enpwdOld.equals(pwd)) {
                if (loginToken.getLut().equals("m"))
                    memberService.modifyPasswordByUserId(userId, password, "127.0.0.1");
                else {
                    orgUserService.modifyPasswordByUserId(Integer.valueOf(userId), password, "127.0.0.1");
                }
                pwd = password;
            }
        }
        //end  临时使用，替换旧平台密码 dengfeng

//        if("o".equals(lut)){
//        	TOrgUser orgUser = userDAO.findByUsername(TOrgUser.class, username);
//        	 if (orgUser == null) {
//                 throw new UnknownAccountException();//没找到帐号
//             }
//        	 int status = orgUser.getStatus();
//        	 if(status == UserStatus.disenable.value()){
//        		 throw new DisabledAccountException("帐号已被禁用.");
//        	 }
//        	 if(status == UserStatus.leaveoff.value()) {
//        		 throw new DisabledAccountException("该员工已离职.");
//        	 }
//        	 userName = orgUser.getUserName();
//             pwd = orgUser.getPassword();
//        }else{
//        	TUser user = userDAO.findByUsername(TUser.class, username);
//            if (user == null) {
//                throw new UnknownAccountException();//没找到帐号
//            }
//            int status = user.getStatus();
//	       	if(status == UserStatus.disenable.value()){
//	       		throw new DisabledAccountException("帐号已被禁用.");
//	       	}
//            userName = user.getUserName();
//            pwd = user.getPassword();
//        }
        loginToken.setEncryPsw(pwd);
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，也可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName, //用户名
                loginToken, //密码
                getName()  //realm name
        );

        return authenticationInfo;
    }

    /**
     * 为权限标签扩展or、and、not功能
     *
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (permission.contains("or")) {
            String[] permissions = permission.split("or");
            for (String orPermission : permissions) {
                if (isPermittedWithNotOperator(principals, orPermission)) {
                    return true;
                }
            }
            return false;
        } else if (permission.contains("and")) {
            String[] permissions = permission.split("and");
            for (String orPermission : permissions) {
                if (!isPermittedWithNotOperator(principals, orPermission)) {
                    return false;
                }
            }
            return true;
        } else {
            return isPermittedWithNotOperator(principals, permission);
        }
    }

    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
        if (permission.startsWith("not")) {
            return !super.isPermitted(principals, permission.substring(3));
        } else {
            return super.isPermitted(principals, permission);
        }
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }


    private List<String> listPermissions(Integer userType) {
        List<String> permissions = new ArrayList<>();
        if (userType == null) {
            return permissions;
        }
        //todo 之后改为存在properties文件
        permissions.add("my/home");
        permissions.add("login");
        permissions.add("org/home");
        if (userType == 0) {    //管理员
            permissions.add("store/home");
            permissions.add("orderManage");
            permissions.add("org/employee");
            permissions.add("org/employee/addemployee");
            permissions.add("org/memberManage/store");
            permissions.add("store/finance");
            permissions.add("store/finance/bankInfo");
            permissions.add("org/service");
            permissions.add("org/service/publishservice/online");
            permissions.add("org/service/publishservice/offline");
            permissions.add("org/service/publishservice/visit");
            permissions.add("message/store");
            permissions.add("org/profile/store");
            permissions.add("org/profile/services/accountsecurity");
            permissions.add("org/profile/services/modifypassword");
        }
        if (userType == 1) {    //服务师
            permissions.add("org/services/home");
            permissions.add("org/services/todayprofit");
            permissions.add("org/services/todayorder");
            permissions.add("message/org");
            permissions.add("org/memberManage");
            permissions.add("orderManage/ordertodo");
            permissions.add("orderManage/orderdone");
            permissions.add("orderManage/ordercomments");
            permissions.add("orderManage/orderdetail");
            permissions.add("org/profile/services");
            permissions.add("org/profile/services/accountsecurity");
            permissions.add("org/profile/services/modifypassword");
            permissions.add("org/profile/services/mobile");

        }
        if (userType == 2) {     //都有（个体门店）
            permissions.add("store/home");
            permissions.add("store/home/todayprofit");
            permissions.add("store/home/todayorder");
            permissions.add("orderManage");
            permissions.add("org/employee");
            permissions.add("org/employee/addemployee");
            permissions.add("org/memberManage");
            permissions.add("store/finance");
            permissions.add("store/finance/bankInfo");
            permissions.add("org/service");
            permissions.add("org/service/publishservice/online");
            permissions.add("org/service/publishservice/offline");
            permissions.add("org/service/publishservice/visit");
            permissions.add("message/org");
            permissions.add("org/profile/store");
            permissions.add("org/profile/services");
            permissions.add("org/profile/services/accountsecurity");
            permissions.add("org/profile/services/modifypassword");
            permissions.add("org/profile/services/mobile");
        }
        return permissions;
    }

}
