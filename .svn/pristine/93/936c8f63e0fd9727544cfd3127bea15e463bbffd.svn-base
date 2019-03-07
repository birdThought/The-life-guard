package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 上传LOG指令的数据	times, interval, time, timesize, imei, op, userId
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午8:20:26
 */
public class LogDto extends CommondDto {

	/**  指令说明  */    
	final String remark = "上传LOG(日志文件)请求指令";
	
	/**
	 * 二级指令
	 */
	final String eventname = "";
	
	/**
	 * 事件类型 event事件类型
	 * */    
	final String type = "event";
	
	/**
	 * 指令, log
	 */
	final String name = "log";
	
	/**  消息   */    
	final String msg = "[]";
	
	/**
	 * 内容的个数
	 */
	final int msgsize = 0;
	
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public int getTimesize() {
		return timesize;
	}

	public void setTimesize(int timesize) {
		this.timesize = timesize;
	}

	public LogDto() {
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
