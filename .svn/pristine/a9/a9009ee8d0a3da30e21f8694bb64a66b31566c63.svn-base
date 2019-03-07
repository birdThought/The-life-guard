package com.lifeshs.dao1.data;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.data.AppBannerPO;

@Repository(value = "appBannerDao")
public interface AppBannerDao {

    /**
     *  查找app的banner列表
     *  @author yuhang.weng 
     *  @DateTime 2017年8月23日 上午10:42:53
     *
     *  @param type 限定类型 1_首页，2_服务页 
     *  @return
     */
    List<AppBannerPO> findBannerByTypeList(@Param("type") int type);
    
    /**
     *  获取时间节点之后更新的banner信息
     *  @author yuhang.weng 
     *  @DateTime 2017年8月23日 上午10:59:10
     *
     *  @param datePoint 时间节点
     *  @param type 类型 可以为NULL
     *  @return
     */
    List<AppBannerPO> findModifyBannerList(@Param("datePoint") Date datePoint, @Param("type") Integer type);
}
