package com.lifeshs.service1.order.vip.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.order.vip.VipOrderFlowDao;
import com.lifeshs.po.order.VipOrderFlowPO;
import com.lifeshs.service1.order.vip.VipOrderFlowService;
import com.lifeshs.vo.agent.AgetnIncomeVo;

@Service(value = "vipOrderFlowServiceImpl")
public class VipOrderFlowServiceImpl implements VipOrderFlowService {

    @Resource(name = "vipOrderFlowDao")
    private VipOrderFlowDao orderFlowDao;

    @Override
    @Transactional(rollbackFor = OperationException.class)
    public void addOrderFlow(String orderNumber, PayTypeEnum payType, int cost, int payCost, String payAccount,
            String sellerAccount, String tradeNo, Integer businessIncome, AgetnIncomeVo vo) throws OperationException {
        VipOrderFlowPO temp = orderFlowDao.findOrderFlowByOrderNumber(orderNumber);
        if (temp != null) {
            throw new OperationException("请勿重复提交该订单的流水", ErrorCodeEnum.REPEAT);
        }
        
        VipOrderFlowPO orderFlow = new VipOrderFlowPO();
        orderFlow.setOrderNumber(orderNumber);
        orderFlow.setPayType(payType.getValue());
        orderFlow.setCost(cost);
        orderFlow.setPayCost(payCost);
        orderFlow.setPayAccount(payAccount);
        orderFlow.setSellerAccount(sellerAccount);
        orderFlow.setTradeNo(tradeNo);
        orderFlow.setBusinessIncome(businessIncome);
        if(vo!=null){
            orderFlow.setSysIncome(vo.getSysIncome());
            orderFlow.setSysProfitShare(vo.getSysProfitShare());
            orderFlow.setAgentIncome(vo.getAgentIncome());
            orderFlow.setAgentProfitShare(vo.getAgentProfitShare());
            orderFlow.setSalesmanIncome(vo.getSalesmanIncome());
            orderFlow.setSalesmanProfitShare(vo.getSalesmanProfitShare());
            orderFlow.setIntroduceOrgIncome(vo.getIntroduceOrgIncome());
            orderFlow.setIntroduceOrgProfitShare(vo.getIntroduceOrgProfitShare());
            orderFlow.setServiceOrgIncome(vo.getServiceOrgIncome());
            orderFlow.setServiceOrgProfitShare(vo.getServiceOrgProfitShare());
            orderFlow.setOrgUserUserId(vo.getOrgUserUserId());
        }
        
        int result = orderFlowDao.addOrderFlow(orderFlow);
        if (result == 0) {
            throw new OperationException("添加流水失败", ErrorCodeEnum.FAILED);
        }
    }
}
