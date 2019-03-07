package com.lifeshs.service1.serve;

import java.util.List;

import com.lifeshs.po.ProjectPO;
import com.lifeshs.pojo.org.service.OrgServiceDTO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.project.ProjectVO;

/*
 * 服务项目
 * date: 2017/8/10 16:27
 */
public interface ProjectService {
    /**
     * 获取项目的服务师列表
     * @param code
     * @return
     */
    List<ServiceOrgUserRelationDTO> findServiceOrgUserRelationList(String code);

    /**
     * 获取门店服务项目的基础信息
     * @param orgId
     * @return
     */
    List<ProjectPO> findProjectList(Integer orgId);
    
    /**
     *  获取门店服务项目列表(含有项目的id)
     *  @author yuhang.weng 
     *  @DateTime 2018年1月24日 上午10:04:21
     *
     *  @param orgId
     *  @return
     */
    List<OrgServiceDTO> listProject(int orgId);
    
    /**
     *  获取门店服务列表
     *  @author yuhang.weng 
     *  @DateTime 2018年2月8日 上午11:24:29
     *
     *  @param curPage
     *  @param pageSize
     *  @param orgName 机构名称
     *  @param classifyName 服务类型
     *  @param serveCode 服务code
     *  @return
     */
    Paging<ProjectVO> listProject(String userNo, int curPage, int pageSize, String orgName, String classifyName, String serveCode);
}
