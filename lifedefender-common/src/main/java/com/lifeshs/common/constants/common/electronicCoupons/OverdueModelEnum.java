package com.lifeshs.common.constants.common.electronicCoupons;

/**
 *  电子券过期模式
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月8日 下午2:10:03
 */
public enum OverdueModelEnum {

    /** 不会失效  */
    ETERNAL(0),
    /** 限制结束日期 */
    END_DATE(1),
    /** 领取后计时 */
    RECKON_AFTER_RECEIVE(2);
    
    private int value;
    
    private OverdueModelEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
    
    /**
     *  查找过期模式
     *  @author yuhang.weng 
     *  @DateTime 2017年12月8日 下午2:28:29
     *
     *  @param value
     *  @return
     */
    public static OverdueModelEnum findModel(int value) {
        for (OverdueModelEnum model : OverdueModelEnum.values()) {
            if (model.getValue() == value) {
                return model;
            }
        }
        return null;
    }
}
