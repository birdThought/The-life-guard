package com.lifeshs.service1.store;

import java.util.List;

import com.lifeshs.po.EmployeePO;
import com.lifeshs.po.OrgPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.member.OrgRcmVo;
import org.apache.ibatis.annotations.Param;

/**
 *  门店
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年7月10日 下午4:17:51
 */
public interface StoreService {

    /**
     *  获取一个门店信息
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午2:47:20
     *
     *  @param id
     *  @return
     */
    OrgPO getStore(int id);
    
    /**
     *  获取门店消费人次
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午2:55:21
     *
     *  @param id
     *  @return
     */
    int getStoreConsumeMemberNumber(int id);
    
    /**
     *  获取推荐门店
     *  @author yuhang.weng 
     *	@DateTime 2017年7月10日 下午5:28:55
     *
     *  @param limit
     *  @return
     */
    List<OrgPO> listRecommendedStore(int limit);
    
    /**
     *  获取门店列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 上午9:15:58
     *
     *  @param limit
     *  @return
     */
    List<OrgPO> listStore(int limit);
    
    /**
     *  获取门店列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月24日 上午9:39:23
     *
     *  @param name 门店名称
     *  @return
     */
    List<OrgPO> listStore(String name);

    /**
     * 获取门店列表
     * @param orgName
     * @param province
     * @param city
     * @param district
     * @param page
     * @param pageSize
     * @return
     */
    Paging<OrgPO> getOrgListData(String userNo, String orgName, String province, String city, String district, Integer page, int pageSize);

    OrgPO getDetailsOrg(Integer id);

    void findByOrgUpdate(boolean isRecommend, Integer id);

    void findByOrgSrttus(Integer status, Integer id);

    Paging<OrgPO> findByOrgCheck(@Param("userNo") String userNo,@Param("param") Integer param,@Param("curPage") Integer page,@Param("pageSize") int pageSize);

    OrgPO findByOrgCheckId(Integer id);

    void findByOrgVerify(Integer id,Integer orgVerified);

    EmployeePO findByModifyIrg(Integer id,Integer orgVerified,String reason);

    Paging<OrgRcmVo> findByOrgRcm(String userNo, Integer page, int pageSize);
}
