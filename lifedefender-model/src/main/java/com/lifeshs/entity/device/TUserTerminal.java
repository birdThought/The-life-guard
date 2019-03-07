package com.lifeshs.entity.device;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_user_terminal
 */
 @Table(name = "t_user_terminal", schema = "")
 @SuppressWarnings("serial")
public class TUserTerminal implements Serializable{

		/**绑定用户的终端设备*/
		private Integer id;
		
		/**用户ID*/
		private Integer userId;
		
		/**设备imei*/
		private String imei;
		
		/**设备的备注名称*/
		private String name;
		
		/**设备手机号码*/
		private String mobile;
		
		/**HL03,C3,HL031*/
		private String terminalType;
		
		/**心跳包频率（秒）*/
		private Integer heartFrequency;
		
		/**位置上传频率（秒）*/
		private Integer locationFrequency;
		
		/**电量70%降频频率*/
		private Integer autoFrequency70;
		
		/**电量50%降频频率*/
		private Integer autoFrequency50;
		
		/**电量30%降频频率*/
		private Integer autoFrequency30;
		
		/**运行模式:家长-7，GPS-6，儿童-5，校园-4，上课-3，会议-2，飞行-1，正常-0*/
		private Integer operationMode;
		
		/**状态，离线_0，在线_1*/
		private Integer status;
		
		/**创建日期*/
		private Date createDate;
		
		/**修改时间*/
		private Date modifyDate;
		
		/**监听的用户联系人id*/
		private Integer monitorContactId;
		
	
	public TUserTerminal() {
		super();
	}
	
	public TUserTerminal(Integer id, Integer userId, String imei, String name, String mobile, String terminalType, Integer heartFrequency, Integer locationFrequency, Integer autoFrequency70, Integer autoFrequency50, Integer autoFrequency30, Integer operationMode, Integer status, Date createDate, Date modifyDate, Integer monitorContactId) {
		super();
		this.id = id; 
		this.userId = userId; 
		this.imei = imei; 
		this.name = name; 
		this.mobile = mobile; 
		this.terminalType = terminalType; 
		this.heartFrequency = heartFrequency; 
		this.locationFrequency = locationFrequency; 
		this.autoFrequency70 = autoFrequency70; 
		this.autoFrequency50 = autoFrequency50; 
		this.autoFrequency30 = autoFrequency30; 
		this.operationMode = operationMode; 
		this.status = status; 
		this.createDate = createDate; 
		this.modifyDate = modifyDate; 
		this.monitorContactId = monitorContactId; 
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
	
	@Column(name ="imei",nullable=true,length=32)
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	} 
	
	@Column(name ="name",nullable=false,length=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	@Column(name ="mobile",nullable=false,length=18)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	} 
	
	@Column(name ="terminalType",nullable=false,length=10)
	public String getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	} 
	
	@Column(name ="heartFrequency",nullable=false,length=6)
	public Integer getHeartFrequency() {
		return heartFrequency;
	}

	public void setHeartFrequency(Integer heartFrequency) {
		this.heartFrequency = heartFrequency;
	} 
	
	@Column(name ="locationFrequency",nullable=false,length=6)
	public Integer getLocationFrequency() {
		return locationFrequency;
	}

	public void setLocationFrequency(Integer locationFrequency) {
		this.locationFrequency = locationFrequency;
	} 
	
	@Column(name ="autoFrequency70",nullable=false,length=6)
	public Integer getAutoFrequency70() {
		return autoFrequency70;
	}

	public void setAutoFrequency70(Integer autoFrequency70) {
		this.autoFrequency70 = autoFrequency70;
	} 
	
	@Column(name ="autoFrequency50",nullable=false,length=6)
	public Integer getAutoFrequency50() {
		return autoFrequency50;
	}

	public void setAutoFrequency50(Integer autoFrequency50) {
		this.autoFrequency50 = autoFrequency50;
	} 
	
	@Column(name ="autoFrequency30",nullable=false,length=6)
	public Integer getAutoFrequency30() {
		return autoFrequency30;
	}

	public void setAutoFrequency30(Integer autoFrequency30) {
		this.autoFrequency30 = autoFrequency30;
	} 
	
	@Column(name ="operationMode",nullable=false,length=4)
	public Integer getOperationMode() {
		return operationMode;
	}

	public void setOperationMode(Integer operationMode) {
		this.operationMode = operationMode;
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
	
	@Column(name ="monitorContactId",nullable=false,length=11)
	public Integer getMonitorContactId() {
		return monitorContactId;
	}

	public void setMonitorContactId(Integer monitorContactId) {
		this.monitorContactId = monitorContactId;
	}

    @Override
    public String toString() {
        return "TUserTerminal [id=" + id + ", userId=" + userId + ", imei=" + imei + ", name=" + name + ", mobile="
                + mobile + ", terminalType=" + terminalType + ", heartFrequency=" + heartFrequency
                + ", locationFrequency=" + locationFrequency + ", autoFrequency70=" + autoFrequency70
                + ", autoFrequency50=" + autoFrequency50 + ", autoFrequency30=" + autoFrequency30 + ", operationMode="
                + operationMode + ", status=" + status + ", createDate=" + createDate + ", modifyDate=" + modifyDate
                + ", monitorContactId=" + monitorContactId + "]";
    } 
}