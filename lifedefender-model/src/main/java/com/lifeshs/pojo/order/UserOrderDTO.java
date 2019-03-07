package com.lifeshs.pojo.order;

/**
 * 用户、订单DTO
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月1日 下午7:46:50
 */
public class UserOrderDTO {
    /** 订单ID */
    private Integer orderId;
    /** 订单支付方式 */
    private Integer orderChargeMode;
    /** 用户ID */
    private Integer userId;
    /** 用户头像 */
    private String userPhoto;
    /** 用户编号 */
    private String userCode;
    /** 用户真实姓名 */
    private String userRealName;

    /** 获取订单ID */
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /** 获取订单支付方式 */
    public Integer getOrderChargeMode() {
        return orderChargeMode;
    }

    public void setOrderChargeMode(Integer orderChargeMode) {
        this.orderChargeMode = orderChargeMode;
    }

    /** 获取用户ID */
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /** 获取用户头像 */
    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    /** 获取用户编号 */
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /** 获取用户真实姓名 */
    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    @Override
    public String toString() {
        return "UserOrderDTO [orderId=" + orderId + ", orderChargeMode=" + orderChargeMode + ", userId=" + userId
                + ", userPhoto=" + userPhoto + ", userCode=" + userCode + ", userRealName=" + userRealName + "]";
    }

}
