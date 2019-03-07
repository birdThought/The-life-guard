package com.lifeshs.dao.org.service;

import com.lifeshs.pojo.org.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 门店-服务项目管理dao
 * @Author: wenxian.cai
 * @Date: 2017/5/17 11:25
 */

@Repository("orgServiceManageDao")
public interface IOrgServiceManageDao {

    /**
     * @Description: 根据项目状态获取健康咨询项目列表
     * @Author: wenxian.cai
     * @Date: 2017/5/17 11:28
     * @param orgId 机构id
     * @param status 项目状态（缺省: 全部； 1：待上线； 2：以上线； 3：已下线）
     */
    List<OrgServiceDTO> listConsultServiceByStatus(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status,
                                                   @Param(value = "pageStart") Integer pageStart, @Param(value = "pageSize") Integer pageSize,
                                                   @Param(value = "search")String search);

    /**
     * @Description: 根据项目状态获取健康咨询项目条数
     * @Author: wenxian.cai
     * @Date: 2017/5/17 11:28
     * @param orgId 机构id
     * @param status 项目状态（缺省: 全部； 1：待上线； 2：以上线； 3：已下线）
     */
    int getCountOfConsultServiceByStatus(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status,
                                         @Param(value = "search")String search);

    /**
     * @Description: 根据项目状态获取健康课堂项目列表
     * @Author: wenxian.cai
     * @Date: 2017/5/18 9:48
     * @param orgId 机构id
     * @param status 项目状态（缺省: 全部； 1：待上线； 2：以上线； 3：已下线）
     */
    List<OrgServiceDTO> listLessonServiceByStatus(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status,
                                                  @Param(value = "pageStart") Integer pageStart, @Param(value = "pageSize") Integer pageSize,
                                                  @Param(value = "search")String search);

    /**
     * @Description: 根据项目状态获取健康咨询项目条数
     * @Author: wenxian.cai
     * @Date: 2017/5/18 9:49
     * @param orgId 机构id
     * @param status 项目状态（缺省: 全部； 1：待上线； 2：以上线； 3：已下线）
     */
    int getCountOfLessonServiceByStatus(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status,
                                        @Param(value = "search")String search);

    /**
     * @Description: 根据项目状态获取上门-居家养老项目列表
     * @Author: wenxian.cai
     * @Date: 2017/5/18 9:48
     * @param orgId 机构id
     * @param status 项目状态（缺省: 全部； 1：待上线； 2：以上线； 3：已下线）
     */
    List<OrgServiceDTO> listVisitServiceByStatus(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status,
                                                 @Param(value = "pageStart") Integer pageStart, @Param(value = "pageSize") Integer pageSize,
                                                 @Param(value = "search")String search);

    /**
     * @Description: 根据项目状态获取上门-居家养老项目条数
     * @Author: wenxian.cai
     * @Date: 2017/5/18 9:49
     * @param orgId 机构id
     * @param status 项目状态（缺省: 全部； 1：待上线； 2：以上线； 3：已下线）
     */
    int getCountOfVisitServiceByStatus(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status,
                                       @Param(value = "search")String search);

    /**
     * @Description: 添加健康课堂项目
     * @Author: wenxian.cai
     * @Date: 2017/5/18 14:20
     */
    void addLessonService(LessonServiceDTO lessonServiceDTO);

    /**
     * @Description: 添加健康咨询服务项目
     * @Author: wenxian.cai
     * @Date: 2017/5/18 15:07
     */
    void addConsultService(ConsultServiceDTO consultServiceDTO);

    /**
     * @Description: 添加服务项目-服务师关系（批量插入）
     * @Author: wenxian.cai
     * @Date: 2017/5/18 15:38
     */
    void addServiceOrgUserRelation(@Param(value = "orgUser") List<ServiceOrgUserRelationDTO> orgUser);

    /**
     * @Description: 添加上门服务-居家养老服务项目
     * @Author: wenxian.cai
     * @Date: 2017/5/18 16:08
     */
    void addVisitService(VisitServiceDTO visitServiceDTO);

    /**
     * @Description: 添加服务项目-套餐
     * @Author: wenxian.cai
     * @Date: 2017/5/18 16:28
     */
    void addServiceCombo(@Param(value = "combo") List<ServiceComboDTO> combo);

    /**
     * @Description: 添加服务项目-媒体资料
     * @Author: wenxian.cai
     * @Date: 2017/5/18 17:07
     */
    void addServiceMedia(ServiceMediaDTO media);

    /**
     * @Description: 获取服务类型列表
     * @Author: wenxian.cai
     * @Date: 2017/5/22 15:30
     */
    List<ServeTypeDTO> listServe();

    /**
     * 更新咨询服务项目状态
     * @param code
     * @param status
     * @return
     */
    void updateConsultServiceStatus(@Param(value = "code") String code, @Param(value = "status") Integer status);

    /**
     * 更新课堂服务项目状态
     * @param code
     * @param status
     * @return
     */
    void updateLessonServiceStatus(@Param(value = "code") String code, @Param(value = "status") Integer status);

    /**
     * 更新上门服务项目状态
     * @param code
     * @param status
     * @return
     */
    void updateVisitServiceStatus(@Param(value = "code") String code, @Param(value = "status") Integer status);

    /**
     * @Description: 获取单个服务项目信息
     * @Author: wenxian.cai
     * @Date: 2017/6/1 17:28
     * @param code 项目唯一code
     */
    VisitServiceDTO getVisitService(@Param(value = "orgId") Integer orgId, @Param(value = "code") String code);

    /**
     * @Description: 根据code获取机构项目
     * @Author: wenxian.cai
     * @Date: 2017/6/7 19:58
     */
//    OrgServiceDTO getServiceByCode(@Param("code") String code);

    /**
     * @Description: 获取门店服务列表
     * @Author: wenxian.cai
     * @Date: 2017/6/17 14:43
     */
    List<OrgServiceDTO> listService(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status,
                                    @Param(value = "search") String search, @Param(value = "type") int type,
                                    @Param(value = "startIndex") Integer startIndex, @Param(value = "pageSize") Integer pageSize);

    /**
     * @Description: 获取门店服务条数
     * @Author: wenxian.cai
     * @Date: 2017/6/17 14:44
     */
    int getCountOfService(@Param(value = "orgId") Integer orgId, @Param(value = "status") Integer status,
                                    @Param(value = "search") String search, @Param(value = "type") int type);

    /**
     * @Description: 添加项目到公用表
     * @Author: wenxian.cai
     * @Date: 2017/6/20 10:00
     */
    void addCommonService(ServiceCommonDTO service);

    /**
     * @Description: 更新通用表的服务状态
     * @Author: wenxian.cai
     * @Date: 2017/6/20 10:11
     */
    void updateCommonServiceStatus(@Param("code") String code, @Param("status") Integer status);
}
