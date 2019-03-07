package com.lifeshs.service1.serve.visit;

import java.util.List;

import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.common.constants.common.sort.serve.SortEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.VisitPO;
import com.lifeshs.pojo.org.service.ServiceComboDTO;
import com.lifeshs.pojo.org.service.ServiceMediaDTO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.pojo.serve.visit.RecommendedComboDTO;
import com.lifeshs.pojo.serve.visit.VisitConditionDTO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.serve.visit.ComboVO;
import com.lifeshs.vo.serve.visit.RecommendedVisitDetailVO;

/**
 *  线下服务
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月21日 下午1:55:14
 */
public interface VisitService {

    /**
     *  获取推荐线下服务列表
     *  @author yuhang.weng 
     *	@DateTime 2017年6月21日 下午1:57:14
     *
     *  @param limit
     *  @return
     */
    List<RecommendedComboDTO> listRecommendedCombo(Double longitude, Double latitude, int limit);
    
    /**
     *  获取服务筛选条件
     *  @author yuhang.weng 
     *	@DateTime 2017年6月23日 下午4:34:58
     *
     *  @param serveType 服务类型枚举类
     */
    VisitConditionDTO getVisitServeCondition(String serveType);
    
    /**
     *  获取线下服务列表
     *  @author yuhang.weng 
     *	@DateTime 2017年6月30日 上午9:14:59
     *
     *  @param areaCode 地区代码
     *  @param longitude 经度
     *  @param latitude 纬度
     *  @param distanceArea 距离表达式
     *  @param priceArea 价格区间
     *  @param type 类型
     *  @param sort 排序类型 具体参考sortEnum
     *  @param likeName 名称
     *  @param curPage 页码
     *  @param pageSize 页面大小
     *  @param projectType 服务枚举类型
     *  @return
     */
    Paging<RecommendedComboDTO> listProjectCombo(String areaCode, Double longitude, Double latitude,
            String distanceArea, String priceArea, String type, SortEnum sort, String likeName, ProjectType projectType,
            int curPage, int pageSize) throws OperationException;
    
    /**
     *  获取套餐详情
     *  @author yuhang.weng 
     *	@DateTime 2017年7月7日 下午2:16:31
     *
     *  @param comboId
     *  @return
     *  @throws OperationException
     */
    RecommendedVisitDetailVO getVisitComboDetail(int comboId) throws OperationException;
    
    /**
     *  获取机构项目套餐列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月8日 下午3:59:02
     *
     *  @param orgId 机构ID
     *  @param serveType 服务类型
     *  @return
     */
    List<RecommendedComboDTO> listOrgProjectCombo(int orgId, ProjectType serveType);
    
    /**
     *  获取服务师所属的套餐列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 下午2:49:30
     *
     *  @param serveUserId
     *  @param serveType
     *  @return
     */
    List<RecommendedComboDTO> listServeUserProjectCombo(int serveUserId, ProjectType serveType);
    
    /**
     *  获取一个套餐
     *  @author yuhang.weng 
     *	@DateTime 2017年7月15日 下午3:36:10
     *
     *  @param id
     *  @return
     */
    RecommendedComboDTO getProjectCombo(int id);

    /**
     * 获取t_project_visit(线下、上门服务)
     * @param code
     * @return
     */
    VisitPO getVisit(String code);

    /**
     * 更新线下、上门服务
     * @param visitPO
     * @param orgUsers
     * @param comboVO
     * @param mediaDTO
     */
    void updateVisit(VisitPO visitPO, List<ServiceOrgUserRelationDTO> orgUsers,
                     List<ServiceComboDTO> comboVO, ServiceMediaDTO mediaDTO) throws OperationException;
}
