package com.lifeshs.pojo.member.commond;

/**
 *  清除SIM卡中SMS内容指令的数据
 *  <p>可以修改的参数有：
 *  <p>imei, userId
 *  @author yuhang.weng  
 *  @DateTime 2016年5月27日 下午2:47:49
 */
/**
 *  版权归
 *  TODO 清除SIM卡中SMS内容指令的数据
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午8:11:38
 */
public class ClearSMSDto extends CommondDto {
	
	/**  指令说明  */    
	final String remark = "清除SIM卡中SMS内容指令";
	
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
	 * 指令, clearsms
	 */
	final String name = "clearsms";
	
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

	public ClearSMSDto() {
		super.eventname = eventname;
		super.imei = imei;
		super.msg = msg;
		super.msgsize = msgsize;
		super.name = name;
		super.op = op;
		super.remark = remark;
		super.status = status;
		super.time = time;
		super.timesize = timesize;
		super.type = type;
		super.userId = userId;
	}
}
