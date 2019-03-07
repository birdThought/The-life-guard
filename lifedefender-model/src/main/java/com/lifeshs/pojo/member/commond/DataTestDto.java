package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 测量指令的数据		eventname, type, times, interval, time, timesize, imei, op, userId
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午8:15:48
 */
public class DataTestDto extends CommondDto {

	/**  指令说明  */    
	final String remark = "通讯录、亲情号码、白名单、黑名单设置指令";
	
	/**
	 * 二级指令:heartRate,temperature,ecg,bloodPressure,all
	 */
//	private String eventname;
	
	/**
	 * 事件类型:once为立即检测数据，之后并上传;event定时检测并上传数据
	 * */    
//	private String type;
	
	/**
	 * 指令, datatest
	 */
	final String name = "datatest";
	
	/**
	 * 操作类型，add:增加,如果已存在同名事件则覆盖，del:删除此事件，modify:修改
	 */
//	private String op;
	
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

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public DataTestDto() {
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
