package com.lifeshs.common.constants.common.order;

/**
 *  vip订单收费类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月17日 下午2:03:00
 */
public enum VipOrderTypeEnum {

    /** 付费 */
    PAY(1),
    /** 激活码 */
    CODE(2),
    /** 扫码进入套餐购买 */
    QRPAY(3);

    public static VipOrderTypeEnum getVipOrderType(int value) {
        for (VipOrderTypeEnum e : VipOrderTypeEnum.values()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }
    
    private int value;

    private VipOrderTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
