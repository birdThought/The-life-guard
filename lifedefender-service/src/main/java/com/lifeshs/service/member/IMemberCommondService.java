package com.lifeshs.service.member;

import java.util.List;

import com.lifeshs.entity.device.TTerminalCommond;
import com.lifeshs.pojo.member.commond.CommondDto;

/**
 * 
 *  版权归
 *  用户对终端的操作指令业务接口
 *  @author dengfeng  
 *  @DateTime 2016-5-20 上午11:04:10
 */
public interface IMemberCommondService {

	/**
	 * 保存用户设置的终端指令
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 下午01:53:25
	 *
	 *  @param commondDto
	 */
	public int save(CommondDto commondDto) throws Exception ;
	
	/**
	 * 获取未执行(发送到终端)的指令
	 *  @author dengfeng
	 *	@DateTime 2016-5-20 下午02:02:34
	 *  @serverComment 服务注解
	 *
	 *  @param imei 设备imei号
	 *  @return
	 */
	public List<TTerminalCommond> getCommonds(String imei);
}
