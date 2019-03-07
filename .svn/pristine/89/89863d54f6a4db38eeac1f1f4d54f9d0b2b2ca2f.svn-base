package com.lifeshs.entity.member;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * t_user_measurement_reminder_detail
 */
@Table(name = "t_user_measurement_reminder_detail", schema = "")
@SuppressWarnings("serial")
public class TUserMeasurementReminderDetail implements Serializable{
	/**提醒id*/
	private Integer reminderDetailId;
	
	/**用户ID*/
	private Integer userId;
	
	/**提醒时间*/
	private String reminderTime;
	
	/**提醒周期*/
	private String repeatCycle;
	
	/**关联设备名称*/
	private String devices;
	
	/**提醒状态 0-关闭 1-开启*/
	private Integer reminderStatus;
	
	
	private Date createTime;


	private Date updateTime;


	private String remark;

	public TUserMeasurementReminderDetail() {
		super();
	}

	public TUserMeasurementReminderDetail(Integer reminderDetailId, Integer userId, String reminderTime, String repeatCycle, String devices, Integer reminderStatus, Date createTime, Date updateTime, String remark) {
		super();
		this.reminderDetailId = reminderDetailId;
		this.userId = userId;
		this.reminderTime = reminderTime;
		this.repeatCycle = repeatCycle;
		this.devices = devices;
		this.reminderStatus = reminderStatus;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.remark = remark;
	}

	@Column(name ="reminderDetailId",nullable=true,length=11)
	public Integer getReminderDetailId() {
		return reminderDetailId;
	}

	public void setReminderDetailId(Integer reminderDetailId) {
		this.reminderDetailId = reminderDetailId;
	}
	@Column(name ="userId",nullable=false,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name ="reminderTime",nullable=false,length=10)
	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}
	@Column(name ="repeatCycle",nullable=false,length=15)
	public String getRepeatCycle() {
		return repeatCycle;
	}

	public void setRepeatCycle(String repeatCycle) {
		this.repeatCycle = repeatCycle;
	}
	@Column(name ="devices",nullable=false,length=500)
	public String getDevices() {
		return devices;
	}

	public void setDevices(String devices) {
		this.devices = devices;
	}
	@Column(name ="reminderStatus",nullable=false,length=2)
	public Integer getReminderStatus() {
		return reminderStatus;
	}

	public void setReminderStatus(Integer reminderStatus) {
		this.reminderStatus = reminderStatus;
	}
	@Column(name ="createTime",nullable=false,length=19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name ="updateTime",nullable=false,length=19)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name ="remark",nullable=false,length=500)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
