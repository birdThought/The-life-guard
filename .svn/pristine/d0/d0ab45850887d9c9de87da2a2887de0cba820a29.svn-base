package com.lifeshs.service.org.service;

import java.util.List;

import com.lifeshs.pojo.org.service.ConsultServiceDTO;
import com.lifeshs.pojo.org.service.LessonServiceDTO;
import com.lifeshs.pojo.org.service.OrgServiceDTO;
import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.org.service.VisitServiceDTO;
import com.lifeshs.pojo.page.PaginationDTO;

/**
 * @Description: 服务项目管理
 * @Author: wenxian.cai
 * @Date: 2017/5/17 11:02
 */

public interface IOrgServiceManage {

    /**
     * @Description: 根据项目状态获取健康咨询项目列表
     * @Author: wenxian.cai
     * @Date: 2017/5/17 11:19
     * @param status 项目状态（null:全部； 1：待上线； 2：以上线； 3：已下线）
     */
    PaginationDTO<OrgServiceDTO> listConsultServiceByStatus(Integer orgId, Integer status, Integer pageIndex, Integer pageSize, String search);

    /**
     * @Description: 根据项目状态获取健康课堂项目
     * @Author: wenxian.cai
     * @Date: 2017/5/18 9:53
     * @param status 项目状态（null:全部； 1：待上线； 2：以上线； 3：已下线）
     */
    PaginationDTO<OrgServiceDTO> listLessonServiceByStatus(Integer orgId, Integer status, Integer pageIndex, Integer pageSize, String search);

    /**
     * @Description: 根据项目状态获取上门-居家养老项目
     * @Author: wenxian.cai
     * @Date: 2017/5/18 9:53
     * @param status 项目状态（null:全部； 1：待上线； 2：以上线； 3：已下线）
     */
    PaginationDTO<OrgServiceDTO> listVisitServiceByStatus(Integer orgId, Integer status, Integer pageIndex, Integer pageSize, String search);

    /**
     * @Description: 添加健康课堂服务项目
     * @Author: wenxian.cai
     * @Date: 2017/5/18 11:59
     */
    boolean addLessonService(LessonServiceDTO lessonServiceDTO, String userCode);

    /**
     * @Description: 添加健康咨询服务项目
     * @Author: wenxian.cai
     * @Date: 2017/5/18 15:08
     */
    boolean addConsultService(ConsultServiceDTO consultServiceDTO);

    /**
     * @Description: 添加上门服务-居家养老服务项目
     * @Author: wenxian.cai
     * @Date: 2017/5/18 16:09
     */
    boolean addVisitService(VisitServiceDTO visitServiceDTO);

    /**
     * @Description: 获取服务类型列表
     * @Author: wenxian.cai
     * @Date: 2017/5/22 15:31
     */
    List<ServeTypeDTO> listServe();

    /**
     * 更新咨询服务项目状态
     * @param code 项目code
     * @param status
     * @return
     */
    boolean updateConsultServiceStatus(String code, Integer status);

    /**
     * 更新课堂服务项目状态
     * @param code 项目code
     * @param status
     * @return
     */
    boolean updateLessonServiceStatus(String code, Integer status);

    /**
     * 更新上门服务项目状态
     * @param code 项目code
     * @param status
     * @return
     */
    boolean updateVisitServiceStatus(String code, Integer status);

    /**
     * @Description: 获取上门服务单个服务项目信息
     * @Author: wenxian.cai
     * @Date: 2017/6/1 17:48
     */
    VisitServiceDTO getVisitService(Integer orgId, String code);
    
    int countOrgOnlineProject(int orgId);


    /**
     * @Description: 获取服务列表
     * @Author: wenxian.cai
     * @Date: 2017/6/17 14:40
     */
    PaginationDTO<OrgServiceDTO> listService(Integer orgId, Integer status, String search,
                                             int type, Integer pageIndex, Integer pageSize);
    
    /**
     * @Description: 获取服务列表*接口
     * @Author: wenxian.cai
     * @Date: 2017/6/17 14:40
     */
    List<OrgServiceDTO> getServiceManageList(Integer orgId, Integer status, String search,
                                             int type, Integer pageIndex, Integer pageSize);
}
