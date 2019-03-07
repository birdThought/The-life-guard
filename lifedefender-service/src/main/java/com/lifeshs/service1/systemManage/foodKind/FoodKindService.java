package com.lifeshs.service1.systemManage.foodKind;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.entity.record.TDataFoodKind;
import com.lifeshs.service1.page.Paging;

/**
 * 食物种类
 * @author shiqiang.zneg
 * @date 2018.1.25 17:40
 *
 */
public interface FoodKindService {
	
	/**
	 * 食物种类列表
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<TDataFoodKind> findFoodKind(int curPage,int pageSize);
	
	/**
	 * 食物种类列表
	 * @return
	 */
	List<TDataFoodKind> findfoodKind();
	
	/**
	 * 添加食物种类
	 * @param tDataFoodKind
	 * @throws OperationException
	 */
	void addFoodKind(TDataFoodKind tDataFoodKind) throws OperationException;
	
	/**
	 * 更改食物种类
	 * @param tDataFoodKind
	 * @throws OperationException
	 */
	void updateFoodKind(TDataFoodKind tDataFoodKind) throws OperationException;
	
	/**
	 * 删除食物种类
	 * @param id
	 * @throws OperationException
	 */
	void deleteFoodKind(Integer id)throws OperationException;
	
	
	

}
