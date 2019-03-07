package com.lifeshs.entity.member;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_user_notice
 */
@Table(name = "t_user_notice", schema = "")
@SuppressWarnings("serial")
public class TUserNotice implements Serializable{
	/**设备提醒*/
	private Integer id;
	
	/**用户设备id，0表示该设备为健康包手环*/
	private Integer userDeviceId;
	
	/**用户ID*/
	private Integer userId;
	
	/**星期设定,从低到高代表星期一到星期日,开启_1,关闭_0*/
	private String weeks;
	
	/**提醒内容*/
	private String content;
	
	/**提醒时间，小时:分钟*/
	private String time;
	
	/**状态，启用_1，关闭_0*/
	private Integer status;
	
	/**创建日期*/
	private Date createDate;
	
	/**修改日期*/
	private Date modifyDate;
	
	/**间隔分钟0表示不重复提醒*/
	private Double intervalM;
	
	public TUserNotice() {
		super();
	}
	
	public TUserNotice(Integer id, Integer userDeviceId, Integer userId, String weeks, String content, String time, Integer status, Date createDate, Date modifyDate, Double intervalM) {
		super();
		this.id = id; 
		this.userDeviceId = userDeviceId; 
		this.userId = userId; 
		this.weeks = weeks; 
		this.content = content; 
		this.time = time; 
		this.status = status; 
		this.createDate = createDate; 
		this.modifyDate = modifyDate; 
		this.intervalM = intervalM; 
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
	@Column(name ="userId",nullable=false,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name ="weeks",nullable=false,length=7)
	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	@Column(name ="content",nullable=false,length=20)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Column(name ="time",nullable=false,length=5)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	@Column(name ="status",nullable=false,length=4)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	@Column(name ="intervalM",nullable=false,length=5)
	public Double getIntervalM() {
		return intervalM;
	}

	public void setIntervalM(Double intervalM) {
		this.intervalM = intervalM;
	}
	
}