package com.lifeshs.service1.order;


import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.CustomOrderPo;
import com.lifeshs.pojo.member.address.AddressDTO;

/**
 * Created by Administrator on 2017/12/8.
 */
public interface CustomOrderService {

    /**
     *  医师创建订单
     * @param paysCode
     * @param userCode
     * @param productName
     * @param productSpec
     * @param produstPrice
     */
    CustomOrderPo addPrivateOrder(String paysCode, String userCode, String productName, String productSpec, String produstPrice) throws OperationException;

    /**
     *  根据用户id 获取地址
     * @param userId
     * @param addressId
     */
    CustomOrderPo confirmPrivateOrder(Integer userId, Integer addressId,String orderNumber) throws OperationException;


    /**
     *  完成订单
     * @param orderNumber  订单编号
     */
    String finishOrder(String orderNumber) throws OperationException;

    /**
     * 根据订单号查询
     * @param orderNumber
     * @return
     */
    CustomOrderPo getOrderDetails(String orderNumber);

    /**
     *   完成回调
     * @param orderNumber 订单好
     * @param tradeNumber  交易号
     * @param payerAccount  买家
     * @param sellerAccount 卖家
     * @param payMoney  支付金额
     * @param alipay       支付类型
     * @param deviceType  支付设备类型
     * @throws OperationException
     */
    void finishOrderPrivate(String orderNumber, String tradeNumber, String payerAccount, String sellerAccount,
                            Double payMoney, PayTypeEnum alipay, String deviceType) throws OperationException;

    /**
     *  查询地址
     * @param userId
     * @param addressId
     * @return
     */
    AddressDTO findByAdddress(Integer userId,Integer addressId);
}
