package com.lifeshs.shop;

import lombok.Data;

public @Data class PageGoodsDTO {
	
	private Integer id;  //id
	private Integer category_id;  //类目id
	private String category_name;	//类目名称
	private Integer sort;	//优先级
	
	

}
