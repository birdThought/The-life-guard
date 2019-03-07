package com.lifeshs.service1.order.vip;

import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.vo.agent.AgetnIncomeVo;

/**
 *  会员vip服务订单相关流水service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月17日 下午2:22:53
 */
public interface VipOrderFlowService {

    // ✿✪‿✪。
    
    /**
     *  添加流水
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午3:12:40
     *
     *  @param orderNumber 订单编号
     *  @param payType 支付类型
     *  @param cost 总金额(单位：分)
     *  @param payCost 支付金额(单位：分)
     *  @param payAccount 买家账号
     *  @param sellerAccount 卖家账号
     *  @param tradeNo 支付平台流水编号
     *  @param businessIncome 渠道商收入分成（单位：分）
     *  @throws OperationException
     */
    void addOrderFlow(String orderNumber, PayTypeEnum payType, int cost, int payCost, String payAccount, String sellerAccount, String tradeNo, Integer businessIncome, AgetnIncomeVo vo) throws OperationException;
}
