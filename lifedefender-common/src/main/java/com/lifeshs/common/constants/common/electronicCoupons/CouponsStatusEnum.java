package com.lifeshs.common.constants.common.electronicCoupons;

/**
 *  电子券状态
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月14日 下午4:18:50
 */
public enum CouponsStatusEnum {

    /** 可用的  */
    USEABLE(0),
    /** 使用中 */
    USING(1),
    /** 已使用 */
    USED(2),
    /** 已过期 */
    OUT_OF_END_DATE(3);
    
    private int value;
    
    private CouponsStatusEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    /**
     *  查找电子券状态
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午2:28:29
     *
     *  @param value
     *  @return
     */
    public static CouponsStatusEnum findModel(int value) {
        for (CouponsStatusEnum model : CouponsStatusEnum.values()) {
            if (model.getValue() == value) {
                return model;
            }
        }
        return null;
    }
}
