package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 可修改参数:times, interval, time, timesize, imei, op, userId
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午5:18:27
 */
public class RunModeDto extends CommondDto {

	/**  用户id  */    
//	private int userId;
	
	/**  设备imei  */    
//	private String imei;
	
	/*
	 * 运行模式:家长-7，GPS-6，儿童-5，校园-4，上课-3，会议-2，飞行-1，正常-0
	 */
//	private int runmode;
	
	protected String msg = "[]";
	
	protected int msgsize = 0;
	
	/**  指令说明  */    
	final String remark = "上传运行模式设定指令";
	
	/**
	 * 二级指令
	 */
	final String eventname = "setmode";
	
	/**
	 * 事件类型 event事件类型
	 * */    
//	final String type = "event";
	
	/*
	 * 重复间隔
	 */
//	final String repeat = "{}";
	
	/**  次数  */    
//	private int times;
	
	/**  间隔  */    
//	private int interval;
	
	/**
	 * 时间组
	 */
//	private String time;
	
	/**
	 * 指令, log
	 */
	final String name = "runmode";
	
	/**
	 * 操作类型，add添加,del删除,modify修改
	 */
	private String op="modify";
	
	/**
	 * 时间组的个数
	 */
	private int timesize=1;
	
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
	
	public String getRepeats() {
		return repeat;
	}

	public void setRepeats(String repeat) {
		this.repeat = repeat;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public RunModeDto() {
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
