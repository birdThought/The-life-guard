package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 位置上传指令的数据	eventname, type, times, interval, time, timesize, imei, op
 *  @子类特有参数： times, interval
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午8:19:16
 */
public class LocationDto extends CommondDto {

	/*
	 * 重复次数
	 */
	private String times;
	
	/*
	 * 重复间隔时间(单位s)
	 */
	private String interval;
	
	/*
	 * action类型
	 */
	final String type = "event";
	/**  指令说明  */    
	final String remark = "位置上传指令";
	
	/**
	 * 指令, location
	 */
	final String name = "location";
	
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

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
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

	public String getRepeat() {
		return repeat;
	}

	public void setRepeat(String repeat) {
		this.repeat = repeat;
	}
	
	public int getTimesize() {
		return timesize;
	}

	public void setTimesize(int timesize) {
		this.timesize = timesize;
	}

	public LocationDto() {
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
