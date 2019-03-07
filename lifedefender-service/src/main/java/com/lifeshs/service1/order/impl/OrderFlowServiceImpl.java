package com.lifeshs.service1.order.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lifeshs.dao1.order.IOrderFlowDao;
import com.lifeshs.service1.order.OrderFlowService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;
import com.lifeshs.vo.order.OrderFlowVO;

@Service("orderFlowService")
public class OrderFlowServiceImpl implements OrderFlowService {

    @Resource(name = "iOrderFlowDao")
	private IOrderFlowDao orderFlowDao;

	@Override
	public Paging<OrderFlowVO> findOrderFlowList(String userNo, String orgName, String realName, String orderNumber, String serveName,
			Date startDate, Date endDate, int curPage, int pageSize) {
		Paging<OrderFlowVO> paging = new Paging<>(curPage, pageSize);
		paging.setQueryProc(new IPagingQueryProc<OrderFlowVO>() {
			
			@Override
			public int queryTotal() {
				return orderFlowDao.getTotalRecord(userNo, orgName, realName, orderNumber, serveName, startDate, endDate);
			}
			
			@Override
			public List<OrderFlowVO> queryData(int startRow, int pageSize) {
				return orderFlowDao.findOrderFlowList(userNo, orgName, realName, orderNumber, serveName, startDate, endDate, startRow, pageSize);
			}
		});
		return paging;
	}

	@Override
	public OrderFlowVO getOrderFlowById(Integer id) {
		return orderFlowDao.getOrderFlowById(id);
	}
}
