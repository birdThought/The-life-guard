package com.lifeshs.vo.app.cache;

import java.util.List;

import com.lifeshs.po.data.HobbyPO;
import com.lifeshs.po.data.RecommendOrgServePO;

import lombok.Data;

/**
 *  app字典缓存
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月22日 下午6:04:25
 */
public @Data class CacheVO {

    /** 兴趣爱好标签 */
    private List<HobbyPO> hobby;
    /** 推荐咨询服务 */
    private RecommendOrgServePO recommendConsultServe;
}
