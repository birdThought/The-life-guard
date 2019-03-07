package com.lifeshs.service1.systemManage.measureReason;

import java.util.List;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.data.HealthPackParamPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.systemManage.MeasureReasonVo;

/**
 * 测量原因service
 * @author shiqiang.zeng
 * @date 2018.1.30 11:11
 */
public interface MeasureReasonService {
	
	/**
	 * 获取测量原因列表
	 * 
	 * @param healthPackageParamId
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<MeasureReasonVo> findReason(Integer healthPackageParamId, int curPage, int pageSize);

	/**
	 * 添加测量原因
	 * 
	 * @param measureReasonVo
	 * @throws OperationException
	 */
	void addReason(MeasureReasonVo measureReasonVo) throws OperationException;

	/**
	 * 编辑测量原因
	 * 
	 * @param measureReasonVo
	 * @throws OperationException
	 */
	void updateReason(MeasureReasonVo measureReasonVo) throws OperationException;

	/**
	 * 删除测量原因
	 * 
	 * @param id
	 */
	void deleteReason(Integer id) throws OperationException;
	
	/**
	 * 获取健康参数列表
	 * @param id
	 * @return
	 */
	List<HealthPackParamPO> getHealthParam();

}
