package com.lifeshs.dao.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.entity.order.TOrder;
import com.lifeshs.pojo.order.OrderCommentDTO;
import com.lifeshs.pojo.order.OrderFlowBasicDTO;
import com.lifeshs.pojo.order.SMSOrderDTO;
import com.lifeshs.pojo.order.ServiceOrderDTO;
import com.lifeshs.pojo.order.ServiceOrderDetailDO;
import com.lifeshs.pojo.order.UserServeDO;
import com.lifeshs.pojo.order.homeOrderDTO;
import com.lifeshs.pojo.org.profit.ProfitDTO;

@Repository("orderDao")
public interface IOrderDao {

    /**
     *  获取服务订单数量
     *  @author yuhang.weng 
     *  @DateTime 2016年10月19日 下午8:09:24
     *
     *  @param userId
     *  @return
     */
    int getCountOfServeOrderByUserId(@Param("status") List<Integer> status, @Param("groupIds") List<Integer> groupIds, @Param("userId") int userId);
    
    /**
     *  分页获取用户服务订单
     *  @author yuhang.weng 
     *  @DateTime 2016年10月19日 下午7:32:11
     *
     *  @param userId 用户ID
     *  @param startIndex 开始下标
     *  @param pageSize 页面大小
     *  @return
     */
    List<Map<String, Object>> listServeOrderWithPageSplit(@Param("status") List<Integer> status,
                                                            @Param("groupIds") List<Integer> groupIds,
                                                            @Param("userId") int userId,
                                                            @Param("startIndex") int startIndex,
                                                            @Param("pageSize") int pageSize);
    
    /**
     *  查询单条服务订单信息
     *  @author yuhang.weng 
     *  @DateTime 2016年10月19日 下午7:32:08
     *
     *  @param userId
     *  @param orderId
     *  @return
     */
    Map<String, Object> getServeOrder(@Param("userId") int userId, @Param("orderId") int orderId);
    
    /**
     *  查询交易流水的基础信息信息
     *  @author yuhang.weng 
     *  @DateTime 2016年10月20日 下午3:23:35
     *
     *  @param userId
     *  @param orderNumber
     *  @return
     */
    OrderFlowBasicDTO getOrderBasicDataByOrderNumber(@Param("orderNumber") String orderNumber);

    /**
     * 查询机构短信充值订单交易流水的基础信息信息
     * author: wenxian.cai
     * date: 2017/8/30 10:36
     */
    OrderFlowBasicDTO getOrderStoreSmsBasicDataByOrderNumber(@Param("orderNumber") String orderNumber);
    
    /**
     *  获取用户服务列表
     *  @author yuhang.weng 
     *  @DateTime 2016年10月22日 下午4:42:21
     *
     *  @param userId
     *  @return
     */
    List<UserServeDO> getUserServeDOList(@Param("userId") int userId);
    
    /**
     *  获取用户服务列表详情
     *  @author yuhang.weng 
     *  @DateTime 2016年10月24日 下午8:25:58
     *
     *  @param userId
     *  @param orderId
     *  @return
     */
    ServiceOrderDetailDO getServiceOrderDetailDO(
            @Param("userId") int userId,
            @Param("orgServeId") int orgServeId);
    
    /**
     *  通过机构服务ID获取用户的第一张服务订单
     *  @author yuhang.weng 
     *  @DateTime 2016年11月8日 下午5:48:32
     *
     *  @param userId
     *  @param orgServeId
     *  @return
     */
//    ServiceOrderDetailDO getUserOrderMaxStartDateAndEndDateByUserIdAndOrgServeId(
//            @Param("userId") Integer userId,
//            @Param("orgServeId") Integer orgServeId);
    
    /**
     *  获取用户特定服务的第一张按次付费服务订单
     *  @author yuhang.weng 
     *  @DateTime 2016年11月16日 下午12:03:53
     *
     *  @param userId
     *  @param orgServeId
     *  @return
     */
    TOrder getUserFirstOrderIdWithChargeModeIsByTime(
            @Param("userId") Integer userId,
            @Param("orgServeId") Integer orgServeId);
    
