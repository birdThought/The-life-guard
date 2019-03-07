package com.lifeshs.common.constants.common;

/**
 *  环信信息接收目标类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月3日 上午10:03:38
 */
public enum HuanxinTargetType {

    /** 单用户 */
    USERS("users"),

    /** 群组 */
    CHAT_GROUPS("chatgroups"),

    /** 聊天室 */
    CHAT_ROOMS("chatrooms");

    private String value;

    private HuanxinTargetType(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
