package com.lifeshs.entity.log;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_log_login
 */
@Table(name = "t_log_login", schema = "")
@SuppressWarnings("serial")
public class TLogLogin implements Serializable{
	/**日志_登录记录*/
	private Integer id;
	
	/**登录时间*/
	private Date loginTime;
	
	/**用户类型：1_会员，2_机构员工*/
	private Integer userType;
	
	/**登录用户ID*/
	private Integer userId;
	
	/**登录用户名*/
	private String userName;
	
	/**用户的机构ID(只记录机构员工)*/
	private Integer orgId;
	
	/**终端类型：ios,android,browse*/
	private String terminalType;
	
	/**登录IP*/
	private String ip;
	
	public TLogLogin() {
		super();
	}
	
	public TLogLogin(Integer id, Date loginTime, Integer userType, Integer userId, String userName, Integer orgId, String terminalType, String ip) {
		super();
		this.id = id; 
		this.loginTime = loginTime; 
		this.userType = userType; 
		this.userId = userId; 
		this.userName = userName; 
		this.orgId = orgId; 
		this.terminalType = terminalType; 
		this.ip = ip; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="loginTime",nullable=true,length=19)
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	@Column(name ="userType",nullable=false,length=4)
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	@Column(name ="userId",nullable=false,length=11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name ="userName",nullable=false,length=20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name ="orgId",nullable=false,length=11)
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	@Column(name ="terminalType",nullable=false,length=10)
	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	@Column(name ="ip",nullable=false,length=17)
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}