package com.lifeshs.service.device.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.device.TSportBandSleep;
import com.lifeshs.service.common.transform.ICommonTrans;

/**
 *  版权归
 *  TODO 手环-睡眠 服务类
 *  @author yuhang.weng  
 *  @DateTime 2016年5月26日 下午1:56:22
 */
@Component
public class BandSleep {
	
	@Autowired
	ICommonTrans commonTrans;
	
	/**
	 * 设备类型
	 */
	HealthPackageType deviceType = HealthPackageType.BandSleep;
	
	public int save(TSportBandSleep entity) throws OperationException {
		int result = commonTrans.save(entity);
		return result;
	}
	
	// TODO 查询方法
	
}
