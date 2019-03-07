package com.lifeshs.dao1.admin;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.admin.AdminPermissionOperation;
import com.lifeshs.po.admin.AdminPermissionPO;
import com.lifeshs.po.admin.OperationCheckedPO;
import com.lifeshs.po.admin.RolePermissionOperationPo;
import com.lifeshs.po.admin.RolePermissionPo;

/**
 * 
 *  权限Dao
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年6月28日 下午4:52:28
 */
@Repository(value = "adminPermissionDao")
public interface AdminPermissionDao {

    //<!--权限-->
    //获取条数
    public int countPermission();
    //获取权限
    public List<AdminPermissionPO> getPermissionList(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
    
    //获取权限及操作
    public List<RolePermissionOperationPo> getPermissionAndOperationList(@Param("startRow") int startRow, @Param("pageSize") int pageSize, @Param("agent") Integer agent);
    
   
    //根据id查找权限
    public AdminPermissionPO getPermissionById(int id);
    //根据已知的role获取权限
    public Set<String> getPermissionListByRoles(@Param(value="idlist") Set<Integer> idlist);
    //获取所有权限
    public Set<String> getPermissionBySuper();
    //修改权限
    public int updatePermissionById(AdminPermissionPO permission);
    //增加权限
    public int addPermission(AdminPermissionPO permission);
    //删除权限
    public int delPermissionById(@Param("id") int id);
    
    //<!--操作-->
    //根据权限id删除此权限下所有操作按钮
    public int delOperationByPermissionId(@Param("permissionId") int permissionId);
    //保存操作
    public int addOperation(AdminPermissionOperation operation);
    //批量保存操作
    public int addOperationList(@Param("datas") List<AdminPermissionOperation> permissionPO);
    //获取操作列表
    public List<AdminPermissionPO> getOperationByPermissionId(@Param("id") int id, @Param("permissionId") int permissionId);
    //获取操作列表
    public List<OperationCheckedPO> getOperationList(@Param("id") int id, @Param("permissionId") int permissionId);
	//删除单个操作
    public int delAnOperation(AdminPermissionOperation operation);
    //根据权限id及操作删除操作
	public int delOperationList(@Param("id")Integer permission, @Param("arr")String[] operationArr);
	//查询roleId对应项
	public List<RolePermissionPo> getRolePermissionByRoleId(Integer roleId);
	//更新删除操作后的中间表对应项
	public int updataRolePermissionById(@Param("id")Integer id,@Param("newStr") String newStr);
}
