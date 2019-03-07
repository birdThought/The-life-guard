package com.lifeshs.service1.order.vip;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.order.PayTypeEnum;
import com.lifeshs.common.constants.common.order.VipOrderTypeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.order.VipUserOrderPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.order.vip.BusinessOrderBillVO;
import com.lifeshs.vo.order.vip.VipUserOrderVO;

/**
 *  vip会员订单service
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月18日 下午2:26:49
 */
public interface VipUserOrderService {

    /**
     *  添加订单
     *  <p>如果填写了businessCardId会被当作激活码订单处理
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午2:01:54
     *
     *  @param userId 用户id
     *  @param vipComboId vip套餐id
     *  @param orderStatus 订单状态
     *  @param subject 订单描述内容
     *  @param businessCardId 激活码id
     *  @param orderType 订单类型
     *  @param businessIncome 渠道商收入分成（分）
     *  @param businessId 渠道商id
     *  
     *  @return 订单id
     *  @throws OperationException
     */
    VipUserOrderPO addOrder(int userId, int vipComboId, OrderStatus orderStatus, String subject,
            Integer businessCardId, VipOrderTypeEnum orderType, Integer businessIncome,
            Integer businessId) throws OperationException;
    
    /**
     *  完成订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午2:03:28
     *
     *  @param orderNumber 订单编号
     *  @param tradeNumber 支付端（支付宝/微信）交易号
     *  @param payerAccount 买家账号
     *  @param sellerAccount 卖家账号
     *  @param totalCount 总支付金额
     *  @param payType 支付类型
     *  @param deviceType 支付设备类型
     *  
     *  @exception OperationException
     */
    void finishOrder(String orderNumber, String tradeNumber, String payerAccount, String sellerAccount, double totalCount,
            PayTypeEnum payType, String deviceType) throws OperationException;
    
    /**
     *  获取支付宝订单支付信息
     *  @author yuhang.weng 
     *  @DateTime 2017年10月19日 下午2:12:44
     *
     *  @param orderId
     *  @return
     *  @throws OperationException
     */
    String getAliPaySign(int orderId) throws OperationException;
    
    /**
     *  获取微信订单支付信息
     *  @author liaoguo 
     *  @DateTime 2018年04月25日 下午15:08:36
     *
     *  @param orderId
     *  @param spbill_create_ip 地址
     *  @param orderType 订单类型
     *  @return
     *  @throws OperationException
     */
    Map<String, Object> getWeChatPaySign(int orderId,String spbill_create_ip,int orderType) throws OperationException;
    
    

    /**
     * 渠道商获取订单列表
     * @param businessId
     * @param startDate
     * @param endDate
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Paging<VipUserOrderVO> listOrder (int businessId, String startDate, String endDate, int pageIndex, int pageSize);

    /**
     * 获取渠道商总账单
     * @param businessId
     * @param startDate
     * @param endDate
     * @return
     */
    BusinessOrderBillVO getBusinessOrderBill (int businessId, String startDate, String endDate);
    
    /**
     * 过期套餐
     * @param endTime
     * @param startTime
     * @return
     * @author liu
     * @时间 2018年12月27日 上午11:23:07
     * @remark
     */
    List<Map<String, Object>> findVipOrderOutOfEndDateList(Date endTime, Date startTime);
    
    int finishOrder(Integer id);
}
