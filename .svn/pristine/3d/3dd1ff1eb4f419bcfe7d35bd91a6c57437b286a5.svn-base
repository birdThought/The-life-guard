package com.lifeshs.dao.data;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.data.MeasureSiteDTO;

/**
 *  测量点DAO
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月3日 下午3:32:18
 */
@Repository(value = "measureSiteDao")
public interface IMeasureSiteDao {

    /**
     *  获取测量点
     *  @author yuhang.weng 
     *	@DateTime 2017年5月4日 上午10:43:58
     *
     *  @param free
     *  @param cityAreaCode
     *  @return
     */
    List<MeasureSiteDTO> listMeasureSite(@Param("free") boolean free, @Param("cityAreaCode") String cityAreaCode);
}
