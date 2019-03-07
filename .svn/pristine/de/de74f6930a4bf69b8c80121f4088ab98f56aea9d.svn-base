package com.lifeshs.common.constants.common.order;

/**
 *  订单前面类型枚举
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2018年1月2日 下午6:01:18
 */
public enum OrderInfoTypeEnum {

    /** 服务订单 */
    SERVE(1),
    /** 自定义订单 */
    PRIVATE(2),
    /** 药品订单 */
    GRUDS(8),
	/** 商城订单**/
	SHOP(9);
    
    private int value;
    
    private OrderInfoTypeEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public static OrderInfoTypeEnum getEnum(int value) {
        for (OrderInfoTypeEnum t : OrderInfoTypeEnum.values()) {
            if (t.getValue() == value) {
                return t;
            }
        }
        return null;
    }
}
