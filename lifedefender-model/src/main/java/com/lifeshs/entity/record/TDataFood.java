package com.lifeshs.entity.record;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_data_food
 */
@Table(name = "t_data_food", schema = "")
@SuppressWarnings("serial")
public class TDataFood implements Serializable{
	/**食物表*/
	private Integer id;
	
	/**食物名称*/
	private String name;
	
	/**食物种类ID*/
	private Integer kind;
	
	/**能量（/g）*/
	private Double kcal;
	
	/**图片路径*/
	private String image;
	
	public TDataFood() {
		super();
	}
	
	public TDataFood(Integer id, String name, Integer kind, Double kcal, String image) {
		super();
		this.id = id; 
		this.name = name; 
		this.kind = kind; 
		this.kcal = kcal; 
		this.image = image; 
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
	@Column(name ="kind",nullable=false,length=11)
	public Integer getKind() {
		return kind;
	}

	public void setKind(Integer kind) {
		this.kind = kind;
	}
	@Column(name ="kcal",nullable=false,length=12)
	public Double getKcal() {
		return kcal;
	}

	public void setKcal(Double kcal) {
		this.kcal = kcal;
	}
	@Column(name ="image",nullable=false,length=150)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}
