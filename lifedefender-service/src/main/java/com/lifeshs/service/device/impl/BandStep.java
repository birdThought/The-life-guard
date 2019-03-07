package com.lifeshs.service.device.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.entity.device.TSportBandStep;
import com.lifeshs.service.common.transform.ICommonTrans;
import com.lifeshs.common.constants.common.HealthPackageType;

/**
 *  版权归
 *  TODO 手环-计步 服务类
 *  @author yuhang.weng  
 *  @DateTime 2016年5月26日 下午1:55:36
 */
@Component("bandStep")
public class BandStep{

	@Autowired
	ICommonTrans commonTrans;
	
	/**
	 * 设备类型
	 */
	HealthPackageType deviceType = HealthPackageType.BandStep;
	
	public int save(TSportBandStep entity) throws Exception{
		int result = commonTrans.save(entity);
		return result;
	}
	// TODO 查询方法



	
	
	
	
}
