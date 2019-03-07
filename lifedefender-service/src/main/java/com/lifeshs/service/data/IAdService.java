package com.lifeshs.service.data;

import java.util.List;

import com.lifeshs.pojo.data.AdDTO;

/**
 *  广告服务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年4月12日 上午11:01:20
 */
public interface IAdService {

    /**
     *  获取banner
     *  @author yuhang.weng 
     *	@DateTime 2017年4月12日 上午11:05:46
     *
     *  @return
     */
    List<AdDTO> listBanner();
}
