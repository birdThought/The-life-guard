package com.lifeshs.po.admin;

import java.util.List;

import com.lifeshs.dto.permission.PermissionAndOperation;



public class RolePermissionOperationPo extends RolePermissionPo {
	
	private PermissionAndOperation permissions;
    
    private List<AdminPermissionOperation> operationList;

    private String roleName;
    
    private String roleRemark;
    
    


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public PermissionAndOperation getPermissions() {
		return permissions;
	}

	public void setPermissions(PermissionAndOperation permissions) {
		this.permissions = permissions;
	}

	public List<AdminPermissionOperation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<AdminPermissionOperation> operationList) {
		this.operationList = operationList;
	}

	
    
    
}
