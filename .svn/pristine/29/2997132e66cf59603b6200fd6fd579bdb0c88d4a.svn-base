package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 定时执行指令的数据
 *  @可修改参数: eventname, times, interval, time, timesize, imei, op, userId
 *  @子类特有参数: interval, time
 *  @author wenxian.cai 
 *  @datetime 2016年12月20日上午9:14:14
 */
public class TimerDto extends CommondDto {

	
	/**  指令说明  */    
	final String remark = "定时执行请求指令";
	
	/**
	 * sport(运动提醒)  eat(吃药提醒)   alarm(闹钟提醒)
	 */    
//	private String eventname;
	
	/**
	 * 指令, playvoice
	 */
	final String name = "playvoice";	//timer
	
	/**
	 * 事件类型 event：事件执行
	 * */    
	final String type = "event";
	
	/**
	 * 操作类型，add:增加,如果已存在同名事件则覆盖，del:删除此事件，modify:修改
	 */
//	private String op;
	
	/**  消息  */    
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

	public int getTimesize() {
		return timesize;
	}

	public void setTimesize(int timesize) {
		this.timesize = timesize;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
	
	public String getRepeats() {
		return repeat;
	}

	public void setRepeats(String repeat) {
		this.repeat = repeat;
	}
	

	public TimerDto() {
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
