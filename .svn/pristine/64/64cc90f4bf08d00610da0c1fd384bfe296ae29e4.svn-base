package com.lifeshs.service.org.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.dao.org.lesson.ILessonGroupDao;
import com.lifeshs.dao.org.service.IOrgServiceManageDao;
import com.lifeshs.pojo.huanxin.GroupDTO;
import com.lifeshs.pojo.org.service.ConsultServiceDTO;
import com.lifeshs.pojo.org.service.LessonServiceDTO;
import com.lifeshs.pojo.org.service.OrgServiceDTO;
import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.org.service.ServiceComboDTO;
import com.lifeshs.pojo.org.service.ServiceCommonDTO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.pojo.org.service.VisitServiceDTO;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.pojo.page.QueryPageData;
import com.lifeshs.service.common.impl.CommonServiceImpl;
import com.lifeshs.service.org.lesson.ILessonGroup;
import com.lifeshs.service.org.service.IOrgServiceManage;
import com.lifeshs.thirdservice.HuanXinService;
import com.lifeshs.utils.UUID;

/**
 * 服务项目管理实现
 *
 * @author wenxian.cai
 * @create 2017-05-17 11:03
 **/

@Service("orgServiceManage")
public class OrgServiceManageImpl extends CommonServiceImpl implements IOrgServiceManage{

    @Autowired
    IOrgServiceManageDao orgServiceManageDao;

    @Autowired
    ILessonGroup lessonGroup;

    @Autowired
    HuanXinService huanxinService;

    @Autowired
    ILessonGroupDao lesson;

