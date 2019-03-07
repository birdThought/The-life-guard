package com.lifeshs.entity.device;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_sport_band
 */
@Table(name = "t_sport_band", schema = "")
@SuppressWarnings("serial")
public class TSportBand implements Serializable{
	/**计步睡眠日数据*/
	private Integer id;
	
	/**用户设备ID*/
	private Integer userId;
	
	/**日期*/
	private Date date;
	
	/**总步数*/
	private Integer steps;
	
	/**总里程*/
	private Integer mileage;
	
	/**卡路里(千卡)*/
	private Integer kcal;
	
	/**总睡眠时长*/
	private Integer sleepDuration;
	
	/**浅度睡眠时长(分钟)*/
	private Integer shallowDuration;
	
	/**深度睡眠时长(分钟)*/
	private Integer deepDuration;
	
	/**苏醒时长(分钟)*/
	private Integer wakeupDuration;
	
	/**终端类型*/
	private String deviceType;
	
	/**创建时间*/
	private Date createDate;
	
	public TSportBand() {
		super();
	}
	
	public TSportBand(Integer id, Integer userId, Date date, Integer steps, Integer mileage, Integer kcal, Integer sleepDuration, Integer shallowDuration, Integer deepDuration, Integer wakeupDuration, String deviceType, Date createDate) {
		super();
		this.id = id; 
		this.userId = userId; 
		this.date = date; 
		this.steps = steps; 
		this.mileage = mileage; 
		this.kcal = kcal; 
		this.sleepDuration = sleepDuration; 
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
	@Column(name ="date",nullable=false,length=10)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	@Column(name ="steps",nullable=false,length=11)
	public Integer getSteps() {
		return steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}
	@Column(name ="mileage",nullable=false,length=15)
	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	@Column(name ="kcal",nullable=false,length=15)
	public Integer getKcal() {
		return kcal;
	}

	public void setKcal(Integer kcal) {
		this.kcal = kcal;
	}
	@Column(name ="sleepDuration",nullable=false,length=6)
	public Integer getSleepDuration() {
		return sleepDuration;
	}

	public void setSleepDuration(Integer sleepDuration) {
		this.sleepDuration = sleepDuration;
	}
	@Column(name ="shallowDuration",nullable=false,length=6)
	public Integer getShallowDuration() {
		return shallowDuration;
	}

	public void setShallowDuration(Integer shallowDuration) {
		this.shallowDuration = shallowDuration;
	}
	@Column(name ="deepDuration",nullable=false,length=6)
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
	@Column(name ="deviceType",nullable=false,length=15)
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