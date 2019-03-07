package com.lifeshs.service1.admin;

import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.po.admin.permissionPo;
import com.lifeshs.po.customer.CustomerRole;
import com.lifeshs.po.user.AdminRolePO;
import com.lifeshs.service1.page.Paging;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-02-09
 * 17:23
 * @desc
 */
public interface AdminRoleService {
    /**
     * 获取列表
     * @return
     */
	Paging<CustomerRole> getAdminRoleData(Integer page, Integer size ,Integer dlsId);

    /**
     *  删除
     * @param id
     * @return
     */
    Integer deleteAdmin(Integer id);

    /**
     * 修改
     * @param id
     * @param name
     * @return
     */
    Integer update(Integer id, String name,String remark);

    List<permissionPo> findByAllpermiss();

    /**
     *  添加
     * @param name
     * @return
     */
    int saveAdmin(String name,String remark,Integer type);

    /**
     *  根据后台用户ID获取后台用户的角色
     * @param userId
     * @return
     */
    List<CustomerRole> findByIdRole(Integer userId);
    
    
    /**
     * 获取角色权限绑定操作
     * @return
     */
	List<RolePermissionPo> queryRolePermission();
	
	
	/**
	 * 变更角色操作表的操作
	 * @param item
	 * @param operation
	 */
	Integer addRolePermission(String item, String operation,Integer roleId);
	Integer delectRolePermission(String item, String operation,Integer roleId);
	
	/**
	 * 删除权限时删除对应的角色绑定中间表数据
	 * @param item
	 * @return
	 */
	Integer delRolePermission(String item);
	/**
	 * 根据ROLEID删除对应的角色绑定中间表数据
	 * @param item
	 * @return
	 */
	Integer delRolePermissionByRoleId(Integer id);

	Integer addRolePermission2(String item, Integer roleId);

	Integer delectRolePermission2(String item, Integer roleId);
	
	/**
	 * 删除权限对应的操作项
	 * @param item
	 *//*
	Integer delRolePermission(Integer id);*/
	
	
	

	
}
