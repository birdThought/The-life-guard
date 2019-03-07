package com.lifeshs.vo.order.reportAnalysis;

import com.lifeshs.po.UserPO;
import com.lifeshs.po.user.ReportAnalysisPO;
import lombok.Data;

import java.util.Date;

/**
 * 分析报告订单VO
 * author: wenxian.cai
 * date: 2017/10/10 11:20
 */

@Data
public class ReportAnalysisOrderVO {

	/** 主键 */
	private int id;

	/** 订单id */
	private String orderNumber;

	/** 诊所用户id */
	private int businessUserId;

	/** 诊所用户姓名*/
	private String businessName;

	/** 状态 */
	private int status;

	/** 价格 */
	private double price;

	/** 创建日期 */
	private Date createDate;

	/** 修改日期 */
	private Date modifyDate;

	/** 分析报告po */
	private ReportAnalysisPO reportAnalysisPO;

	/** 用户po*/
	private UserPO userPO;
}
