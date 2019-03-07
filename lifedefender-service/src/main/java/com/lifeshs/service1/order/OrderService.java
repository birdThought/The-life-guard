package com.lifeshs.service1.order;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alipay.api.AlipayApiException;
import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.ProjectType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.exception.database.DataBaseException;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.OrderStoreSmsPO;
import com.lifeshs.po.order.OrderWithVipPO;
import com.lifeshs.pojo.order.PaymentOrderDTO;
import com.lifeshs.pojo.order.v2.OrderCountDTO;
import com.lifeshs.pojo.order.v2.OrderDTO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.RefundOrderVO;
import com.lifeshs.vo.StatisticsVO;
import com.lifeshs.vo.order.OrderAgentAmountVO;
import com.lifeshs.vo.order.customer.OrderCountVO;

public interface OrderService {

    /**
     *  获取咨询服务的订单信息</p>
     *  ps:同一个用户与服务师之间只存在一条有效的订单</p>
     *  @author yuhang.weng 
     *	@DateTime 2017年6月19日 上午10:23:11
     *
     *  @param userId
     *  @param serveUserId
     *  @return
     */
    OrderDTO getConsultOrder(int userId, int serveUserId);
    
    /**
     *  获取课堂服务的订单信息
     *  @author yuhang.weng 
     *  @DateTime 2017年8月22日 上午9:12:07
     *
     *  @param userId
     *  @param lessonId 课堂id
     *  @return
     */
    OrderPO getLessonOrder(int userId, int lessonId);
    
    /**
     *  添加服务订单
     *  @author yuhang.weng 
     *	@DateTime 2017年7月15日 上午11:07:06
     *
     *  @param userId 会员id
     *  @param projectType 服务类型code(必填)
     *  @param id consultId/lessonId/comboId(必填)
     *  @param serveUserId 线下服务所选服务师id(注意与线上咨询的服务师需要区分)(线下服务)
     *  @param number 数量(线下服务)
     *  @param addressId 用户地址id(线下服务)
     *  @param chargeMode 付费类型
     *  @return
     *  @throws OperationException
     */
    OrderDTO addServeOrder(int userId, ProjectType projectType, int id, Integer serveUserId, Integer number, Integer addressId, int chargeMode)
            throws OperationException;
    
    /**
     *  获取支付宝订单签名
     *  @author yuhang.weng 
     *  @DateTime 2017年8月7日 下午1:54:53
     *
     *  @param order 订单编号
     *  @param notifyUrl 通知url
     *  @param extraData 额外携带参数（会在支付成功后原数据返回）
     *  @return
     *  @throws OperationException
     */
    String getOrderAlipaySignInfo(OrderPO order, String notifyUrl, Map<String, String> extraData) throws OperationException;
    
    /**
     * 
     *  获取支付宝订单签名(可扩展)
     *  @author liaoguo
     *  @DateTime 2018年6月19日 下午5:30:12
     *
     *  @param order
     *  @param notifyUrl
     *  @param extraData
     *  @return
     *  @throws OperationException
     */
    String getOrderAlipaySignInfoNew(PaymentOrderDTO order,String notifyUrl, Map<String, String> extraData) throws OperationException;
    
    /**
     *  获取订单
     *  @author yuhang.weng 
     *	@DateTime 2017年6月28日 上午11:14:43
     *
     *  @param userId
     *  @param projectType
     *  @param easyStatus 1_全部,2_待付款,3_待使用,4_待评价,5_退款
     *  @param curPage
     *  @param pageSize
     *  @return
     *  @throws OperationException 操作异常
     */
    Paging<OrderDTO> listOrder(int userId, ProjectType projectType, int easyStatus, int curPage, int pageSize) throws OperationException;
    
    /**
     *  统计订单
     *  @author yuhang.weng
     *	@DateTime 2017年7月13日 上午11:49:31
     *
     *  @param userId
     *  @return
     */
    OrderCountDTO getOrderCount(int userId);

    /**
     * 查询订单详细信息
     * @param orderId
     * @return
     */
    OrderPO getOrderDetail(int orderId);

    /**
     * 得到退款订单详细信息
     * @param orderId
     * @return
     */
    RefundOrderVO getRefundOrderDetail(int orderId);

