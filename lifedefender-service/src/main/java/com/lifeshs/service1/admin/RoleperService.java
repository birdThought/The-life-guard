package com.lifeshs.service1.admin;

import com.lifeshs.po.admin.AdminPermissionCheckedPO;
import com.lifeshs.po.admin.AdminPermissionPO;
import com.lifeshs.po.admin.RolePermissionOperationPo;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.po.admin.RoleperPO;
import com.lifeshs.service1.page.Paging;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * @author Administrator
 * @create 2018-02-26
 * 9:22
 * @desc
 */
public interface RoleperService {

    /**
     *  获取权限菜单
     * @return
     */
    List<AdminPermissionPO> findByAll();

    /**
     *  获取权限明细
     * @param roleId
     * @return
     */
    List<RoleperPO> findByid(Integer roleId);

    /**
     * 添加或修改用户权限
     * @param roleId
     * @param params
     * @return
     */
    boolean uptateRoleMenuData(Integer roleId, List<Map<String, Object>> params);

    /**
     *  获取当前用户的权限明细
     * @param id
     * @return
     */
    Set<String> getCustomerPermissionSet(Integer id);

    /**
     *  获取角色菜单
     * @param roleId
     * @return
     */
    List<Map<String,Object>> findByRoleMenu(Integer roleId);

	List<RolePermissionPo> findPermissionByid(Integer roleId);
	
	/**
	 * 获取全部权限
	 * @return
	 */
	Paging<AdminPermissionCheckedPO> findByAllOperation(Integer page,Integer size,Integer agentId);
	
	/**
	 * 添加角色和权限关联
	 * @param roleId
	 * @param params
	 */
	void addRolePermission(Integer roleId, List<String> params);
	/**
	 * 删除id对应的权限列表
	 * @param roleId
	 */
	void deleteRoleMenu(Integer roleId);
	
	/**
	 * 根据item获取对应rolePermission
	 * @param item
	 * @param oldName 
	 * @return
	 */
	List<RolePermissionPo> getRolePermissionByItem(String item, String name);
	
	/**
	 * 根据id修改rolePermission
	 * @param item
	 * @return
	 */
	Integer updateRolePermissionById(int id, String st);
	
	/**
	 * 获取权限列表
	 * @param page
	 * @param adminPermissionPageSize
	 * @return
	 */
	Paging<AdminPermissionCheckedPO> getPermissionlist(Integer page, int adminPermissionPageSize,Integer roleId,Integer agentId);
	
	
    
    
    
}
