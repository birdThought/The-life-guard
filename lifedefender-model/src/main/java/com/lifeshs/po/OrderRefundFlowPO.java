package com.lifeshs.po;

import java.util.Date;

import lombok.Data;

public @Data class OrderRefundFlowPO {
    private Integer id;

    private String orderNumber;

    private String realName;

    private Integer orgId;

    private String orgName;

    private String serveName;

    private Byte flowType;

    private String payDevice;

    private Byte payType;

    private Integer cost;

    private Integer payCost;

    private Boolean usePoints;

    private Integer points;

    private Integer pointsCost;

    private String payAccount;

    private String tradeNo;

    private String sellerAccount;

    private Integer profitShare;

    private Integer orgIncome;

    private Integer sysIncome;

    private Date createTime;

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }


    public void setServeName(String serveName) {
        this.serveName = serveName == null ? null : serveName.trim();
    }

    public void setPayDevice(String payDevice) {
        this.payDevice = payDevice == null ? null : payDevice.trim();
    }


    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount == null ? null : sellerAccount.trim();
    }
}