package com.lifeshs.service.shop.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lifeshs.dao.shop.ShopOrderDao;
import com.lifeshs.pojo.page.PaginationDTO;
import com.lifeshs.service.shop.ShopOrderService;
import com.lifeshs.service1.page.IPagingQueryProc;
import com.lifeshs.service1.page.Paging;

@Service("shop_order_service")
@Transactional
public class ShopOrderServiceImpl implements ShopOrderService {
	
	@Autowired @Qualifier("shop_order_dao")
	ShopOrderDao orderDao;
	
	@Override
	public PaginationDTO<Map<String, Object>> getOrdersPagination(final Map<String, Object> search, Integer page, Integer pageSize) {
		Paging<Map<String, Object>> paging = new Paging<>(page, pageSize);
		paging.setQueryProc(new IPagingQueryProc<Map<String,Object>>() {
			
			@Override
			public int queryTotal() {
				return orderDao.getTotalOfOrders(search);
			}
			
			@Override
			public List<Map<String, Object>> queryData(int startRow, int pageSize) {
				search.put("startRow", startRow);
				search.put("pageSize", pageSize);
				return orderDao.getOrderList(search);
			}
		});
		PaginationDTO<Map<String, Object>> pDto = paging.getPagination();
		pDto.setPageSize(pageSize);
		return pDto;
	}

}
