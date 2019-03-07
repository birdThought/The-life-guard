package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 发送SMS信息数据传输对象
 *  @可修改参数: imei,content,number,userId
 *  @子类特有参数: content,number
 *  @author wenxian.cai 
 *  @datetime 2016年12月20日上午9:10:39
 */
public class SendSMSDto extends CommondDto {

	/**
	 * SMS内容
	 */
	private String content;
	
	/**
	 * 需要发送的手机号
	 */
	private String number;
	
	/**
	 * 指令说明 
	 */    
	final String remark = "发送SMS信息请求指令";
	
	/**
	 * Action名称，这里为sendsms
	 */
	final String name = "sendsms";
	
	/**
	 * Action类型,这里为终端接收到命令立即执行
	 */
	final String type = "once";
	
	/**
	 * 二级指令
	 */
	final String eventname = "";
	
	/**
	 * 操作类型
	 */
	final String op = "";
	
	/**
	 * 次数和间隔时间
	 */
	final String repeat = "{}";		// 空
	
	/**
	 * 时间组
	 */
	final String time = "[]";			// 空
	
	/**
	 * 时间组的个数
	 */
	final int timesize = 0;
	
	/**
	 * 消息数组元素个数，这里为1
	 */
	final int msgsize = 1;
	
	/**
	 * 发送状态, false未发送, true已发送, 这里统一未发送
	 **/    
	final boolean status = false;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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

	public SendSMSDto() {
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
