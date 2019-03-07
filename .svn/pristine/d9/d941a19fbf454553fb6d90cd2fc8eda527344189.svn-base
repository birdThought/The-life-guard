package com.lifeshs.po.drugs;

import java.util.List;

import lombok.Data;

/**
 * 
 *  订单详情
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年6月7日 上午11:58:30
 */
@Data
public class OrderPO {
    private int id;
    private String orderNo;//订单编号
    private String invoice;//发票信息invoice的话根据用户有无填写，没有填写的话 可以传 “”
    private String orderNotes;//订单备注                                                                         *非必填
    private int money;//订单金额单位：分
    private String orderTime;//下单时间 
    private String externalOrderNo;//健客订单编号
    private int userId;  //收货人id
//    private int addressId; //用户地址表id
    private String consignee;//收件人
    private String mobilephone; //收件人手机号码
    private String town; //收货乡镇或者街道
    private String address; //收货详细地址
    private String createDate; //创建订单时间
    private String physCode; //医生编号
    private int paymentType;//支付类型1支付宝，2微信，3网银在线，4货到付款       *非必填
    private int transportCosts;//运费单位：分                                                                     *非必填
    private int status;//订单状态 1未支付，2已支付
    private String shippingNo; //快递单号
    private int payCost; //付款帐户支付金额
    private String payAccount; //付款帐户
    private String sellerAccount; //收款帐户
    private String clinicalDiagnosis;//临床诊断
    private String doctorAdvice;  //医嘱
//    private int orderType; //订单类型
    private List<OrderProductPO> orderProductList;//订单产品列表
}
