package com.lifeshs.common.constants.common.order;

/**
 *  收费方式
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年7月15日 上午11:33:48
 */
public enum ChargeModeEnum {

    FREE(0, "免费"),

    TIMES(1, "按次"),

    MONTH(2, "按月"),

    YEAR(3, "按年");

    public static ChargeModeEnum getChargeModeEnum(int value) {
        for (ChargeModeEnum e : ChargeModeEnum.values()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }
    
    private int value;
    private String remark;

    private ChargeModeEnum(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }

    public int getValue() {
        return this.value;
    }

    public String gerRemark() {
        return this.remark;
    }
}
