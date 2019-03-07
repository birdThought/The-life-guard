package com.lifeshs.service1.data;

import java.util.Date;

import com.lifeshs.po.data.RecommendOrgServePO;

/**
 *  推荐（机构）服务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月18日 上午11:46:55
 */
public interface RecommendOrgServeService {

    /**
     *  获取生命守护推荐的健康咨询
     *  @author yuhang.weng 
     *  @DateTime 2017年8月18日 上午10:26:53
     *
     *  @return 返回null表示没有推荐的健康咨询
     */
    RecommendOrgServePO getLifeshsRecommendConsult();
    
    /**
     *  获取时间节点之后，生命守护推荐的健康咨询变更模型
     *  @author yuhang.weng 
     *  @DateTime 2017年8月23日 上午9:54:02
     *
     *  @param datePoint 时间节点
     *  @return 返回null表示没有更新信息
     */
    RecommendOrgServePO getModifyRecommendConsultServe(Date datePoint);
}
