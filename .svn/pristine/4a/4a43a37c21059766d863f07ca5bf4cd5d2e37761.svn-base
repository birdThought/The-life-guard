package com.lifeshs.service1.app.cache.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.constants.app.cache.table.RelationEnum;
import com.lifeshs.dao1.data.ModifyLogDao;
import com.lifeshs.po.data.HobbyPO;
import com.lifeshs.po.data.ModifyLogPO;
import com.lifeshs.po.data.RecommendOrgServePO;
import com.lifeshs.service1.app.cache.CacheService;
import com.lifeshs.service1.data.AppBannerService;
import com.lifeshs.service1.data.HobbyService;
import com.lifeshs.service1.data.RecommendOrgServeService;
import com.lifeshs.vo.app.cache.CacheVO;

@Service(value = "appCacheService")
public class CacheServiceImpl implements CacheService {

    @Resource(name = "dataModifyLogDao")
    private ModifyLogDao modifyLogDao;
    
    @Resource(name = "hobbyService")
    private HobbyService hobbyService;
    
    @Resource(name = "recommendOrgServeService")
    private RecommendOrgServeService recommendOrgServeService;
    
    @Resource(name = "appBannerService")
    private AppBannerService appBannerService;
    
    @Override
    public CacheVO getDataCache(Date datePoint) {
        CacheVO cacheData = new CacheVO();
        // 获取节点之后的最新数据
        List<ModifyLogPO> logList = modifyLogDao.findLatestLogList(datePoint);
        if (logList.isEmpty()) {
            return cacheData;
        }
        
        for (ModifyLogPO log : logList) {
            String tableName = log.getTableName();
            // 兴趣爱好
            if (RelationEnum.HOBBY.tableName().equals(tableName)) {
                List<HobbyPO> hobbyList = hobbyService.listModifyHobby(datePoint);
                cacheData.setHobby(hobbyList);
                continue;
            }
            // 推荐（设备测量页）咨询服务
            if (RelationEnum.RECOMMEND_ORG_SERVE.tableName().equals(tableName)) {
                RecommendOrgServePO recommendConsultServe = recommendOrgServeService.getModifyRecommendConsultServe(datePoint);
                cacheData.setRecommendConsultServe(recommendConsultServe);
                continue;
            }
        }
        return cacheData;
    }
}
