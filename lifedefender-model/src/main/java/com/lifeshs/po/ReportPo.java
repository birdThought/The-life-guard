package com.lifeshs.po;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2018-02-01
 * 15:15
 * @desc
 */
public @Data class ReportPo {

    private Integer id;
    private String message;
    private String contactInformation;
    private boolean isRead;
    private Date createDate;
    private Integer userId;
    private String  reply;
    private Integer  userType;


}
