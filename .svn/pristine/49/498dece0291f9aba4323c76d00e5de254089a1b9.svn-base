package com.lifeshs.common.constants.common.order;

/**
 *  支付返回订单类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月17日 下午4:34:02
 */
public enum PayReturnOrderTypeEnum {

    /** 服务 */
    SERVE(1),
    /** 短信充值 */
    SMS(2),
    /** 服务退款 */
    SERVE_REFUND(3),
    /** 分析报告 */
    REPORT_ANALYSIS(4),
    /** VIP充值 */
    VIP(5),
    /** 名医预约 */
    FAMOUS_DOCTOR(6),
    /** 自定义订单 */
    PRIVATE_ORDER(7),
    
    /** 药品订单 */
    DRUGS(8),
	
    /**商城订单**/
	SHOP(9);
    

    public static PayReturnOrderTypeEnum getPayReturnOrderType(int value) {
        for (PayReturnOrderTypeEnum a : PayReturnOrderTypeEnum.values()) {
            if (a.getValue() == value) {
                return a;
            }
        }
        return null;
    }
    
    private int value;
    
    private PayReturnOrderTypeEnum(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
