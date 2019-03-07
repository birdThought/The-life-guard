package com.lifeshs.service.terminal;

/**
 * 
 *  面向上层的终端设备业务接口(C3)(已废弃）
 *  @author dengfeng  
 *  @DateTime 2016-5-20 上午10:59:55
 */
public interface IC3Terminal {

/*	*//**
	 * 终端登录
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 上午10:38:58
	 *
	 *  @param imei
	 *  @param password
	 *  @return 终端接收JSON串
	 *//*
	public String login(String imei, String password);
	
	*//**
	 * 数据同步，将平台设置同步到终端
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 上午10:32:41
	 *
	 *  @return 终端接收JSON串
	 *//*
	public String datasync(String imei, String password);
	
	*//**
	 * 心跳
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 上午10:32:41
	 *
	 *  @return 终端接收JSON串
	 *//*
	public String heartpackge(String imei, String password);
	
	*//**
	 * 关机
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 上午10:32:41
	 *
	 *  @return 终端接收JSON串
	 *//*
	public String poweroff(String imei,String password);
	
	*//**
	 * 通话记录
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 上午10:32:41
	 *
	 *  @return 终端接收JSON串
	 *//*
	//public String calllog(List<?> entitys);
	
	*//**
	 * 位置信息
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 上午10:32:41
	 *
	 *  @return 终端接收JSON串
	 *//*
	public String location(C3Location entity);
	
	
	*//**
	 * 按照终端通信协议返回Json
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 下午02:18:54
	 *
	 *  @param commonds
	 *  @return 终端接收JSON串
	 *//*
	public String returnJson(String heart,String passwrod,TerminalErrorType errorType, List action);
	
	*//**
	 * 返回错误信息的Json
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 下午02:18:54
	 *
	 *  @param commonds
	 *  @return
	 *//*
	public String returnErrorJson(TerminalErrorType errorType);*/
}
