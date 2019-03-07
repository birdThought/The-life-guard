package com.lifeshs.entity.org;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_org_role
 */
@Table(name = "t_org_role", schema = "")
@SuppressWarnings("serial")
public class TOrgRole implements Serializable{
	/**机构角色*/
	private Integer id;
	
	/**机构ID*/
	private Integer orgId;
	
	/**角色名称*/
	private String name;
	
	/**权限*/
	private Integer permissions;
	
	public TOrgRole() {
		super();
	}
	
	public TOrgRole(Integer id, Integer orgId, String name, Integer permissions) {
		super();
		this.id = id; 
		this.orgId = orgId; 
		this.name = name; 
		this.permissions = permissions; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="orgId",nullable=false,length=11)
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	@Column(name ="name",nullable=false,length=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name ="permissions",nullable=false,length=11)
	public Integer getPermissions() {
		return permissions;
	}

	public void setPermissions(Integer permissions) {
		this.permissions = permissions;
	}
	
}