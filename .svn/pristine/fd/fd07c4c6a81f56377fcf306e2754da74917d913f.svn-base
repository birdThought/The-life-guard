package com.lifeshs.entity.record;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_data_food_kind
 */
@Table(name = "t_data_food_kind", schema = "")
@SuppressWarnings("serial")
public class TDataFoodKind implements Serializable{
	/**食物种类*/
	private Integer id;
	
	/**种类名称*/
	private String name;
	
	public TDataFoodKind() {
		super();
	}
	
	public TDataFoodKind(Integer id, String name) {
		super();
		this.id = id; 
		this.name = name; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="name",nullable=true,length=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
