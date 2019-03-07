package com.lifeshs.dao.serve.consult;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.common.constants.common.sort.serve.SortEnum;
import com.lifeshs.po.ConsultPO;
import com.lifeshs.vo.serve.consult.OrgUserVO;
import com.lifeshs.vo.serve.consult.ServeUserVO;

/**
 *  咨询
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月20日 下午2:42:00
 */
@Repository(value = "consultDao")
public interface ConsultDao {

    /**
     *  获取销量多的咨询服务
     *  @author yuhang.weng 
     *	@DateTime 2017年6月21日 上午10:02:12
     *
     *  @param limit
     *  @return
     */
    List<ServeUserVO> findLagerNumberOfBuyerList(@Param("limit") int limit);
    
    List<ServeUserVO> findConsultServeUserWithConditionList(@Param("priceType") String priceType,
            @Param("startPrice") Double startPrice, @Param("endPrice") Double endPrice, @Param("type") String type,
            @Param("likeName") String likeName, @Param("startIndex") int startIndex, @Param("pageSize") int pageSize,
            @Param("sort") SortEnum sort);
    
    int countConsultServeUserWithCondition(@Param("priceType") String priceType, @Param("startPrice") Double startPrice,
            @Param("endPrice") Double endPrice, @Param("type") String type, @Param("likeName") String likeName);
    
    /**
     *  获取机构下的咨询服务师列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月8日 下午5:02:05
     *
     *  @param orgId
     *  @return
     */
    List<ServeUserVO> findConsultServeUserByOrgIdList(@Param("orgId") int orgId);
    
    /**
     *  获取服务师的咨询列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 上午11:43:28
     *
     *  @param serveUserId
     *  @return
     */
    List<ServeUserVO> findConsultServeUserByServeUserIdList(@Param("serveUserId") int serveUserId);

    /**
     *  获取咨询服务师详情
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午6:54:33
     *
     *  @param relationId
     *  @return
     */
    ServeUserVO findServeUser(@Param("id") int relationId);
    
    /**
     *  获取咨询服务师详情
     *  @author yuhang.weng 
     *	@DateTime 2017年7月20日 下午1:57:07
     *
     *  @param serveUserId 服务师ID
     *  @param projectCode 项目code
     *  @return
     */
    ServeUserVO findServeUserByUserIdAndProjectCode(@Param("serveUserId") int serveUserId, @Param("projectCode") String projectCode);

    /**
     * 获取单个咨询服务
     * @param code
     * @return
     */
    ConsultPO findConsult(@Param("code") String code);

    /**
     * 更新咨询服务
     * @param consultPO
     */
    void updateConsult(ConsultPO consultPO);
    
    /**
     * 获取慢病资讯服务师列表
     * 
     * @author zizhen.huang
     * @DateTime 2017年12月19日15:15:31
     * 
     * @return
     */
    List<ServeUserVO> listConsultServeUser();
    
    List<OrgUserVO> listComboOrgUserByComboId(@Param("comboId") int comboId,@Param("comboItemId") int comboItemId);
    
}
