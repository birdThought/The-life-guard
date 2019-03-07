package com.lifeshs.entity.record;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_record_diet_food
 */
 @Table(name = "t_record_diet_food", schema = "")
 @SuppressWarnings("serial")
public class TRecordDietFood implements Serializable{

		/**健康档案(饮食-食物)*/
		private Integer id;
		
		/**饮食ID*/
		private Integer dietId;
		
		/**能量*/
		private Float kcal;
		
		/**食物重量(克)*/
		private Integer foodWeight;
		
		/**食物id*/
		private Integer foodID;
		
		/**创建时间*/
		private Date createDate;
		
	
	public TRecordDietFood() {
		super();
	}
	
	public TRecordDietFood(Integer id, Integer dietId, Float kcal, Integer foodWeight, Integer foodID, Date createDate) {
		super();
		this.id = id; 
		this.dietId = dietId; 
		this.kcal = kcal; 
		this.foodWeight = foodWeight; 
		this.foodID = foodID; 
		this.createDate = createDate; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	@Column(name ="dietId",nullable=false,length=11)
	public Integer getDietId() {
		return dietId;
	}

	public void setDietId(Integer dietId) {
		this.dietId = dietId;
	} 
	
	@Column(name ="kcal",nullable=false,length=11)
	public Float getKcal() {
		return kcal;
	}

	public void setKcal(Float kcal) {
		this.kcal = kcal;
	} 
	
	@Column(name ="foodWeight",nullable=false,length=11)
	public Integer getFoodWeight() {
		return foodWeight;
	}

	public void setFoodWeight(Integer foodWeight) {
		this.foodWeight = foodWeight;
	} 
	
	@Column(name ="foodID",nullable=false,length=11)
	public Integer getFoodID() {
		return foodID;
	}

	public void setFoodID(Integer foodID) {
		this.foodID = foodID;
	} 
	
	@Column(name ="createDate",nullable=false,length=19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	} 
	
	
}
