package com.lifeshs.service1.data.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.lifeshs.common.constants.common.ProjectType;
import org.springframework.stereotype.Service;

import com.lifeshs.dao1.data.RecommendOrgServeDao;
import com.lifeshs.po.data.RecommendOrgServePO;
import com.lifeshs.service1.data.RecommendOrgServeService;

@Service(value = "recommendOrgServeService")
public class RecommendOrgServeServiceImpl implements RecommendOrgServeService{

    @Resource(name = "recommendOrgServeDao")
    private RecommendOrgServeDao orgServeDao;
    
    @Override
    public RecommendOrgServePO getLifeshsRecommendConsult() {
        // 限定serveId为咨询的id
        Integer serveId = ProjectType.PROJECT_CONSULT.getValue();
        // 查找一条推荐（机构）服务，限定为健康咨询
        List<RecommendOrgServePO> orgServe = orgServeDao.findServeByServeIdList(serveId, 1);
        // 避免没有设置推荐服务
        if (orgServe.size() != 0) {
            return orgServe.get(0);
        }
        return null;
    }

    @Override
    public RecommendOrgServePO getModifyRecommendConsultServe(Date datePoint) {
        // 限定serveId为咨询的id
        Integer serveId = ProjectType.PROJECT_CONSULT.getValue();
        // 查找时间节点之后更新的健康咨询数据
        List<RecommendOrgServePO> orgServeList = orgServeDao.findModifyRecommendOrgServeList(datePoint, serveId);
        for (RecommendOrgServePO orgServe : orgServeList) {
            if (orgServe.getId().equals(serveId)) {
                return orgServe;
            }
        }
        // 如果找不到就直接返回null
        return null;
    }

}
