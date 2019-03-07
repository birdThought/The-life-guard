package com.lifeshs.pojo.member.commond;

import java.util.Date;

import com.lifeshs.entity.device.TTerminalCommond;

/**
 *  版权归
 *  TODO 指令action父类
 *  @author wenxian.cai 
 *  @datetime 2016年12月19日下午4:23:57
 */
public class CommondDto {
	
	/**  用户id  */    
	protected int userId;
	
	/**  设备imei  */    
	protected String imei;
	
	/**  指令说明  */    
	protected String remark;
	
	/*
	 * 次数和间隔时间
	 */
	protected String repeat;
	
	/**
	 * 二级指令
	 */
	protected String eventname;
	
	/**
	 * 事件类型 event事件类型
	 * */    
	protected String type;
	
	/**  次数  *//*    
	protected int times;
	
	*//**  间隔  *//*    
	protected int interval;*/
	
	/**
	 * 时间组
	 */
	protected String time;
	
	/**
	 * 指令, log
	 */
	protected String name;
	
	/**
	 * 操作类型，add添加,del删除,modify修改
	 */
	protected String op;
	
	/**
	 * 时间组的个数
	 */
	protected int timesize;
	
	/**  消息   */    
	protected String msg;
	
	/**
	 * 内容的个数
	 */
	protected int msgsize;
	
	/**
	 * 发送状态, false未发送, true已发送, 这里统一未发送
	 **/    
	protected boolean status;
	
	/*
	 * 提供给外部使用
	 */
	public TTerminalCommond getEntity() {
		TTerminalCommond commondEntity = new TTerminalCommond();
		commondEntity.setType(type);
		commondEntity.setName(name);
		commondEntity.setEventName(eventname);
		commondEntity.setOp(op);
		commondEntity.setRepeats(repeat);
		commondEntity.setTime(time);
		commondEntity.setTimeSize(timesize);
		commondEntity.setMsg(msg);
		commondEntity.setMsgSize(msgsize);
		commondEntity.setStatus(status);
		commondEntity.setCreateDate(new Date());
		commondEntity.setUserId(userId);
		commondEntity.setImei(imei);
		commondEntity.setRemark(remark);
		return commondEntity;
	}
}
