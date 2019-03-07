package com.lifeshs.po.shop;

import java.util.List;

import com.lifeshs.shop.ShopDTO;
import com.lifeshs.shop.SkuDTO;

public class SearchPo {
	
	private List<ShopDTO> shopList;
	private List<SearchSkuDisplayPO> skuList;
	
	
	public List<ShopDTO> getShopList() {
		return shopList;
	}
	public void setShopList(List<ShopDTO> shopList) {
		this.shopList = shopList;
	}
	public List<SearchSkuDisplayPO> getSkuList() {
		return skuList;
	}
	public void setSkuList(List<SearchSkuDisplayPO> skuList) {
		this.skuList = skuList;
	}
	
	
	
}
