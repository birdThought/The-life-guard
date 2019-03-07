package com.lifeshs.service1.admin;

import java.util.List;

import com.lifeshs.dto.permission.PermissionAndOperation;
import com.lifeshs.po.admin.AdminPermissionOperation;
import com.lifeshs.po.admin.AdminPermissionPO;
import com.lifeshs.po.admin.OperationCheckedPO;
import com.lifeshs.po.admin.RolePermissionOperationPo;
import com.lifeshs.po.admin.RolePermissionPo;
import com.lifeshs.service1.page.Paging;

import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface PermissionService {

    //查询权限列表
    public Paging<RolePermissionOperationPo> getPermissionlist(int curPage, int pageSize,Integer agent);
    
    //根据id查找权限
    public AdminPermissionPO getPermissionById(int id);

    //根据id获取操作列表
    public List<OperationCheckedPO> getOperationList(int id, int permissionId);

    //根据已知的role获取权限
    public Set<String> getPermissionByRoles(Set<Integer> idList);

    //获取所有权限
    Set<String> getPermissionBySuper();

    //获取操作列表
    public List<AdminPermissionPO> getOperationByPermissionId(int id, int permissionId);
    
    //修改权限
    public int updatePermissionById(AdminPermissionPO permission);
    //增加权限
    public int addPermission(AdminPermissionPO permission);
    //删除权限
    public int delPermissionById(int id);
    //删除操作
    public int delOperationByPermissionId(int permissionId);
    //保存操作
    public int addOperation(AdminPermissionOperation operation);
    //批量保存操作
    public int addOperationList(List<AdminPermissionOperation> permissionPO);
    //删除单个操作
	public int delAnOperation(AdminPermissionOperation operation);
	//根据权限id及操作删除操作
	public int delOperationList(Integer permission, String[] operationArr);
	//删除权限角色中间表的操作
	public int delRolePermission(String[] operationArr, String item, Integer roleId);

	
}
