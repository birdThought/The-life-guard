package com.lifeshs.service.order;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lifeshs.common.constants.common.HealthPackageType;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.common.model.ServiceMessage;
import com.lifeshs.entity.order.TOrderFlow;
import com.lifeshs.pojo.order.SMSOrderDTO;
import com.lifeshs.pojo.order.ServiceOrderDTO;
import com.lifeshs.pojo.order.UserServeDO;
import com.lifeshs.pojo.order.homeOrderDTO;
import com.lifeshs.pojo.org.profit.ProfitDTO;
import com.lifeshs.pojo.page.PaginationDTO;

public interface IOrderService {

    /**
     *  生成一个服务订单
     *  @author yuhang.weng 
     *  @DateTime 2016年10月19日 下午12:00:42
     *
     *  @param userId
     *  @param orgServeId
     *  @param orderNumber
     *  @param priceType
     *  @param count
     *  @param subject
     *  @param body
     *  @return success包含orderId，false包含错误信息
     */
    ServiceMessage addServeOrder(int userId, int orgServeId, String orderNumber, int priceType, int count,
            String subject, String body, int status, Integer groupId);
    
    /**
     *  分页获取用户订单信息
     *  @author yuhang.weng 
     *  @DateTime 2016年10月19日 下午7:46:04
     *
     *  @param userId
     *  @param curPage
     *  @param pageSize
     *  @return
     */
    PaginationDTO getOrderListWithPageSplit(List<Integer> status, List<Integer> serveGroup, int userId, int curPage, int pageSize);
    
    /**
     *  查询单条订单信息
     *  @author yuhang.weng 
     *  @DateTime 2016年10月20日 上午10:29:52
     *
     *  @param userId
     *  @param orderId
     *  @return
     */
    Map<String, Object> getOrder(int userId, int orderId);
    
    /**
     *  修改订单状态为有效(暂时对web有效)
     *  @author yuhang.weng 
     *  @DateTime 2016年10月20日 下午2:50:27
     *
     *  @param userId
     *  @param orderNumber
     *  @param status
     *  @param totalMoney 元为单位
     *  @return
     */
    boolean modifyOrderStatusToValid(int userId, String orderNumber,String totalMoney,boolean isfree);
    
    /**
     *  修改订单的状态为支付失败
     *  @author yuhang.weng 
     *  @DateTime 2016年10月20日 下午8:15:43
     *
     *  @param userId
     *  @param orderNumber
     *  @return
     */
    boolean modifyOrderStatusToFaild(int userId, String orderNumber);
    
    /**
     *  添加一条交易流水，并且将orderNumber对应的订单记录状态修改为已完成
     *  @author yuhang.weng 
     *  @DateTime 2016年10月20日 下午4:26:48
     *
     *  @param orderFlow
     *  @return
     */
    boolean addOverFlow(TOrderFlow orderFlow);
    
    /**
     *  查询交易流水是否已存在
     *  @author yuhang.weng 
     *  @DateTime 2016年10月20日 下午4:39:24
     *
     *  @param orderNumber
     *  @return
     */
    boolean isOverFlowExist(String orderNumber);

    /**
     * 删除一个订单
     * @param userId
     * @param ordeId
     */
    void delOrder(Integer userId,Integer ordeId);
    
    /**
     *  获取用户服务列表（用户已购服务项）
     *  @author yuhang.weng 
     *  @DateTime 2016年10月22日 下午4:52:53
     *
     *  @param userId
     *  @return
     */
    List<UserServeDO> getUserServeDOList(int userId);
    
    /**
     *  获取用户服务列表（用户已购服务项）（WEB封装）
     *  @author yuhang.weng 
     *  @DateTime 2016年10月26日 上午10:48:21
     *
     *  @param userId
     *  @return
     */
    List<Map<String, Object>> getUserServeList(int userId);
    
    /**
     *  取消订单
     *  @author yuhang.weng 
     *  @DateTime 2016年10月24日 下午6:04:05
     *
     *  @param userId
     *  @param orderNumber
     *  @return
     */
    boolean cancelOrder(Integer userId, String orderNumber);
    
    /**
     *  获取用户服务列表详情
     *  @author yuhang.weng 
     *  @DateTime 2016年10月24日 下午8:27:43
     *
     *  @param userId
     *  @param orgServeId
     *  @return
     */
    Map<String, Object> getUserServeDetail(int userId, int orgserveId);
    
