package com.lifeshs.dao1.systemManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.data.TDataSportKind;

/**
 * 运动种类
 * @author shiqiang.zeng
 * @date 2018.1.27 16:06
 */
@Repository("sportKindDao")
public interface SportKindDao {
	
	/**
	 * 获取运动类型(分页)
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<TDataSportKind> findSportKind(@Param("startRow")int startRow,@Param("pageSize") int pageSize);
	
	/**
	 * 获取运动类型列表
	 * @return
	 */
	List<TDataSportKind> findsportKind();
	
	/**
	 * 统计运动类型
	 * @return
	 */
	int countSportKind();
	
	/**
	 * 添加运动种类
	 * @param tDataSportKind
	 */
	void addSportKind(TDataSportKind tDataSportKind);
	
	/**
	 * 编辑运动种类
	 * @param tDataSportKind
	 */
	void updateSportKind(TDataSportKind tDataSportKind);
	
	/**
	 * 删除运动种类
	 * @param id
	 */
	void deleteSportKind(Integer id);
	
}
