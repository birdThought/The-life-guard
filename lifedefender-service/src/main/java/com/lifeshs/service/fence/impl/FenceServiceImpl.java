package com.lifeshs.service.fence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao.fence.IFenceDao;
import com.lifeshs.entity.member.TUserElectronicFence;
import com.lifeshs.service.fence.IFenceService;

/**
 *  版权归
 *  TODO C3业务的实现类
 *  @author duosheng.mo  
 *  @DateTime 2016年5月26日 下午5:18:37
 */
@Service("fenceService")
public class FenceServiceImpl implements IFenceService{
	
	@Autowired
	public IFenceDao fenceDao;
	

	@Override
	public List<TUserElectronicFence> findFenceByList(int userDeviceId) {
		return fenceDao.findFenceByList(userDeviceId);
	}


	@Override
	public List<TUserElectronicFence> findFenceByListOrderByNumber(int userDeviceId) {
		return fenceDao.findFenceByListOrderByNumber(userDeviceId);
	}

}
