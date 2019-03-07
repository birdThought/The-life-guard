package com.lifeshs.dao1.project;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.ProjectPO;
import com.lifeshs.pojo.org.service.OrgServiceDTO;
import com.lifeshs.pojo.org.service.ServiceComboDTO;
import com.lifeshs.pojo.org.service.ServiceMediaDTO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.vo.project.ProjectVO;

/**
 * 功能描述
 * Created by dengfeng on 2017/7/4 0004.
 */
@Repository("projectDao")
public interface IProjectDao {

    /**
     * 得到服务师的服务列表
     * @param employeeId
     * @return
     */
    List<ProjectPO> findProjectListByEmployee(@Param("employeeId") int employeeId);

    /**
     * 获取服务项目的服务师列表
     * @param code
     * @return
     */
    List<ServiceOrgUserRelationDTO> findServiceOrgUserRelationList (@Param("code") String code);

    /**
     * 删除服务项目的服务师
     * @param ids
     */
    void delServiceOrgUserRelationList(@Param("code") String code, @Param("ids") List<Integer> ids);

    /**
     * 添加服务项目-服务师关系
     * @param orgUser
     */
    void addServiceOrgUserRelation(@Param(value = "orgUser") List<ServiceOrgUserRelationDTO> orgUser);

    /**
     * 更新服务项目-服务师关系
     * @param orgUser
     */
    void updateServiceOrgUserRelation(@Param(value = "orgUser") List<ServiceOrgUserRelationDTO> orgUser);

    /**
     * 更新公用表数据
     * @param projectPO
     */
    void updateProject(ProjectPO projectPO);

    /**
     * 更新服务项目媒体资料
     * @param media
     */
    void updateProjectMedia(ServiceMediaDTO media);

    /**
     * 添加项目套餐
     * @param combo
     */
    void addProjectComboList(@Param("combo") List<ServiceComboDTO> combo);

    /**
     * 删除项目套餐
     * @param code
     */
    void delProjectComboList(@Param("code") String code);

    /**
     * 获取门店服务项目的基础信息
     * @param orgId
     * @return
     */
    List<ProjectPO> findProjectList(@Param("orgId") Integer orgId);

    /**
     *  获取门店项目列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月24日 上午9:57:50
     *
     *  @param orgId 门店id
     *  @return
     */
    List<OrgServiceDTO> findProjectByOrgId(@Param("orgId") int orgId);
    
    /**
     *  获取项目服务列表
     *  @author yuhang.weng 
     *  @DateTime 2018年2月8日 上午10:34:53
     *
     *  @param orgName 机构名称
     *  @param classifyName 服务类型
     *  @param serveCode 服务code
     *  @return
     */
    List<ProjectVO> findProjectWithCondition(@Param("userNo") String userNo, @Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("orgName") String orgName, @Param("classifyName") String classifyName,
            @Param("serveCode") String serveCode);
    
    /**
     *  统计项目服务数量
     *  @author yuhang.weng 
     *  @DateTime 2018年2月8日 上午10:49:31
     *
     *  @param orgName 机构名称
     *  @param classifyName 服务类型
     *  @param serveCode 服务code
     *  @return
     */
    int countProjectWithCondition(@Param("userNo") String userNo, @Param("orgName") String orgName, @Param("classifyName") String classifyName,
            @Param("serveCode") String serveCode);
    
    /**
     * 
     *  根据订单id查询服务
     *  @author NaN
     *  @DateTime 2018年11月12日 下午7:31:25
     *
     *  @param orderNumber
     *  @return
     */
    ProjectPO findProjectByOrderNumber(@Param("orderNumber") String orderNumber);
}
