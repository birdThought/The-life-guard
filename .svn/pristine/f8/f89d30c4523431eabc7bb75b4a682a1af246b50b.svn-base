package com.lifeshs.service1.order.reportAnalysis.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.constants.common.MessageType;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.UserType;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.constants.common.reportAnalysis.ContentStatusEnum;
import com.lifeshs.common.constants.common.reportAnalysis.RequestorTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.dao1.order.reportAnalysis.IReportAnalysisOrderDao;
import com.lifeshs.po.order.ReportAnalysisOrderPO;
import com.lifeshs.po.push.UserDeviceTokenPO;
import com.lifeshs.component.umeng.util.CallBackDTO;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service1.member.ReportAnalysisService;
import com.lifeshs.service1.order.reportAnalysis.ReportAnalysisOrderService;
import com.lifeshs.service1.order.vip.VipOrderFlowService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.push.PushDataService;
import com.lifeshs.thirdservice.UMengPushService;
import com.lifeshs.utils.DateTimeUtilT;
import com.lifeshs.utils.NumberUtils;
import com.lifeshs.vo.order.reportAnalysis.ReportAnalysisOrderVO;

/**
 * 分析报告订单
 * author: wenxian.cai
 * date: 2017/10/10 14:19
 */
@Service("reportAnalysisOrderService")
public class ReportAnalysisOrderServiceImpl implements ReportAnalysisOrderService{
	private Logger logger = Logger.getLogger(ReportAnalysisOrderServiceImpl.class);

	@Autowired
	IReportAnalysisOrderDao reportAnalysisOrderDao;
	@Autowired
	ReportAnalysisService reportAnalysisService;
	@Resource(name = "pushDataService")
	private PushDataService pDataService;
	@Autowired
	private UMengPushService uMengService;

	@Resource(name = "vipOrderFlowServiceImpl")
    private VipOrderFlowService orderFlowService;
	
	@Override
	public Paging<ReportAnalysisOrderVO> findOrderList(String startDate, String endDate, Integer businessId, String keyword, Integer status, int pageIndex, int pageSize) {
		Paging paging = new Paging(pageIndex, pageSize);
		paging.setQueryProc(new IPagingQueryProc() {
			@Override
			public int queryTotal() {
				return reportAnalysisOrderDao.countOrder(startDate, endDate, businessId, keyword, status);
			}

			@Override
			public List queryData(int startRow, int pageSize) {
				List<ReportAnalysisOrderVO> list = reportAnalysisOrderDao.findOrderList(startDate, endDate, businessId, keyword, status, paging.getStartRow(), pageSize);
				for (ReportAnalysisOrderVO reportAnalysisOrderVO : list) {
					reportAnalysisOrderVO.setPrice(NumberUtils.changeF2Y(reportAnalysisOrderVO.getPrice() + ""));
				}
				return list;
			}
		});
		return paging;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public void finishOrder(int orderId, String reply, String doctorSign, int customerUserId) throws OperationException{
		ReportAnalysisOrderPO reportAnalysisOrderPO = reportAnalysisOrderDao.getOrder(orderId);
		if (reportAnalysisOrderPO == null) {
			throw new OperationException("该订单不存在", ErrorCodeEnum.NOT_FOUND);
		}
		if (reportAnalysisOrderPO.getStatus() == OrderStatus.COMPLETED.getStatus()) {
			throw new OperationException("订单已完成，不可重复提交", ErrorCodeEnum.REPEAT);
		}
		reportAnalysisService.replyReport(reportAnalysisOrderPO.getUserReportAnalysisId(), customerUserId, reply, doctorSign);

		try {
			reportAnalysisOrderDao.updateOrder(orderId, OrderStatus.COMPLETED.getStatus());
		} catch (Exception e) {
			throw new OperationException("分析报告订单：更新状态失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public ReportAnalysisOrderVO createOrder(ReportAnalysisOrderVO vo) throws OperationException {

		//添加报告
		HealthPackageType healthPackageType = null;
		int healthProduct = vo.getReportAnalysisPO().getHealthProduct();
		for (HealthPackageType health : HealthPackageType.values()) {
			if (health.value() == healthProduct) {
				healthPackageType = health;
			}
		}
		RequestorTypeEnum requestorType = null;
		int requestor = vo.getReportAnalysisPO().getRequestorType();
		for (RequestorTypeEnum requestorTypeEnum : RequestorTypeEnum.values()) {
			if (requestorTypeEnum.getValue()== requestor) {
				requestorType = requestorTypeEnum;
			}
		}
		String content = vo.getReportAnalysisPO().getContent();
		ContentStatusEnum status = ContentStatusEnum.getStatus(vo.getReportAnalysisPO().getStatus());
		int id = reportAnalysisService.commitReport(vo.getUserPO().getId(), requestorType, healthPackageType, content, status);

		//添加订单
		ReportAnalysisOrderPO order = new ReportAnalysisOrderPO();
		order.setUserReportAnalysisId(id);
		order.setOrderNumber(AlipayService.createOrderNumber());
		order.setBusinessUserId(vo.getBusinessUserId());
		order.setStatus(OrderStatus.PAYABLE.getStatus());
		order.setPrice(NumberUtils.changeY2F(vo.getPrice() + ""));
		order.setUserId(vo.getUserPO().getId());
		if (vo.getReportAnalysisPO().getHealthProduct() == 16384) {
			order.setSubject("血脂分析");
		}else if (vo.getReportAnalysisPO().getHealthProduct() == 8192){
			order.setSubject("尿酸分析");
		}else if (vo.getReportAnalysisPO().getHealthProduct() == 4096){
			order.setSubject("尿检分析");
		}else if (vo.getReportAnalysisPO().getHealthProduct() == 64){
			order.setSubject("血糖分析");
		}else{
			order.setSubject("心电分析");
		}
		vo.setOrderNumber(order.getOrderNumber());
		// 如果订单编号重复，最多重新生成5次订单编号
		int result = 0;
		for (int i = 0; i < 5; i++) {
			try{
				result = reportAnalysisOrderDao.addOrder(order);
				break;
			} catch (DuplicateKeyException e) {
				// 重新生成订单编号
				order.setOrderNumber(AlipayService.createOrderNumber());
			}
		}
		if (result == 0) {
			throw new OperationException("分析报告：新增订单失败", ErrorCodeEnum.FAILED);
		}

		// 发送推送消息
		uMengService.replyReportStart(vo.getUserPO().getId(), result);

		return vo;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
	public void validOrder(String orderNumber, String tradeNo, String payAccount, String sellerAccount,
            double totalCount, PayTypeEnum payType, String deviceType) throws OperationException{
		ReportAnalysisOrderPO reportAnalysisOrderPO = reportAnalysisOrderDao.getOrderByOrderNumber(orderNumber);
		if (reportAnalysisOrderPO == null) {
			throw new OperationException("该订单不存在", ErrorCodeEnum.NOT_FOUND);
		}
		try {
			reportAnalysisOrderDao.updateOrder(reportAnalysisOrderPO.getId(), OrderStatus.VALID.getStatus());
		} catch (Exception e) {
			throw new OperationException("分析报告订单：更新状态失败", ErrorCodeEnum.FAILED);
		}
		
		// 添加流水
        int totalC = NumberUtils.changeY2F(String.valueOf(totalCount));
        orderFlowService.addOrderFlow(reportAnalysisOrderPO.getOrderNumber(), payType, totalC, totalC, payAccount, sellerAccount, tradeNo, null,null);
	}
}
