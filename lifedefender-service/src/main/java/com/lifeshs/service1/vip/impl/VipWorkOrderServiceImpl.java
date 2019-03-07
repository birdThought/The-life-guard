package com.lifeshs.service1.vip.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.vip.IVipUserDao;
import com.lifeshs.dao1.vip.IVipWorkOrderDao;
import com.lifeshs.po.workOrder.WorkOrderPO;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.service1.vip.IVipUserService;
import com.lifeshs.service1.vip.IVipWorkOrderService;
import com.lifeshs.vo.workOrder.WorkOrderVO;

@Service("vipWorkOrderService")
public class VipWorkOrderServiceImpl implements IVipWorkOrderService {

	@Resource(name = "iVipWorkOrderDao")
	private IVipWorkOrderDao vipWorkOrderDao;

	@Resource(name = "vipUserService")
	private IVipUserService vipUserService;

	@Resource(name = "vipUserDao")
	private IVipUserDao vipUserDao;

	@Override
	public void subtractComboNumber(int userId, int comboId, int comboItemId) throws OperationException {
		int comboNumber = vipUserService.getComboNumberById(userId, comboId, comboItemId);
		if (comboNumber > 0) {
			comboNumber--;
			int result = vipWorkOrderDao.updateComboNumber(userId,comboId, comboItemId, comboNumber);
			if (result == 0)
				throw new OperationException("减少次数失败", ErrorCodeEnum.FAILED);
		} else {
			throw new OperationException("当前套餐次数已用完", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public void submitWorkOrder(WorkOrderPO workOrderPo) throws OperationException {
		int result = vipWorkOrderDao.addWorkOrder(workOrderPo);
		if (result == 0) {
			throw new OperationException("工单提交失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public Paging<WorkOrderVO> findWorkOrderList(int userId, int curPage, int pageSize) {
		Paging<WorkOrderVO> p = new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<WorkOrderVO>() {

			@Override
			public int queryTotal() {
				return vipWorkOrderDao.countWorkOrder(userId);
			}

			@Override
			public List<WorkOrderVO> queryData(int startRow, int pageSize) {
				return vipWorkOrderDao.findWorkOrderList(userId, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public void addComboNumber(int userId,int comboId, int comboItemId, int comboNumber) throws OperationException {
		int result = vipWorkOrderDao.addComboNumber(userId, comboId, comboItemId, comboNumber);
		if (result == 0) {
			throw new OperationException("添加失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public Integer findComboNumberById(int userId, int comboItemId) {
		return vipWorkOrderDao.findComboNumberById(userId, comboItemId);
	}
	
	@Override
    public Integer findComboNumberById(int userId, int comboId, int comboItemId) {
        return vipWorkOrderDao.findComboNumberById(userId, comboId, comboItemId);
    }

	@Override
	public void updateComboNumber(int userId, int comboId, int comboItemId, int comboNumber) throws OperationException {
		int result = vipWorkOrderDao.updateComboNumber(userId,comboId, comboItemId, comboNumber);
		if (result == 0) {
			throw new OperationException("更改失败", ErrorCodeEnum.FAILED);
		}
	}
}