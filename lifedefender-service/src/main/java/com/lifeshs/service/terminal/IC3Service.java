package com.lifeshs.service.terminal;

import com.lifeshs.pojo.C3.C3Location;
import com.lifeshs.common.constants.common.TerminalErrorType;

/**
 *  版权归
 *  TODO C3数据处理接口
 *  @author duosheng.mo  
 *  @DateTime 2016年5月26日 下午4:28:28
 */
public interface IC3Service {

	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月30日 下午3:38:32
	 *  @serverComment 错误信息返回
	 *
	 *  @param errorType
	 *  @return
	 */
	public String returnErrorJson(TerminalErrorType errorType);
	
	/**
	 * 终端登录
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月30日 下午3:38:32
	 *
	 *  @param imei
	 *  @param password
	 *  @return 终端接收JSON串
	 */
	public String login(String imei, String password);
	
	/**
	 * 数据同步，将平台设置同步到终端
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月30日 下午3:38:32
	 *
	 *  @return 终端接收JSON串
	 */
	public String datasync(String imei, String password);
	
	/**
	 * 心跳
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月30日 下午3:38:32
	 *
	 *  @return 终端接收JSON串
	 */
	public String heartpackge(String imei, String password);
	
	/**
	 * 关机
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月30日 下午3:38:32
	 *
	 *  @return 终端接收JSON串
	 */
	public String poweroff(String imei,String password);
	
	/**
	 * 位置信息
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月30日 下午3:38:32
	 *
	 *  @return 终端接收JSON串
	 */
	public String location(C3Location entity);
	
	/**
	 * 按照终端通信协议返回Json
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 下午02:18:54
	 *
	 *  @param commonds
	 *  @return 终端接收JSON串
	 */
	public String returnJson(String heart,String passwrod);
	
	
}
