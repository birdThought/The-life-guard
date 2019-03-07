package com.lifeshs.dao1.customer;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.customer.CustomerOrderPo;
import com.lifeshs.po.customer.UpdateOrderPo;


/**
 * 客服工单表
 * @author shiqiang.zeng
 * @date: 2017年12月21日9:30
 *
 */
@Repository(value="customerOrderDao")
public interface ICustomerOrderDao {
	
	
	/**
	 * 更改工单信息
	 * @param id 
	 * @param updataOrderPo 订单更改信息
	 * 
	 */
	void updateOrder(@Param("id")Integer id, @Param("data") UpdateOrderPo updateOrderPo);	
	
	/**
	 * 统计总工单数量
	 * 
	 */
	int countOrder();

	/**
	 * 统计未提交工单数量
	 * @Param status 状态0
	 */
	int countNotOrder(  @Param("status") int status);
	
	/**
	 * 统计提交成功工单数量
	 * @Param status 状态1
	 */
	int countSuccessOrder( @Param("status") int status);
	
	/**
	 * 统计提交失败工单数量
	 * @Param status 状态2
	 */
	int countFailOrder( @Param("status") int status);
	
	/**
	 * 根据id获取工单信息
	 * @param id 
	 * 
	 */
	CustomerOrderPo getOrder(@Param("id") Integer id);
	
	/**
	 * 获取工单列表
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<CustomerOrderPo> findOrderList(@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	/**
	 * 获取未提交的工单列表
	 * @param startRow
	 * @param pageSize
	 * @Param status 状态0未提交
	 * @return
	 */
	List<CustomerOrderPo> findNotOrderList(@Param("status") int status,@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	/**
	 * 获取提交成功的工单列表
	 * @param startRow
	 * @param pageSize
	 * @Param status 状态1提交成功
	 * @return
	 */
	List<CustomerOrderPo> findSuccessOrderList(@Param("status") int status,@Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
	/**
	 * 获取提交失败的工单列表
	 * @param startRow
	 * @param pageSize
	 * @Param status 状态1提交成功
	 * @return
	 */
	List<CustomerOrderPo> findFailOrderList(@Param("status") int status,@Param("startRow") int startRow, @Param("pageSize") int pageSize);
}
