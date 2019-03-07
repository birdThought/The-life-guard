package com.lifeshs.common.constants.common;

/**
 *  环信透传消息命令
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月3日 上午10:17:28
 */
public enum HuanxinCmdActionType {

    /** 禁言 */
    GAG("gag"),
    /** 解除禁言 */
    GAG_REMOVE("gag_remove");
    
    private String action;
    
    private HuanxinCmdActionType(String action) {
        this.action = action;
    }
    
    public String getAction() {
        return this.action;
    }
}
