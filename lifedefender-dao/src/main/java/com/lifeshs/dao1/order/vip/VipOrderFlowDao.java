package com.lifeshs.dao1.order.vip;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.order.VipOrderFlowPO;

/**
 *  会员vip相关订单流水dao
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月17日 下午2:26:00
 */
@Repository(value = "vipOrderFlowDao")
public interface VipOrderFlowDao {

    /**
     *  通过订单编号查找订单流水信息
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午3:19:19
     *
     *  @param orderNumber 订单编号
     *  @return
     */
    VipOrderFlowPO findOrderFlowByOrderNumber(@Param("orderNumber") String orderNumber);

    /**
     *  添加订单流水
     *  @author yuhang.weng 
     *  @DateTime 2017年10月17日 下午3:19:11
     *
     *  @param orderFlow
     *  @return
     */
    int addOrderFlow(VipOrderFlowPO orderFlow);

}
