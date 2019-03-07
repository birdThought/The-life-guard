package com.lifeshs.vo.business;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2018-04-13
 * 17:21   推广数据结算类
 * @desc
 */
public @Data class ComboRecordVo {
    private Integer id;
    private Integer count;
    private String moon;
    private Integer qdsId;
    private Integer userId;
    private Integer VipComboId;
    private Integer qdsUserId;
    private Integer price;
    private Integer payMoney;
    private Integer status;
    private Date createDate;
    private Date modifyDate;
}
