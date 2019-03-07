package com.lifeshs.po.business;

import lombok.Data;

import java.util.Date;

/**
 * 渠道商服务卡号
 * author: wenxian.cai
 * date: 2017/9/29 11:07
 */
public @Data class BusinessCardPO {

	/** 主键 */
	private int id;

	/** 渠道商id */
	private int businessId;

	/** 服务卡号 */
	private String code;

	/** 套餐id */
	private int vipComboId;

	/** 状态 */
	private int status;

	/** 创建时间 */
	private Date createDate;

	/** 修改时间 */
	private Date modifyDate;
}
