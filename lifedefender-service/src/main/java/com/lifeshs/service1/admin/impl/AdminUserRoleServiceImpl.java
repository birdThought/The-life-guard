package com.lifeshs.service1.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao1.user.AdminUserRoleDao;
import com.lifeshs.service1.admin.AdminUserRoleService;

/**
 * 
 *  用户角色表
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年11月1日 下午4:57:42
 */
@Service("adminUserRoleService")
public class AdminUserRoleServiceImpl implements AdminUserRoleService {

    @Autowired
    private AdminUserRoleDao adminUserRoleDao;

    @Override
    public int addAdminUserRole(Integer userId, Integer roleId) {
        return adminUserRoleDao.addAdminUserRole(userId, roleId);
    }
}
