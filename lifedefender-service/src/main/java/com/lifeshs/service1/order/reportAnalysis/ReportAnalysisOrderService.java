package com.lifeshs.service1.order.reportAnalysis;

import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.order.reportAnalysis.ReportAnalysisOrderVO;

/**
 * 分析报告订单
 * author: wenxian.cai
 * date: 2017/10/10 14:19
 */
public interface ReportAnalysisOrderService {

	/**
	 * 获取分析报告订单列表
	 * @param keyword
	 * @param status
	 * @return
	 */
	Paging<ReportAnalysisOrderVO> findOrderList(String startDate, String endDate, Integer businessId, String keyword, Integer status, int pageIndex, int pageSize);

	/**
	 * 完成订单
	 * @param orderId
	 * @param reply
	 */
	void finishOrder(int orderId, String reply, String doctorSign, int customerUserId) throws OperationException;

	/**
	 * 创建订单
	 * @param vo
	 */
	ReportAnalysisOrderVO createOrder(ReportAnalysisOrderVO vo) throws OperationException;

	/**
	 * 设置订单为“处理中”状态
	 * author: wenxian.cai
	 * date: 2017/11/2 14:15
	 */
	void validOrder(String orderNumber, String tradeNo, String payAccount, String sellerAccount,
            double totalCount, PayTypeEnum payType, String deviceType) throws OperationException;
}