    /**
     * 支付回调完成订单
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
    @Deprecated
    boolean finishOrder(String outNo, String tradeNo,String payerAccount,String sellerAccount, double totalCount,int payType,String deviceType, String extraData);

    /**
     * 更新订购次数
     * @param orderId
     * @param userId
     * @param count
     * @return
     */
    boolean updateOrderNumber(Integer orderId,Integer userId,int count);

    /**
     * 创建一个订单信息
     * @param orgServeId 机构服务ID
     * @param userId 用户ID
     * @param count  购买的数量
     * @param mode 购买的模式
     * @return
     */
    Map<String,Object> createOrderMsg(Integer orgServeId,Integer userId,Integer count,Integer mode);
    
    /**
     *  减少服务次数
     *  @author yuhang.weng 
     *  @DateTime 2016年11月16日 上午11:39:14
     *
     *  @param userId
     *  @param orgServeId
     *  @return
     */
    boolean reduceServeTime(Integer userId, Integer orgServeId);

    /**
     * 更新用户订单的健康警示值
     *
     * @param measureDate 测量时间
     * @param userId      用户ID
     * @param deviceType  设备类型
     * @author yuhang.weng
     * @updateBy wuj 2017-08-29 15:47:33
     * @updateReason 与前端要求不符, 且可能会出现日期BUG.例如,用户在2017-08-29测试数据,2017-08-30号上传数据,
     *  那么new Date()获取到的就不是用户测试时间.
     *
     * @DateTime 2016年11月29日 下午4:27:30
     */
    void updateUserHealthWarningToOrder(int userId, HealthPackageType deviceType, Date measureDate);
    
    /**
     *  移除红点(V24改版为按照服务师的id来消除红点)
     *  @author yuhang.weng 
     *  @DateTime 2016年12月15日 上午9:35:47
     *
     *  @param userId
     *  @param serveUserId
     */
    void removeRedPoint(int userId, int serveUserId);
    
    /**
     *  添加短信充值订单
     *  @author yuhang.weng 
     *	@DateTime 2017年4月28日 下午1:41:09
     *
     *  @param orderNumber
     *  @param userId
     *  @param price
     *  @param number
     *  @param subject
     *  @param body
     */
    void addSMSOrder(String orderNumber, int userId, int price, int number, String subject, String body);
    
    /**
     *  输入邀请码充值短信
     *  @author yuhang.weng 
     *	@DateTime 2017年4月28日 下午1:41:06
     *
     *  @param code
     *  @param userId
     *  @return
     */
    void addSmsInvitationCode(String code, int userId) throws OperationException;
    
    /**
     *  获取用户短信充值历史记录
     *  @author yuhang.weng 
     *	@DateTime 2017年4月28日 下午1:43:12
     *
     *  @param userId
     *  @return
     */
    PaginationDTO<SMSOrderDTO> listSMSOrder(int userId, int curPage, int pageSize);

    /**
     * @Description: 根据服务师获取服务信息（分页）
     * @author: wenxian.cai
     * @create: 2017/5/8 17:04
     * @param orgUserId 服务师id
     * @param status 订单状态
     * @param userName 用户名称
     */
    PaginationDTO<ServiceOrderDTO> listOrderByOrgUser(Integer orgUserId, Integer status, String userName, String type, Integer pageIndex, Integer pageSize);

    /**
     * @Description: 根据评论获取待评论服务信息（分页）
     * @author: wenxian.cai
     * @create: 2017/5/9 13:36
     */
    PaginationDTO<ServiceOrderDTO> listOrderByComments(Integer orgUserId, Integer status, String type, String userName, Boolean commentsStatus, Integer pageIndex, Integer pageSize);

    /**
     * @Description: 计算当天收益
     * @Author: wenxian.cai
     * @Date: 2017/6/28 16:35
     */
    ProfitDTO countDayProfit(int orgId);

    /**
     * @Description: 计算服务师当天收益
     * @Author: wenxian.cai
     * @Date: 2017/7/7 10:04
     * @param orgUserId 服务师id
     */
    ProfitDTO countDayProfitByServices(int orgUserId);

    /**
     *  计算最近一周的收益
     *  @author yuhang.weng 
     *	@DateTime 2017年6月7日 上午9:53:26
     *
     *  @param orgId
     *  @return
     */
    ProfitDTO countWeekProfit(int orgId);

    /**
     *  获取最近一周的每日收益
     *  @author yuhang.weng 
     *	@DateTime 2017年6月7日 下午2:08:12
     *
     *  @param orgId
     *  @return
     */
    List<ProfitDTO> listWeekProfit(int orgId);

