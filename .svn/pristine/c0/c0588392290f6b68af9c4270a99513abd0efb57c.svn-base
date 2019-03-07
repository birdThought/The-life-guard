package com.lifeshs.customer.security.realm;

import com.lifeshs.po.customer.CustomerRole;
import com.lifeshs.service1.admin.AdminRoleService;
import com.lifeshs.service1.admin.PermissionService;
import com.lifeshs.service1.admin.RoleperService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lifeshs.customer.security.token.UserToken;
import com.lifeshs.po.customer.CustomerUserPO;
import com.lifeshs.pojo.client.LoginUser;
import com.lifeshs.security.sessionmgr.ClientManager;
import com.lifeshs.service1.customer.CustomerUserService;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 客服用户
 * author: wenxian.cai
 * date: 2017/9/25 11:12
 */
public class UserRealm extends AuthorizingRealm {
    public void setCacheManager(CacheManager cacheManager) {
        super.setCacheManager(cacheManager);
    }

    @Autowired
    CustomerUserService userService;

    @Autowired
    AdminRoleService adminRoleService;

    @Autowired
    PermissionService permissionService;

    @Override
    public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        LoginUser user = ClientManager.getClient().getUser();
        // 获取所有角色对象
        List<CustomerRole> roles = adminRoleService.findByIdRole(user.getId());
        if (roles != null && roles.size() > 0) {
            Set<String> roleNames = new HashSet<>();
            Set<Integer> roleIds = new HashSet<Integer>();
            for (CustomerRole role : roles) {
                roleNames.add(role.getName());
                roleIds.add(role.getId());
            }
            info.setRoles(roleNames);
            //如果是超级管理员，直接返回所有权限，因为在角色权限表中没有设置super的权限
            if(roleNames.contains("super")){
                Set<String> permissionSet = permissionService.getPermissionBySuper();
                info.setStringPermissions(permissionSet);
            }else {
                Set<String> permissionSet = permissionService.getPermissionByRoles(roleIds);
                info.setStringPermissions(permissionSet);
            }
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserToken userToken = (UserToken) authenticationToken;
        String userName = userToken.getUsername();
        CustomerUserPO user = userService.getUserByUserName(userName);
        if (user == null) {
            throw new UnknownAccountException("该账号不存在");
        }
        return new SimpleAuthenticationInfo(userName, user.getPassword(), getName());
    }

    /**
     * 自定义权限验证
     *
     * @param principals
     * @param permission
     * @return
     */
    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        return super.isPermitted(principals, permission);
    }
//
//    private boolean isPermittedWithNotOperator(PrincipalCollection principals, String permission) {
//        if (permission.startsWith("not")) {
//            return !super.isPermitted(principals, permission.substring(3));
//        } else {
//            return super.isPermitted(principals, permission);
//        }
//    }
//
//    @Override
//    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
//        super.clearCachedAuthorizationInfo(principals);
//    }
//
//    @Override
//    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
//        super.clearCachedAuthenticationInfo(principals);
//    }
//
//    @Override
//    public void clearCache(PrincipalCollection principals) {
//        super.clearCache(principals);
//    }
//
//    public void clearAllCachedAuthorizationInfo() {
//        getAuthorizationCache().clear();
//    }
//
//    public void clearAllCachedAuthenticationInfo() {
//        getAuthenticationCache().clear();
//    }
//
//    public void clearAllCache() {
//        clearAllCachedAuthenticationInfo();
//        clearAllCachedAuthorizationInfo();
//    }
}
