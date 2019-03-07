package com.lifeshs.pojo.order;

public class PaymentOrderDTO {

    private String orderNumber;
    private int orderType;
    private String subject;
    private String projectCode; 
    private Integer serveId;
    private int projectType;
    private int userId;
    private int employee;
    private int status;
    private int charge;
    
    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    public int getOrderType() {
        return orderType;
    }
    public String getSubject() {
        return subject;
    }
    public String getProjectCode() {
        return projectCode;
    }
    public Integer getServeId() {
        return serveId;
    }
    public int getProjectType() {
        return projectType;
    }
    public int getUserId() {
        return userId;
    }
    public int getEmployee() {
        return employee;
    }
    public int getStatus() {
        return status;
    }
    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    public void setServeId(Integer serveId) {
        this.serveId = serveId;
    }
    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setEmployee(int employee) {
        this.employee = employee;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getCharge() {
        return charge;
    }
    public void setCharge(int charge) {
        this.charge = charge;
    }
}
