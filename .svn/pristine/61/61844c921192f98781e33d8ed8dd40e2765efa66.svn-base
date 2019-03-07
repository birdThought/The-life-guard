package com.lifeshs.entity.member;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_user_blackWhiteList
 */
 @Table(name = "t_user_blackWhiteList", schema = "")
 @SuppressWarnings("serial")
public class TUserBlackWhiteList implements Serializable{

		/**用户黑白名单*/
		public Integer id;
		
		/**用户设备id*/
		public Integer userDeviceId;
		
		/**备注名称*/
		public String name;
		
		/**类型 1：白名单，2：黑名单*/
		public Integer type;
		
		/**接听限制号码*/
		public String limited;
		
		/**创建时间*/
		public Date createDate;
		
	
	public TUserBlackWhiteList() {
		super();
	}
	
	public TUserBlackWhiteList(Integer id, Integer userDeviceId, String name, Integer type, String limited, Date createDate) {
		super();
		this.id = id; 
		this.userDeviceId = userDeviceId; 
		this.name = name; 
		this.type = type; 
		this.limited = limited; 
		this.createDate = createDate; 
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
	
	@Column(name ="name",nullable=false,length=10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	@Column(name ="type",nullable=false,length=4)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	} 
	
	@Column(name ="limited",nullable=false,length=15)
	public String getLimited() {
		return limited;
	}

	public void setLimited(String limited) {
		this.limited = limited;
	} 
	
	@Column(name ="createDate",nullable=false,length=19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	} 
	
	
}