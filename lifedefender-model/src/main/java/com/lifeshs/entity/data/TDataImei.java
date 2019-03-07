package com.lifeshs.entity.data;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_data_imei
 */
 @Table(name = "t_data_imei", schema = "")
 @SuppressWarnings("serial")
public class TDataImei implements Serializable{

		/**设备入库表*/
		public Long id;
		
		/**串号*/
		public String imei;
		
		/**默认密码(保留)*/
		public String password;
		
		/**状态,在库_0,已卖出_1,已绑定_2*/
		public Integer status;
		
		/**入库时间*/
		public Date createDate;
		
	
	public TDataImei() {
		super();
	}
	
	public TDataImei(Long id, String imei, String password, Integer status, Date createDate) {
		super();
		this.id = id; 
		this.imei = imei; 
		this.password = password; 
		this.status = status; 
		this.createDate = createDate; 
	}
	
	@Column(name ="id",nullable=true,length=20)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	} 
	
	@Column(name ="imei",nullable=false,length=32)
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	} 
	
	@Column(name ="password",nullable=false,length=18)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	
}
