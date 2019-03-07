package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 通讯录、亲情号码、白名单、黑名单设置指令的数据
 *  @子类特有参数: family, contact, number, relat
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午8:14:55
 */
public class ContactDto extends CommondDto {

	/*
	 * 是否为亲情号码	是(yes)，否(no)
	 */
	private String family;
	
	/*
	 * 通讯录姓名
	 */
	private String contactName;
	
	/*
	 * 电话号码
	 */
	private String number;
	
	/*
	 * 黑名单(black) 白名单(white)  都不是(no)   sos求救(sos)
	 */
	private String relat;
	
	/**  指令说明  */    
	final String remark = "亲情号码、黑白名单设置";
	
	/**
	 * 二级指令
	 */
	final String eventname = "";
	
	/**
	 * 事件类型 once：单次
	 * */    
	final String type = "once";
	
	/**
	 * 次数和间隔时间
	 */
	final String repeat = "{}";
	
	/**
	 * 时间组
	 */
	final String time = "[]";
	
	/**
	 * 指令, contact
	 */
	final String name = "contact";
	
	/**
	 * 操作类型，此处为空
	 */
	final String op = "";
	
	/**
	 * 时间组的个数
	 */
	final int timesize = 0;
	
	/**
	 * 发送状态, false未发送, true已发送, 这里统一未发送
	 **/    
	final boolean status = false;
	

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRelat() {
		return relat;
	}

	public void setRelat(String relat) {
		this.relat = relat;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getMsgsize() {
		return msgsize;
	}

	public void setMsgsize(int msgsize) {
		this.msgsize = msgsize;
	}

	public ContactDto() {
		super.eventname = eventname;
		super.imei = imei;
//		super.interval = interval;
		super.msg = msg;
		super.msgsize = msgsize;
		super.name = name;
		super.op = op;
		super.remark = remark;
		super.status = status;
		super.time = time;
//		super.times = times;
		super.timesize = timesize;
		super.type = type;
		super.userId = userId;
		super.repeat = repeat;
	}

	
}