    /**
     * 得到退款订单详细信息
     * @param orderNumber
     * @return
     */
    RefundOrderVO getRefundOrderDetail(String orderNumber);

    /**
     * 完成订单（状态置为已完成，记录完成时间）
     * @param orderId
     * @return
     */
    boolean completeOrder(int orderId);

    /**
     * 判断订单是否属于该用户
     * @param orderId
     * @param userId
     * @return
     */
    boolean isOrderBelongToUser(int orderId, int userId);

    /**
     * @Description: 根据订单号获取订单信息
     * @Author: wenxian.cai
     * @Date: 2017/7/12 16:32
     */
    OrderPO getOrderByOrderNumber(String orderNumber);

    /**
     * 将订单状态设置为 退款中_7
     *
     * @param orderId
     */
    Map<String, Object> updateRefundOrder(int orderId, String refundCause, int userId) throws DataBaseException;

    /**获取退款订单消息*/
    Paging<RefundOrderVO> findRefundOrderList(Integer orgId, Integer pageIndex, Integer pageSize);

    /**
     * 退款通过审核
     * @param orderNumber
     * @param auditorId
     */
    void refundAuditedOrder(String orderNumber, Integer auditorId);

    /**
     * 退款中
     * @param orderNumber
     */
    void refundingOrder(String orderNumber);

    /**
     * 退款完成
     * @param orderNumber
     */
    void refundCompleteOrder(String orderNumber);

    /**
     * 退款失败
     * @param orderNumber
     */
    void refundFailOrder(String orderNumber);
    /**
     * 获取退款详情
     *
     * @param orderId
     * @return
     * @throws DataBaseException
     */
    Map<String, Object> getRefundDetail(int orderId) throws DataBaseException;

    /**
     * 确认退款订单
     *
     * @param orderId
     * @param auditorId
     * @throws AlipayApiException
     * @throws IOException
     * @throws DataBaseException
     */
    void confirmRefundOrder(int orderId, int auditorId) throws AlipayApiException, IOException, DataBaseException;

    /**
     * 删除订单
     * @param orderId
     */
    void deleteOrder(int orderId) throws DataBaseException;
    
    /**
     * 支付回调完成订单
     * @param outNo 订单号
     * @param tradeNo 支付宝交易号
     * @param payerAccount 买家账号
     * @param sellerAccount 卖家账号
     * @param totalCount 支付总金额
     * @param payType 支付方式
     * @param deviceType web，app
     * @param couponsId 电子券id
     * @exception OperationException
     * @return
     */
    void finishOrder(String outNo, String tradeNo, String payerAccount, String sellerAccount, double totalCount,
            int payType, String deviceType, Integer couponsId) throws OperationException;

    /**
     * 机构短信充值订单支付回调完成订单
     * @param outNo 订单号
     * @param tradeNo 支付宝交易号
     * @param payerAccount 买家账号
     * @param sellerAccount 卖家账号
     * @param totalCount 支付总金额
     * @param payType 支付方式
     * @param deviceType web，app
     * @param extraData 额外的数据
     * @return
     */
    boolean finishOrderStoreSms(String outNo, String tradeNo, String payerAccount, String sellerAccount,
            double totalCount, int payType, String deviceType, String extraData);
    /**
     *  服务注解
     *  @author yuhang.weng 
     *	@DateTime 2017年7月19日 上午11:49:28
     *
     *  @param userId
     *  @param prejectType
     *  @param pageIndex
     *  @param pageSize
     *  @return
     */
    List<OrderPO> getOrderPOByCodeAndUserId(int userId, ProjectType prejectType, int pageIndex, int pageSize);
    
    /**
     *  获取订单列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月19日 下午5:55:04
     *
     *  @param projectCode
     *  @return
     */
    List<OrderPO> listOrderByProjectCode(String projectCode);
    
    /**
     *  获取已经过期的咨询订单
     *  @author yuhang.weng 
     *	@DateTime 2017年7月26日 下午2:29:29
     *
     *  @param 
     *  @return
     */
    List<OrderPO> listConsultOrderOutOfEndDate(Date endTime, Date startTime);


