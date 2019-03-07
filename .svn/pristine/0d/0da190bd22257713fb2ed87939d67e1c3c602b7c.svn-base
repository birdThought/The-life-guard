package com.lifeshs.service.terminal;

import java.util.List;

import com.lifeshs.common.model.map.Gps;

/**
 *  版权归
 *  TODO HL系列-Web服务类
 *  @author yuhang.weng  
 *  @DateTime 2016年7月12日 上午10:43:07
 */
public interface IHLWebService {
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月12日 上午10:47:41
	 *  @serverComment 获取GPS数据(lng,lat)
	 *
	 *  @param userId
	 *  @return
	 *  @throws Exception
	 */
	public Gps getGps(int userId) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月12日 下午3:02:16
	 *  @serverComment 获取Gps的List对象
	 *
	 *  @param userId
	 *  @param limit 获取数量
	 *  @return
	 *  @throws Exception
	 */
	public List<Gps> getGpsList(int userId, int limit) throws Exception;
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年7月12日 下午5:55:22
	 *  @serverComment 获取轨迹回放信息
	 *
	 *  @param userId
	 *  @param start 开始时间
	 *  @param end 结束时间
	 *  @return
	 *  @throws Exception
	 */
	public List<Gps> getOrbit(int userId, String start, String end) throws Exception;


}
