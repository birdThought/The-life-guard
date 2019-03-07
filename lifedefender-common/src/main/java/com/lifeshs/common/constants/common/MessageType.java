package com.lifeshs.common.constants.common;

/**
 *  消息类型
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年5月12日 下午6:12:42
 */
public enum MessageType {

    SYSTEM(1, "系统消息"),
    
    SERVICE(2, "服务消息"),

    STORE(3, "门店推送"),

    SERVICES(4, "服务师推送"),

    ORDER(5, "订单消息");
    
    private Integer value;
    
    private String remark;
    
    private MessageType(Integer value, String remark) {
        this.value = value;
        this.remark = remark;
    }
    
    public Integer value() {
        return value;
    }
    
    public String remark() {
        return remark;
    }

    public static MessageType parseOf(int value){

        for(MessageType messageType : MessageType.values()){
            if(messageType.value == value)
                return messageType;
        }
        return null;
    }
}
