package com.lifeshs.pojo.order;

import com.lifeshs.pojo.org.service.OrgServiceDTO;
import lombok.Data;

import java.util.Date;

/**
 * 服务订单
 *
 * @author wenxian.cai
 * @create 2017-05-08 16:59
 **/

public @Data class ServiceOrderDTO {

    /**订单id*/
    private int orderId;

    /**服务项目名称*/
    private String orgServeName;

    /**服务项目图片路径*/
    private String orgServeImage;

    /**购买用户名*/
    private String userName;

    /**购买用户code*/
    private String userCode;

    /**购买用户photo*/
    private String userPhoto;

    /**服务价格*/
    private Double price;

    /**创建日期*/
    private Date createDate;

    /**服务logo*/
    private String logo;

    /**订单状态*/
    private String status;

    /**订单编号*/
    private String orderNumber;

    /**购买者电话*/
    private String mobile;

    /**购买数量*/
    private String number;
    
    private OrderCommentDTO comment;

    /**服务师id*/
    private Integer orgUserId;

    /**服务师姓名*/
    private String orgRealName;

    /**服务项目*/
    private OrgServiceDTO service;

    /**服务项目code*/
    private String code;
}
