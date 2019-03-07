package com.lifeshs.service.data.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.dao.data.IMeasureSiteDao;
import com.lifeshs.pojo.data.MeasureSiteDTO;
import com.lifeshs.service.data.IMeasureSiteService;

/**
 *  测量点Service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月4日 上午10:42:11
 */
@Service(value = "measureSiteService")
public class MeasureSiteServiceImpl implements IMeasureSiteService {

    @Resource(name = "measureSiteDao")
    private IMeasureSiteDao siteDao;
    
    @Override
    public List<MeasureSiteDTO> listFreeMeasureSite(String cityAreaCode) {
        return siteDao.listMeasureSite(true, cityAreaCode);
    }

}
