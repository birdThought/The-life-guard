package com.lifeshs.dao1.order.vip;

import com.lifeshs.po.order.VipUserOrderPO;
import com.lifeshs.vo.order.vip.VipUserOrderVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository(value = "vipUserOrderDao")
public interface VipUserOrderDao {

    /**
     *  获取一条订单记录
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午3:45:10
     *
     *  @param id 订单id
     *  @return
     */
    VipUserOrderPO getOrder(@Param("id") int id);
    
    /**
     *  获取一条订单记录
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午3:45:08
     *
     *  @param orderNumber 订单编号
     *  @return
     */
    VipUserOrderPO findOrderByOrderNumber(@Param("orderNumber") String orderNumber);
    
    /**
     *  获取订单列表
     *  <p>会按照订单的id降序排列
     *  @author yuhang.weng 
     *  @DateTime 2017年11月1日 下午4:37:20
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @param userId 用户id
     *  @param vipComboId vip套餐id
     *  @param status 订单状态
     *  @param type 订单类型,1_会员付费,2_邀请码
     *  @param businessId 渠道商id
     *  @param deleted 是否已删除
     *  @return
     */
    List<VipUserOrderPO> findOrderListWithCondition(@Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("userId") Integer userId, @Param("vipComboId") Integer vipComboId, @Param("status") Integer status,
            @Param("type") Integer type, @Param("businessId") Integer businessId, @Param("deleted") Boolean deleted);
    
    /**
     *  添加订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午3:39:42
     *
     *  @param order
     *  @return
     */
    int addOrder(VipUserOrderPO order);
    
    /**
     *  更新订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午3:39:50
     *
     *  @param order
     *  @return
     */
    int updateOrder(VipUserOrderPO order);

    /**
     * 获取渠道商订单列表
     * @param businessId
     * @param startDate
     * @param endDate
     * @return
     */
    List<VipUserOrderVO> findOrderListByBusinessId(@Param("businessId") int businessId, @Param("startDate") String startDate,
                                                   @Param("endDate") String endDate, @Param("startRow") int startRow,
                                                   @Param("pageSize") int pageSize);

    /**
     * 获取渠道商订单总数
     * @param businessId
     * @param startDate
     * @param endDate
     * @return
     */
   int countOrderByBusinessId(@Param("businessId") int businessId, @Param("startDate") String startDate,
                              @Param("endDate") String endDate);

    /**
     * 获取渠道商分成总金额
     * @param businessId
     * @param startDate
     * @param endDate
     * @return
     */
    Integer countBusinessIncome(@Param("businessId") int businessId, @Param("startDate") String startDate,
                               @Param("endDate") String endDate);

    /**
     * 获取渠道商应付服务卡金额
     * @param businessId
     * @param startDate
     * @param endDate
     * @return
     */
    Integer countBusinessPayCard(@Param("businessId") int businessId, @Param("startDate") String startDate,
                               @Param("endDate") String endDate);
    
    /**
     * 获取已过期或即将过期套餐
     * @param endTime
     * @param startTime
     * @return
     * @author liu
     * @时间 2018年12月27日 上午11:17:29
     * @remark endTime和startTime结合限制消息重复发送
     */
    List<Map<String, Object>> findVipOrderOutOfEndDateList(@Param("endTime") Date endTime, @Param("startTime") Date startTime);
    /**
     * 订单完成
     * @param id
     * @return
     * @author liu
     * @时间 2018年12月27日 下午1:54:51
     * @remark
     */
    int finishOrder(@Param("id") Integer id);
}
