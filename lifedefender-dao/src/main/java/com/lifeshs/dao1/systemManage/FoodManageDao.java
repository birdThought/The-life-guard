package com.lifeshs.dao1.systemManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.beust.jcommander.Parameter;
import com.lifeshs.vo.systemManage.FoodVo;

@Repository(value = "foodManageDao")
public interface FoodManageDao {

	/**
	 * 查询食物列表
	 * 
	 * @param kind
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<FoodVo> findFood(@Param("kind") Integer kind, @Param("name") String name,
						@Param("startRow") int startRow,@Param("pageSize") int pageSize);

	/**
	 * 统计食物列表
	 * 
	 * @return
	 */
	int countFood(@Param("kind") Integer kind,@Param("name") String name);

	/**
	 * 添加食物
	 * 
	 * @param tDataFood
	 */
	void addFood(FoodVo foodVo);

	/**
	 * 编辑食物
	 * 
	 * @param tDataFood
	 */
	void updateFood(FoodVo foodVo);

	/**
	 * 删除食物
	 * 
	 * @param id
	 */
	void deleteFood(@Param("id") Integer id);

}
