package com.lifeshs.vo.order.vip;

import lombok.Data;

/**
 * 渠道商账单
 * author: wenxian.cai
 * date: 2017/11/1 16:34
 */
@Data
public class BusinessOrderBillVO {

	/*总分成*/
	private Integer Income;

	/*总应付服务卡金额*/
	private Integer payForCard;

	/*总结算*/
	private Integer learning;
}
