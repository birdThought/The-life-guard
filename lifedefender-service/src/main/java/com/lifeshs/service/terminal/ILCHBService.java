package com.lifeshs.service.terminal;

import com.lifeshs.pojo.mina.HLCommand;

/**
 *  版权归
 *  TODO 梦时代
 *  @author yuhang.weng  
 *  @DateTime 2016年7月7日 上午11:30:55
 */
public interface ILCHBService {

	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月7日 上午11:52:42
	 *  @serverComment 终端登录请求
	 *
	 *  @param data
	 *  @return
	 */
	public String t1(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:38:48
	 *  @serverComment 终端上报心跳
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t2(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:38:59
	 *  @serverComment 终端上传脉搏数据
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t28(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:40:18
	 *  @serverComment 终端上传定位数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t29(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:40:46
	 *  @serverComment 终端上传综合数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t50(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:41:19
	 *  @serverComment 终端上传血压数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t45(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:42:04
	 *  @serverComment 终端获取天气指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t47(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:42:41
	 *  @serverComment 终端上关机指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t51(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午9:43:43
	 *  @serverComment 终端上传血糖数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t59(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午10:07:17
	 *  @serverComment 终端发送消息指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t63(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午11:44:38
	 *  @serverComment 终端获取积分，关注，点赞数据指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t65(HLCommand data) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月8日 上午11:44:40
	 *  @serverComment 服务器设置终端时钟界面指令
	 *
	 *  @param data
	 *  @return
	 *  @throws Exception
	 */
	public String t66(HLCommand data) throws Exception;
}
