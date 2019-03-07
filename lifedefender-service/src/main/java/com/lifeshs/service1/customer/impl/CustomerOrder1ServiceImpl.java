package com.lifeshs.service1.customer.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.dao1.customer.ICustomerOrderDao;
import com.lifeshs.po.customer.CustomerOrderPo;
import com.lifeshs.po.customer.UpdateOrderPo;
import com.lifeshs.service1.customer.CustomerOrder1Service;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;


/**
 * 客服工单服务实现
 * @author shiqiang.zeng
 * @Date 2017.12.21
 */
@Service("customerorder1Service")
public class CustomerOrder1ServiceImpl implements CustomerOrder1Service{
	
	@Autowired
	ICustomerOrderDao customerOrderDao;
	
	@Override
	public void updateOrder(int id,UpdateOrderPo updateOrderPo) throws OperationException {
		updateOrderPo.setStatus(1);
		CustomerOrderPo customerOrderVo=customerOrderDao.getOrder(id);
		if(customerOrderVo == null)
			throw new OperationException("工单不存在", ErrorCodeEnum.NOT_FOUND);
		if(customerOrderVo.getStatus().intValue() == 1)
			throw new OperationException("订单已经提交成功,不可更改", ErrorCodeEnum. REPEAT);
		try {
			customerOrderDao.updateOrder(id,updateOrderPo);
		} catch (Exception e) {
			throw new OperationException("客服工单:更新失败", ErrorCodeEnum.FAILED);
		}
	}

	@Override
	public int countOrder() {
	
		return customerOrderDao.countOrder();
	}

	@Override
	public int countNotOrder( int status) {
		
		return customerOrderDao.countNotOrder( status);
	}

	@Override
	public int countSuccessOrder( int status) {
		
		return countSuccessOrder( status);
	}

	@Override
	public CustomerOrderPo getOrder(int id) {
		
		return customerOrderDao.getOrder(id);
	}

	@Override
	public Paging<CustomerOrderPo> findOrderList(int curPage, int pageSize) {
		Paging<CustomerOrderPo> p=new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<CustomerOrderPo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return customerOrderDao.countOrder();
			}

			@Override
			public List<CustomerOrderPo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return customerOrderDao.findOrderList(startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public Paging<CustomerOrderPo> findNotOrderList(int status, int curPage, int pageSize) {
		Paging<CustomerOrderPo> p=new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<CustomerOrderPo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return customerOrderDao.countNotOrder(status);
			}

			@Override
			public List<CustomerOrderPo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return customerOrderDao.findNotOrderList(status, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public Paging<CustomerOrderPo> findSuccessList(int status, int curPage, int pageSize) {
		Paging<CustomerOrderPo> p=new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<CustomerOrderPo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return customerOrderDao.countSuccessOrder(status);
			}

			@Override
			public List<CustomerOrderPo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return customerOrderDao.findSuccessOrderList(status, startRow, pageSize);
			}
		});
		return p;
	}

	@Override
	public Paging<CustomerOrderPo> findFailList(int status, int curPage, int pageSize) {
		Paging<CustomerOrderPo> p=new Paging<>(curPage, pageSize);
		p.setQueryProc(new IPagingQueryProc<CustomerOrderPo>() {

			@Override
			public int queryTotal() {
				// TODO Auto-generated method stub
				return customerOrderDao.countFailOrder(status);
			}

			@Override
			public List<CustomerOrderPo> queryData(int startRow, int pageSize) {
				// TODO Auto-generated method stub
				return customerOrderDao.findFailOrderList(status, startRow, pageSize);
			}
		});
		return p;
	}



}