    /**
     *  更新服务订单hasWarning字段
     *  @author yuhang.weng 
     *  @DateTime 2016年11月29日 下午7:12:44
     *
     *  @param warningDate
     *  @param hasWarning
     *  @param userId
     *  @param serveUserId 服务师ID(V24改版)
     */
    void updateServeOrderHasWarning(@Param("warningDate") Date warningDate,
                                      @Param("hasWarning") Integer hasWarning,
                                      @Param("userId") Integer userId,
                                      @Param("serveUserId") Integer serveUserId);
    
    /**
     *  将有效的服务订单状态修改为已完成
     *  <p>备注：如果userId为null，就不根据用户ID进行筛选需要更新的字段
     *  @author yuhang.weng 
     *  @DateTime 2017年3月9日 下午4:02:02
     *
     *  @param serveGroupId
     *  @param userId
     */
    void updateValidServeOrderToComplete(@Param("serveGroupId") int serveGroupId,
                                    @Param("userId") Integer userId);
    
    /**
     *  将未支付的服务订单修改为支付失败状态
     *  <p>备注：如果userId为null，就不根据用户ID进行筛选需要更新的字段
     *  @author yuhang.weng 
     *	@DateTime 2017年3月16日 上午11:20:24
     *
     *  @param serveGroupId
     *  @param orgServeId
     *  @param userId
     */
    void updateBeforePayServeOrderToPayFailded(@Param("serveGroupId") int serveGroupId,
                                          @Param("orgServeId") int orgServeId,
                                          @Param("userId") Integer userId);
    
    /**
     *  添加一条短信充值服务订单
     *  @author yuhang.weng 
     *	@DateTime 2017年4月27日 下午3:04:27
     *
     */
    void addSMSOrder(@Param("orderNumber") String orderNumber, @Param("userId") int userId,
            @Param("price") int price, @Param("number") int number, @Param("charge") int charge,
            @Param("subject") String subject, @Param("body") String body, @Param("status") int status);
    
    int getCountOfSMSOrderByUserId(@Param("statusList") List<Integer> statusList, @Param("userId") int userId);
    
    List<SMSOrderDTO> listSMSOrder(@Param("userId") int userId, @Param("statusList") List<Integer> statusList,
            @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);
    
    void updateServeOrderStatus();
    
    List<Map<String, Object>> listNeedToBeFaildLessonOrder();

    /**
     * @Description: 根据服务师获取服务订单
     * @author: wenxian.cai
     * @create: 2017/5/8 19:15
     * @param type 服务类型
     * @param keyword 关键词（项目名称、订单号、用户名）
     */
    List<ServiceOrderDTO> listOrderByOrgUser(@Param(value = "orgUserId") Integer orgUserId, @Param(value = "status") Integer status,
                                             @Param(value = "keyword") String keyword, @Param(value = "type") String type,
                                             @Param(value = "startIndex") Integer startIndex, @Param(value = "pageSize") Integer pageSize);


    /**
     * @Description: 获取服务师订单总数
     * @author: wenxian.cai
     * @create: 2017/5/8 19:25
     * @param type 服务类型
     * @param keyword 关键词（项目名称、订单号、用户名）
     */
    int getCountOfServiceOrderByOrgUser(@Param(value = "orgUserId") Integer orgUserId, @Param(value = "status") Integer status,
                                        @Param(value = "keyword") String keyword, @Param(value = "type") String type);

    /**
     * @Description: 获取服务师的指定日期订单总数
     * @author: wenxian.cai
     * @create: 2017/5/8 19:25
     * @param createDate 查询日期，"yyyy-MM-dd"
     */
    int getTodayCountOfServiceOrderByOrgUser(@Param(value = "orgUserId") Integer orgUserId, @Param(value = "status") Integer status, @Param(value = "createDate") String createDate);

    /**
     * @Description: 获取服务师待评论订单列表
     * @author: wenxian.cai
     * @create: 2017/5/9 12:05
     */
    List<ServiceOrderDTO> listOrderByComments(@Param(value = "orgUserId") Integer orgUserId, @Param(value = "status") Integer status,
                                              @Param(value = "userName") String userName, @Param(value = "type") String type,
                                              @Param(value = "commentsStatus") Boolean commentsStatus,
                                              @Param(value = "startIndex") Integer startIndex, @Param(value = "pageSize") Integer pageSize);

