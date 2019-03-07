package com.lifeshs.pojo.org.profile;

import lombok.Data;

/**
 *  修改手机号码
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月16日 上午10:04:19
 */
public @Data class ModifyMobileDTO {

    /** 用户ID */
    private Integer userId;
    
    /** 手机收到的验证码 */
    private String code;
    
    /** 绑定的手机号码 */
    private String mobile;
    
    /** 用户密码 */
    private String password;
}
