package com.lifeshs.entity.device;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_terminal_commond
 */
 @Table(name = "t_terminal_commond", schema = "")
 @SuppressWarnings("serial")
public class TTerminalCommond implements Serializable{

		/**ID*/
		public Integer id;
		
		/**用户ID*/
		public Integer userId;
		
		/**imei号*/
		public String imei;
		
		/**实际发送时间*/
		public Date sendTime;
		
		/**指令,1.modnum,2.sendsms*/
		public String name;
		
		/**二级指令*/
		public String eventName;
		
		/**次数和间隔时间*/
		public String repeats;
		
		/**时间组*/
		public String time;
		
		/**时间组的个数*/
		public Integer timeSize;
		
		/**内容*/
		public String msg;
		
		/**内容的个数*/
		public Integer msgSize;
		
		/**事件类型 once：单次、event：事件（多次）*/
		public String type;
		
		/**操作类型，add:增加,如果已存在同名事件则覆盖，del:删除此事件，modify:修改*/
		public String op;
		
		/**0：未发送、1：已发送*/
		public Boolean status;
		
		/**指令说明*/
		public String remark;
		
		/**创建时间*/
		public Date createDate;
		
	
	public TTerminalCommond() {
		super();
	}
	
	public TTerminalCommond(Integer id, Integer userId, String imei, Date sendTime, String name, String eventName, String repeats, String time, Integer timeSize, String msg, Integer msgSize, String type, String op, Boolean status, String remark, Date createDate) {
		super();
		this.id = id; 
		this.userId = userId; 
		this.imei = imei; 
		this.sendTime = sendTime; 
		this.name = name; 
		this.eventName = eventName; 
		this.repeats = repeats; 
		this.time = time; 
		this.timeSize = timeSize; 
		this.msg = msg; 
		this.msgSize = msgSize; 
		this.type = type; 
		this.op = op; 
		this.status = status; 
		this.remark = remark; 
		this.createDate = createDate; 
	}
	
	@Column(name ="id",nullable=true,length=32)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	} 
	
	@Column(name ="userId",nullable=true,length=32)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	} 
	
	@Column(name ="imei",nullable=false,length=36)
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	} 
	
	@Column(name ="sendTime",nullable=false,length=19)
	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	} 
	
	@Column(name ="name",nullable=false,length=16)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
	
	@Column(name ="eventName",nullable=false,length=16)
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	} 
	
	@Column(name ="repeats",nullable=false,length=50)
	public String getRepeats() {
		return repeats;
	}

	public void setRepeats(String repeats) {
		this.repeats = repeats;
	} 
	
	@Column(name ="time",nullable=false,length=200)
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	} 
	
	@Column(name ="timeSize",nullable=false,length=4)
	public Integer getTimeSize() {
		return timeSize;
	}

	public void setTimeSize(Integer timeSize) {
		this.timeSize = timeSize;
	} 
	
	@Column(name ="msg",nullable=false,length=200)
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	} 
	
	@Column(name ="msgSize",nullable=false,length=4)
	public Integer getMsgSize() {
		return msgSize;
	}

	public void setMsgSize(Integer msgSize) {
		this.msgSize = msgSize;
	} 
	
	@Column(name ="type",nullable=false,length=5)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	} 
	
	@Column(name ="op",nullable=false,length=6)
	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	} 
	
	@Column(name ="status",nullable=false,length=1)
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	} 
	
	@Column(name ="remark",nullable=false,length=200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	} 
	
	@Column(name ="createDate",nullable=false,length=19)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	} 
	
	
}
