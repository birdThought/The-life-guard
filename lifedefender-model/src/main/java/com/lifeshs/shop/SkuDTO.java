package com.lifeshs.shop;

import lombok.Data;

/*
 * 商品SKU(t_shop_sku)
 * Founder: jiang chang bin   2018/11/7 10:56
 */
public @Data class SkuDTO {

    //编号
	private Integer id;

	//商品ID
	private String goodsId;
	

	//SKU属性
	private String skuAttribute;

	//SKU属性
	private String groupSpec;

	
	//市场价
	private Double marketPrice;
	
	//优惠价
	private Double favorablePrice;
	
	//库存
	private Integer inventory;
	
	private String braCode;
	
	//SKU销量
	private Integer salesVolume;
	

	private String group_spec;
	
	

	// 图片地址
	private String picture;
	

}
