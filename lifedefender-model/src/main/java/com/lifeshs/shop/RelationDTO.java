package com.lifeshs.shop;

import java.util.List;

import lombok.Data;

/*
 * 商品和属性关系表(t_shop_attribute_relation)
 * Founder: jiang chang bin   2018/11/8 17:23
 */
public @Data class RelationDTO {

	 //编号
	private Integer id;

	//商品ID
	private Integer goodsId;
	
	//属性名ID
	private Integer attributeId;
	
	//属性值ID
	private Integer attributeVId;
	
	/*
	 * 商品表(t_shop_goods)
	 */
	private List<GoodsDTO> getGoods;
	
	/*
	 * 属性值表(t_shop_attribute_value) getAttributeValue
	 */
	private List<AttributeValueDTO> getAttributeValue;
}
