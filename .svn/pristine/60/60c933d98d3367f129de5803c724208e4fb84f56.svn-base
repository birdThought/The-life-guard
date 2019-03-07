package com.lifeshs.service1.serve.impl;

import com.lifeshs.dao1.project.IProjectDao;
import com.lifeshs.po.ProjectPO;
import com.lifeshs.pojo.org.service.OrgServiceDTO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.serve.ProjectService;
import com.lifeshs.vo.project.ProjectVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * 服务项目
 * date: 2017/8/10 16:30
 */

@Service(value = "v2ProjectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    IProjectDao projectDao;

    @Override
    public List<ServiceOrgUserRelationDTO> findServiceOrgUserRelationList(String code) {
        return projectDao.findServiceOrgUserRelationList(code);
    }

    @Override
    public List<ProjectPO> findProjectList(Integer orgId) {
        return projectDao.findProjectList(orgId);
    }

    @Override
    public List<OrgServiceDTO> listProject(int orgId) {
        return projectDao.findProjectByOrgId(orgId);
    }

    @Override
    public Paging<ProjectVO> listProject(String userNo, int curPage, int pageSize, String orgName, String classifyName,
            String serveCode) {
        Paging<ProjectVO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<ProjectVO>() {
            
            @Override
            public int queryTotal() {
                return projectDao.countProjectWithCondition(userNo, orgName, classifyName, serveCode);
            }
            
            @Override
            public List<ProjectVO> queryData(int startRow, int pageSize) {
                return projectDao.findProjectWithCondition(userNo, startRow, pageSize, orgName, classifyName, serveCode);
            }
        });
        return p;
    }
}
