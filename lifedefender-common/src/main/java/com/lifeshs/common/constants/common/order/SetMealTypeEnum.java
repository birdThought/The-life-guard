package com.lifeshs.common.constants.common.order;

/**
 * WeChatController类微信套餐与非套餐调用枚举
 *  
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年4月27日 下午3:01:08
 */
public enum SetMealTypeEnum {

    /** VIP套餐 */
    VIP(1),
    /** 非套餐 */
    NOTVIP(2);
    
    private int value;
    
    private SetMealTypeEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static SetMealTypeEnum getEnum(int value) {
        for (SetMealTypeEnum t : SetMealTypeEnum.values()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return null;
    }
}
