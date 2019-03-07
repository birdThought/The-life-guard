package com.lifeshs.pojo.order;

/**
 *  短信充值交易流水
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年4月28日 下午2:05:57
 */
public class SMSOrderFlowDTO {

    private Integer id;

    private int userId;

    private int flowType;

    private int payType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFlowType() {
        return flowType;
    }

    public void setFlowType(int flowType) {
        this.flowType = flowType;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    @Override
    public String toString() {
        return "SMSOrderFlowDTO [id=" + id + ", userId=" + userId + ", flowType=" + flowType + ", payType=" + payType
                + "]";
    }

}
