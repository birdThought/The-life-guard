package com.lifeshs.shop;

import lombok.Data;

/*
 * 商品和属性关系表(t_shop_attribute_relation)
 * Founder: jiang chang bin   2018/11/8 17:23
 */
public @Data class ScreenDTO {

	    //编号
		private Integer id;

		//商品ID
		private Integer goodsId;
		
		//商品具有的属性值编号
		private String attributeVId;
		
		//属性值表(t_shop_attribute_value)
		private AttributeValueDTO getAttributeValue;
	
}