    /**
     * 获取订单统计报表数据
     * @param projectCode
     * @param diseasesId
     * @param gender
     * @param startAge
     * @param endAge
     * @param orgId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Paging<StatisticsVO> findStatistics(String projectCode, Integer diseasesId, Integer gender,String startAge,
                                        String endAge, Integer orgId, Integer pageIndex, Integer pageSize);

    /**
     * 获取订单统计详细报表数据
     * @param projectCode
     * @param diseasesId
     * @param gender
     * @param startAge
     * @param endAge
     * @param orgId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Paging<StatisticsVO> findStatisticsDetails(String projectCode, Integer diseasesId, Integer gender,String startAge,
                                        String endAge, String mobile, Integer orgId, Integer pageIndex, Integer pageSize);

    /**
     * 添加机构短信充值订单
     * @param orderStoreSmsPO
     */
    void createOrderStoreSms(OrderStoreSmsPO orderStoreSmsPO) throws OperationException;

    /**
     * 获取机构短信订单状态
     * @param orderNumber
     * @return
     */
    OrderStatus getOrderStoreSmsStatus(String orderNumber) throws OperationException;

    /**
     * 获取状态为未完成的订单数量
     *
     * @param status 订单扎un柜台
     * @param id 机构用户ID
     * @return
     */
    Integer getOrderCountByIdAndStatus(Integer id, Integer status);

    /**
     * 更新用户备注（基于订单表）
     * @param orderId
     * @param userId
     * @param userRemark
     */
    void updateUserRemark(int orderId, int userId, String userRemark) throws OperationException;

    /**
     * 更新用户病种（基于订单表）
     * @param orderId
     * @param userId
     * @param userDiseasesId
     * @param userDiseasesName
     * @throws OperationException
     */
    void updateUserDiseases(int orderId, int userId, Integer userDiseasesId, String userDiseasesName) throws OperationException;

    /**
     *  添加续费订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月31日 下午4:05:46
     *
     *  @param userId 用户id
     *  @param projectType 服务类型code
     *  @param id 服务id
     *  @param number 购买数量
     *  @return
     *  @throws OperationException
     */
    OrderDTO addRenewalOrder(int userId, ProjectType projectType, int id, int number) throws OperationException;
    
    /**
     *  使用电子券完成订单支付
     *  @author yuhang.weng 
     *  @DateTime 2017年12月28日 下午6:57:33
     *
     *  @param orderId 订单id
     *  @param couponsId 电子券id
     *  @throws OperationException
     */
    void finishOrderWithCoupon(int orderId, int couponsId) throws OperationException;
    
    /**
     * 
     *  服务注解
     *  @author NaN
     *  @DateTime 2018年12月6日 上午9:40:20
     *
     *  @param userNo 上级编号
     *  @param userName 用户名
     *  @param realName 姓名
     *  @param orgName 机构名
     *  @param projectType 服务类型
     *  @param status  订单状态
     *  @return
     */
    OrderAgentAmountVO findTotalMoney(String userNo,String userName, String realName, String orgName, 
            String projectType, OrderStatus status, String orderType);
    
    /**
     *  获取客服系统订单列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月30日 下午2:01:23
     *
     *  @param userNo 上级编号
     *  @param curPage 当前页码
     *  @param pageSize 页面大小
     *  @param userName 用户名
     *  @param realName 姓名
     *  @param orgName 机构名
     *  @param serveCode 服务code
     *  @param status 订单状态
     *  @return
     */
    Paging<OrderWithVipPO> listOrder(String userNo, int curPage, int pageSize, String userName,
            String realName, String orgName, String serveCode, OrderStatus status, String orderType);
    
    /**
     *  服务注解
     *  @author yuhang.weng 
     *  @DateTime 2018年1月31日 下午3:38:38
     *
     *  @param curPage 当前页码
     *  @param pageSize 页面大小
     *  @param orgName 机构名
     *  @param serveCode 服务code
     *  @param start 开始时间
     *  @param end 结束时间
     *  @return
     */
    Paging<OrderCountVO> listOrderCount(String userNo, int curPage, int pageSize, String orgName, String serveCode,
            Date start, Date end);
    
    
}
