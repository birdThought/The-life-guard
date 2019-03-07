package com.lifeshs.dao1.order.reportAnalysis;

import com.lifeshs.po.order.ReportAnalysisOrderPO;
import com.lifeshs.vo.order.reportAnalysis.ReportAnalysisOrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分析报告订单业务
 * author: wenxian.cai
 * date: 2017/10/10 11:29
 */

@Repository("reportAnalysisOrderDao")
public interface IReportAnalysisOrderDao {

	/**
	 * 获取分析报告订单
	 * @param keyword 关键字（用户名/用户手机号/诊所用户名）
	 * @param status 订单状态
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<ReportAnalysisOrderVO> findOrderList(@Param("startDate") String startDate, @Param("endDate") String endDate,
											  @Param("businessId") Integer businessId,
											  @Param("keyword") String keyword, @Param("status") Integer status,
											  @Param("pageIndex")int pageIndex, @Param("pageSize")int pageSize);

	/**
	 * 获取分析报告订单数量
	 * @param keyword 关键字（用户名/用户手机号/诊所用户名）
	 * @param status 订单状态
	 * @return
	 */
	int countOrder(@Param("startDate") String startDate, @Param("endDate") String endDate,
				   @Param("businessId") Integer businessId,
				   @Param("keyword") String keyword, @Param("status") Integer status);

	/**
	 * 获取单个分析报告订单
	 * @param id
	 * @return
	 */
	ReportAnalysisOrderPO getOrder(@Param("id") int id);

	/**
	 * 获取单个分析报告订单
	 * @param orderNumber
	 * @return
	 */
	ReportAnalysisOrderPO getOrderByOrderNumber(@Param("orderNumber") String orderNumber);

	/**
	 * 更新订单状态
	 * @param id
	 * @param status
	 */
	void updateOrder(@Param("id") int id, @Param("status") int status);

	/**
	 * 新增订单
	 * @param po
	 */
	int addOrder(ReportAnalysisOrderPO po);
}
