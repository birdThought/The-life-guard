package com.lifeshs.pojo.order;

import com.lifeshs.pojo.org.group.GroupDTO;

/**
 * 订单
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月8日 上午11:08:41
 */
public class OrderDTO {

    private Integer id;

    /** 收费方式:0_免费，1_按次，2_按月，3_按年 */
    private Integer chargeMode;

    /** 费用 */
    private Integer charge;

    /** 用户ID(会员) */
    private Integer userId;

    private String orderNumber;

    private Integer status;

    private GroupDTO group;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChargeMode() {
        return chargeMode;
    }

    public void setChargeMode(Integer chargeMode) {
        this.chargeMode = chargeMode;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public GroupDTO getGroup() {
        return group;
    }

    public void setGroup(GroupDTO group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "OrderDTO [id=" + id + ", chargeMode=" + chargeMode + ", charge=" + charge + ", userId=" + userId
                + ", orderNumber=" + orderNumber + ", status=" + status + ", group=" + group + "]";
    }

}
