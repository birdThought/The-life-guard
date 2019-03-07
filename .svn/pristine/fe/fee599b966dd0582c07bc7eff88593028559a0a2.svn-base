package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 功能自定义指令的数据  	eventname, msg, msgsize, imei, userId
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午8:23:01
 */
public class ModeDefineDto extends CommondDto {

	/**  指令说明  */    
	final String remark = "功能自定义指令";
	
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
	 * 指令, modedefine
	 */
	final String name = "modedefine";
	
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

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
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

	public ModeDefineDto() {
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
	}
}