    List<ProfitDTO> listWeekProfitByOrgUser(int orgUserId);
    
    /**
     *  获取最近两周的每日收益
     *  @author yuhang.weng 
     *	@DateTime 2017年6月7日 下午2:08:25
     *
     *  @param orgId
     *  @return
     */
    List<ProfitDTO> listTwoWeekProfit(int orgId);

    List<ProfitDTO> listTwoWeekProfitByOrgUser(int orgUserId);
    
    /**
     *  获取最近一月的每日收益
     *  @author yuhang.weng 
     *	@DateTime 2017年6月7日 下午2:08:35
     *
     *  @param orgId
     *  @return
     */
    List<ProfitDTO> listMonthProfit(int orgId);

    List<ProfitDTO> listMonthProfitByOrgUser(int orgUserId);
    
    /**
     *  获取指定时间段的每日收益
     *  @author yuhang.weng 
     *	@DateTime 2017年6月7日 下午2:08:46
     *
     *  @param orgId
     *  @param startDate
     *  @param endDate
     *  @return
     */
    List<ProfitDTO> listProfit(int orgId, String startDate, String endDate);

    List<ProfitDTO> listProfitByOrgUser(int orgUserId, String startDate, String endDate);
    
    /**
     *  计算本月的结算（纯收入）金额
     *  @author yuhang.weng 
     *	@DateTime 2017年6月9日 下午2:09:22
     *
     *  @param orgId
     *  @return
     */
    double countMonthIncome(int orgId);

    /**
     * @Description: 计算上一月（纯收入）金额
     * @Author: wenxian.cai
     * @Date: 2017/7/4 11:06
     */
    double countLastMonthIncome(int orgId);

    /**
     * @Description: 根据机构获取订单列表
     * @Author: wenxian.cai
     * @Date: 2017/6/7 19:22
     */
    PaginationDTO<ServiceOrderDTO> listOrderByOrg(Integer orgId, String userName, Integer status, String type, Integer pageIndex, Integer pageSize);

    /**
     * @Description: 获取订单详细
     * @Author: wenxian.cai
     * @Date: 2017/6/8 14:32
     */
    ServiceOrderDTO getOrderDetails(Integer orderId, Integer orgId);

    /**
     * @Description: 获取服务师订单数
     * @Author: wenxian.cai
     * @Date: 2017/6/13 16:05
     */
    int getCountOfServiceOrderByOrgUser(Integer orgUserId, Integer status, String userName, String type);

    /**
     * @Description: 获取服务师最近一周收益
     * @Author: wenxian.cai
     * @Date: 2017/6/13 16:53
     */
    ProfitDTO countWeekProfitByOrgUser(Integer orgUserId);

    /**
     * @Description: 获取订单验证码
     * @Author: wenxian.cai
     * @Date: 2017/6/15 19:38
     */
    boolean checkVerifyCode(int orderId, String orderNumber, String verifyCode);

    /**
     * @Description: 获取指定时间段服务师订单列表
     * @Author: wenxian.cai
     * @Date: 2017/6/29 11:24
     */
    List<homeOrderDTO> listOrderByOrgUser(String startDate, String endDate, int orgUserId);

    /**
     * @Description: 获取指定时间段门店订单列表
     * @Author: wenxian.cai
     * @Date: 2017/6/29 11:44
     */
    List<homeOrderDTO> listOrderByOrg(String startDate, String endDate, int orgId);

    /**
     * @Description: 计算本月的收益
     * @Author: wenxian.cai
     * @Date: 2017/7/4 10:01
     */
    ProfitDTO countThisMonthProfit(int orgId);

    /**
     * @Description: 计算服务师本月收益
     * @Author: wenxian.cai
     * @Date: 2017/7/7 10:21
     * @param orgUserId 服务师id
     */
    ProfitDTO countThisMonthProfitByServices(int orgUserId);

    /**
     * @Description: 计算上一个月的收益
     * @Author: wenxian.cai
     * @Date: 2017/7/4 10:41
     */
    ProfitDTO countLastMonthProfit(int orgId);

    /**
     * @Description: 计算每个月的收益
     * @Author: wenxian.cai
     * @Date: 2017/7/4 14:22
     * startMonth： 2017-07
     */
    List<ProfitDTO> countProfitByMonth(int orgId, String startMonth, String endMonth);
}
