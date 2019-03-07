package com.lifeshs.entity.device;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_sport_band_sleep
 */
 @Table(name = "t_sport_band_sleep", schema = "")
 @SuppressWarnings("serial")
public class TSportBandSleep implements Serializable{

		/**睡眠*/
		public Integer id;
		
		/**用户设备ID*/
		public Integer userId;
		
		/**开始时间*/
		public Date startTime;
		
		/**结束时间*/
		public Date endTime;
		
		/**浅度睡眠时长(分钟)*/
		public Integer shallowDuration;
		
		/**深度睡眠时长(分钟)*/
		public Integer deepDuration;
		
		/**苏醒时长(分钟)*/
		public Integer wakeupDuration;
		
		/**终端类型*/
		public String deviceType;
		
		/**创建时间*/
		public Date createDate;
		
	
	public TSportBandSleep() {
		super();
	}
	
	public TSportBandSleep(Integer id, Integer userId, Date startTime, Date endTime, Integer shallowDuration, Integer deepDuration, Integer wakeupDuration, String deviceType, Date createDate) {
		super();
		this.id = id; 
		this.userId = userId; 
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.shallowDuration = shallowDuration; 
		this.deepDuration = deepDuration; 
		this.wakeupDuration = wakeupDuration; 
		this.deviceType = deviceType; 
		this.createDate = createDate; 
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
	
	@Column(name ="shallowDuration",nullable=false,length=12)
	public Integer getShallowDuration() {
		return shallowDuration;
	}

	public void setShallowDuration(Integer shallowDuration) {
		this.shallowDuration = shallowDuration;
	} 
	
	@Column(name ="deepDuration",nullable=false,length=100)
	public Integer getDeepDuration() {
		return deepDuration;
	}

	public void setDeepDuration(Integer deepDuration) {
		this.deepDuration = deepDuration;
	} 
	
	@Column(name ="wakeupDuration",nullable=false,length=6)
	public Integer getWakeupDuration() {
		return wakeupDuration;
	}

	public void setWakeupDuration(Integer wakeupDuration) {
		this.wakeupDuration = wakeupDuration;
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
	
	
}
