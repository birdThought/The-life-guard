package com.lifeshs.entity.member;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * t_user_measurement_reminder
 */
@Table(name = "t_user_measurement_reminder", schema = "")
@SuppressWarnings("serial")
public class TUserMeasurementReminder implements Serializable{
	
	private Integer reminderId;
	
	/**用户ID*/
	private Integer userId;
	
	/**测量短信提醒状态 0-关闭 1-开启*/
	private Integer smsSwitch;
	
	/**测量推送提醒状态 0-关闭 1-开启*/
	private Integer pushSwitch;
	
	public TUserMeasurementReminder() {
		super();
	}
	
	public TUserMeasurementReminder(Integer reminderId, Integer userId, Integer smsSwitch, Integer pushSwitch) {
		super();
		this.reminderId = reminderId; 
		this.userId = userId; 
		this.smsSwitch = smsSwitch; 
		this.pushSwitch = pushSwitch; 
	}
	
	@Column(name ="reminderId",nullable=true,length=11)
	public Integer getReminderId() {
		return reminderId;
	}

	public void setReminderId(Integer reminderId) {
		this.reminderId = reminderId;
	}
	@Column(name ="userId",nullable=true,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name ="smsSwitch",nullable=false,length=2)
	public Integer getSmsSwitch() {
		return smsSwitch;
	}

	public void setSmsSwitch(Integer smsSwitch) {
		this.smsSwitch = smsSwitch;
	}
	@Column(name ="pushSwitch",nullable=false,length=2)
	public Integer getPushSwitch() {
		return pushSwitch;
	}

	public void setPushSwitch(Integer pushSwitch) {
		this.pushSwitch = pushSwitch;
	}
	
}
