package com.lifeshs.po.drugs;

import java.util.List;

import lombok.Data;

/**
 * 
 *  订单推送
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年6月26日 上午9:40:44
 */
@Data
public class OrderPushPO {
    private String externalOrderNo;//健客订单编号
    private String deliveryZipCode;//邮编
    private String invoice;//发票信息invoice的话根据用户有无填写，没有填写的话 可以传 “”
    private String orderNotes;//订单备注                                                                         *非必填
    private int money;//订单金额单位：分
    private String orderTime;//下单时间 
    private int accountId;  //收货人id
    private String consignee;//收件人
    private String telephone;//联系电话
    private String mobilephone;//收货人手机
    private String province;//收货省
    private String city;//收货市
    private String district;//收货区
    private String town;//收货乡镇或者街道
    private String address;//收货详细地址
    private String paymentType;//支付类型1支付宝，2微信，3网银在线，4货到付款       *非必填
    private int transportCosts;//运费单位：分                                                                     *非必填
    private int status;//订单状态 1未支付，2已支付
    private List<OrderProductPushPO> orderProductList;//订单产品列表
}
