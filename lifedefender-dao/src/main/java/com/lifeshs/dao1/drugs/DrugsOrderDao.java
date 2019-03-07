package com.lifeshs.dao1.drugs;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.drugs.OrderPO;

/**
 * 
 * 药品管理
 * 
 * @author liaoguo
 * @version 1.0
 * @DateTime 2018年6月6日 下午4:59:19
 */
@Repository(value = "drugsOrderDao")
public interface DrugsOrderDao {
    
    public int countDrugsOrder(@Param(value = "orderNo") String orderNo,
                               @Param(value = "paymentType") Integer paymentType,
                               @Param(value = "status") Integer status);
    
    //查询订单
    public List<OrderPO> getDrugsOrderList(@Param(value = "orderNo") String orderNo,
                                             @Param(value = "paymentType") Integer paymentType,
                                             @Param(value = "status") Integer status,
                                             @Param(value = "startRow") int startRow,
                                             @Param(value = "pageSize") int pageSize);
    
    //根据订单编号查找订单详情
    public OrderPO findDrugsOrderInfo(@Param(value = "orderNo") String orderNo);
    
}
