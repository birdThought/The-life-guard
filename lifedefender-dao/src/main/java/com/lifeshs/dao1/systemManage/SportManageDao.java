package com.lifeshs.dao1.systemManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.vo.systemManage.SportVo;

@Repository(value="sportManageDao")
public interface SportManageDao {
	
	/**
	 * 查询运动列表
	 * 
	 * @param kind
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<SportVo> findSport(@Param("kind") Integer kind, @Param("name") String name,
						@Param("startRow") int startRow,@Param("pageSize") int pageSize);

	/**
	 * 统计运动列表
	 * 
	 * @return
	 */
	int countSport(@Param("kind") Integer kind,@Param("name") String name);

	/**
	 * 添加运动
	 * 
	 * @param tDataSport
	 */
	void addSport(SportVo sportVo);

	/**
	 * 编辑运动
	 * 
	 * @param tDataSport
	 */
	void updateSport(SportVo sportVo);

	/**
	 * 删除运动
	 * 
	 * @param id
	 */
	void deleteSport(@Param("id") Integer id);

}
