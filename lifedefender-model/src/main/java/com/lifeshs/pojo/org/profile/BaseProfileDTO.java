package com.lifeshs.pojo.org.profile;

import lombok.Data;

/**
 *  机构用户基础个人信息
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月11日 上午9:51:13
 */
public @Data class BaseProfileDTO {

    /** 用户ID */
    private Integer userId;
    /** 专业特长 */
    private String expertise;
    /** 个人简介 */
    private String about;
    /** 通讯地址 */
    private String address;
    /** 邮箱 */
    private String email;
}
