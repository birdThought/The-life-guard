package com.lifeshs.common.constants.common;

/**
 * 推送消息类型
 * author: wenxian.cai
 * date: 2017/8/24 11:11
 */
public enum  PushMsgType {

    system(1, "系统推送"),

    store(2, "门店推送"),

    services(3, "服务师推送"),

    customers(4, "客服推送");

    private Integer value;

    private String remark;

   private PushMsgType(Integer value,String remark){
       this.value = value;
       this.remark = remark;
   }
    public Integer getValue() {
        return value;
    }

    public String getRemark() {
        return remark;
    }
}