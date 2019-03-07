package com.lifeshs.shop;

import java.util.Date;
import java.util.List;

import lombok.Data;

/*
 * 商品表(t_shop_goods)
 * Founder: jiang chang bin   2018/11/7 10:43
 * com.lifeshs.stores.GoodsDTO
 */
public @Data class GoodsDTO {
   
    //编号
	private Integer id;

	//商铺ID
	private Integer shopId;
	
	// 类目id
	private Integer categoryId;
	
	//商品编号
	private String goodsCode;
	
	//商品名称
	private String goodsName;
	
	//通用名
	private String commonTitle;
	
	//状态【待上架、已上架、已下架】
	private Integer status;
	
	//标签ID
	private String labelIds;
	
	//评论数
	private Integer commentNum;
	
	//说明书
	private String instructions;
	
	//创建时间
	private Date createTime;
			
	//最后调整状态时间
	private Date stateTime;
	
	private String details;
	
	// 统一规格或多规格,spec_type,1(统一规格,默认值),2(多规格)
	private Integer specType = 1;
	
	// 市场价格:market_price
	private Double marketPrice;
	
	// 优惠价格:favorable_price
	private Double favorablePrice;
	
	// 库存:inventory
	private Integer inventory;

	// 条形码
	private String braCode;
	
	// 销量:sales_volume
	private Integer salesVolume;
	
	// 是否限购
	private Integer limitBuy;
	
	// 所在地
	private String areaCode;
	
	// 发票
	private Integer invoice;
	
	// 保修
	private Integer warranty;
	
	private CategoryDTO category;
	
	/*
	 * 主图
	 */
	private String mainPic;
	
	// 其他图片,使用'|'分割, otherPics.split("\\|");
	private String otherPics;
	
	/*
	 * 商品SKU表       的价格
	 */
	private SkuDTO getSku;
	
	/*
	 * 商品基本属性值表(t_shop_basic_attribute)
	 */
	private List<BasicAttributeDTO> getBasicAttribute;
	
	/*
	 * 商铺表(t_shop)
	 */
	private ShopDTO getShop;
	
	/*
	 * 商品和属性筛选表(t_shop_attribute_screen)
	 */
	private  ScreenDTO getScreen;
	
	/*
	 * 商品和属性关系表(t_shop_attribute_relation)
	 * getRelation
	 */
	private List<RelationDTO> getRelation;
}
