package com.lifeshs.entity.member;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_user_operationDetail
 */
 @Table(name = "t_user_operationDetail", schema = "")
 @SuppressWarnings("serial")
public class TUserOperationDetail implements Serializable{

		/**设备运行模式详细配置*/
		public Integer id;
		
		/**用户设备id*/
		public Integer userDeviceId;
		
		/**运行模式:家长-7，GPS-6，儿童-5，校园-4，上课-3，会议-2，飞行-1，正常-0*/
		public Integer operationMode;
		
		/**星期设定,从低到高代表星期一到星期日,开启_1,关闭_0*/
		public String weeks;
		
		/**提醒时间1*/
		public String startTime1;
		
		public String endTime1;
		
		/**提醒时间2*/
		public String startTime2;
		
		public String endTime2;
		
		/**提醒时间3*/
		public String startTime3;
		
		public String endTime3;
		
		/*是否选择某个时间段*/
		public String enable;
		
		/**创建日期*/
		public Date createDate;
		
		/**更新日期*/
		public Date modifyDate;
		
	
	public TUserOperationDetail() {
		super();
	}
	
	public TUserOperationDetail(Integer id, Integer userDeviceId, Integer operationMode, String weeks, String time1, String time2, String time3, Date createDate, Date modifyDate) {
		super();
		this.id = id; 
		this.userDeviceId = userDeviceId; 
		this.operationMode = operationMode; 
		this.weeks = weeks; 
		/*this.time1 = time1; 
		this.time2 = time2; 
		this.time3 = time3; */
		this.createDate = createDate; 
		this.modifyDate = modifyDate; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	@Column(name ="userDeviceId",nullable=false,length=11)
	public Integer getUserDeviceId() {
		return userDeviceId;
	}

	public void setUserDeviceId(Integer userDeviceId) {
		this.userDeviceId = userDeviceId;
	} 
	
	@Column(name ="operationMode",nullable=false,length=4)
	public Integer getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(Integer operationMode) {
		this.operationMode = operationMode;
	} 
	
	@Column(name ="weeks",nullable=false,length=7)
	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	
	@Column(name ="enable",nullable=false,length=3)
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	@Column(name ="startTime1",nullable=false,length=5)
	public String getStartTime1() {
		return startTime1;
	}

	public void setStartTime1(String startTime1) {
		this.startTime1 = startTime1;
	}
	
	@Column(name ="endTime1",nullable=false,length=5)
	public String getEndTime1() {
		return endTime1;
	}

	public void setEndTime1(String endTime1) {
		this.endTime1 = endTime1;
	}
	
	@Column(name ="startTime2",nullable=false,length=5)
	public String getStartTime2() {
		return startTime2;
	}

	public void setStartTime2(String startTime2) {
		this.startTime2 = startTime2;
	}
	
	@Column(name ="endTime2",nullable=false,length=5)
	public String getEndTime2() {
		return endTime2;
	}

	public void setEndTime2(String endTime2) {
		this.endTime2 = endTime2;
	}
	
	@Column(name ="startTime3",nullable=false,length=5)
	public String getStartTime3() {
		return startTime3;
	}

	public void setStartTime3(String startTime3) {
		this.startTime3 = startTime3;
	}
	
	@Column(name ="endTime3",nullable=false,length=5)
	public String getEndTime3() {
		return endTime3;
	}

	public void setEndTime3(String endTime3) {
		this.endTime3 = endTime3;
	}
	
	@Column(name ="createDate",nullable=false,length=19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	} 
	
	@Column(name ="modifyDate",nullable=false,length=19)
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	} 
	
	
}