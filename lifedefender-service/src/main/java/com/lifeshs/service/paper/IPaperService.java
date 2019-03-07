package com.lifeshs.service.paper;

import java.util.List;

import com.lifeshs.pojo.paper.*;

/**
 *  版权归
 *  TODO 问卷接口
 *  @author wenxian.cai 
 *  @datetime 2017年3月7日下午4:57:29
 */
public interface IPaperService {
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月7日下午4:59:32
	 * @serverComment 根据问卷类型获取问卷题目列表
	 * @param type
	 */
	List<PaperDTO> listPaper(String type);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月7日下午5:47:53
	 * @serverComment 根据问卷类型获取问卷选项
	 * @param type
	 */
	List<PaperOptionDTO> listPaperOption(String type);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月8日下午3:18:15
	 * @serverComment 添加问卷测试结果
	 * @param paperResultDTO
	 */
	Boolean addPaperResult(PaperResultDTO paperResultDTO);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月8日下午7:16:43
	 * @serverComment 获取亚健康结果分析
	 * @param 
	 */
	PaperSubHealthStandardDTO getPaperSubHealthStandard(int score);
	
	/**
	 * @author wenxian.cai
	 * @DateTime 2017年3月9日下午4:12:35
	 * @serverComment 获取体质结果分析
	 * @param 
	 */
	PaperPhysicalStandardDTO getPaperPhysicalStandard(String physicalName);

	/**
	 * 获取中风风险测试结果分析
	 * @param physicalName
	 * @return
	 */
	PaperStrokeStandardDTO getPaperStrokeStandard(String physicalName);
}
