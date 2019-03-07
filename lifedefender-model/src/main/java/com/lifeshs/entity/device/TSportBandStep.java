package com.lifeshs.entity.device;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_sport_band_step
 */
 @Table(name = "t_sport_band_step", schema = "")
 @SuppressWarnings("serial")
public class TSportBandStep implements Serializable{

		/**计步*/
		public Integer id;
		
		/**用户设备ID*/
		public Integer userId;
		
		/**开始时间*/
		public Date startTime;
		
		/**结束时间*/
		public Date endTime;
		
		/**步数*/
		public String steps;
		
		/**里程*/
		public String mileage;
		
		/**卡路程*/
		public String kcal;
		
		/**终端类型*/
		public String deviceType;
		
		/**创建时间*/
		public Date createDate;
		
		
		public String sportMode;
		
	
	public TSportBandStep() {
		super();
	}
	
	public TSportBandStep(Integer id, Integer userId, Date startTime, Date endTime, String steps, String mileage, String kcal, String deviceType, Date createDate, String sportMode) {
		super();
		this.id = id; 
		this.userId = userId; 
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.steps = steps; 
		this.mileage = mileage; 
		this.kcal = kcal; 
		this.deviceType = deviceType; 
		this.createDate = createDate; 
		this.sportMode = sportMode; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	@Column(name ="userId",nullable=false,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	} 
	
	@Column(name ="startTime",nullable=false,length=19)
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	} 
	
	@Column(name ="endTime",nullable=false,length=19)
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	} 
	
	@Column(name ="steps",nullable=false,length=11)
	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	} 
	
	@Column(name ="mileage",nullable=false,length=15)
	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	} 
	
	@Column(name ="kcal",nullable=false,length=15)
	public String getKcal() {
		return kcal;
	}

	public void setKcal(String kcal) {
		this.kcal = kcal;
	} 
	
	@Column(name ="deviceType",nullable=false,length=20)
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	} 
	
	@Column(name ="createDate",nullable=false,length=19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	} 
	
	@Column(name ="sportMode",nullable=false,length=4)
	public String getSportMode() {
		return sportMode;
	}

	public void setSportMode(String sportMode) {
		this.sportMode = sportMode;
	} 
	
	
}
