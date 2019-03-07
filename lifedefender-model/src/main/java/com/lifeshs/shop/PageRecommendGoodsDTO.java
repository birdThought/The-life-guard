package com.lifeshs.shop;

import java.util.Date;

/**
 * 表: t_shop_page_goods
 * @author liu
 * @时间 2018年12月20日 上午11:58:24
 * @说明
 */
public class PageRecommendGoodsDTO {
	private Integer id;
	
	private String categoryName;
	
	private Integer sort;
	
	private Date endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
