package com.lifeshs.service1.systemManage.food;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.systemManage.FoodVo;

public interface FoodService {
	
	/**
	 * 获取食物列表
	 * @param kind
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<FoodVo> findFood(Integer kind,String name,int curPage,int pageSize);
	
	/**
	 * 添加食物
	 * @param foodVo
	 */
	void addFood(FoodVo foodVo) throws OperationException;
	
	/**
	 * 编辑食物
	 * @param foodVo
	 */
	void updateFood(FoodVo foodVo)throws OperationException;
	
	/**
	 * 删除食物
	 * @param id
	 */
	void deleteFood(Integer id)throws OperationException;
}
