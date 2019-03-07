package com.lifeshs.po.business;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 渠道商结算记录
 * 
 * @author zizhen.huang
 * @DateTime 2018年1月5日16:50:12
 */
@Data
public class BusinessAccountPO implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private Integer id;
    /** 渠道商id */
    private Integer businessId;
    /** 月份 */
    private Date months;
    /** 消费次数 */
    private Integer number;
    /** 消费金额 */
    private Integer price;
    /** 实付金额 */
    private Integer payMoney;
    /** 状态(未支付_1, 已支付_2) */
    private Integer status;
    /** 成分比率 */
    private Integer percentage;
    /* 结算金额 */
    private Integer settleAccounts;
    /* 结算时间 */
    private Date closeDate;
}
