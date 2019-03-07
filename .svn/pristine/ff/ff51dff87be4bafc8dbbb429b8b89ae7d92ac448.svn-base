package com.lifeshs.dao.paper;

import java.util.List;

import com.lifeshs.pojo.paper.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *  版权归
 *  TODO 问卷调查Dao
 *  @author wenxian.cai 
 *  @datetime 2017年3月7日下午4:32:17
 */

@Repository(value = "paperDao")
public interface IPaperDao {
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月7日下午4:52:59
	 * @serverComment 根据问卷类型获取问卷题目列表
	 * @param type
	 */
	List<PaperDTO> listPaper(@Param(value = "name") String typeName);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月7日下午5:49:12
	 * @serverComment 根据问卷类型获取问卷选项
	 * @param type
	 */
	List<PaperOptionDTO> listPaperOption(@Param(value = "name") String typeName);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月8日下午3:17:11
	 * @serverComment 添加问卷测试结果
	 * @param paperResultDTO
	 */
	Integer addPaperResult(PaperResultDTO paperResultDTO);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月8日下午3:51:11
	 * @serverComment 获取全部问卷类型
	 * @param 
	 */
	List<PaperTypeDTO> listPaperType();
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月8日下午7:15:15
	 * @serverComment 获取亚健康结果分析
	 * @param score 分数
	 */
	PaperSubHealthStandardDTO getPaperSubHealthStandard(Integer score);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月9日下午4:11:27
	 * @serverComment 获取体质分析结果
	 * @param physicalName 体质名称
	 */
	PaperPhysicalStandardDTO getPaperPhysicalStandard(String physicalName);

	/**
	 * 获取中风风险测试结果分析
	 * @param name
	 * @return
	 */
	PaperStrokeStandardDTO getPaperStrokeStandard(String name);
}
