package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 关机控制指令的数据		
 *  @可修改参数:imei, userId
 *  @author wenxian.cai 
 *  @datetime 2016年12月20日上午9:05:56
 */
public class PowerOffDto extends CommondDto {

	/**  指令说明  */    
	final String remark = "关机指令";
	
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
	 * 指令, poweroff
	 */
	final String name = "poweroff";
	
	/**
	 * 操作类型，此处为空
	 */
	final String op = "";
	
	/**
	 * 时间组的个数
	 */
	final int timesize = 0;
	
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

	public PowerOffDto() {
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
