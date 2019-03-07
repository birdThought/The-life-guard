package com.lifeshs.pojo.org;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.lifeshs.pojo.org.group.GroupDTO;

import lombok.Data;

/**
 * 机构用户
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月29日 上午9:27:01
 */
public @Data class OrgUserDTO {

    private int id;
    
    /** 用户编号 */
    private String userNo;
    
    /** 推荐人编号 */
    private String parentId;

    /** 用户编号 */
    private String userCode;

    /** 用户登录名 */
    private String userName;

    /** 登录密码 */
    private String password;

    /** app token(也可用于web自动登录) */
    private String token;

    /** 真实姓名 */
    private String realName;

    /** 手机号码 */
    private String mobile;

    /** 0:未验证,1:已验证 */
    private Boolean mobileVerified;

    /** 用户邮箱 */
    private String email;

    /** 0:未验证,1:已验证 */
    private Boolean emailVerified;

    /** 性别0表示女,1表示男 */
    private Boolean sex;

    /** 出生日期 */
    @JSONField(format = "yyyy-MM-dd")
    private java.util.Date birthday;

    /** 工作电话 */
    private String tel;

    /** 家庭地址 */
    private String address;

    /** 状态：正常_0,停用_1,注销_2,离职_e */
    private Integer status;

    /** 用户类型:管理员_0,服务师_1,都有_2 */
    private Integer userType;

    /** 个人照片路径 */
    private String photo;

    /** 个人简介 */
    private String about;

    /** 个人详细介绍(html) */
    private String detail;

    /** 创建日期 */
    private java.util.Date createDate;

    /** 修改时间 */
    private java.util.Date modifyDate;

    /** 机构ID */
    private Integer orgId;

    /** 所属机构 */
    private OrgDTO org;

    /** 所属群组列表 */
    private List<GroupDTO> groups;

    /** 身份证*/
    private String idCard;

    /** 身份证件照路径1*/
    private String idCardPicOne;

    /** 身份证件照路径2*/
    private String idCardPicTwo;

    /** 专业特长 */
    private String expertise;

    /** 职称 */
    private String professionalName;

    /** 职业资格证件 */
    private String professionalPic;

    /** 注册验证码 */
    private String verifyCode;
}
