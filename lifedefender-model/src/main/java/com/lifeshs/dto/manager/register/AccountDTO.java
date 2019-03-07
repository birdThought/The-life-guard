package com.lifeshs.dto.manager.register;

import lombok.Data;

/**
 * 管理app - 注册 - 账号申请
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月9日 下午5:43:27
 */
public @Data class AccountDTO {

    /** 登录账号 */
    private String userName;
    /** 登录密码 */
    private String password;
    
    // 引荐人ID 
    private String parentId;
    
    /** 手机号码 */
    private String mobile;
    /** 验证码 */
    private String verifyCode;
    /** 申请类型 */
    private Integer userType;
}
