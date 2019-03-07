package com.lifeshs.service.terminal;

import java.util.List;
import java.util.Map;

import com.lifeshs.pojo.mina.WatchCommand;

public interface IGPRSWatchService {

	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午10:49:18
	 *  @serverComment 链路保持
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String lk(WatchCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午10:55:23
	 *  @serverComment 位置数据上报
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public void ud(WatchCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午10:55:32
	 *  @serverComment 盲点补传数据
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public void ud2(WatchCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午10:56:17
	 *  @serverComment 报警数据上传
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String al(WatchCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午10:56:48
	 *  @serverComment 请求地址指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String wad(WatchCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 上午10:57:15
	 *  @serverComment 请求经纬度指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String wg(WatchCommand data) throws Exception;
	
	/* 服务器发送指令到终端手表 */
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:21:51
	 *  @serverComment 数据上传间隔设置
	 *
	 *	@param imei 串号
	 *  @param time 时间间隔
	 *  @return
	 *  @throws Exception
	 */
	public String sendUploadCommand(String imei, int time) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:23:11
	 *  @serverComment 控制密码设置
	 *
	 *	@param imei 串号
	 *  @param password 密码
	 *  @return
	 *  @throws Exception
	 */
	public String sendControlPasswordCommand(String imei, String password) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:26:27
	 *  @serverComment 服务注解
	 *
	 *  @param imei 串号
	 *  @param mobile 短信号码
	 *  @param content 短信内容
	 *  @return
	 *  @throws Exception
	 */
	public String sendSMSCommand(String imei, String mobile, String content) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:26:59
	 *  @serverComment 监听
	 *
	 *  @param imei 串号
	 *  @return
	 *  @throws Exception
	 */
	public String sendMonitorCommand(String imei) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:28:52
	 *  @serverComment SOS号码设置
	 *
	 *  @param imei 串号
	 *  @param sosCount 第N个SOS号码，目前最多设置3个，即sosCount取值范围{1&lt;sosCount&lt;3}
	 *  @param sosNumber 电话号码
	 *  @return
	 *  @throws Exception
	 */
	public String sendSOSCommand(String imei, int sosCount, String sosNumber) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:32:22
	 *  @serverComment IP端口设置
	 *
	 *  @param imei 串号
	 *  @param ipOrURL ip或域名
	 *  @param port 端口号
	 *  @return
	 *  @throws Exception
	 */
	public String sendIpAndPortCommand(String imei, String ipOrURL, int port) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:34:56
	 *  @serverComment 关机指令
	 *
	 *  @param imei 串号
	 *  @return
	 *  @throws Exception
	 */
	public String sendPowerOffCommand(String imei) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:36:43
	 *  @serverComment 计步时间段设置
	 *
	 *  @param imei 串号
	 *  @param interval1	时间段1
	 *  @param interval2	时间段2
	 *  @param interval3	时间段3
	 *  @return
	 *  @throws Exception
	 */
	public String sendCountStepCommand(String imei, String interval1, String interval2, String interval3) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月2日 下午2:41:37
	 *  @serverComment 电话本设置指令
	 *
	 *  @param imei 串号
	 *  @param contactCount 本次发送电话本设置属于第N次请求，如果发送10个电话号码，前5个属于第一次请求，后5个属于第二次请求，以此类推
	 *  @param list list的size最大为5，超出则只对前5个数据进行处理，其数据结构为[index=&gt;{"name":&lt;"name"&gt;,"number":&lt;"number"&gt;}]
	 *  @return
	 *  @throws Exception
	 */
	public String sendContactCommand(String imei, int contactCount, List<Map<String, String>> list) throws Exception;
}
