package com.lifeshs.common.constants.common;

/**
 *  订单类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年4月27日 下午2:14:27
 */
public enum OrderType {

    ServeOrder(1, "服务订单"),
    
    SMSOrder(2, "短信充值订单"),
    
    Other(3, "其他待扩展订单"),
    
    Drugs(8,"药品订单"),
	
	SHOP(9,"商城订单");
    
    private int value;
    
    private String remark;
    
    private OrderType(int value, String remark) {
        this.value = value;
        this.remark = remark;
    }
    
    public int getValue() {
        return value;
    }
    
    public String getRemark() {
        return remark;
    }
}