    @Override
    public PaginationDTO<OrgServiceDTO> listConsultServiceByStatus(Integer orgId, Integer status, Integer pageIndex, Integer pageSize, String search) {

        PaginationDTO<OrgServiceDTO> pagination = new PaginationDTO<>();
        List<OrgServiceDTO> services = new ArrayList<>();

        int count = orgServiceManageDao.getCountOfConsultServiceByStatus(orgId, status, search);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, count)) {
            pagination.setData(services);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        services = orgServiceManageDao.listConsultServiceByStatus(orgId, status, startIndex, pageSize, search);
        pagination.setData(services);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }

    @Override
    public PaginationDTO<OrgServiceDTO> listLessonServiceByStatus(Integer orgId, Integer status, Integer pageIndex, Integer pageSize, String search) {
        PaginationDTO<OrgServiceDTO> pagination = new PaginationDTO<>();
        List<OrgServiceDTO> services = new ArrayList<>();

        int count = orgServiceManageDao.getCountOfLessonServiceByStatus(orgId, status, search);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, count)) {
            pagination.setData(services);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        services = orgServiceManageDao.listLessonServiceByStatus(orgId, status, startIndex, pageSize, search);
        pagination.setData(services);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }

    @Override
    public PaginationDTO<OrgServiceDTO> listVisitServiceByStatus(Integer orgId, Integer status, Integer pageIndex, Integer pageSize, String search) {
        PaginationDTO<OrgServiceDTO> pagination = new PaginationDTO<>();
        List<OrgServiceDTO> services = new ArrayList<>();

        int count = orgServiceManageDao.getCountOfVisitServiceByStatus(orgId, status, search);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, count)) {
            pagination.setData(services);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        services = orgServiceManageDao.listVisitServiceByStatus(orgId, status, startIndex, pageSize, search);
        pagination.setData(services);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }

    @Override
    public boolean addLessonService(LessonServiceDTO lessonServiceDTO, String userCode) {
//        /** 注册环信群组 */
//        GroupDTO group = new GroupDTO();
//        group.setOwner(userCode);
//        group.setApproval(false);
//        group.setDesc(lessonServiceDTO.getIntroduce());
//        group.setGroupName(lessonServiceDTO.getName());
//        group.setMaxusers(200);
//        group.setPublicGroup(true);
//        String huanxinId = huanxinService.createGroup(group);
//
//        huanXinService.joinGroup(huanxinId, userCode);

//        lessonServiceDTO.setHuanxinId(huanxinId);
        String serialId = UUID.generate();
        lessonServiceDTO.setSerialId(serialId);
        orgServiceManageDao.addLessonService(lessonServiceDTO);
        //添加服务师
        for (ServiceOrgUserRelationDTO serviceOrgUserRelationDTO : lessonServiceDTO.getOrgUser()) {
            serviceOrgUserRelationDTO.setProjectCode(lessonServiceDTO.getCode());
        }
        orgServiceManageDao.addServiceOrgUserRelation(lessonServiceDTO.getOrgUser());
        //添加服务到公用表
        ServiceCommonDTO service = new ServiceCommonDTO();
        service.setProjectCode(lessonServiceDTO.getCode());
        service.setImage(lessonServiceDTO.getImage());
        service.setName(lessonServiceDTO.getName());
        service.setServeId(lessonServiceDTO.getServeId());
        service.setOrgId(lessonServiceDTO.getOrgId());
        service.setStatus(lessonServiceDTO.getStatus());
        service.setPrice(lessonServiceDTO.getPrice());
        service.setProjectType(lessonServiceDTO.getProjectType());
//        service.setHuanxinId(huanxinId);
        orgServiceManageDao.addCommonService(service);
        return lessonServiceDTO.getCode() == null ? false : true;
    }

    @Override
    public boolean addConsultService(ConsultServiceDTO consultServiceDTO) {
        String serialId = UUID.generate();
        consultServiceDTO.setSerialId(serialId);
        orgServiceManageDao.addConsultService(consultServiceDTO);
        for (ServiceOrgUserRelationDTO serviceOrgUserRelationDTO : consultServiceDTO.getOrgUser()) {
            serviceOrgUserRelationDTO.setProjectCode(consultServiceDTO.getCode());
        }
        orgServiceManageDao.addServiceOrgUserRelation(consultServiceDTO.getOrgUser());
        //添加服务到公用表
        ServiceCommonDTO service = new ServiceCommonDTO();
        service.setProjectCode(consultServiceDTO.getCode());
        service.setImage(consultServiceDTO.getImage());
        service.setName(consultServiceDTO.getName());
        service.setServeId(consultServiceDTO.getServeId());
        service.setOrgId(consultServiceDTO.getOrgId());
        service.setStatus(consultServiceDTO.getStatus());
        service.setProjectType(consultServiceDTO.getProjectType());
        service.setLessonId(null);
        orgServiceManageDao.addCommonService(service);
        return true;
    }

    @Override
    @Transactional
    public boolean addVisitService(VisitServiceDTO visitServiceDTO) {
        String serialId = UUID.generate();
        visitServiceDTO.setSerialId(serialId);
        orgServiceManageDao.addVisitService(visitServiceDTO);
        for (ServiceOrgUserRelationDTO serviceOrgUserRelationDTO : visitServiceDTO.getOrgUser()) {
            serviceOrgUserRelationDTO.setProjectCode(visitServiceDTO.getCode());
        }
        for (ServiceComboDTO serviceComboDTO : visitServiceDTO.getCombo()) {
            serviceComboDTO.setProjectCode(visitServiceDTO.getCode());
        }
        orgServiceManageDao.addServiceOrgUserRelation(visitServiceDTO.getOrgUser());
        orgServiceManageDao.addServiceCombo(visitServiceDTO.getCombo());
        visitServiceDTO.getMedia().setProjectCode(visitServiceDTO.getCode());
        orgServiceManageDao.addServiceMedia(visitServiceDTO.getMedia());

        //添加服务到公用表
        ServiceCommonDTO service = new ServiceCommonDTO();
        service.setProjectCode(visitServiceDTO.getCode());
        service.setImage(visitServiceDTO.getImage());
        service.setName(visitServiceDTO.getName());
        service.setServeId(visitServiceDTO.getServeId());
        service.setOrgId(visitServiceDTO.getOrgId());
        service.setStatus(visitServiceDTO.getStatus());
        service.setLessonId(null);
        service.setProjectType(visitServiceDTO.getProjectType());
        orgServiceManageDao.addCommonService(service);

        return true;
    }

    @Override
    public List<ServeTypeDTO> listServe() {
        return orgServiceManageDao.listServe();
    }

    @Override
    public boolean updateConsultServiceStatus(String code, Integer status) {
        orgServiceManageDao.updateConsultServiceStatus(code, status);
        orgServiceManageDao.updateCommonServiceStatus(code, status);
        return true;
    }

    @Override
    public boolean updateLessonServiceStatus(String code, Integer status) {
        orgServiceManageDao.updateLessonServiceStatus(code, status);
        orgServiceManageDao.updateCommonServiceStatus(code, status);
        return true;
    }

    @Override
    public boolean updateVisitServiceStatus(String code, Integer status) {
        orgServiceManageDao.updateVisitServiceStatus(code, status);
        orgServiceManageDao.updateCommonServiceStatus(code, status);
        return true;
    }

    @Override
    public VisitServiceDTO getVisitService(Integer orgId, String code) {
        return orgServiceManageDao.getVisitService(orgId, code);
    }

    @Override
    public int countOrgOnlineProject(int orgId) {
        int count = 0;
        count += orgServiceManageDao.getCountOfConsultServiceByStatus(orgId, 2, null);
        count += orgServiceManageDao.getCountOfLessonServiceByStatus(orgId, 2, null);
        count += orgServiceManageDao.getCountOfVisitServiceByStatus(orgId, 2, null);
        return count;
    }

    @Override
    public PaginationDTO<OrgServiceDTO> listService(Integer orgId, Integer status, String search,
                                                    int type, Integer pageIndex, Integer pageSize) {

        PaginationDTO<OrgServiceDTO> pagination = new PaginationDTO<>();
        List<OrgServiceDTO> services = new ArrayList<>();

        if(search != null && search.equals(""))
            search = null;
        int count = orgServiceManageDao.getCountOfService(orgId, status, search, type);
        if (PaginationDTO.isDataOverFlow(pageIndex, pageSize, count)) {
            pagination.setData(services);
            return pagination;
        }

        QueryPageData queryPageData = PaginationDTO.getQueryPageData(pageIndex, pageSize, count);
        int startIndex = queryPageData.getStartIndex();
        int totalPage = queryPageData.getTotalPage();
        pageIndex = queryPageData.getCurPage();

        services = orgServiceManageDao.listService(orgId, status, search, type, startIndex, pageSize);
        pagination.setData(services);
        pagination.setNowPage(pageIndex);
        pagination.setTotalPage(totalPage);
        pagination.setTotalSize(count);

        return pagination;
    }
    
    @Override
    public List<OrgServiceDTO> getServiceManageList(Integer orgId, Integer status, String search,
                                                    int type, Integer startIndex, Integer pageSize) {

        List<OrgServiceDTO> services = orgServiceManageDao.listService(orgId, status, search, type, startIndex, pageSize);
        return services;
    }
}
