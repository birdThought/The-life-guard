package com.lifeshs.service.shop;

import java.util.Map;

import com.lifeshs.pojo.page.PaginationDTO;

public interface ShopOrderService {
	PaginationDTO<Map<String, Object>> getOrdersPagination(Map<String, Object> search, Integer page, Integer pageSize);
}
