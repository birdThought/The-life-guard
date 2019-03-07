package com.lifeshs.dao1.order;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.dto.manager.order.GetOrderListData;
import com.lifeshs.po.OrderPO;
import com.lifeshs.po.OrderStoreSmsPO;
import com.lifeshs.po.order.OrderWithVipPO;
import com.lifeshs.vo.OrderCountAndChargeVO;
import com.lifeshs.vo.OrderStatisticsByDayVO;
import com.lifeshs.vo.RefundOrderVO;
import com.lifeshs.vo.StatisticsVO;
import com.lifeshs.vo.WarningUserVO;
import com.lifeshs.vo.order.OrderAgentAmountVO;
import com.lifeshs.vo.order.customer.OrderCountVO;

/**
 * 功能描述
 * Created by dengfeng on 2017/6/27 0027.
 */
@Repository("orderDao1")
public interface IOrderDao {

    /**
     * 查询指定异常会员
     *  如果存在如下场景: 用户购买了两个订单,都是有效的,且都存在异常,那么查询结果显然不止一个.
     *  // todo
     *
     * @param employeeIds
     * @param memberId
     * @return
     */
    int checkWarningMsgIsRead(List<Integer> employeeIds,Integer memberId);


    /**
     * 分页查询门店/服务师所属的有异常会员
     * @param orgId
     * @param startRow
     * @param lenght
     * @return
     */
    List<WarningUserVO> findWainingMemberList(@Param("orgId") Integer orgId, @Param("orgUserId") Integer orgUserId,
                                              @Param("startRow") int startRow, @Param("lenght") int lenght);

    /**
     * 分页查询门店/服务师所属的订单
     * @param orgId 门店ID
     * @param orgUserId 服务师ID，与orgId二选一
     * @param projectType 1：咨询；2：线下；3：上门；4：课堂，0全部
     * @param status 订单状态，状态：待付款_1, 付款失败_2，有效_3，已完成_4，已退款_5，已取消_6
     * @param startRow
     * @param lenght
     * @param memberId 会员ID
     * @return
     */
    List<OrderPO> findOrderList(@Param("orgId") Integer orgId,
                                @Param("orgUserId") Integer orgUserId,
                                @Param("projectType") Integer projectType,
                                @Param("status") Integer status,
                                @Param("search") String search,
                                @Param("startRow") int startRow,
                                @Param("lenght") int lenght,
                                @Param("memberId") Integer memberId);

    /**
     * 分页查询门店/服务师所属的订单(只查已有评论的订单)
     * @param orgId 门店ID
     * @param orgUserId 服务师ID，与orgId二选一
     * @param commentStatus 评论状态（0：未回复；1：已回复）
     * @param startRow
     * @param lenght
     * @return
     */
    List<OrderPO> findOrderListWithComments(@Param("orgId") Integer orgId,
                                            @Param("orgUserId") Integer orgUserId,
                                            @Param("commentStatus") Integer commentStatus,
                                            @Param("startRow") int startRow,
                                            @Param("lenght") int lenght,
                                            @Param("memberId") Integer memberId);

    /**
     * 分页查询门店/服务师所属的订单(只关联用户)
     * @param orgId 门店ID
     * @param orgUserId 服务师ID，与orgId二选一
     * @param startDate 开始日期
     * @param startRow
     * @param lenght
     * @return
     */
    List<GetOrderListData> findOrderListWithUser(@Param("orgId") Integer orgId, @Param("orgUserId") Integer orgUserId, @Param("startDate") String startDate, @Param("endDate") String endDate, @Param("startRow") int startRow, @Param("lenght") int lenght);

