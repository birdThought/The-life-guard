package com.lifeshs.entity.record;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_record_diet
 */
 @Table(name = "t_record_diet", schema = "")
 @SuppressWarnings("serial")
public class TRecordDiet implements Serializable{

		/**健康档案(饮食)*/
		private Integer id;
		
		/**用户ID*/
		private Integer userId;
		
		/**档案日期*/
		private Date recordDate;
		
		/**早餐，早餐加餐，午餐，午餐加餐，晚餐，晚餐加餐*/
		private String dietType;
		
		/**进餐时间*/
		private java.sql.Time dietTime;
		
		/**能量*/
		private Integer energy;
		
		/**创建时间*/
		private Date createDate;
		
	
	public TRecordDiet() {
		super();
	}
	
	public TRecordDiet(Integer id, Integer userId, Date recordDate, String dietType, java.sql.Time dietTime, Integer energy, Date createDate) {
		super();
		this.id = id; 
		this.userId = userId; 
		this.recordDate = recordDate; 
		this.dietType = dietType; 
		this.dietTime = dietTime; 
		this.energy = energy; 
		this.createDate = createDate; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	@Column(name ="userId",nullable=true,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	} 
	
	@Column(name ="recordDate",nullable=true,length=10)
	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	} 
	
	@Column(name ="dietType",nullable=false,length=10)
	public String getDietType() {
		return dietType;
	}

	public void setDietType(String dietType) {
		this.dietType = dietType;
	} 
	
	@Column(name ="dietTime",nullable=false,length=10)
	public java.sql.Time getDietTime() {
		return dietTime;
	}

	public void setDietTime(java.sql.Time dietTime) {
		this.dietTime = dietTime;
	} 
	
	@Column(name ="energy",nullable=false,length=11)
	public Integer getEnergy() {
		return energy;
	}

	public void setEnergy(Integer energy) {
		this.energy = energy;
	} 
	
	@Column(name ="createDate",nullable=false,length=19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	} 
	
	
}
