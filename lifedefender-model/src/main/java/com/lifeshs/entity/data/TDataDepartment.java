package com.lifeshs.entity.data;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_data_department
 */
@Table(name = "t_data_department", schema = "")
@SuppressWarnings("serial")
public class TDataDepartment implements Serializable{
	
	private Integer id;
	
	/**科室名称*/
	private String name;
	
	/**父类ID*/
	private Integer parentId;
	
	public TDataDepartment() {
		super();
	}
	
	public TDataDepartment(Integer id, String name, Integer parentId) {
		super();
		this.id = id; 
		this.name = name; 
		this.parentId = parentId; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="name",nullable=false,length=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name ="parentId",nullable=false,length=11)
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
}