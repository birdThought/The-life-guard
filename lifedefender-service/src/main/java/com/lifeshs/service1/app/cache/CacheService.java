package com.lifeshs.service1.app.cache;

import java.util.Date;

import com.lifeshs.vo.app.cache.CacheVO;

/**
 *  app缓存
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月22日 下午5:19:51
 */
public interface CacheService {

    /**
     *  获取字典缓存
     *  @author yuhang.weng 
     *  @DateTime 2017年8月22日 下午5:19:38
     *
     *  @param datePoint 时间节点
     */
    CacheVO getDataCache(Date datePoint);
}
