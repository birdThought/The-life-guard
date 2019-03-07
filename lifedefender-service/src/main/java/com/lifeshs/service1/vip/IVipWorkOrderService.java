package com.lifeshs.service1.vip;

import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.po.workOrder.WorkOrderPO;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.workOrder.WorkOrderVO;

/**
 * vip工单
 * 
 * @author zizhen.huang
 * @DateTime 2017年12月26日15:54:50
 */
public interface IVipWorkOrderService {
	/**
	 * 减少vip用户套餐次数
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月26日15:24:46
	 * 
	 * @param userId
	 *            用户id
	 * @param comboItemId
	 *            套餐项id
	 * @throws OperationException
	 */
	void subtractComboNumber(int userId, int comboId, int comboItemId) throws OperationException;

	/**
	 * 提交用户填写的工单
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月27日17:15:56
	 * @param customerOrderPo
	 *            工单PO对象
	 * @throws OperationException
	 */
	void submitWorkOrder(WorkOrderPO workOrderPo) throws OperationException;

	/**
	 * 获取工单列表
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月28日15:24:39
	 * 
	 * @param userId
	 *            用户id
	 * @param curPage
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	Paging<WorkOrderVO> findWorkOrderList(int userId, int curPage, int pageSize);

	/**
	 * 添加vip套餐次数
	 * 
	 * @author zizhen.huang
	 * @DateTime 2018年1月2日14:25:03
	 * @param userId
	 *            用户id
	 * @param comboItemId
	 *            套餐项id
	 * @param comboNumber
	 *            套餐次数
	 */
	void addComboNumber(int userId, int comboId, int comboItemId, int comboNumber) throws OperationException;

	/**
	 * 根据id获取套餐次数
	 * 
	 * @author zizhen.huang
	 * @DateTime 2018年1月2日14:31:21
	 * 
	 * @param userId
	 *            用户id
	 * @param comboItemId
	 *            套餐项id
	 * @return 套餐次数
	 */
	Integer findComboNumberById(int userId, int comboItemId);
	
	/**
     * 根据用户、套餐、套餐项id获取套餐次数
     * 
     * @author liaoguo
     * @DateTime 2018年8月17日15:17:17
     * 
     * @param userId 用户id
     * @param comboId 套餐id
     * @param comboItemId 套餐项id
     * @return 套餐次数
     */
    Integer findComboNumberById(int userId, int comboId, int comboItemId);

	/**
	 * @author zizhen.huang
	 * @DateTime 2018年1月2日14:36:02
	 * 
	 * @param userId
	 *            用户id
	 * @param comboItemId
	 *            套餐项id
	 * @param comboNumber
	 *            套餐次数
	 * @throws OperationException
	 */
	void updateComboNumber(int userId, int comboId, int comboItemId, int comboNumber) throws OperationException;
}
