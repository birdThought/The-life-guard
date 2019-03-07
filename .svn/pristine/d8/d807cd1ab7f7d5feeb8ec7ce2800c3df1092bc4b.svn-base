package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 监听指令的数据		msg, msgsize, imei, userId
 *  @author wenxian.cai 
 *  @datetime 2016年12月20日上午9:04:34
 */
public class MoniterDto extends CommondDto {

	/**  指令说明  */    
	final String remark = "监听请求指令";
	
	/**
	 * 次数和间隔时间
	 */
	final String repeat = "{}";
	
	/**
	 * 时间组
	 */
	final String time = "[]";
	
	/**
	 * 时间组的个数
	 */
	final int timesize = 0;
	
	/**
	 * 事件名，此处为空
	 */    
	final String eventname = "";
	
	/**
	 * 指令, moniter
	 */
	final String name = "monitor";
	
	/**
	 * 事件类型 once:立即执行
	 * */    
	final String type = "once";
	
	/**
	 * 操作类型，此处为空
	 */
	final String op = "";
	
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

	public MoniterDto() {
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
