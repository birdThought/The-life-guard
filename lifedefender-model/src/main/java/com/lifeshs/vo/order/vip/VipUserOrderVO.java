package com.lifeshs.vo.order.vip;


import lombok.Data;

import java.util.Date;

/**
 * vip订单VO
 * author: wenxian.cai
 * date: 2017/11/1 15:38
 */
@Data
public class VipUserOrderVO {

	private Integer id;
	/** 订单编号 */
	private String orderNumber;
	/** 用户id */
	private Integer userId;
	/** 用户账号 */
	private String userName;
	/** 用户真实姓名 */
	private String realName;
	/** 价格(分) */
	private Integer price;
	/** 渠道商分成*/
	private Integer businessIncome;
	/** 订单类型,1_会员付费,2_邀请码 */
	private Integer type;
	/** 是否已删除 */
	private Boolean deleted;
	/** 创建日期 */
	private Date createDate;
	/** 修改日期 */
	private Date modifyDate;


}
