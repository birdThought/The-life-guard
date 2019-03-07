package com.lifeshs.shop;

import lombok.Data;

/*
 * 订单拆单表(t_shop_order_decompose)
 * Founder: jiang chang bin   2018/11/7 10:30
 */
public @Data class OrderDecomposeDTO {

	//编号
	private Integer id;

	//订单编号
	private String orderNo;
	
	//商铺Id
	private Integer shopId;
	
	//商铺名称
	private String shopName;
	
	//商品名称
	private String goodsNam;
	
	//商品Id
	private String goodsId;

	//商品名称
	private String goodsName;
	
	//订单状态1未支付，2已支付，3已完成,4运送中
	private Integer status;
	
	//商品总额
	private Double amount;
	
	//实付总额
	private Double discount_amount;
		
	//订单备注
	private String orderNotes;
	
	//运费
	private Double transportCosts;
	
	//快递单号
	private String shippingNo;
	
	//创建时间
	/*private Date createTime;*/
	
	//发票信息
	private String invoice;
	
	//属性
	private String attributeValue;
	


	//商品建议价
	private Double suggestedPrice;

	//商品优惠价
	private Double favorablePrice;



	
	// 商品数量
	private Integer num;
	
	
	
	//商品价格
	private Double price;
	
	//实付总额
	private Double discountAmount;
	
	
	
}
