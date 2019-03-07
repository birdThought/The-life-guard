package com.lifeshs.vo.order;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 交易流水
 * @author zizhen.huang
 * @DateTime 2018年1月29日11:21:59
 */
@Data
public class OrderFlowVO implements Serializable {

	private static final long serialVersionUID = 1L;
	/** 主键 */
    private Integer id;
    /** 订单号 */
    private String orderNumber;
    /** 用户姓名 */
    private String realName;
    /** 机构ID */
    private Integer orgId;
    /** 机构名称 */
    private String orgName;
    /** 服务名称 */
    private String serveName;
    /** 流水类型：收款_1，退款_2 */
    private Integer flowType;
    /** 支付终端：APP,web */
    private String payDevice;
    /** 支付类型：支付宝_1, 微信_2, 网银在线_3 */
    private Integer payType;
    /** 总金额 */
    private Integer cost;
    /** 支付金额 */
    private Integer payCost;
    /** 是否使用积分(预留) */
    private Integer usePoints;
    /** 积分 */
    private Integer points;
    /** 积分金额 */
    private Integer pointsCost;
    /** 付款帐户 */
    private String payAccount;
    /** 支付平台的交易流水 */
    private String tradeNo;
    /** 收款帐户 */
    private String sellerAccount;
    /** 平台分成(百分比数字) */
    private Integer profitShare;
    /** 机构分成收入 */
    private Integer orgIncome;
    /** 平台分成收入 */
    private Integer sysIncome;
    /** 创建时间 */
    private Date createTime;
}
