package com.lifeshs.entity.order;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by  自定义订单
 * Administrator on 2017/12/7.
 */

@Table(name = "t_privatr_order", schema = "")
@SuppressWarnings("serial")
public class TCustomOrder  implements Serializable{

    /** 自定义订单id */
    private Integer id;

    /** 产品名称 */
    private String productName;

    /** 产品规格*/
    private Integer productSpec;

    /** 产品价格*/
    private Integer productPrice;

    /** 收货地址*/
    private String detaAddress;

    /** 收货人姓名*/
    private String receiverName;

    /** 收货人电话*/
    private String receiverMobile;

    /** 创建时间*/
    private java.util.Date createDate;

    /** 医师编号*/
    private String physCode;

    /** 用户编号*/
    private String userCode;

    /** 订单状态*/
    private Integer state;

    /** 订单编号*/
    private String  orderNumber;
    /** 支付方式*/
    private Integer payType;
    /** 付款帐户支付金额*/
    private Integer payCost;
    /** 付款帐户*/
    private String  payAccount;
    /** 收款帐户*/
    private String  sellerAccount;
    /** 总金额*/
    private Integer cost;

    public TCustomOrder() {
        super();
    }

    public TCustomOrder(Integer id, String  productName, Integer productSpec, Integer productPrice, String detaAddress, String receiverName,
                        String  receiverMobile, java.util.Date createDate, String physCode, String userCode, Integer state,String  orderNumber,Integer payType,Integer payCost,String  payAccount
        , String sellerAccount,Integer cost) {
        super();
        this.id = id;
        this.productName = productName;
        this.productSpec = productSpec;
        this.productPrice = productPrice;
        this.detaAddress = detaAddress;
        this.receiverName = receiverName;
        this.receiverMobile = receiverMobile;
        this.createDate = createDate;
        this.physCode = physCode;
        this.userCode = userCode;
        this.state = state;
        this.orderNumber = orderNumber;
        this.payType = payType;
        this.payCost = payCost;
        this.payAccount = payAccount;
        this.sellerAccount = sellerAccount;
        this.cost = cost;

    }

    @Column(name ="id",nullable=true,length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name ="productName",nullable=false,length=20)
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @Column(name ="productSpec",nullable=false,length=1)
    public Integer getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(Integer productSpec) {
        this.productSpec = productSpec;
    }
    @Column(name ="productPrice",nullable=false,length=11)
    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }
    @Column(name ="detaAddress",nullable=false,length=100)
    public String getDetaAddress() {
        return detaAddress;
    }

    public void setDetaAddress(String detaAddress) {
        this.detaAddress = detaAddress;
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
    @Column(name ="createDate",nullable=false,length=10)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }
    @Column(name ="physCode",nullable=false,length=6)
    public String getPhysCode() {
        return physCode;
    }

    public void setPhysCode(String  physCode) {
        this.physCode = physCode;
    }
    @Column(name ="userCode",nullable=false,length=6)
    public String  getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    @Column(name ="state",nullable=false,length=1)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    @Column(name ="orderNumber",nullable=true,length=50)
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    @Column(name ="payType",nullable=true,length=1)
    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    @Column(name ="payCost",nullable=true,length=11)
    public Integer getPayCost() {
        return payCost;
    }

    public void setPayCost(Integer payCost) {
        this.payCost = payCost;
    }
    @Column(name ="payAccount",nullable=true,length=50)
    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }
    @Column(name ="sellerAccount",nullable=true,length=50)
    public String getSellerAccount() {
        return sellerAccount;
    }

    public void setSellerAccount(String sellerAccount) {
        this.sellerAccount = sellerAccount;
    }
    @Column(name ="cost",nullable=true,length=11)
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
