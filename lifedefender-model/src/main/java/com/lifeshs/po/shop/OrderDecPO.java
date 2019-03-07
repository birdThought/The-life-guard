package com.lifeshs.po.shop;

import java.util.List;

import com.lifeshs.shop.OrderDecomposeDTO;

public class OrderDecPO extends OrderDecomposeDTO {
	
	private String pictureUrl;	
	
	private Integer skuId;
	
	

	public Integer getSkuId() {
		return skuId;
	}

	public void setSkuId(Integer skuId) {
		this.skuId = skuId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	

	
	
	
}
