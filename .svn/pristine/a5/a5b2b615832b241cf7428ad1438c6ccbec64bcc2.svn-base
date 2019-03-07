package com.lifeshs.service1.serve.consult;

import java.util.List;

import com.lifeshs.common.constants.common.sort.serve.SortEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.ConsultPO;
import com.lifeshs.pojo.org.service.ServiceOrgUserRelationDTO;
import com.lifeshs.pojo.serve.consult.ConsultConditionDTO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.serve.consult.OrgUserVO;
import com.lifeshs.vo.serve.consult.ServeUserVO;

public interface ConsultService {

    /**
     *  获取推荐服务师
     *  @author yuhang.weng 
     *	@DateTime 2017年6月20日 下午2:33:16
     *
     *  @param limit 数量
     *  @return
     */
    List<ServeUserVO> listRecommendedConsult(int limit);
    
    /**
     *  获取咨询条件
     *  @author yuhang.weng 
     *	@DateTime 2017年7月5日 上午11:39:36
     *
     *  @return
     */
    ConsultConditionDTO getConsultCondition();
    
    /**
     *  获取咨询服务列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午2:37:47
     *
     *  @param priceArea
     *  @param type
     *  @param sort
     *  @param likeName
     *  @param curPage
     *  @param pageSize
     *  @return
     */
    Paging<ServeUserVO> listProjectConsult(String priceArea, String type, SortEnum sort, String likeName, int curPage,
            int pageSize);
    
    /**
     *  获取咨询服务师
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午6:53:31
     *
     *  @param id
     *  @return
     */
    ServeUserVO getServeUser(int relationId);
    
    /**
     *  获取咨询服务师
     *  @author yuhang.weng 
     *	@DateTime 2017年7月20日 下午1:54:06
     *
     *  @param serveUserId
     *  @param projectCode
     *  @return
     */
    ServeUserVO getServeUser(int serveUserId, String projectCode);
    
    /**
     *  获取机构咨询服务
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午2:37:31
     *
     *  @param orgId
     *  @return
     */
    List<ServeUserVO> listOrgConsultServe(int orgId);
    
    /**
     *  获取服务师的所有咨询服务
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 下午2:07:28
     *
     *  @param serveUserId
     *  @return
     */
    List<ServeUserVO> listUserConsultServe(int serveUserId);

    /**
     * 获取单个咨询服务
     * @param code
     * @return
     */
    ConsultPO getConsult(String code);

    /**
     * 更新咨询服务
     * @param consultPO
     */
    void updateConsult(ConsultPO consultPO, List<ServiceOrgUserRelationDTO> orgUsers) throws OperationException;
    
    /**
     * 获取慢病资讯服务师列表
     * 
     * @author zizhen.huang
     * @DataTime 2017年12月19日15:15:31
     * 
     * @return
     */
    List<ServeUserVO> listConsultServeUser();
    
    /**
     * 
     *  获取套餐服务师
     *  @author NaN
     *  @DateTime 2018年10月16日 下午4:29:08
     *
     *  @return
     */
    List<OrgUserVO> listComboOrgUserByComboId(int comboId,int comboItemId);
}
