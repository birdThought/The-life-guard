package com.lifeshs.thirdservice;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.entity.weChat.CloseOrderInfo;
import com.lifeshs.entity.weChat.RefundInfo;
import com.lifeshs.entity.weChat.UnifiedOrderInfo;

/**
 * 微信接口
 * 
 * @author liaoguo
 * @version 1.0
 * @DateTime 2018年4月20日 上午10:49:14
 */
public interface IWeChatService {

    /**
     * 获取预支付订单
     */
    public JSONObject unifiedOrder(UnifiedOrderInfo info);

    /**
     * 退款
     */
    public JSONObject refund(RefundInfo info);

    /**
     * 关闭订单
     */
    public JSONObject closeorder(CloseOrderInfo info);

    /**
     * 获取预支付订单
     */
    public JSONObject unifiedOrderNew(String body, String outTradeNo, String totalFee, String tradeType,
            String spbillCreateIp, String attach);
}
