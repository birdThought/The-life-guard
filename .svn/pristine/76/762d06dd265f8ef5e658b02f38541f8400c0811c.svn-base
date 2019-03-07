package com.lifeshs.entity.huanxin;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_huanxin_user
 */
@Table(name = "t_huanxin_user", schema = "")
@SuppressWarnings("serial")
public class THuanxinUser implements Serializable{
	
	private Integer id;
	
	/**用户id*/
	private Integer userId;
	
	/**环信userName*/
	private String huanxinCode;
	
	/**环信密码*/
	private String pwd;
	
	public THuanxinUser() {
		super();
	}
	
	public THuanxinUser(Integer id, Integer userId, String huanxinCode, String pwd) {
		super();
		this.id = id; 
		this.userId = userId; 
		this.huanxinCode = huanxinCode; 
		this.pwd = pwd; 
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
	@Column(name ="huanxinCode",nullable=false,length=10)
	public String getHuanxinCode() {
		return huanxinCode;
	}

	public void setHuanxinCode(String huanxinCode) {
		this.huanxinCode = huanxinCode;
	}
	@Column(name ="pwd",nullable=false,length=32)
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}