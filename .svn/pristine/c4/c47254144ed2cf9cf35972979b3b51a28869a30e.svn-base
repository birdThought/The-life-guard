package com.lifeshs.dao1.combo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.vip.VipComboItemRelationPO;

/**
 * 套餐关联dao
 * @author shiqiang.zeng
 * @date 2018.1.11 13:28
 */
@Repository(value="comboRelationDao")
public interface ComboRelationDao {
	
	/**
	 * 删除关联关系
	 * @param vipComboId
	 * @param comboItemIdList
	 * @return
	 */
	void delRelationByVipComboIdAndcomboItemId(@Param("vipComboId") int vipComboId);
	
	/**
     * 删除套餐与服务师关联关系
     * @param vipComboId
     * @return
     */
    void delOrgUserRelationByVipComboId(@Param("vipComboId") int vipComboId,@Param("vipComboItemId") String vipComboItemId,
            @Param("userId") String userId);
	
	
	/**
	 * 添加关联关系
	 * @param vipComboId
	 * @param vipComboItemId
	 * @prram number
	 * @return
	 */
	int addComboRelation(@Param("vipComboId") int vipComboId,@Param("vipComboItemId")int vipComboItemId,@Param("number") int number);
	
	/**
	 * 
	 *  添加套餐与服务师关系
	 *  @author NaN
	 *  @DateTime 2018年10月15日 上午9:31:26
	 *
	 *  @return
	 */
	int addComboOrgUserRelation(@Param("comboId") Integer comboId,@Param("vipComboItemId")String vipComboItemId,@Param("orgUserId") Integer orgUserId);
	
	/**
     * 
     *  查找套餐与服务师关系
     *  @author NaN
     *  @DateTime 2018年10月15日 上午9:31:26
     *
     *  @return
     */
	int findComboOrgUserRelation(@Param("comboId") Integer comboId,@Param("vipComboItemId")String vipComboItemId,@Param("orgUserId") Integer orgUserId);
	
	/**
     * 根据套餐id查找关系
     * @param vipComboId
     * @param comboItemIdList
     * @return
     */
    List<VipComboItemRelationPO> findComboItemList(@Param("vipComboId") int vipComboId);
    
//    List<VipComboItemServeUserRelationPO> findComboItemList(@Param("vipComboId") int vipComboId);
	
}
