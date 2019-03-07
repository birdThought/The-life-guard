package com.lifeshs.entity.order;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_order
 */
@Table(name = "t_order", schema = "")
@SuppressWarnings("serial")
public class TOrder implements Serializable{
    
    private Integer id;
    
    /**订单号*/
    private String orderNumber;
    
    /**用户ID(会员)*/
    private Integer userId;
    
    /**收费方式:0_免费，1_按次，2_按月，3_按年*/
    private Integer chargeMode;
    
    /**交易标题/订单标题*/
    private String subject;
    
    /**交易的内容描述*/
    private String body;
    
    /**价格*/
    private Integer price;
    
    /**数量*/
    private Integer number;
    
    /**费用*/
    private Integer charge;
    
    /**开始日期*/
    private java.util.Date startDate;
    
    /**结束日期*/
    private java.util.Date endDate;
    
    /**剩余次数*/
    private Integer timesRemaining;
    
    /**设备异常值和*/
    private Integer hasWarning;
    
    
    private java.util.Date warningDate;
    
    /**状态：待付款_1, 付款失败_2，有效_3，已完成_4，已取消_6，退款中_7，退款成功_8*/
    private Integer status;
    
    /**服务记录*/
    private String recard;
    
    /**创建时间*/
    private java.util.Date createDate;
    
    /**订单类型,1_服务订单,2_短信充值订单,3_其他待扩展订单*/
    private Integer orderType;
    
    /**服务师id*/
    private Integer orgUserId;
    
    /**服务项目唯一code*/
    private String projectCode;
    
    /**验证码*/
    private String verifyCode;
    
    /**服务ID*/
    private Integer serveId;
    
    /**项目图片*/
    private String projectImage;
    
    /**（上门服务）收货地址*/
    private String address;
    
    /**收货人名字*/
    private String receiverName;
    
    /**收货人电话*/
    private String receiverMobile;
    
    /**平台分成（百分比数字）*/
    private Integer profitShare;
    
    /**机构收入*/
    private Integer orgIncome;
    
    /**结算id*/
    private Integer statementId;
    
    /**是否已删除(0：未删除；1：已删除)*/
    private Boolean deleted;
    
    public TOrder() {
        super();
    }
    
    public TOrder(Integer id, String orderNumber, Integer userId, Integer chargeMode, String subject, String body, Integer price, Integer number, Integer charge, java.util.Date startDate, java.util.Date endDate, Integer timesRemaining, Integer hasWarning, java.util.Date warningDate, Integer status, String recard, java.util.Date createDate, Integer orderType, Integer orgUserId, String projectCode, String verifyCode, Integer serveId, String projectImage, String address, String receiverName, String receiverMobile, Integer profitShare, Integer orgIncome, Integer statementId, Boolean deleted) {
        super();
        this.id = id; 
        this.orderNumber = orderNumber; 
        this.userId = userId; 
        this.chargeMode = chargeMode; 
        this.subject = subject; 
        this.body = body; 
        this.price = price; 
        this.number = number; 
        this.charge = charge; 
        this.startDate = startDate; 
        this.endDate = endDate; 
        this.timesRemaining = timesRemaining; 
        this.hasWarning = hasWarning; 
        this.warningDate = warningDate; 
        this.status = status; 
        this.recard = recard; 
        this.createDate = createDate; 
        this.orderType = orderType; 
        this.orgUserId = orgUserId; 
        this.projectCode = projectCode; 
        this.verifyCode = verifyCode; 
        this.serveId = serveId; 
        this.projectImage = projectImage; 
        this.address = address; 
        this.receiverName = receiverName; 
        this.receiverMobile = receiverMobile; 
        this.profitShare = profitShare; 
        this.orgIncome = orgIncome; 
        this.statementId = statementId; 
        this.deleted = deleted; 
    }
    
    @Column(name ="id",nullable=true,length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name ="orderNumber",nullable=true,length=32)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    @Column(name ="userId",nullable=true,length=11)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name ="chargeMode",nullable=false,length=4)
    public Integer getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(Integer chargeMode) {
        this.chargeMode = chargeMode;
    }
    @Column(name ="subject",nullable=false,length=50)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    @Column(name ="body",nullable=false,length=200)
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    @Column(name ="price",nullable=false,length=11)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    @Column(name ="number",nullable=false,length=11)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    @Column(name ="charge",nullable=false,length=11)
    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }
    @Column(name ="startDate",nullable=false,length=10)
    public java.util.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.util.Date startDate) {
        this.startDate = startDate;
    }
    @Column(name ="endDate",nullable=false,length=10)
    public java.util.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.util.Date endDate) {
        this.endDate = endDate;
    }
    @Column(name ="timesRemaining",nullable=false,length=5)
    public Integer getTimesRemaining() {
        return timesRemaining;
    }

    public void setTimesRemaining(Integer timesRemaining) {
        this.timesRemaining = timesRemaining;
    }
    @Column(name ="hasWarning",nullable=false,length=11)
    public Integer getHasWarning() {
        return hasWarning;
    }

    public void setHasWarning(Integer hasWarning) {
        this.hasWarning = hasWarning;
    }
    @Column(name ="warningDate",nullable=false,length=10)
    public java.util.Date getWarningDate() {
        return warningDate;
    }

    public void setWarningDate(java.util.Date warningDate) {
        this.warningDate = warningDate;
    }
    @Column(name ="status",nullable=false,length=4)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Column(name ="recard",nullable=false,length=21845)
    public String getRecard() {
        return recard;
    }

    public void setRecard(String recard) {
        this.recard = recard;
    }
    @Column(name ="createDate",nullable=false,length=19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }
    @Column(name ="orderType",nullable=false,length=2)
    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    @Column(name ="orgUserId",nullable=false,length=11)
    public Integer getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(Integer orgUserId) {
        this.orgUserId = orgUserId;
    }
    @Column(name ="projectCode",nullable=false,length=32)
    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    @Column(name ="verifyCode",nullable=false,length=32)
    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
    @Column(name ="serveId",nullable=false,length=11)
    public Integer getServeId() {
        return serveId;
    }

    public void setServeId(Integer serveId) {
        this.serveId = serveId;
    }
    @Column(name ="projectImage",nullable=false,length=300)
    public String getProjectImage() {
        return projectImage;
    }

    public void setProjectImage(String projectImage) {
        this.projectImage = projectImage;
    }
    @Column(name ="address",nullable=false,length=500)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    @Column(name ="receiverName",nullable=false,length=50)
    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    @Column(name ="receiverMobile",nullable=false,length=50)
    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
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
    @Column(name ="statementId",nullable=false,length=11)
    public Integer getStatementId() {
        return statementId;
    }

    public void setStatementId(Integer statementId) {
        this.statementId = statementId;
    }
    @Column(name ="deleted",nullable=false,length=1)
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
    
}