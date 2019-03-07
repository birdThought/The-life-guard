package com.lifeshs.dao.org;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.pojo.org.bank.BankInfoDTO;
import com.lifeshs.pojo.org.profile.OrgProfileDTO;
import com.lifeshs.pojo.org.v2.OrgDTO;

/**
 *  机构dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月9日 下午3:29:40
 */
//@Repository(value = "orgDao")
public interface OrgDao {

    /**
     *  获取机构对象(暂未实现orgServes的值填入，可能后期要取消这个属性)
     *  @author yuhang.weng 
     *  @DateTime 2017年6月9日 下午3:21:42
     *
     *  @param id
     *  @return
     */
    OrgDTO getOrg(int id);


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
    List<OrgDTO> findRecomandedStoreList(@Param("limit") Integer limit);
    
    /**
     *  获取门店列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月12日 上午9:11:59
     *
     *  @param limit
     *  @return
     */
    List<OrgDTO> findStoreList(@Param("limit") Integer limit);
}
