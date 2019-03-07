package com.lifeshs.dao1.data;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.data.RecommendOrgServePO;

/**
 *  推荐（机构）服务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月18日 上午10:18:04
 */
@Repository(value = "recommendOrgServeDao")
public interface RecommendOrgServeDao {

    /**
     *  查找推荐（机构）服务信息
     *  @author yuhang.weng 
     *  @DateTime 2017年8月18日 上午10:12:53
     *
     *  @param serveId 服务id
     *  @param limit 数量
     *  @return
     */
    List<RecommendOrgServePO> findServeByServeIdList(@Param("serveId") Integer serveId, @Param("limit") Integer limit);
    
    /**
     *  获取时间节点之后更新的推荐（机构）服务信息
     *  @author yuhang.weng 
     *  @DateTime 2017年8月23日 上午9:48:25
     *
     *  @param datePoint 时间节点
     *  @param serveId 限定的服务类型id 可以为NULL不作限制
     *  @return
     */
    List<RecommendOrgServePO> findModifyRecommendOrgServeList(@Param("datePoint") Date datePoint, @Param("serveId") Integer serveId);
}
