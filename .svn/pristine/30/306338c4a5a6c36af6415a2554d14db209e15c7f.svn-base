package com.lifeshs.service1.order.famousDoctor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.order.PayReturnOrderTypeEnum;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.order.famousDoctor.FamousDoctorOrderDao;
import com.lifeshs.po.famousDoctor.FamousDoctorPO;
import com.lifeshs.po.order.FamousDoctorOrderPO;
import com.lifeshs.service.alipay.AlipayService;
import com.lifeshs.service1.famousDoctor.FamousDoctorService;
import com.lifeshs.service1.order.famousDoctor.FamousDoctorOrderService;
import com.lifeshs.service1.order.vip.VipOrderFlowService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.utils.NumberUtils;

@Service(value = "famousDoctorOrderServiceImpl")
public class FamousDoctorOrderServiceImpl implements FamousDoctorOrderService {

    @Resource(name = "famousDoctorOrderDao")
    private FamousDoctorOrderDao orderDao;
    
    @Resource(name = "famousDoctorServiceImpl")
    private FamousDoctorService doctorService;

    @Resource(name = "vipOrderFlowServiceImpl")
    private VipOrderFlowService orderFlowService;
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public FamousDoctorOrderPO addOrder(int userId, int famousDoctorId, OrderStatus orderStatus) throws OperationException {
        FamousDoctorPO doctor = doctorService.getFamousDoctor(famousDoctorId);
        if (doctor == null) {
            throw new OperationException("该名医不存在", ErrorCodeEnum.NOT_FOUND);
        }
        int price = doctor.getPrice();
        
        // 获取订单编号
        String orderNumber = AlipayService.createOrderNumber();
        
        FamousDoctorOrderPO order = new FamousDoctorOrderPO();
        order.setOrderNumber(orderNumber);
        order.setSubject("名医预约订单");
        order.setBody("预约医生" + doctor.getName());
        order.setStatus(orderStatus.getStatus()); // 3_已完成
        order.setUserId(userId);
        order.setFDoctorId(famousDoctorId);
        order.setPrice(price);
        
        // 如果订单编号重复，最多重新生成5次订单编号
        int result = 0;
        for (int i = 0; i < 5; i++) {
            try{
                result = orderDao.addOrder(order);
                break;
            } catch (DuplicateKeyException e) {
                // 重新生成订单编号
                orderNumber = AlipayService.createOrderNumber();
                order.setOrderNumber(orderNumber);
            }
        }
        
        if (result == 0) {
            throw new OperationException("添加订单失败", ErrorCodeEnum.FAILED);
        }
        
        return order;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void validOrder(String orderNumber, String tradeNo, String payAccount, String sellerAccount,
            double totalCount, PayTypeEnum payType, String deviceType) throws OperationException {
        FamousDoctorOrderPO order = orderDao.findOrderByOrderNumber(orderNumber);
        if (order == null) {
            throw new OperationException("该订单不存在", ErrorCodeEnum.NOT_FOUND);
        }
        if (!OrderStatus.PAYABLE.getStatus().equals(order.getStatus())) {
            throw new OperationException("该订单状态为不可支付", ErrorCodeEnum.FAILED);
        }
        
        FamousDoctorOrderPO orderPO = new FamousDoctorOrderPO();
        orderPO.setId(order.getId());
        orderPO.setStatus(OrderStatus.VALID.getStatus());   // 有效的
        int result = orderDao.updateOrder(orderPO);
        if (result == 0) {
            throw new OperationException("修改订单状态失败", ErrorCodeEnum.FAILED);
        }
        
        // 添加流水
        int totalC = NumberUtils.changeY2F(String.valueOf(totalCount));
        orderFlowService.addOrderFlow(orderNumber, payType, totalC, totalC, payAccount, sellerAccount, tradeNo, null,null);
        
        // 添加预约记录
        // 订单状态为有效的（status = Valid）作为判断，查询预约记录
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = OperationException.class)
    public void finishOrder(int orderId) throws OperationException {
        FamousDoctorOrderPO order = orderDao.getOrder(orderId);
        if (order == null) {
            throw new OperationException("该订单不存在", ErrorCodeEnum.NOT_FOUND);
        }
        if (!OrderStatus.VALID.getStatus().equals(order.getStatus())) {
            throw new OperationException("该订单状态不是有效的", ErrorCodeEnum.FAILED);
        }
        FamousDoctorOrderPO orderPO = new FamousDoctorOrderPO();
        orderPO.setId(order.getId());
        orderPO.setStatus(OrderStatus.COMPLETED.getStatus());   // 完成的
        int result = orderDao.updateOrder(orderPO);
        if (result == 0) {
            throw new OperationException("修改订单状态失败", ErrorCodeEnum.FAILED);
        }
    }

    @Override
    public String getAliPaySign(int orderId) throws OperationException {
        FamousDoctorOrderPO order = orderDao.getOrder(orderId);
        if (order == null) {
            throw new OperationException("订单不存在", ErrorCodeEnum.NOT_FOUND);
        }
        if (!OrderStatus.PAYABLE.getStatus().equals(order.getStatus())) {
            throw new OperationException("订单状态不可支付", ErrorCodeEnum.FAILED);
        }
        
        int price = order.getPrice();
        double total = NumberUtils.changeF2Y(String.valueOf(price));
        String subject = order.getSubject();
        String body = order.getBody();
        String orderNumber = order.getOrderNumber();
        String notifyUrl = Normal.APP_RECHARGE_NOTIFY_URL;
        Map<String, String> extraData = new HashMap<>();
        extraData.put("orderType", String.valueOf(PayReturnOrderTypeEnum.FAMOUS_DOCTOR.getValue()));
        
        String sign = AlipayService.getOrderInfo(total, subject, body, orderNumber, extraData, notifyUrl);
        return sign;
    }

    @Override
    public Paging<FamousDoctorOrderPO> listOrder(int curPage, int pageSize, Integer userId, Integer famousDoctorId,
            OrderStatus status) {
        Paging<FamousDoctorOrderPO> p = new Paging<>(curPage, pageSize);
        p.setQueryProc(new IPagingQueryProc<FamousDoctorOrderPO>() {

            @Override
            public int queryTotal() {
                Integer statusValue = null;
                if (status != null) {
                    statusValue = status.getStatus();
                }
                return orderDao.countOrderWithCondition(userId, famousDoctorId, statusValue);
            }

            @Override
            public List<FamousDoctorOrderPO> queryData(int startRow, int pageSize) {
                Integer statusValue = null;
                if (status != null) {
                    statusValue = status.getStatus();
                }
                return orderDao.findOrderListWithCondition(startRow, pageSize, userId, famousDoctorId, statusValue);
            }
        });
        return p;
    }

}
