package com.lifeshs.service1.combo;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.vip.VipComboItemRelationPO;
import com.lifeshs.po.vip.VipComboPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.combo.ComboItemListVo;
import com.lifeshs.vo.combo.ComboVo;

/**
 * 套餐管理
 * @author shiqiang.zeng
 * @Date 2018.1.10 14:27
 *
 */
public interface ComboManageService {
	
	/**
	 * 套餐列表
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<ComboVo> findComboList(int curPage,int pageSize);
	
	/**
	 * 更改套餐
	 * @param comboVo
	 * @throws OperationException
	 */
	void updataCombo(ComboVo comboVo)throws OperationException;
	
	/**
	 * 
	 *  更改套餐关系
	 *  @author NaN
	 *  @DateTime 2018年5月30日 下午3:36:29
	 *
	 *  @param comboId
	 *  @param vipComboItem
	 *  @param orgUserIds
	 */
	void updataComboItemRelation(Integer comboId, String vipComboItem,String orgUserIds);
	
	/**
	 * 删除套餐
	 * @param comboVo
	 * @throws OperationException
	 */
	void deleteCombo(ComboVo comboVo)throws OperationException;
	
	/**
	 * 
	 *  删除套餐与服务师关系
	 *  @author NaN
	 *  @DateTime 2018年10月22日 下午4:19:36
	 *
	 *  @param vipComboId
	 *  @param vipComboItemId
	 *  @param userId
	 */
	void delOrgUserRelationByVipComboId(Integer vipComboId, String vipComboItemId,String userId);
	/**
     * 
     *  添加套餐与服务师关系
     *  @author NaN
     *  @DateTime 2018年10月22日 下午4:19:36
     *
     *  @param vipComboId
     *  @param vipComboItemId
     *  @param userId
     */
	void addComboOrgUserRelation(Integer vipComboId, String vipComboItemId,Integer orgUserId);
	
	/**
     * 
     *  查找套餐与服务师关系
     *  @author NaN
     *  @DateTime 2018年10月22日 下午4:19:36
     *
     *  @param vipComboId
     *  @param vipComboItemId
     *  @param userId
     */
    int findComboOrgUserRelation(Integer vipComboId, String vipComboItemId,Integer userId);
	
	/**
     * 根据套餐id查找关系
     * @param vipComboId
     * @param comboItemIdList
     * @return
     */
    List<VipComboItemRelationPO> findComboItemList(Integer vipComboId);
//    List<VipComboItemServeUserRelationPO> findComboItemList(Integer vipComboId);
    
    
	/**
	 * 添加套餐
	 * @param vipComboItem
	 * @param comboVo
	 * @throws OperationException
	 */
	void addCombo(String vipComboItem,String orgUserIds, ComboVo comboVo) throws OperationException;
	
	/**
	 * 获取套餐项目列表
	 * @return
	 */
	List<ComboItemListVo > getComboItemList();
	
	/**
     * 
     *  获取套餐类型
     *  @author liaoguo
     *  @DateTime 2018年5月29日 下午4:12:30
     *
     *  @return
     */
    List<VipComboPO> findL1All();
}
