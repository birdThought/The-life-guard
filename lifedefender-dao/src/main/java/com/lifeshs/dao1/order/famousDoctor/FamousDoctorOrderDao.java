package com.lifeshs.dao1.order.famousDoctor;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.order.FamousDoctorOrderPO;

/**
 *  名医预约订单dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月19日 下午1:53:13
 */
@Repository(value = "famousDoctorOrderDao")
public interface FamousDoctorOrderDao {

    /**
     *  添加订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月19日 下午1:52:43
     *
     *  @param order
     *  @return
     */
    int addOrder(FamousDoctorOrderPO order);
    
    /**
     *  更新订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月19日 下午1:52:37
     *
     *  @param order
     *  @return
     */
    int updateOrder(FamousDoctorOrderPO order);
    
    /**
     *  获取订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月19日 下午1:52:31
     *
     *  @param id 订单id
     *  @return
     */
    FamousDoctorOrderPO getOrder(@Param("id") int id);
    
    /**
     *  获取订单
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午2:01:49
     *
     *  @param orderNumber
     *  @return
     */
    FamousDoctorOrderPO findOrderByOrderNumber(@Param("orderNumber") String orderNumber);
    
    /**
     *  统计订单数量
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午2:05:13
     *
     *  @param userId 用户id
     *  @param famousDoctorId 名医id
     *  @param status 订单状态
     *  @return
     */
    int countOrderWithCondition(@Param("userId") Integer userId, @Param("famousDoctorId") Integer famousDoctorId,
            @Param("status") Integer status);
    
    /**
     *  获取订单列表
     *  @author yuhang.weng 
     *  @DateTime 2017年10月24日 下午2:05:16
     *
     *  @param startRow 开始下标
     *  @param pageSize 页面大小
     *  @param userId 用户id
     *  @param famousDoctorId 名医id
     *  @param status 订单状态
     *  @return
     */
    List<FamousDoctorOrderPO> findOrderListWithCondition(@Param("startRow") int startRow, @Param("pageSize") int pageSize,
            @Param("userId") Integer userId, @Param("famousDoctorId") Integer famousDoctorId, @Param("status") Integer status);
}
