package com.lifeshs.dao1.admin;

import com.lifeshs.po.admin.AdminPermissionCheckedPO;
import com.lifeshs.po.admin.AdminPermissionPO;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.po.admin.RoleperPO;
import com.lifeshs.po.admin.permissionPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @create 2018-02-24
 * 15:57
 * @desc
 */
@Repository("roleperDao")
public interface RoleperDao {

    /**
     *  获取权限明细
     * @param releId
     * @return
     */
    List<RoleperPO> findById(@Param("roleId")Integer releId);

    /**
     *  获取权限菜单列表
     * @return
     */
    List<AdminPermissionPO> findByAll();

    /**
     *  删除角色权限
     * @param roleId
     * @return
     */
    boolean deleteRoleMenu(@Param("roleId") Integer roleId);

    /**
     *  添加或修改用户权限
     * @param roleId
     * @param params
     * @return
     */
    boolean addRoleMenu(@Param("roleId") Integer roleId,@Param("params") Map<String, Object> params);

    /**
     *  根据已存在的id 获取权限集合
     * @param idlist
     * @return
     */
    List<permissionPo> loadPermissionList(@Param("idlist") List<Integer> idlist);

    /**
     *  根据角色id 获取角色菜单
     * @param roleId
     * @return
     */
    List<Map<String,Object>> loadRoleMenu(@Param("roleId") Integer roleId);
    
    /**
     * 根据角色获取权限集
     * @param roleId
     * @return
     */
	List<RolePermissionPo> findPermissionByid(Integer roleId);
	
	/**
	 * 获取权限
	 * @return
	 */
	List<AdminPermissionCheckedPO> findByAllOperation(@Param("agentId")Integer agentId,@Param("page")Integer page,@Param("size")Integer size);
	
	/**
	 * 添加权限及角色关联
	 * @param roleId
	 * @param params
	 */
	void addRolePermission(@Param("roleId")Integer roleId, @Param("params")String params);
	
	/**
	 * 根据item获取对应rolePermission
	 * @param item
	 * @return
	 */
	List<RolePermissionPo> getRolePermissionByItem(@Param("item")String item,@Param("name")String name);
	
	/**
	 * 根据id修改rolePermission
	 * @param item
	 * @return
	 */
	Integer updateRolePermissionById(@Param("id")int id,@Param("st") String st);
	/**
	 * 获取总长度
	 * @return
	 */
	Integer getPermissionSize(@Param("agentId")Integer agentId);
	/**
	 * 获取权限分页
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	List<AdminPermissionCheckedPO> getPermissionList(@Param("curPage")int curPage,@Param("pageSize") int pageSize,@Param("agentId") Integer agentId);
	
	/**
	 * 获取size
	 * @param agentId
	 * @return
	 */
	int getRoleSize(@Param("agentId")Integer agentId);
	
	
	

	
}
