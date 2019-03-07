package com.lifeshs.dao1.systemManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.record.TDataFoodKind;

/**
 * 食物种类
 * @author shiqiang.zeng
 * @date 2018.1.25 17:41
 *
 */
@Repository(value="foodKindDao")
public interface FoodKindDao {
	
	/**
	 * 查询食物种类列表
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<TDataFoodKind> findFoodKind(@Param("startRow")int startRow,@Param("pageSize") int pageSize);
	
	/**
	 * 食物种类列表
	 * @return
	 */
	List<TDataFoodKind> findfoodkind();
	/**
	 * 统计食物种类数量
	 * @return
	 */
	int countFoodKind();
	
	/**
	 * 添加食物种类
	 * @param tDataFoodKind
	 */
	void addFoodKind(TDataFoodKind tDataFoodKind);
	
	/**
	 * 更改食物种类
	 * @param tDataFoodKind
	 */
	void updateFoodKind(TDataFoodKind tDataFoodKind);
	
	/**
	 * 删除食物种类
	 * @param id
	 */
	void deleteFoodKind(@Param("id") Integer id);

}
