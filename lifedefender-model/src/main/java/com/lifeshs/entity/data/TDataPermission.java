package com.lifeshs.entity.data;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_data_permission
 */
 @Table(name = "t_data_permission", schema = "")
 @SuppressWarnings("serial")
public class TDataPermission implements Serializable{

		
		public Integer id;
		
		/**权限项名称*/
		public String name;
		
		/**权限项值,2的x次方*/
		public Integer value;
		
	
	public TDataPermission() {
		super();
	}
	
	public TDataPermission(Integer id, String name, Integer value) {
		super();
		this.id = id; 
		this.name = name; 
		this.value = value; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	@Column(name ="name",nullable=false,length=30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	@Column(name ="value",nullable=false,length=11)
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	} 
	
	
}
