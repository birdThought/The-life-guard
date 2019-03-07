package com.lifeshs.po.shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
  
	private static final long serialVersionUID = -481137853571154203L;
	//商家id
    private Integer sellerId;
    //商家名称
    private String seller;

    //sku列表
    private List<SkuItem> orderItemList;

    public void newOrderItemList(){
    	orderItemList = new ArrayList<SkuItem>();
    }

    public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public List<SkuItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<SkuItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

	@Override
	public String toString() {
		return "Cart [sellerId=" + sellerId + ", seller=" + seller + ", orderItemList=" + orderItemList + "]";
	}
    
    
}
