package com.lifeshs.dao1.user;

import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.po.customer.CustomerRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Administrator
 * @create 2018-02-09
 * 17:17
 * @desc
 */
@Repository(value = "adminRoleDao")
public interface AdminRoleDao{


    List<CustomerRole> findByAdminRoleData(@Param("dlsId")Integer dlsId,@Param("page") Integer page,@Param("size") Integer size);

    Integer delete(@Param("id") Integer id);

    Integer update(@Param("id")Integer id,@Param("name")String name, @Param("remark")String remark);

    int save(@Param("name") String name ,@Param("remark")String remark ,@Param("type")Integer type);

    List<CustomerRole> findByIdRole(@Param("userId") Integer userId);

	List<RolePermissionPo> queryRolePermission();
	
	Integer updateRolePermission(@Param("id")Integer id ,@Param("str1") String item);

	List<RolePermissionPo> queryRolePermission2(Integer roleId);

	Integer insertPemission(Integer roleId,String st);

	Integer delRolePermission(@Param("id")Integer roleId,@Param("str1")String item);

	Integer delRolePermissionByItem(@Param("item")String item);

	Integer delRolePermissionByRoleId(@Param("roleId")Integer id);
	
	int getRoleSize(@Param("dlsId")Integer dlsId);

	Integer addRolePermission2(@Param("item")String item, @Param("roleId")Integer roleId);

	Integer delectRolePermission2(@Param("item")String item, @Param("roleId")Integer roleId);

	
	
}
