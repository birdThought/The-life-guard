package com.lifeshs.po.vip;

import lombok.Data;

import java.util.Date;

/**
 * vip会员
 * author: wenxian.cai
 * date: 2017/9/29 9:44
 */
public @Data class VipUserPO {

	/** 主键id */
	private int id;

	/** 用户id */
	private int userId;

	/** 有效期 */
	private Date endTime;

	/** 状态 （0_正常；1_过期）*/
	private int status;

	/** 会员vip套餐id */
	private Integer vipComboId;

	/** 创建时间 */
	private Date createDate;

	/** 修改时间 */
	private Date modifyDate;
}
