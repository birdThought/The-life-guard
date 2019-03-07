package com.lifeshs.po.shop;

import java.util.List;

public class OrderShopPO {
	
	private Integer shopId;
	
	private String shopName;
	
	private Integer status;
	
	private String shippingNo;
	
	
	private List<OrderDecPO> orderList;
	
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public List<OrderDecPO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderDecPO> orderList) {
		this.orderList = orderList;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	
}
