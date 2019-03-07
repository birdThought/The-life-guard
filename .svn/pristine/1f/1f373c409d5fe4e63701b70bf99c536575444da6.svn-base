package com.lifeshs.service1.customer;



import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.customer.CustomerOrderPo;
import com.lifeshs.po.customer.UpdateOrderPo;
import com.lifeshs.service1.page.Paging;


/**
 * 客服工单服务
 * @author shiqiang.zeng
 * @Date 2017.12.21 14:00
 */
public interface CustomerOrder1Service {
	
	/**
	 * 填写工单信息
	 * @param id
	 * @throws OperationException
	 */
	void updateOrder(int id, UpdateOrderPo updateOrderPo) throws OperationException;
	/**
	 * 统计总工单数量
	 * @return
	 */
	int countOrder();
	
	/**
	 * 统计未提交工单数量
	 * @param status
	 * @return
	 */
	int countNotOrder(int status);
	
	/**
	 * 统计提交成功工单数量
	 * @param status
	 * @return
	 */
	int countSuccessOrder(int status);
	
	/**
	 * 根据id获取工单信息
	 * @param id
	 * @return
	 */
	CustomerOrderPo getOrder(int id);
	
	/**
	 * 获取工单列表
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	Paging<CustomerOrderPo> findOrderList(int curPage,int pageSize);
	
	/**
	 * 获取未提交的工单列表(状态0提交)
	 * @param status
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<CustomerOrderPo> findNotOrderList(int status,int curPage, int pageSize);
	
	/**
	 * 获取已提交的工单列表(状态1提交)
	 * @param status
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<CustomerOrderPo> findSuccessList(int status,int curPage,int pageSize);
	
	/**
	 * 获取提交失败的工单列表(状态1提交)
	 * @param status
	 * @param curPage
	 * @param pageSize
	 * @return
	 */
	Paging<CustomerOrderPo> findFailList(int status,int curPage,int pageSize);


	
	
	

}
