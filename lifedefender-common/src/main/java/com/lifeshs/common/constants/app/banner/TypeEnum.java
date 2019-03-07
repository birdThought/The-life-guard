package com.lifeshs.common.constants.app.banner;

/**
 *  app的banner类型枚举
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月23日 上午10:54:14
 */
public enum TypeEnum {

    HOME(1, "首页"),
    
    SERVICE(2, "服务页"),
    
    HOME_VIP(3, "首页-vipBanner");
    
    /** 值 */
    private int value;
    /** 描述文字 */
    private String remark;
    
    private TypeEnum(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }
    
    public int value() {
        return this.value;
    }
    
    public String remark() {
        return this.remark;
    }
}