    /**
     * 查询门店/服务师所属的订单总数(只关联用户)
     * @param orgId 门店ID
     * @param orgUserId 服务师ID，与orgId二选一
     * @param startDate 开始日期
     * @return
     */
    int findOrderCountWithUser(@Param("orgId") Integer orgId, @Param("orgUserId") Integer orgUserId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询门店/服务师所属的订单总数和合计金额(按日期段)
     * @param orgId 门店ID
     * @param orgUserId 服务师ID，与orgId二选一
     * @param startDate 开始日期
     * @return
     */
    OrderCountAndChargeVO getCountAndChargeByDateDiff(@Param("orgId") Integer orgId, @Param("orgUserId") Integer orgUserId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 查询订单详细信息
     * @param orderId 订单ID
     * @return
     */
    OrderPO getOrderDetail(@Param("orderId") Integer orderId);
    
    /**
     *  查询订单详细信息
     *  @author yuhang.weng 
     *	@DateTime 2017年7月18日 下午5:16:54
     *
     *  @param orderNumber
     *  @return
     */
    OrderPO getOrderDetailByOrderNumber(@Param("orderNumber") String orderNumber);

    /**
     * 查询机构短信充值订单详细信息
     * author: wenxian.cai
     * date: 2017/8/30 10:47
     */
    OrderPO getOrderStoreSmsDetailByOrderNumber(@Param("orderNumber") String orderNumber);

    /**
     * 得到退款订单详细信息
     * @param orderId
     * @param orderNumber
     * @return
     */
    RefundOrderVO getRefundOrderDetail(@Param("orderId") Integer orderId, @Param("orderNumber") String orderNumber);

    /**
     * 完成订单（状态置为已完成，记录完成时间）
     * @param orderId
     * @return
     */
    int completeOrder(@Param("orderId") int orderId);

    /**
     * 查询门店/服务师的指定月份的订单数和订单合计金额
     * @param month 月份："yyyy-MM"
     * @return
     */
    OrderCountAndChargeVO getCountAndChargeByMonth(@Param("orgId") Integer orgId, @Param("employeeId") Integer employeeId, @Param("month") String month);

    /**
     * 查询门店/服务师的指定某天的订单数和订单合计金额
     * @param orgId
     * @param day 指定日期："yyyy-MM-dd"
     * @return
     */
    OrderCountAndChargeVO getCountAndChargeByDay(@Param("orgId") Integer orgId, @Param("employeeId") Integer employeeId, @Param("day") String day);


    /**
     * 根据用户与订单id获取订单数量（用于判断订单会否属于该用户）
     * @param orderId
     * @param userId
     * @return
     */
    int countOrderByUser(@Param("orderId") int orderId, @Param("userId") int userId);

    /**
     * 根据门店与订单id获取订单数量（用于判断该订单是否属于该服务师/门店）
     * 门店id与服务师id只选其一
     * @param orderId 订单id
     * @param orgId 门店id
     * @param orgUserId 服务师id
     * @return
     */
    int countOrderByStore(@Param("orderId") int orderId, @Param("orgId") int orgId,
                          @Param("orgUserId") int orgUserId);

    /**
     * 按日统计门店的指定月份的订单数量及金额合计
     * @param statementId 结算记录ID
     * @return
     */
    List<OrderStatisticsByDayVO> getMonthOrderStatistics(@Param("statementId") int statementId);

    /**
     * 根据订单号获取订单信息
     * @param orderNumber
     * @return
     */
    OrderPO findOrderByOrderNumber(@Param("orderNumber") String orderNumber);

    /**
     * 获取退款订单信息列表
     * @param orgId 门店id
     * @param status 退款状态： 0已完成,1待退款,2已审核,3退款失败
     * @return
     */
    List<RefundOrderVO> findRefundOrderList(@Param("orgId") Integer orgId, @Param("status") Integer status,
                                      @Param("startRow") Integer startRow, @Param("length") Integer length);

    /**
     * 获取退款订单信息总数
     * @param orgId 门店id
     * @param status 退款状态： 0已完成,1待退款,2已审核,3退款失败
     * @return
     */
    int countRefundOrder(@Param("orgId") Integer orgId, @Param("status") Integer status);

    /**
     *  统计门店订单数量
     *  @author yuhang.weng
     *  @DateTime 2017年7月10日 下午3:03:35
     *
     *  @param orgId
     *  @param statusList
     *  @param distinctUser 是否限定一个用户只有一条记录
     *  @return
     */
    int countStoreOrder(@Param("orgId") int orgId, @Param("statusList") List<Integer> statusList, @Param("distinctUser") boolean distinctUser);

    /**
     * 修改退款装态
     * @param orderNumber
     * @param status
     * @param auditorId
     * @return
     */
    int updateRefundOrderStatus(@Param("orderNumber") String orderNumber, @Param("status") Integer status,
                                @Param("auditorId") Integer auditorId);

    /**
     * 修改订单装态
     * @param orderNumber
     * @param status
     * @return
     */
    int updateOrderStatusByNumber(@Param("orderNumber") String orderNumber, @Param("status") Integer status);

    /**
     * 设置订单的状态
     *
     * @param orderId
     * @return
     */
    int updateOrderStatus(@Param("orderId") int orderId,@Param("status") int status);

    /**
     * 删除订单
     * @param orderId
     */
    int deleteOrder(@Param("orderId") int orderId);

    List<OrderPO> getOrderPOByCodeAndUserId(@Param("userId")int userId, @Param("projectType")int projectType,@Param("startRow") int startRow,@Param("length") int length);
    
    /**
     *  获取用户有效的课堂订单
     *  @author yuhang.weng 
     *  @DateTime 2017年7月17日 下午1:59:19
     *
     *  @param userId
     *  @param lessonId
     *  @return
     */
    OrderPO findUserValidLessonOrder(@Param("userId") int userId, @Param("lessonId") int lessonId);
    
    /**
     *  查找projectCode的订单
     *  @author yuhang.weng 
     *	@DateTime 2017年7月19日 下午5:59:01
     *
     *  @param projectCode
     *  @return
     */
    List<OrderPO> findOrderByProjectCodeList(@Param("projectCode") String projectCode);
    
    /**
     *  获取已过期的咨询服务订单列表
     *  @author yuhang.weng 
     *	@DateTime 2017年7月26日 下午2:31:47
     *
     *  @param
     *  @return
     */
    List<OrderPO> findConsultOrderOutOfEndDateList(@Param("endTime") Date endTime, @Param("startTime") Date startTime);

    /**
     * 获取门店订单统计报表
     * @param projectCode
     * @param diseasesId
     * @param gender 0/1
     * @param startAge '2017-01-01'
     * @param endAge '2017-01-01'
     * @param orgId
     * @return
     */
    List<StatisticsVO> findStatisticsList(@Param("projectCode") String projectCode,
                                          @Param("diseasesId") Integer diseasesId,
                                          @Param("gender") Integer gender,
                                          @Param("startAge") String startAge,
                                          @Param("endAge") String endAge,
                                          @Param("orgId") Integer orgId,
                                          @Param("startIndex") Integer startIndex,
                                          @Param("pageSize") Integer pageSize);

    /**
     * 获取门店订单统计报表总数
     * @param projectCode
     * @param diseasesId
     * @param gender
     * @param startAge
     * @param endAge
     * @param orgId
     * @return
     */
    int countStatistics(@Param("projectCode") String projectCode,
                          @Param("diseasesId") Integer diseasesId,
                          @Param("gender") Integer gender,
                          @Param("startAge") String startAge,
                          @Param("endAge") String endAge,
                          @Param("orgId") Integer orgId);

    /**
     * 获取门店订单统计报表
     * @param projectCode
     * @param diseasesId
     * @param gender 0/1
     * @param startAge '2017-01-01'
     * @param endAge '2017-01-01'
     * @param orgId
     * @return
     */
    List<StatisticsVO> findStatisticsDetailsList(@Param("projectCode") String projectCode,
                                          @Param("diseasesId") Integer diseasesId,
                                          @Param("gender") Integer gender,
                                          @Param("startAge") String startAge,
                                          @Param("endAge") String endAge,
                                                 @Param("mobile") String mobile,
                                          @Param("orgId") Integer orgId,
                                          @Param("startIndex") Integer startIndex,
                                          @Param("pageSize") Integer pageSize);

    /**
     * 获取门店订单统计报表总数
     * @param projectCode
     * @param diseasesId
     * @param gender
     * @param startAge
     * @param endAge
     * @param orgId
     * @return
     */
    int countStatisticsDetails(@Param("projectCode") String projectCode,
                               @Param("diseasesId") Integer diseasesId,
                               @Param("gender") Integer gender,
                               @Param("startAge") String startAge,
                               @Param("endAge") String endAge,
                               @Param("mobile") String mobile,
                               @Param("orgId") Integer orgId);

    /**
     * 添加订单-机构短信充值订单
     * @param po
     */
    void addOrderStoreSms(OrderStoreSmsPO po);

    /**
     * 获取机构短信充值订单状态
     * @param orderNumber
     * @return
     */
    int getOrderStoreSmsStatus(@Param("orderNumber") String orderNumber);

    /**
     * 更新机构短信充值订单状态
     * @param orderNumber
     * @param status
     */
    void updateOrderStoreSmsStatus(@Param("orderNumber") String orderNumber,
                                   @Param("status") int status);
    /**
     * 根据ID和status获取订单数量
     *
     * @param id 门店用户ID
     * @param status 订单状态
     * @return
     */
    Integer getOrderCountByIdAndStatus(@Param("id") Integer id, @Param("status") Integer status);


    /**
     * 修改用户备注（基于订单表）
     * @param orderId
     * @param userId
     * @param userRemark
     */
    void updateUserRemark(@Param("orderId") int orderId, @Param("userId") int userId,
                          @Param("userRemark") String userRemark);

    /**
     * 修改用户病种（基于订单表）
     * @param orderId
     * @param userId
     * @param userDiseasesId
     * @param userDiseasesName
     */
    void updateUserDiseases(@Param("orderId") int orderId, @Param("userId") int userId,
                            @Param("userDiseasesId") Integer userDiseasesId, @Param("userDiseasesName") String userDiseasesName);

    /**
     * 根据用户ID获取服务师ID集合
     *
     * @param userId
     * @return
     */
    List<Integer> getOrgUserIds(@Param("userId") int userId);
    
    OrderAgentAmountVO findTotalMoney(@Param("userNo") String userNo,@Param("userName") String userName, 
            @Param("realName") String realName, @Param("orgName") String orgName,
            @Param("projectType") String projectType, @Param("status") Integer status,@Param("orderType") String orderType);

    /**
     *  获取订单列表数量
     *  @author yuhang.weng 
     *  @DateTime 2018年1月30日 下午2:09:25
     *
     *  @param userName 用户名
     *  @param realName 姓名
     *  @param orgName 机构名
     *  @param projectType 项目类型(1：咨询；2：线下；3：上门；4：课堂)
     *  @param status 订单状态
     *  @return
     */
    int countOrderWithCondition(@Param("userNo") String userNo, @Param("userName") String userName, @Param("realName") String realName, @Param("orgName") String orgName,
            @Param("projectType") String projectType, @Param("status") Integer status,@Param("orderType") String orderType);
    
    /**
     *  获取订单列表
     *  @author yuhang.weng 
     *  @DateTime 2018年1月30日 下午2:09:23
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @param userName 用户名
     *  @param realName 姓名
     *  @param orgName 机构名
     *  @param projectType 项目类型(1：咨询；2：线下；3：上门；4：课堂)
     *  @param status 订单状态
     *  @return
     */
    List<OrderWithVipPO> findOrderWithCondition(@Param("userNo") String userNo, @Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("userName") String userName, @Param("realName") String realName, @Param("orgName") String orgName,
            @Param("projectType") String projectType, @Param("status") Integer status,@Param("orderType") String orderType);
    
    /**
     *  统计订单统计数量
     *  @author yuhang.weng 
     *  @DateTime 2018年1月31日 下午3:25:09
     *
     *  @param orgName 机构名
     *  @param serveCode 服务code
     *  @param start 开始时间
     *  @param end 结束时间
     *  @return
     */
    int countOrderCountWithCondition(@Param("userNo") String userNo, @Param("orgName") String orgName, @Param("serveCode") String serveCode, @Param("start") Date start,
            @Param("end") Date end);
    
    /**
     *  获取订单统计
     *  @author yuhang.weng 
     *  @DateTime 2018年1月31日 下午3:23:28
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @param orgName 机构名
     *  @param serveCode 服务code
     *  @param start 开始时间
     *  @param end 结束时间
     *  @return
     */
    List<OrderCountVO> findOrderCountWithCondition(@Param("userNo") String userNo, @Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("orgName") String orgName, @Param("serveCode") String serveCode, @Param("start") Date start, @Param("end") Date end);
}
