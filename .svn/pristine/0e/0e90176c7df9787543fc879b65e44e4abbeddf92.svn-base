package com.lifeshs.thirdservice.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.dao1.order.IOrderDao;
import com.lifeshs.dao1.order.IOrderFlowDao;
import com.lifeshs.dao1.order.IOrderRefundDao;
import com.lifeshs.po.OrderFlowPO;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.OrderRefundPO;
import com.lifeshs.thirdservice.IAlipayService;
import com.lifeshs.utils.MoneyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 支付宝服务统一接口，所有方法统一返回{@code <T entends AlipayResponse> alipayResponse}
 * <p>
 * 退款用法举例: if(aliPayResponse.isSuccess()){
 * //响应成功逻辑处理
 * <p>
 * if("10000".equals(alipayResponse.getCode()){
 * //退款成功逻辑
 * }else{
 * //退款失败逻辑
 * <p>
 * }
 * }else{
 * //响应失败，业务逻辑处理
 * }
 */
@Service("iAlipayService")
public class AlipayServiceImpl implements IAlipayService {

    private final Logger logger = LoggerFactory.getLogger(AlipayServiceImpl.class);

    @Autowired
    IOrderRefundDao orderRefundDao;

    @Autowired
    IOrderDao orderDao;

    @Autowired
    IOrderFlowDao orderFlowDao;

    /**
     * 退款
     *
     * @param orderId
     * @return aliPayResponse 支付宝退款响应对象
     * @throws AlipayApiException
     * @throws IOException
     * @throws DataBaseException
     * @author wuj
     * @update none
     * @since 2017-07-17 16:09:22
     */
    public AlipayTradeRefundResponse refund(Integer orderId) throws AlipayApiException, IOException, DataBaseException {
        logger.info("开始支付宝退款流程,退款订单ID:{}", orderId);

        //获得初始化的AlipayClient
        AlipayClient alipayClient = AlipayUtils.getAlipayClient();

        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        // 获取OrderPO
        OrderPO orderPO = orderDao.getOrderDetail(orderId);
        if (orderPO == null) {
            logger.info("未查询到用户订单,订单ID:" + orderId);
            throw new DataBaseException("未查询到用户订单,订单ID:" + orderId);
        } else if (OrderStatus.REFUNDED.getStatus().equals(orderPO.getStatus())) {
            throw new DataBaseException("该订单已成功退款");
        }

        //获取orderRefundPO
        String orderNumber = orderPO.getOrderNumber();
        OrderRefundPO orderRefundPO = orderRefundDao.getOrderRefundPOByOrderNumber(orderNumber);
        if (orderRefundPO == null) {
            logger.info("未查询到退款订单，退款订单号:" + orderNumber);
            throw new DataBaseException("未查询到退款订单，退款订单号:" + orderNumber);
        }

        //获取orderFlowPO
        OrderFlowPO orderFlowPO = orderFlowDao.getOrderFlowPOByOrderNumber(orderNumber);
        if (orderFlowPO == null) {
            logger.info("未查到订单号:" + orderNumber + " 对应流水");
            throw new DataBaseException("未查到订单号:" + orderNumber + " 对应流水");
        }

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = orderNumber;
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = MoneyUtils.unitTransfer(String.valueOf(orderFlowPO.getPayCost()));
        //退款的原因说明
        String refund_reason = orderRefundPO.getCause();

        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                + "\"refund_amount\":\"" + refund_amount + "\","
                + "\"refund_reason\":\"" + refund_reason + "\"}");

        return alipayClient.execute(alipayRequest);
    }


    /**
     * 退款查询
     *
     * @param orderId
     * @return
     */
    public AlipayTradeFastpayRefundQueryResponse refundQuery(Integer orderId) throws AlipayApiException {
        AlipayClient alipayClient = AlipayUtils.getAlipayClient();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

        request.setBizContent("{" +
                "\"out_trade_no\":\"1500262302510\"," +
                "\"out_request_no\":\"1500262302510\"" +
                "  }");

        return alipayClient.execute(request);
    }

    /**
     * 订单撤销
     *
     * @return
     * @throws AlipayApiException
     */
    public AlipayTradeCancelResponse cancel(Integer orderId) throws AlipayApiException {
        AlipayClient alipayClient = AlipayUtils.getAlipayClient();
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();

        request.setBizContent("{" +
                "\"out_trade_no\":\"20150320010101001\"," +
                "\"trade_no\":\"2014112611001004680073956707\"" +
                "  }");

        return alipayClient.execute(request);
    }

    /**
     * 订单关闭
     *
     * @param orderId
     * @return
     */
    public AlipayTradeCloseResponse close(Integer orderId) throws AlipayApiException {
        AlipayClient alipayClient = AlipayUtils.getAlipayClient();
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();

        request.setBizContent("{" +
                "\"trade_no\":\"2013112611001004680073956707\"," +
                "\"out_trade_no\":\"HZ0120131127001\"," +
                "\"operator_id\":\"YX01\"" +
                "  }");

        return alipayClient.execute(request);
    }



}
