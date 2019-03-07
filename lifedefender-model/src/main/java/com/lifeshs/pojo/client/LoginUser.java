package com.lifeshs.pojo.client;

/**
 * 版权归 TODO 类说明 登录用户
 * 
 * @author duosheng.mo
 * @DateTime 2016年4月28日 下午1:51:01
 */

import lombok.Data;

public @Data class LoginUser implements java.io.Serializable {

    /** 描述 */
    private static final long serialVersionUID = 1L;

    private Integer id;
    /** 用户编号 */
    private String userNo;
    /** 用户登录名 */
    private String userName;

    /** 用户环信账号 */
    private String userCode;

    // /** 手机号码 */
    private String mobile;

    /** 微信openid */
    // private String openId;

    /** 用户类型:管理员_0,服务师_1，两者都有_2 */
    private Integer userType;

    /** 用户机构ID */
    private Integer orgId;

    /** 企业用户所属的机构类型 */
    private Integer type;

    /** 用户类型 m表示普通用户,o表示企业用户 */
    private String lut;

    /**真实姓名*/
    private String realName;

    /**头像*/
    private String head;

    /**是否企业验证:已审核_1,未审核_0,未通过_2,后台不显示审核_3*/
    private Integer orgVerified;
    /** 渠道商id */
    private Integer superior;
    /** 代理商类型 1代理商 2业务员 null平台用户*/
    private Integer agentId;
    
    private Integer agentNum;
}
