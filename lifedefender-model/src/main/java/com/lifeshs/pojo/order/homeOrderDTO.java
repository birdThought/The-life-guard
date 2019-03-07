package com.lifeshs.pojo.order;

import lombok.Data;

import java.util.Date;

/**
 * 主页专用
 *
 * @author wenxian.cai
 * @create 2017-06-29 11:14
 **/

public @Data class homeOrderDTO {

    /**创建日期*/
   private Date createDate;

    /**订单编号*/
    private String orderNumber;

    /**项目名称*/
    private String projectName;

    /**用户名称*/
    private String userName;

    /** 订单总额*/
    private Double charge;
}
