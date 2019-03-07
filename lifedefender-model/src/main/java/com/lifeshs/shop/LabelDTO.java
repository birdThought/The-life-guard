package com.lifeshs.shop;


import lombok.Data;

/*
 * 标签表（t_shop_label）
 * Founder: jiang chang bin   2018/11/7 11:06
 * com.lifeshs.stores.LabelDTO
 */
public @Data class LabelDTO {
	
	    //编号
		private Integer id;

		//标签名称
		private String labelName;

		//优先级
		private Integer sort;
		
		//图标
		private String icon;
		
		
		
}
