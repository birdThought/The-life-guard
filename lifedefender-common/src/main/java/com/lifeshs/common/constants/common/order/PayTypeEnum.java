package com.lifeshs.common.constants.common.order;

/**
 *  支付类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月17日 下午3:11:22
 */
public enum PayTypeEnum {

    /** 支付宝 */
    ALIPAY(1),
    /** 微信 */
    WECHAT(2),
    /** 网银在线 */
    BANK_ONLINE(3);
    
    private int value;
    
    public static PayTypeEnum getPayTypeEnum(int value) {
        for (PayTypeEnum p : PayTypeEnum.values()) {
            if (p.getValue() == value) {
                return p;
            }
        }
        return null;
    }
    
    private PayTypeEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
