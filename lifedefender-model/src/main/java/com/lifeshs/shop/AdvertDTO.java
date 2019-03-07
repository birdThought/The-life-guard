package com.lifeshs.shop;

import java.util.Date;

/**
 * table: t_shop_advertising_image
 * @author liu
 * @时间 2018年12月24日 上午9:59:10
 * @说明 商城首页广告
 */
public class AdvertDTO {
	private Integer id;
	
	private String image;
	
	private Integer goodsId;
	
	private Integer sort;
	
	private Integer status;
	
	private Integer shopId;
	
	private Integer userId;
	
	private Date createTime;
	
	private String showTime;
	
	private Integer advertType;
	
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public Integer getAdvertType() {
		return advertType;
	}

	public void setAdvertType(Integer advertType) {
		this.advertType = advertType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
