package com.lifeshs.entity.member;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_user_monitorTrack
 */
 @Table(name = "t_user_monitorTrack", schema = "")
 @SuppressWarnings("serial")
public class TUserMonitorTrack implements Serializable{

		/**监控轨迹设置*/
		public Integer id;
		
		/**用户设备id*/
		public Integer userDeviceId;
		
		/**轨迹编号*/
		public Integer number;
		
		/**监控名称*/
		public String name;
		
		/**监控开始时间*/
		public java.sql.Time startTime;
		
		/**监控结束时间*/
		public java.sql.Time endTime;
		
		/**启用_1,停止_0*/
		public Boolean enabled;
		
		/**创建时间*/
		public Date createDate;
		
	
	public TUserMonitorTrack() {
		super();
	}
	
	public TUserMonitorTrack(Integer id, Integer userDeviceId, Integer number, String name, java.sql.Time startTime, java.sql.Time endTime, Boolean enabled, Date createDate) {
		super();
		this.id = id; 
		this.userDeviceId = userDeviceId; 
		this.number = number; 
		this.name = name; 
		this.startTime = startTime; 
		this.endTime = endTime; 
		this.enabled = enabled; 
		this.createDate = createDate; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	@Column(name ="userDeviceId",nullable=true,length=11)
	public Integer getUserDeviceId() {
		return userDeviceId;
	}

	public void setUserDeviceId(Integer userDeviceId) {
		this.userDeviceId = userDeviceId;
	} 
	
	@Column(name ="number",nullable=false,length=4)
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	} 
	
	@Column(name ="name",nullable=false,length=30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	@Column(name ="startTime",nullable=false,length=10)
	public java.sql.Time getStartTime() {
		return startTime;
	}

	public void setStartTime(java.sql.Time startTime) {
		this.startTime = startTime;
	} 
	
	@Column(name ="endTime",nullable=false,length=10)
	public java.sql.Time getEndTime() {
		return endTime;
	}

	public void setEndTime(java.sql.Time endTime) {
		this.endTime = endTime;
	} 
	
	@Column(name ="enabled",nullable=false,length=1)
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	} 
	
	@Column(name ="createDate",nullable=false,length=19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	} 
	
	
}