    /**
     * @Description: 根据评论获取服务师待评论订单总数
     * @author: wenxian.cai
     * @create: 2017/5/8 19:25
     */
    int getCountOfServiceOrderByComments(@Param(value = "orgUserId") Integer orgUserId, @Param(value = "status") Integer status,
                                         @Param(value = "userName") String userName, @Param(value = "type") String type,
                                         @Param(value = "commentsStatus") Boolean commentsStatus);

    /**
     * @Description: 获取服务详情
     * @author: wenxian.cai
     * @create: 2017/5/9 15:29
     */
//    ServiceOrderDTO getServeOrderDetail(@Param(value = "orderId") Integer orderId, @Param("orgUserId") int userId);

    /**
     *  添加评论
     *  @author yuhang.weng 
     *	@DateTime 2017年6月17日 下午3:25:54
     *
     *  @param comment
     *  @return
     */
    int saveServeOrderComment(OrderCommentDTO comment);
    
    /**
     *  更新评论
     *  @author yuhang.weng 
     *	@DateTime 2017年5月12日 下午2:47:03
     *
     *  @param comment
     */
    int updateServeOrderComment(OrderCommentDTO comment);

    /**
     * @Description: 获取指定日期收益
     * @Author: wenxian.cai
     * @Date: 2017/6/28 16:37
     * @param orgId 机构id
     */
    ProfitDTO countDayProfit(@Param("orgId") int orgId, @Param("date") String date);

    /**
     * @Description: 获取指定日期收益
     * @Author: wenxian.cai
     * @Date: 2017/7/7 10:00
     * @param orgUserId 服务师id
     */
    ProfitDTO countDayProfitByServices(@Param("orgUserId") int orgUserId, @Param("date") String date);

    /**
     *  计算指定日期前一周的收益
     *  比如：指定日期2017-06-06，就会获取05-31,06-01,06-02,06-03,06-04,06-05,06-06的收益
     *  @author yuhang.weng 
     *	@DateTime 2017年6月6日 下午5:06:26
     *
     *  @param orgId
     *  @param date 指定日期
     *  @return
     */
    ProfitDTO countWeekProfit(@Param("orgId") int orgId, @Param("date") String date);



    
    /**
     *  获取指定时间段内每天的收益(orgId 与orgUserId取其一)
     *  @author yuhang.weng 
     *	@DateTime 2017年6月7日 下午2:04:01
     *
     *  @param orgId
     *  @param orgUserId
     *  @param startDate 不包含startDate这一天
     *  @param endDate 包含endDate这一天
     *  @return
     */
    List<ProfitDTO> listProfit(@Param("orgId") Integer orgId, @Param("orgUserId") Integer orgUserId, @Param("startDate") String startDate, @Param("endDate") String endDate);
    
