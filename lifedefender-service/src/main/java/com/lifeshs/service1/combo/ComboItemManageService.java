package com.lifeshs.service1.combo;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.vip.VipComboItemPO;
import com.lifeshs.service1.page.Paging;

/**
 * 
 *  套餐项目管理
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年6月1日 下午5:01:12
 */
public interface ComboItemManageService {
	
	/**
	 * 套餐列表
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<VipComboItemPO> listComboItem(int curPage,int pageSize);
	
	 /**
      * 添加套餐项目
      * @param comboItemVo
      * @throws OperationException
      */
     void addComboItem(VipComboItemPO comboItemVo) throws OperationException;
	
	
	/**
	 * 更改套餐项目
	 * @param comboItemVo
	 * @throws OperationException
	 */
	void updataComboItem(VipComboItemPO comboItemVo)throws OperationException;
	

	/**
	 * 删除套餐项目
	 * @param comboItemVo
	 * @throws OperationException
	 */
	void deleteComboItem(Integer comboItemId)throws OperationException;
	
}
