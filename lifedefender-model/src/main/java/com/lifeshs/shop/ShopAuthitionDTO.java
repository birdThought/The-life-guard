package com.lifeshs.shop;

import java.util.Date;

/**
 * 商铺审核
 * @author liu
 *
 */
public class ShopAuthitionDTO {
	private Integer id;
	
	private Integer shopId;
	
	private Integer pass;
	
	private String remarks;
	
	private Integer userId;
	
	private Date authitTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getPass() {
		return pass;
	}

	public void setPass(Integer pass) {
		this.pass = pass;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getAuthitTime() {
		return authitTime;
	}

	public void setAuthitTime(Date authitTime) {
		this.authitTime = authitTime;
	}
	
}
