package com.lifeshs.pojo.member;

import java.util.Date;

import lombok.Data;

/**
 * 用户DTO
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月17日 下午2:53:41
 */
public @Data class UserDTO {

    private int id;
    
    private String userNo;

    /** 用户编号 */
    private String userCode;

    /** 用户登录名 */
    private String userName;

    /** 登录密码 */
    private String password;
    
    // 引荐人ID 
    private String parentId;

    /** 用户头像保存相对路径 */
    private String photo;

    /** app token(也可用于web自动登录) */
    private String token;

    /** 手机号码 */
    private String mobile;

    /** 0:未验证,1:已验证 */
    private Boolean mobileVerified;

    /** 用户邮箱 */
    private String email;

    /** 0:未验证,1:已验证 */
    private Boolean emailVerified;

    /** 真实姓名 */
    private String realName;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 县(区) */
    private String county;

    /** 街道地址 */
    private String street;

    /** 勾选的健康包产品 */
    private Integer healthProduct;

    /** 勾选的健康预警 */
    private Integer healthWarning;

    /** 无异常数据_0,有异常_加设备的值 */
    private Integer hasWarning;

    /** 家庭组 */
    private String groupKey;

    /** 修改时间 */
    private java.util.Date modifyDate;

    /** 状态：正常_0,禁用_1 */
    private Integer status;

    /** 用户个人档案 */
    private UserRecordDTO recordDTO;

    /** 渠道商ID */
    private Integer businessId;

    /** 引入日期 */
    private Date joinDate;
}
