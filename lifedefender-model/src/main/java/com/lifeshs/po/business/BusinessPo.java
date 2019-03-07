package com.lifeshs.po.business;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2018-03-22
 * 10:11   渠道商
 * @desc
 */
public @Data class BusinessPo {

    private Integer id;
    /** 机构名 */
    private String name;
    /** 状态 */
    private Integer status;
    /** 头像存放路径 */
    private String logo;
    private String contactMan;
    private String phone;
    private String email;
    private Integer type;
    private Date createDate;
    private Date modifyDate;
}
