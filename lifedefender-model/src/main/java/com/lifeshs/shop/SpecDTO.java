package com.lifeshs.shop;

import java.util.List;

/**
 * table: t_shop_sepc
 * @author liu
 * @时间 2018年12月28日 上午10:01:23
 * @说明
 */
public class SpecDTO {
	private Integer id;	
	
	private Integer goodsId;
	
	private String name;
	
	private Integer usedNum;
	
	private List<SpecValueDTO> specValues;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getUsedNum() {
		return usedNum;
	}
	
	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}
	
	public List<SpecValueDTO> getSpecValues() {
		return specValues;
	}

	public void setSpecValues(List<SpecValueDTO> specValues) {
		this.specValues = specValues;
	}
	
}
