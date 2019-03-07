package com.lifeshs.dao1.org;

import java.util.List;

import com.lifeshs.vo.member.OrgRcmVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.OrgPO;
import com.lifeshs.pojo.org.bank.BankInfoDTO;
import com.lifeshs.pojo.org.profile.OrgProfileDTO;

/**
 *  机构dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月9日 下午3:29:40
 */
@Repository(value = "orgDao")
public interface OrgDao {

    /**
     *  获取机构对象(暂未实现orgServes的值填入，可能后期要取消这个属性)
     *  @author yuhang.weng 
     *  @DateTime 2017年6月9日 下午3:21:42
     *
     *  @param id
     *  @return
     */
    OrgPO getOrg(int id);


    /**
     * @Description: 获取门店信息
     * @Author: wenxian.cai
     * @Date: 2017/6/9 9:38
     */
    OrgProfileDTO getOrgInfo(@Param("orgId") Integer orgId);

    /**
     * @Description: 更新门店信息
     * @Author: wenxian.cai
     * @Date: 2017/6/9 14:51
     */
    int updateOrgInfo(OrgProfileDTO org);
    
    /**
     *  更新机构银行信息
     *  @author yuhang.weng 
     *	@DateTime 2017年6月12日 下午3:43:48
     *
     *  @param data
     *  @return
     */
    int updateOrgBankInfo(BankInfoDTO data);
    
    /**
     *  获取推荐门店
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 上午9:12:07
     *
     *  @param limit
     *  @return
     */
    List<OrgPO> findRecomandedStoreList(@Param("limit") Integer limit);
    
    /**
     *  获取门店列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 上午9:11:59
     *
     *  @param limit
     *  @return
     */
    List<OrgPO> findStoreList(@Param("limit") Integer limit);
    
    /**
     *  通过父管理机构id查询门店列表
     *  @author yuhang.weng 
     *  @DateTime 2017年9月21日 下午2:59:54
     *
     *  @param parentId 管理机构id
     *  @return
     */
    List<OrgPO> findStoreByParentIdList(@Param("parentId") int parentId);
    
    /**
     *  获取门店列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月24日 上午9:35:20
     *
     *  @param name
     *  @return
     */
    List<OrgPO> findStoreListByName(@Param("name") String name);

    /**
     * 获取门店列表及搜索条件
     * @param orgName
     * @param province
     * @param city
     * @param district
     * @param curPage
     * @param pageSize
     * @return
     */
    List<OrgPO> findByListData(@Param("userNo") String userNo, @Param("orgName") String orgName,@Param("province") String province,@Param("city") String city,@Param("district") String district,
                                @Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    /**
     * 统计
     * @param province
     * @param city
     * @param district
     * @param orgName
     * @return
     */
    Integer getListCount(@Param("userNo") String userNo, @Param("province") String province, @Param("city") String city,
                         @Param("district") String district, @Param("orgName") String orgName);

    OrgPO selectByPrimaryKey(@Param("id") Integer id);

    void updateOrgBoolean(@Param("isRecommend") boolean isRecommend,@Param("id") Integer id);

    void findByStatus(@Param("status") Integer status,@Param("id") Integer id);


    Integer getOrgCountByOption(@Param("userNo") String userNo,@Param("param") Integer param);

    List<OrgPO> getOrgListByOption(@Param("userNo") String userNo,@Param("param") Integer param,@Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);

    void updateOrgId(@Param("orgVerified") Integer orgVerified,@Param("id") Integer id,@Param("reason")String reason);

    void updateOrgDelId(Integer orgVerified, Integer id);

    int getOrgRcm(@Param("userNo") String userNo);

    List<OrgRcmVo> getOrgRcmDataList(@Param("userNo") String userNo, @Param("curPage") Integer curPage,@Param("pageSize") Integer pageSize);
}
