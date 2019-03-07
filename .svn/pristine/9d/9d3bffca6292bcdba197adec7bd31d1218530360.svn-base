package com.lifeshs.po;

import java.util.Date;

public class OrderRefundPO {
    private Integer id;

    private String orderNumber;

    private String outRequestNo;

    private String cause;

    private Date refundTime;

    private Byte status;

    private Integer auditorId;

    private Date auditorTime;

    private Date completeTime;

    public OrderRefundPO(Integer id, String orderNumber, String outRequestNo, String cause, Date refundTime,
                         Byte status, Integer auditorId, Date auditorTime, Date completeTime) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.outRequestNo = outRequestNo;
        this.cause = cause;
        this.refundTime = refundTime;
        this.status = status;
        this.auditorId = auditorId;
        this.auditorTime = auditorTime;
        this.completeTime = completeTime;
    }

    public String getOutRequestNo() {
        return outRequestNo;
    }

    public void setOutRequestNo(String outRequestNo) {
        this.outRequestNo = outRequestNo;
    }

    public OrderRefundPO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Integer auditorId) {
        this.auditorId = auditorId;
    }

    public Date getAuditorTime() {
        return auditorTime;
    }

    public void setAuditorTime(Date auditorTime) {
        this.auditorTime = auditorTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    @Override
    public String toString() {
        return "OrderRefundPO{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", cause='" + cause + '\'' +
                ", refundTime=" + refundTime +
                ", status=" + status +
                ", auditorId=" + auditorId +
                ", auditorTime=" + auditorTime +
                ", completeTime=" + completeTime +
                '}';
    }
}