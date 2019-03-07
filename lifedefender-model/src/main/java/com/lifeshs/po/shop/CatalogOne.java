package com.lifeshs.po.shop;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

public @Data class CatalogOne {
	
	private Integer id ;
	private String cName;
	private Integer sort;
	
	private List<CatalogTwo> catalogTwos;

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
	
	
	
}
