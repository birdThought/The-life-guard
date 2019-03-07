package com.lifeshs.pojo.member.commond;

/**
 *  版权归
 *  TODO 修改指令的数据		userId, imei, eventname, newData
 *  @子类特有参数: oldDate, newData
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午8:24:20
 */
public class ModnumDto extends CommondDto {

	/** 旧的数据  */    
	private String oldDate;
	
	/**
	 * 新的数据
	 */
	private String newData;
	
	
	/**  指令说明  */    
	final String remark = "修改指令";
	
	/**
	 * 指令,1.modnum
	 */
	final String name = "modnum";
	
	/**
	 * 事件类型 once：单次
	 * */    
	final String type = "once";
	
	/**
	 * 操作类型，modify:修改
	 */
	final String op = "modify";
	
	/**
	 * 次数和间隔时间
	 */
	final String repeat = "{}"; // blank
	
	/**
	 * 时间组
	 */
	final String time = "[]";	// blank
	
	/**
	 * 时间组的个数
	 */
	final int timesize = 0;
	
	/**
	 * 内容的个数
	 */
	final int msgsize = 1; 
	
	/**
	 * 发送状态, false未发送, true已发送, 这里统一未发送
	 **/    
	final boolean status = false;

	public String getOldDate() {
		return oldDate;
	}

	public void setOldDate(String oldDate) {
		this.oldDate = oldDate;
	}

	public String getNewData() {
		return newData;
	}

	public void setNewData(String newData) {
		this.newData = newData;
	}

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

	/*public TTerminalCommond getEntity(){
		TTerminalCommond commondEntity = new TTerminalCommond();
		commondEntity.setType(type);
		commondEntity.setName(name);
		commondEntity.setEventName(eventname);
		commondEntity.setOp(op);
		commondEntity.setRepeats(repeat);
		commondEntity.setTime(time);
		commondEntity.setTimeSize(timesize);
		String msg = "[{'new':'" + newData + "'}]";
		if(StringUtils.isNotBlank(oldDate))
			msg = "[{'new':'" + newData + "','old':'" + oldDate +"'}]";
		commondEntity.setMsg(msg);
		commondEntity.setMsgSize(msgsize);
		commondEntity.setStatus(status);
		commondEntity.setCreateDate(new Date());
		commondEntity.setUserId(userId);
		commondEntity.setImei(imei);
		commondEntity.setRemark(remark);
		return commondEntity;
	}*/
	
	public ModnumDto() {
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
