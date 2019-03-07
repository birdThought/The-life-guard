package com.lifeshs.po.drugs;

import java.util.List;

import lombok.Data;

@Data
public class OrderLogisticsPO {

    private int id;
    private String orderNo;//订单编号
    private String invoice;//发票信息invoice的话根据用户有无填写，没有填写的话 可以传 “”
    private String orderNotes;//订单备注                                                                         *非必填
    private int money;//订单金额单位：分
    private String orderTime;//下单时间 
    private String externalOrderNo;//健客订单编号
    private int userId;  //收货人id
    private String consignee;//收件人
    private int addressId; //用户地址表id
    private String createDate; //创建订单时间
    private String physCode; //医生编号
    private String paymentType;//支付类型1支付宝，2微信，3网银在线，4货到付款       *非必填
    private int transportCosts;//运费单位：分                                                                     *非必填
    private int status;//订单状态 1未支付，2已支付
    private int payCost; //付款帐户支付金额
    private String payAccount; //付款帐户
    private String sellerAccount; //收款帐户
    private String clinicalDiagnosis;//临床诊断
    private String doctorAdvice;  //医嘱
    
    private String shippingCode; //快递公司编码
    private String shippingName; //快递公司名称
    private String shippingNo;   //快递单号
    
    private List<OrderProductPO> orderProductList;//订单产品列表
    private List<LogisticsPO> logisticsList;      //物流信息
}