    /**
     *  获取指定时间段内的结款（纯收入）
     *  @author yuhang.weng 
     *	@DateTime 2017年6月9日 上午9:35:46
     *
     *  @param orgId
     *  @param startDate
     *  @param endDate
     *  @return
     */
    Long countIncome(@Param("orgId") int orgId, @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * @Description: 根据机构获取订单信息
     * @Author: wenxian.cai
     * @Date: 2017/6/7 17:53
     */
    List<ServiceOrderDTO> listOrderByOrg(@Param(value = "orgId") Integer orgId, @Param(value = "userName") String userName,
                                         @Param(value = "status") Integer status, @Param(value = "type") String type,
                                         @Param(value = "startIndex") Integer startIndex, @Param(value = "pageSize") Integer pageSize);

    /**
     * @Description: 根据机构获取订单总数
     * @Author: wenxian.cai
     * @Date: 2017/6/8 9:53
     */
    Integer getCountOfOrderByOrg(@Param(value = "orgId") Integer orgId, @Param(value = "userName") String userName,
                                 @Param(value = "status") Integer status, @Param(value = "type") String type);

    /**
     * @Description: 根据机构获取指定日期的订单总数
     * @Author: wenxian.cai
     * @Date: 2017/6/8 9:53
     * @param createDate 查询日期 yyyy-MM-dd
     */
    int getTodayCountOfOrderByOrg(@Param(value = "orgId") Integer orgId,  @Param(value = "status") Integer status, @Param(value = "createDate") String createDate);

    /**
     * @Description: 获取订单详细
     * @Author: wenxian.cai
     * @Date: 2017/6/8 14:31
     */
    ServiceOrderDTO getOrderDetails(@Param("orderId") Integer order, @Param("orgId") Integer orgId);

    /**
     * @Description: 获取服务师一周收入
     * @Author: wenxian.cai
     * @Date: 2017/6/13 16:21
     */
    ProfitDTO countWeekProfitByOrgUser(@Param("orgUserId") int orgUserId, @Param("date") String date);

    /**
     * @Description: 获取订单验证码
     * @Author: wenxian.cai
     * @Date: 2017/6/15 19:36
     */
    String getVerifyCode(@Param("orderId") int orderId, @Param("orderNumber") String orderNumber);
    
    /**
     *  通过用户ID与服务师ID查询一条有效的订单
     *  @author yuhang.weng 
     *	@DateTime 2017年6月19日 上午10:29:33
     *
     *  @param userId
     *  @param serveUserId
     *  @param projectType 项目类型(1：咨询；2：线下；3：上门；4：课堂)
     *  @return
     */
    com.lifeshs.pojo.order.v2.OrderDTO getServiceOrderByUserIdAndServeUserId(@Param("userId") Integer userId, @Param("serveUserId") Integer serveUserId, @Param("projectType") int projectType);

    /**
     * @Description: 获取指定时间段服务师订单列表
     * @Author: wenxian.cai
     * @Date: 2017/6/29 11:48
     */
    List<homeOrderDTO> listOrderByServices(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                          @Param("orgUserId") int orgUserId);

    /**
     * @Description: 获取指定时间段门店订单列表
     * @Author: wenxian.cai
     * @Date: 2017/6/29 11:48
     */
    List<homeOrderDTO> listOrderByStore(@Param("startDate") String startDate, @Param("endDate") String endDate,
                                          @Param("orgId") int orgId);

    /**
     *  保存一条订单记录
     *  @author yuhang.weng 
     *	@DateTime 2017年6月27日 下午4:35:43
     *
     *  @param order
     *  @exception Exception
     */
    void saveOrder(com.lifeshs.pojo.order.v2.OrderDTO order) throws org.springframework.dao.DuplicateKeyException;
    
    /**
     *  更新一条订单记录
     *  @author yuhang.weng 
     *	@DateTime 2017年7月19日 下午2:23:03
     *
     *  @param order
     *  @return
     */
    int updateOrder(com.lifeshs.pojo.order.v2.OrderDTO order);
    
    /**
     *  按条件查询用户订单数量
     *  @author yuhang.weng 
     *	@DateTime 2017年6月28日 上午10:57:26
     *
     *  @param userId
     *  @param statusList
     *  @param projectTypeList
     *  @return
     */
    int countOrderWithCondition(@Param("userId") Integer userId,
            @Param("statusList") List<Integer> statusList, @Param("projectTypeList") List<Integer> projectTypeList,
            @Param("refundNotNull") Boolean refundNotNull, @Param("commentNotNull") Boolean commentNotNull);
    
    /**
     *  按条件查询用户订单
     *  @author yuhang.weng 
     *	@DateTime 2017年6月28日 上午10:56:12
     *
     *  @param userId
     *  @param statusList
     *  @param projectTypeList
     *  @return
     */
    List<com.lifeshs.pojo.order.v2.OrderDTO> listOrderWithCondition(@Param("userId") Integer userId,
            @Param("statusList") List<Integer> statusList, @Param("projectTypeList") List<Integer> projectTypeList,
            @Param("refundNotNull") Boolean refundNotNull, @Param("commentNotNull") Boolean commentNotNull,
            @Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    /**
     * @Description: 计算指定时间段收益
     * @Author: wenxian.cai
     * @Date: 2017/7/4 9:55
     */
    ProfitDTO countProfit(@Param("orgId") int orgId, @Param("startDate") String startDate,
                               @Param("endDate") String endDate);

    /**
     * @Description: 计算服务师指定时间段收益
     * @Author: wenxian.cai
     * @Date: 2017/7/7 10:25
     * @param orgUserId 服务师id
     */
    ProfitDTO countProfitByServices(@Param("orgUserId") int orgUserId, @Param("startDate") String startDate,
                          @Param("endDate") String endDate);

    /**
     * @Description: 按月分组获取收益
     * @Author: wenxian.cai
     * @Date: 2017/7/4 14:08
     */
    List<ProfitDTO> countProfitByMonth(@Param("orgId") int orgId, @Param("startDate") String startDate,
                                 @Param("endDate") String endDate);
}
