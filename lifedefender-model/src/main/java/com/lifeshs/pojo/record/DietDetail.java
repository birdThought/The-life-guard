package com.lifeshs.pojo.record;

import java.sql.Time;
import java.util.Date;

public class DietDetail {

	private int id;
	
	private String dietType;
	
	private Time dietTime;
	
	private int energy;
	
	private Date recordDate;
	
	private int foodId;
	
	private String foodName;
	
	private int foodWeight;
	
	private float kcal;
	
	private String image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDietType() {
		return dietType;
	}

	public void setDietType(String dietType) {
		this.dietType = dietType;
	}

	public Time getDietTime() {
		return dietTime;
	}

	public void setDietTime(Time dietTime) {
		this.dietTime = dietTime;
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodWeight() {
		return foodWeight;
	}

	public void setFoodWeight(int foodWeight) {
		this.foodWeight = foodWeight;
	}

	public float getKcal() {
		return kcal;
	}

	public void setKcal(float kcal) {
		this.kcal = kcal;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
