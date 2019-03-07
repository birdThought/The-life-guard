package com.lifeshs.pojo.org.profile;

import lombok.Data;

/**
 * 修改密码
 *
 * @author wenxian.cai
 * @create 2017-06-14 14:11
 **/

public @Data class ModifyPasswordVO {

    /**旧密码*/
    private String oldPassword;

    /**新密码*/
    private String newPassword;
}
