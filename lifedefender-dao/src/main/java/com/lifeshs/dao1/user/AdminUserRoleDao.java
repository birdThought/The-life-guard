package com.lifeshs.dao1.user;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 
 *  用户角色表
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年11月1日 下午4:50:48
 */
@Repository(value = "adminUserRoleDao")
public interface AdminUserRoleDao{
    
    int addAdminUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
