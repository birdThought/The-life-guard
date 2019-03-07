package com.lifeshs.service1.order.famousDoctor;

import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.order.FamousDoctorOrderPO;
import com.lifeshs.service1.page.Paging;

/**
 *  名医预约订单service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月19日 下午2:18:43
 */
public interface FamousDoctorOrderService {

    /**
     *  添加订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午2:19:18
     *
     *  @param userId 用户id
     *  @param famousDoctorId 名医id
     *  @param orderStatus 订单状态
     *  @return 订单id
     *  @throws OperationException
     */
    FamousDoctorOrderPO addOrder(int userId, int famousDoctorId, OrderStatus orderStatus) throws OperationException;
    
    /**
     *  修改订单状态为有效的
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午2:18:10
     *
     *  @param orderNumber 订单编号
     *  @param tradeNumber 支付端（支付宝/微信）交易号
     *  @param payerAccount 买家账号
     *  @param sellerAccount 卖家账号
     *  @param totalCount 总支付金额
     *  @param payType 支付类型
     *  @param deviceType 支付设备类型
     *  @throws OperationException
     */
    void validOrder(String orderNumber, String tradeNo, String payAccount, String sellerAccount,
            double totalCount, PayTypeEnum payType, String deviceType) throws OperationException;
    
    /**
     *  完成订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午2:51:23
     *
     *  @param orderId
     *  @throws OperationException
     */
    void finishOrder(int orderId) throws OperationException;
    
    /**
     *  获取订单的支付签名
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午2:17:59
     *
     *  @param orderId 订单id
     *  @return
     *  @throws OperationException
     */
    String getAliPaySign(int orderId) throws OperationException;
    
    /**
     *  获取订单列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午1:59:07
     *
     *  @param curPage 当前页码
     *  @param pageSize 页面大小
     *  @param userId 用户id
     *  @param famousDoctorId 名医id
     *  @param status 订单状态
     *  @return
     */
    Paging<FamousDoctorOrderPO> listOrder(int curPage, int pageSize, Integer userId, Integer famousDoctorId, OrderStatus status);
}
