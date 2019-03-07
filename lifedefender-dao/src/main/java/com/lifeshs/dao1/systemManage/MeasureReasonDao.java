package com.lifeshs.dao1.systemManage;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.data.HealthPackParamPO;
import com.lifeshs.vo.systemManage.MeasureReasonVo;

/**
 * 测量原因dao
 * @author shiqiang.zeng
 * @date 2018.1.30 10:49
 */
@Repository(value="measureReasonDao")
public interface MeasureReasonDao {
	
	/**
	 * 获取测量原因列表
	 * 
	 * @param healthPackageParamId
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<MeasureReasonVo> findMessageReason(@Param("healthPackageParamId") Integer healthPackageParamId,
			@Param("startRow") int startRow, @Param("pageSize") int pageSize);

	/**
	 * 统计测量原因
	 * 
	 * @param healthPackageParamId
	 * @return
	 */
	int countReason(@Param("healthPackageParamId") Integer healthPackageParamId);

	/**
	 * 添加测量原因
	 * 
	 * @param measureReasonVo
	 */
	void addReason(MeasureReasonVo measureReasonVo);

	/**
	 * 修改测量原因
	 * 
	 * @param measureReasonVo
	 */
	void updateReason(MeasureReasonVo measureReasonVo);

	/**
	 * 删除测量原因
	 * 
	 * @param id
	 */
	void deleteReason(@Param("id") Integer id);
	
	/**
	 * 获取健康参数列表
	 * @param id
	 * @return
	 */
	List<HealthPackParamPO> getHealthParamById();
}
