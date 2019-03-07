package com.lifeshs.dao1.vip;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.lifeshs.po.workOrder.WorkOrderPO;
import com.lifeshs.vo.workOrder.WorkOrderVO;

/**
 * vip工单
 * 
 * @author zizhen.huang
 * @DateTime 2017年12月26日16:04:41
 *
 */
@Repository("iVipWorkOrderDao")
public interface IVipWorkOrderDao {
	/**
	 * 更新vip套餐次数
	 * 
	 * @author zizhen.huang
	 * @DataTime 2017年12月26日16:22:10
	 * 
	 * @param userId
	 *            用户id
	 * @param comboItemId
	 *            套餐项id
	 * @return
	 */
	int updateComboNumber(@Param("userId") int userId, @Param("comboId") int comboId,
	        @Param("comboItemId") int comboItemId,@Param("comboNumber") int comboNumber);

	/**
	 * 添加vip用户工单
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月27日18:19:10
	 * @param customerOrderPo
	 *            工单PO对象
	 * @return
	 */
	int addWorkOrder(WorkOrderPO workOrderPo);

	/**
	 * 获取工单总记录数
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月28日15:31:06
	 * @param userId
	 *            用户id
	 * @return 总记录数
	 */
	int countWorkOrder(@Param("userId") int userId);

	/**
	 * 获取工单列表
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月28日15:42:31
	 * 
	 * @param userId
	 *            用户id
	 * @param startRow
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	List<WorkOrderVO> findWorkOrderList(@Param("userId") int userId, @Param("startRow") int startRow,
			@Param("pageSize") int pageSize);

	/**
	 * 添加vip套餐次数
	 * 
	 * @author zizhen.huang
	 * @DateTime 2017年12月29日20:03:02
	 * 
	 * @param userId
	 *            用户名
	 * @param comboItemId
	 *            套餐项id
	 * @param comboNumber
	 *            套餐次数
	 * @return
	 */
	int addComboNumber(@Param("userId") int userId,@Param("comboId") int comboId,
	        @Param("comboItemId") int comboItemId,@Param("comboNumber") int comboNumber);

	/**
	 * 根据id获取套餐次数
	 * 
	 * @author zizhen.huang
	 * @DateTime 2018年1月2日11:00:19
	 * 
	 *           获取套餐记录
	 * @param userId
	 *            用户id
	 * @param comboItemId
	 *            套餐项id
	 * @return 套餐次数
	 */
	Integer findComboNumberById(@Param("userId") int userId, @Param("comboItemId") int comboItemId);
	
	/**
     * 根据id获取套餐次数
     * 
     * @author zizhen.huang
     * @DateTime 2018年1月2日11:00:19
     * 
     *           获取套餐记录
     * @param userId
     *            用户id
     * @param comboItemId
     *            套餐项id
     * @return 套餐次数
     */
    Integer findComboNumberById(@Param("userId") int userId,@Param("comboId") int comboId, @Param("comboItemId") int comboItemId);
}