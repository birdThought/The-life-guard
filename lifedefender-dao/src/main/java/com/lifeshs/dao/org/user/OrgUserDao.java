package com.lifeshs.dao.org.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.org.user.TOrgUser;
import com.lifeshs.po.org.user.OrgUserPO;
import com.lifeshs.pojo.org.OrgUserDTO;
import com.lifeshs.pojo.org.profile.BaseProfileDTO;
import com.lifeshs.shop.ShopDTO;

@Repository(value = "orgUserDao")
public interface OrgUserDao {

    /**
     * 更新基础信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年5月11日 上午10:36:59
     *
     * @param data
     */
    void updateBaseProfile(BaseProfileDTO data);

    List<Integer> getOrgUsersByOrgId(@Param("orgId") Integer orgId);
    
    /**
     *  通过用户名查找机构用户
     *  @author yuhang.weng 
     *  @DateTime 2018年1月19日 下午3:55:40
     *
     *  @param userName
     *  @return
     */
    OrgUserPO findUserByUserName(@Param("userName") String userName);
    
    /**
     *  通过已验证的手机号码查找用户
     *  @author yuhang.weng 
     *  @DateTime 2018年1月19日 下午3:55:54
     *
     *  @param mobile
     *  @return
     */
    OrgUserPO findUserByVerifyMobile(@Param("mobile") String mobile);
    
    /**
     * 
     *  根据门店id查找服务师
     *  @author liaoguo
     *  @DateTime 2018年5月23日 上午11:17:18
     *
     *  @param orgId
     *  @return
     */
    List<TOrgUser> findOrgUserByOrgId(@Param("orgId") Integer orgId);
    
    /**
     * 
     *  修改服务师密码
     *  @author liaoguo
     *  @DateTime 2018年5月23日 下午4:53:27
     *
     *  @param userId
     *  @param pwd
     *  @return
     */
    Integer updateOrgUserPwdByUserId(@Param("id") Integer id, @Param("pwd") String pwd);
    
    
    /**
     * 
     *  根据门店Id查找服务师总数量
     *  @author liaoguo
     *  @DateTime 2018年5月23日 上午11:15:05
     *
     *  @param orgId 门店id
     *  @return
     */
    Integer getOrgUserCountByOrgId(@Param("orgId") Integer orgId);


    /**
     * 通过id 查找
     * @param id
     * @return
     */
    OrgUserPO findUserByVerifyId(@Param("id") Integer id);
    
    OrgUserPO findOrgUserByParam(@Param("userNo") String userNo,@Param("orgId") Integer orgId);

    
    public List<TOrgUser> getEmployListByRealName(@Param("rName") String rName, @Param("orgId") int orgId);
    
    public List<OrgUserPO> getOrgUserByRealName(@Param("rName") String rName);
    
    public List<OrgUserPO> findComboOrgUserRelation(@Param("comboId") int comboId,@Param("vipComboItemId") int vipComboItemId);
    
    public List<OrgUserDTO> findComboServeUserRelation(@Param("comboId") int comboId,@Param("vipComboItemId") int vipComboItemId);
    
    ShopDTO getShopByOrgId(@Param("orgId") Integer orgId);
}
