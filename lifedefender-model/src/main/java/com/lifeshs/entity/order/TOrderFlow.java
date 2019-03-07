package com.lifeshs.entity.order;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_order_flow
 */
@Table(name = "t_order_flow", schema = "")
@SuppressWarnings("serial")
public class TOrderFlow implements Serializable{
	/**交易流水*/
	private Integer id;
	
	/**订单号*/
	private String orderNumber;
	
	/**用户姓名*/
	private String realName;
	
	/**机构ID*/
	private Integer orgId;
	
	/**机构名称*/
	private String orgName;
	
	/**服务名称*/
	private String serveName;
	
	/**流水类型：收款_1，退款_2*/
	private Integer flowType;
	
	/**支付终端：app,web*/
	private String payDevice;
	
	/**支付类型：支付宝_1, 微信_2, 网银在线_3*/
	private Integer payType;
	
	/**总金额*/
	private Integer cost;
	
	/**支付金额*/
	private Integer payCost;
	
	/**是否使用积分(预留)*/
	private Boolean usePoints;
	
	/**积分*/
	private Integer points;
	
	/**积分金额*/
	private Integer pointsCost;
	
	/**付款帐户*/
	private String payAccount;
	
	/**支付平台的交易流水*/
	private String tradeNo;
	
	/**收款帐户*/
	private String sellerAccount;
	
	/**平台分成(百分比数字)*/
	private Integer profitShare;
	
	/**机构分成收入*/
	private Integer orgIncome;
	
	/**平台分成收入*/
	private Integer sysIncome;
	
	/**创建时间*/
	private Date createTime;
	
	public TOrderFlow() {
		super();
	}
	
	public TOrderFlow(Integer id, String orderNumber, String realName, Integer orgId, String orgName, String serveName, Integer flowType, String payDevice, Integer payType, Integer cost, Integer payCost, Boolean usePoints, Integer points, Integer pointsCost, String payAccount, String tradeNo, String sellerAccount, Integer profitShare, Integer orgIncome, Integer sysIncome, Date createTime) {
		super();
		this.id = id; 
		this.orderNumber = orderNumber; 
		this.realName = realName; 
		this.orgId = orgId; 
		this.orgName = orgName; 
		this.serveName = serveName; 
		this.flowType = flowType; 
		this.payDevice = payDevice; 
		this.payType = payType; 
		this.cost = cost; 
		this.payCost = payCost; 
		this.usePoints = usePoints; 
		this.points = points; 
		this.pointsCost = pointsCost; 
		this.payAccount = payAccount; 
		this.tradeNo = tradeNo; 
		this.sellerAccount = sellerAccount; 
		this.profitShare = profitShare; 
		this.orgIncome = orgIncome; 
		this.sysIncome = sysIncome; 
		this.createTime = createTime; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="orderNumber",nullable=false,length=22)
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Column(name ="realName",nullable=false,length=15)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	@Column(name ="orgId",nullable=false,length=11)
	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	@Column(name ="orgName",nullable=false,length=30)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name ="serveName",nullable=false,length=20)
	public String getServeName() {
		return serveName;
	}

	public void setServeName(String serveName) {
		this.serveName = serveName;
	}
	@Column(name ="flowType",nullable=false,length=4)
	public Integer getFlowType() {
		return flowType;
	}

	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}
	@Column(name ="payDevice",nullable=false,length=3)
	public String getPayDevice() {
		return payDevice;
	}

	public void setPayDevice(String payDevice) {
		this.payDevice = payDevice;
	}
	@Column(name ="payType",nullable=false,length=4)
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	@Column(name ="cost",nullable=false,length=11)
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	@Column(name ="payCost",nullable=false,length=11)
	public Integer getPayCost() {
		return payCost;
	}

	public void setPayCost(Integer payCost) {
		this.payCost = payCost;
	}
	@Column(name ="usePoints",nullable=false,length=1)
	public Boolean getUsePoints() {
		return usePoints;
	}

	public void setUsePoints(Boolean usePoints) {
		this.usePoints = usePoints;
	}
	@Column(name ="points",nullable=false,length=11)
	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	@Column(name ="pointsCost",nullable=false,length=11)
	public Integer getPointsCost() {
		return pointsCost;
	}

	public void setPointsCost(Integer pointsCost) {
		this.pointsCost = pointsCost;
	}
	@Column(name ="payAccount",nullable=false,length=50)
	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	@Column(name ="tradeNo",nullable=false,length=35)
	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	@Column(name ="sellerAccount",nullable=false,length=50)
	public String getSellerAccount() {
		return sellerAccount;
	}

	public void setSellerAccount(String sellerAccount) {
		this.sellerAccount = sellerAccount;
	}
	@Column(name ="profitShare",nullable=false,length=11)
	public Integer getProfitShare() {
		return profitShare;
	}

	public void setProfitShare(Integer profitShare) {
		this.profitShare = profitShare;
	}
	@Column(name ="orgIncome",nullable=false,length=11)
	public Integer getOrgIncome() {
		return orgIncome;
	}

	public void setOrgIncome(Integer orgIncome) {
		this.orgIncome = orgIncome;
	}
	@Column(name ="sysIncome",nullable=false,length=11)
	public Integer getSysIncome() {
		return sysIncome;
	}

	public void setSysIncome(Integer sysIncome) {
		this.sysIncome = sysIncome;
	}
	@Column(name ="createTime",nullable=false,length=19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}