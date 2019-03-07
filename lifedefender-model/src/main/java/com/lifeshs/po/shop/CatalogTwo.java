package com.lifeshs.po.shop;

import java.util.ArrayList;
import java.util.List;

import com.lifeshs.dto.manager.drugs.DrugsClassNode;

import lombok.Data;

public @Data class CatalogTwo {

	private Integer id1;
	private String cName1;	
	private Integer sort1;
	
	
	public String getcName1() {
		return cName1;
	}


	public void setcName1(String cName1) {
		this.cName1 = cName1;
	}


	private List<CatalogThree>CatalogThree;
	
	
}
