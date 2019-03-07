package com.lifeshs.po.order;


import lombok.Data;

import java.util.Date;

/**
 * 分析报告实体
 * author: wenxian.cai
 * date: 2017/10/10 16:02
 */
@Data
public class ReportAnalysisOrderPO {

	/** 主键 */
	private int id;

	/** 订单id */
	private String orderNumber;

	/** 用户id */
	private int userId;

	/** 诊所用户id */
	private int businessUserId;

	/** 状态 */
	private int status;

	/** 价格 */
	private double price;

	/** 创建日期 */
	private Date createDate;

	/** 修改日期 */
	private Date modifyDate;

	/** 分析报告id*/
	private int userReportAnalysisId;

	/** 交易标题/订单标题 */
	private String subject;

	/** 交易的内容描述 */
	private String body;
}
