package com.lifeshs.entity.member;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_user_electronicFence
 */
 @Table(name = "t_user_electronicFence", schema = "")
 @SuppressWarnings("serial")
public class TUserElectronicFence implements Serializable{

		/**电子围栏设置*/
		public Integer id;
		
		/**用户设备id*/
		public Integer userDeviceId;
		
		/**围栏编号*/
		public Integer number;
		
		/**经度(建议不超过.后六位)*/
		public Double longitude;
		
		/**纬度(建议不超过.后六位)*/
		public Double latitude;
		
		/**半径（米）*/
		public Integer radius;
		
		/**中文地址*/
		public String address;
		
		/**预警方式1：入栏报警，2：出栏报警、3：出入报警*/
		public Integer warningType;
		
		/**报警手机号*/
		public String warningPhone;
		
		/**地图类型 B：百度、G：谷歌*/
		public String mapType;
		
		/**开始时间段1*/
		public java.sql.Time startTime1;
		
		/**结束时间段1*/
		public java.sql.Time endTime1;
		
		/**开始时间段2*/
		public java.sql.Time startTime2;
		
		/**结束时间段2*/
		public java.sql.Time endTime2;
		
		/**开始时间段3*/
		public java.sql.Time startTime3;
		
		/**结束时间段3*/
		public java.sql.Time endTime3;
		
		/**启用_1,停止_0,000表示全部停用*/
		public String enabled;
		
		/**修改时间*/
		public Date modifyDate;
		
	
	public TUserElectronicFence() {
		super();
	}
	
	public TUserElectronicFence(Integer id, Integer userDeviceId, Integer number, Double longitude, Double latitude, Integer radius, String address, Integer warningType, String warningPhone, String mapType, java.sql.Time startTime1, java.sql.Time endTime1, java.sql.Time startTime2, java.sql.Time endTime2, java.sql.Time startTime3, java.sql.Time endTime3, String enabled, Date modifyDate) {
		super();
		this.id = id; 
		this.userDeviceId = userDeviceId; 
		this.number = number; 
		this.longitude = longitude; 
		this.latitude = latitude; 
		this.radius = radius; 
		this.address = address; 
		this.warningType = warningType; 
		this.warningPhone = warningPhone; 
		this.mapType = mapType; 
		this.startTime1 = startTime1; 
		this.endTime1 = endTime1; 
		this.startTime2 = startTime2; 
		this.endTime2 = endTime2; 
		this.startTime3 = startTime3; 
		this.endTime3 = endTime3; 
		this.enabled = enabled; 
		this.modifyDate = modifyDate; 
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
	
	@Column(name ="number",nullable=true,length=4)
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	} 
	
	@Column(name ="longitude",nullable=false,length=12)
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	} 
	
	@Column(name ="latitude",nullable=false,length=12)
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	} 
	
	@Column(name ="radius",nullable=false,length=6)
	public Integer getRadius() {
		return radius;
	}

	public void setRadius(Integer radius) {
		this.radius = radius;
	} 
	
	@Column(name ="address",nullable=false,length=100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	} 
	
	@Column(name ="warningType",nullable=false,length=4)
	public Integer getWarningType() {
		return warningType;
	}

	public void setWarningType(Integer warningType) {
		this.warningType = warningType;
	} 
	
	@Column(name ="warningPhone",nullable=false,length=15)
	public String getWarningPhone() {
		return warningPhone;
	}

	public void setWarningPhone(String warningPhone) {
		this.warningPhone = warningPhone;
	} 
	
	@Column(name ="mapType",nullable=false,length=1)
	public String getMapType() {
		return mapType;
	}

	public void setMapType(String mapType) {
		this.mapType = mapType;
	} 
	
	@Column(name ="startTime1",nullable=false,length=10)
	public java.sql.Time getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(java.sql.Time startTime1) {
		this.startTime1 = startTime1;
	} 
	
	@Column(name ="endTime1",nullable=false,length=10)
	public java.sql.Time getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(java.sql.Time endTime1) {
		this.endTime1 = endTime1;
	} 
	
	@Column(name ="startTime2",nullable=false,length=10)
	public java.sql.Time getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(java.sql.Time startTime2) {
		this.startTime2 = startTime2;
	} 
	
	@Column(name ="endTime2",nullable=false,length=10)
	public java.sql.Time getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(java.sql.Time endTime2) {
		this.endTime2 = endTime2;
	} 
	
	@Column(name ="startTime3",nullable=false,length=10)
	public java.sql.Time getStartTime3() {
		return startTime3;
	}

	public void setStartTime3(java.sql.Time startTime3) {
		this.startTime3 = startTime3;
	} 
	
	@Column(name ="endTime3",nullable=false,length=10)
	public java.sql.Time getEndTime3() {
		return endTime3;
	}

	public void setEndTime3(java.sql.Time endTime3) {
		this.endTime3 = endTime3;
	} 
	
	@Column(name ="enabled",nullable=false,length=3)
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	} 
	
	@Column(name ="modifyDate",nullable=false,length=19)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	} 
	
	
}