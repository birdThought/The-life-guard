package com.lifeshs.po;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;

/**
 *  自定义订单
 * Created by Administrator on 2017/12/7.
 */
public @Data class CustomOrderPo {

    private Integer id;
    private String productName;
    private String productSpec;
    private Integer productPrice;
    private Integer userId;
    private Integer addressId;
    @JSONField(format="yyyy-MM-dd")
    private Date createDate;
    private String orderNumber;
    private String physCode;
    private String userCode;
    private Integer status;
    private Integer payType;
    private Integer payCost;
    private String payAccount;
    private String sellerAccount;
    private Integer cost;

}
