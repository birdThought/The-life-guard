package com.lifeshs.shop;

import java.util.Date;

import lombok.Data;

/*
 * 订单表(t_shop_order)
 * Founder: jiang chang bin   2018/11/7 10:30
 */
public @Data class OrderDTO {

	//编号
	private Integer id;

	//订单编号
	private String orderNo;

	//用户ID
	private Integer userId;
	
	//发票信息
	private String invoice;

	//订单状态1未支付，2已支付，3已完成
	private Integer status;

	//商品总额
	private Double money;

	//商铺ID
	private Integer shopId;
	
	//收件人地址
	private String addressId;

	//创建时间
	private Date createTime;

	//修改时间
	private Date orderTime;
	
	//支付类型(微、支)
	private Integer paymentType;

	//订单备注
	private String orderNotes;

	//运费
	private Double transportCosts;

	//快递单号
	private String shippingNo;
	
}
