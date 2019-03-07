package com.lifeshs.service1.data.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.dao1.data.MeasureSiteDao;
import com.lifeshs.pojo.data.MeasureSite;
import com.lifeshs.pojo.data.MeasureSiteOpeningTime;
import com.lifeshs.service1.data.MeasureSiteService;
@Service
public class MeasureSiteServiceImpl implements MeasureSiteService {
	
	@Autowired 
	private MeasureSiteDao measureSiteDao;
	
	@Override
	public MeasureSite selectSizeStatusById(int orgId) {
		
		return measureSiteDao.selectSizeStatusById(orgId);
	}

	@Override
	public MeasureSiteOpeningTime selectSizeTimeById(Integer id) {
		
		return measureSiteDao.selectSizeTimeById(id);
	}

}
