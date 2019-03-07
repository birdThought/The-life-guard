package com.lifeshs.service.data;

import java.util.List;

import com.lifeshs.pojo.data.MeasureSiteDTO;

public interface IMeasureSiteService {

    /**
     *  获取免费测量点
     *  @author yuhang.weng 
     *	@DateTime 2017年5月4日 上午10:42:57
     *
     *  @param cityAreaCode
     *  @return
     */
    List<MeasureSiteDTO> listFreeMeasureSite(String cityAreaCode);
}
