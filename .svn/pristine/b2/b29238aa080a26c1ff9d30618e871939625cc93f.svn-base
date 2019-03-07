package com.lifeshs.dao.serve.visit;

import java.util.List;

import com.lifeshs.po.VisitPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.common.constants.common.sort.serve.SortEnum;
import com.lifeshs.pojo.serve.visit.RecommendedComboDTO;
import com.lifeshs.vo.serve.visit.ComboVO;
import com.lifeshs.vo.serve.visit.RecommendedVisitDetailVO;

/**
 *  线下服务
 *  @author yuhang.weng
 *  @version 2.0
 *  @DateTime 2017年6月21日 下午1:58:12
 */
/**
 *  类说明
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年7月7日 下午2:17:25
 */
@Repository(value = "visitDao")
public interface VisitDao {

    /**
     *  获取销量多的线下服务
     *  @author yuhang.weng 
     *	@DateTime 2017年6月21日 上午10:02:47
     *
     *  @param longitude 经度
     *  @param latitude 纬度
     *  @param limit 查询结果最大数量
     *  @return
     */
    List<RecommendedComboDTO> listLagerNumberOfBuyer(@Param("longitude") Double longitude,
            @Param("latitude") Double latitude, @Param("limit") int limit);
    
    /**
     *  统计服务数量
     *  @author yuhang.weng 
     *	@DateTime 2017年7月7日 下午2:17:11
     *
     *  @param longitude
     *  @param latitude
     *  @param areaCodeRegex
     *  @param priceType
     *  @param startPrice
     *  @param endPrice
     *  @param type
     *  @param likeName
     *  @param code
     *  @return
     */
    int countVisitServeWithCondition(@Param("longitude") Double longitude, @Param("latitude") Double latitude,
            @Param("areaCodeRegex") String areaCodeRegex, @Param("priceType") String priceType,
            @Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice, @Param("type") String type,
            @Param("likeName") String likeName, @Param("projectType") Integer projectType);
    
    /**
     *  按条件搜索服务
     *  @author yuhang.weng 
     *	@DateTime 2017年7月7日 下午2:17:00
     *
     *  @param longitude 经度
     *  @param latitude 纬度
     *  @param areaCodeRegex 地址正则表达式
     *  @param priceType 价格类型
     *  @param startPrice
     *  @param endPrice
     *  @param type 类型
     *  @param likeName 名字
     *  @param startIndex
     *  @param pageSize
     *  @param sort 排序方式
     *  @param code 服务code
     *  @return
     */
    List<RecommendedComboDTO> listVisitServeWithCondition(@Param("longitude") Double longitude,
            @Param("latitude") Double latitude, @Param("areaCodeRegex") String areaCodeRegex,
            @Param("priceType") String priceType, @Param("startPrice") Double startPrice,
            @Param("endPrice") Double endPrice, @Param("type") String type, @Param("likeName") String likeName,
            @Param("startIndex") int startIndex, @Param("pageSize") int pageSize, @Param("sort") SortEnum sort,
            @Param("projectType") Integer projectType);
    
    /**
     *  查询套餐详细内容
     *  @author yuhang.weng 
     *	@DateTime 2017年7月7日 下午2:16:48
     *
     *  @param id
     *  @return
     */
    RecommendedVisitDetailVO findComboDetail(@Param("id") int id);
    
    /**
     *  查询项目的套餐列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月7日 下午2:17:27
     *
     *  @param projectCode
     *  @return
     */
    List<ComboVO> findComboByProjectCode(@Param("projectCode") String projectCode);
    
    /**
     *  查询机构的项目列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月8日 下午4:42:37
     *
     *  @param orgId
     *  @param projectType 项目类型(1：咨询；2：线下；3：上门；4：课堂)
     *  @return
     */
    List<RecommendedComboDTO> findVisitServeByOrgId(@Param("orgId") int orgId, @Param("projectType") Integer projectType);
    
    /**
     *  查询服务师的项目列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 上午9:42:40
     *
     *  @param serveUserId
     *  @param projectType 项目类型(1：咨询；2：线下；3：上门；4：课堂)
     *  @return
     */
    List<RecommendedComboDTO> findVisitServeByServeUserId(@Param("serveUserId") int serveUserId,
            @Param("projectType") Integer projectType);
    
    /**
     *  查找一个项目
     *  @author yuhang.weng 
     *	@DateTime 2017年7月15日 下午3:34:56
     *
     *  @param id
     *  @return
     */
    RecommendedComboDTO findVisitServe(@Param("id")int id);

    /**
     * 获取t_project_visit(线下、上门服务)
     * @param code
     * @return
     */
    VisitPO findVisit(@Param("code") String code);

    /**
     * 更新t_project_visit(线下、上门服务)
     * @param visitPO
     */
    void updateVisit(VisitPO visitPO);
}
