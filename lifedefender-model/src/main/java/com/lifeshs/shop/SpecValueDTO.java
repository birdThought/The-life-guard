package com.lifeshs.shop;

/**
 * table: t_shop_spec_value
 * @author liu
 * @时间 2018年12月28日 上午10:02:03
 * @说明
 */
public class SpecValueDTO {
	private Integer id;
	
	private Integer specId;
	
	private String name;
	
	private Boolean used;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSpecId() {
		return specId;
	}

	public void setSpecId(Integer specId) {
		this.specId = specId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getUsed() {
		return used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}
	
}
