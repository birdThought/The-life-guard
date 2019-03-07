package com.lifeshs.dao1.systemManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.data.HealthPackParamPO;
import com.lifeshs.vo.systemManage.MeasureSuggestionVo;

/**
 * 测量建议dao
 * 
 * @author shiqiang.zeng
 * @date 2018.1.29 11:37
 */
@Repository(value = "measureSuggestionDao")
public interface MeasureSuggestionDao {

	/**
	 * 获取测量建议列表
	 * 
	 * @param healthPackageParamId
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<MeasureSuggestionVo> findMessageSuggestion(@Param("healthPackageParamId") Integer healthPackageParamId,
			@Param("startRow") int startRow, @Param("pageSize") int pageSize);

	/**
	 * 统计测量建议
	 * 
	 * @param healthPackageParamId
	 * @return
	 */
	int countSuggestion(@Param("healthPackageParamId") Integer healthPackageParamId);

	/**
	 * 添加测量建议
	 * 
	 * @param measureSuggestionVo
	 */
	void addSuggestion(MeasureSuggestionVo measureSuggestionVo);

	/**
	 * 修改测量建议
	 * 
	 * @param measureSuggestionVo
	 */
	void updateSuggestion(MeasureSuggestionVo measureSuggestionVo);

	/**
	 * 删除测量建议
	 * 
	 * @param id
	 */
	void deleteSuggestion(@Param("id") Integer id);
	
	/**
	 * 获取健康参数列表
	 * @param id
	 * @return
	 */
	List<HealthPackParamPO> getHealthParamById();
}
