package com.lifeshs.service1.data;

import java.util.Date;
import java.util.List;

import com.lifeshs.common.constants.app.banner.TypeEnum;
import com.lifeshs.po.data.AppBannerPO;

/**
 *  app的banner service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月23日 上午11:04:43
 */
public interface AppBannerService {

    /**
     *  获取banner列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月23日 上午10:57:08
     *
     *  @param type banner类型
     *  @return
     */
    List<AppBannerPO> listBanner(TypeEnum type);
    
    /**
     *  获取时间节点之后更新的banner列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月23日 上午11:03:29
     *
     *  @param datePoint 时间节点
     *  @return
     */
    List<AppBannerPO> listModifyBanner(Date datePoint);
}
