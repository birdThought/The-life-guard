package com.lifeshs.dao1.order;

import com.lifeshs.po.OrderRefundPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("iOrderRefundDao")
public interface IOrderRefundDao {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderRefundPO record);

    int insertSelective(OrderRefundPO record);

    OrderRefundPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderRefundPO record);

    int updateByPrimaryKey(OrderRefundPO record);

    /**
     * 根据订单号获取OrderRefundPO
     *
     * @param orderNumber
     * @return
     */
    OrderRefundPO getOrderRefundPOByOrderNumber(@Param("orderNumber") String orderNumber);
}