package com.lifeshs.service1.systemManage.measureSuggestion;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.data.HealthPackParamPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.systemManage.MeasureSuggestionVo;

/**
 * 测量建议service
 * 
 * @author shiqiang.zeng
 * @date 2018.1.29
 */
public interface MeasureSuggestionService {

	/**
	 * 获取测量建议列表
	 * 
	 * @param healthPackageParamId
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<MeasureSuggestionVo> findSuggestion(Integer healthPackageParamId, int curPage, int pageSize);

	/**
	 * 添加测量建议
	 * 
	 * @param measureSuggestionVo
	 * @throws OperationException
	 */
	void addSuggestion(MeasureSuggestionVo measureSuggestionVo) throws OperationException;

	/**
	 * 编辑测量建议
	 * 
	 * @param measureSuggestionVo
	 * @throws OperationException
	 */
	void updateSuggestion(MeasureSuggestionVo measureSuggestionVo) throws OperationException;

	/**
	 * 删除测量建议
	 * 
	 * @param id
	 */
	void deleteSuggestion(Integer id) throws OperationException;
	
	/**
	 * 获取健康参数列表
	 * @param id
	 * @return
	 */
	List<HealthPackParamPO> getHealthParam();

}
