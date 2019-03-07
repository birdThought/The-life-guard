package com.lifeshs.thirdservice;


import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeCancelResponse;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.lifeshs.common.exception.database.DataBaseException;

import java.io.IOException;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
public interface IAlipayService {
    /**
     * 退款
     *
     * @param orderId
     * @return
     * @throws AlipayApiException
     * @throws IOException
     * @throws DataBaseException
     */
    AlipayTradeRefundResponse refund(Integer orderId) throws AlipayApiException, IOException, DataBaseException;

    /**
     * 退款查询
     *
     * @param orderId
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeFastpayRefundQueryResponse refundQuery(Integer orderId) throws AlipayApiException;

    /**
     * 交易取消
     *
     * @param orderId
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeCancelResponse cancel(Integer orderId) throws AlipayApiException;

    /**
     * 交易关闭
     *
     * @param orderId
     * @return
     * @throws AlipayApiException
     */
    AlipayTradeCloseResponse close(Integer orderId) throws AlipayApiException;
}
