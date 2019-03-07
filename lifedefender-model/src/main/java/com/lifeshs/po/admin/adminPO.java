package com.lifeshs.po.admin;

import lombok.Data;

import java.util.Date;

/**
 * @author Administrator
 * @create 2018-02-06
 * 15:00  后台用户
 * @desc
 */
public @Data class adminPO {

    private Integer id;
    private Integer adminId;
    private String login_ip;
    private Date login_time;
    private String head;
    private String mobile;
    private String password;
    private String realName;
    private String userName;
    private Integer role_id;
    private Integer status;
    private String photo;
    private Date createDate;
    private Integer agentId;
    private Integer agentNum;
}
