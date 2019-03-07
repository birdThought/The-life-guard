package com.lifeshs.po;

import java.util.Date;

public class GluCometerPO {
    private Integer id;

    private Integer userId;

    private Long status;

    private Short measureType;

    private Float bloodSugar;

    private String bloodSugarArea;

    private String bloodSugarStatusDescription;

    private Date measureDate;

    private String deviceType;

    private Date createDate;

    private Short dataType;

    public GluCometerPO(Integer id, Integer userId, Long status, Short measureType, Float bloodSugar, String bloodSugarArea, String bloodSugarStatusDescription, Date measureDate, String deviceType, Date createDate, Short dataType) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.measureType = measureType;
        this.bloodSugar = bloodSugar;
        this.bloodSugarArea = bloodSugarArea;
        this.bloodSugarStatusDescription = bloodSugarStatusDescription;
        this.measureDate = measureDate;
        this.deviceType = deviceType;
        this.createDate = createDate;
        this.dataType = dataType;
    }

    public GluCometerPO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Short getMeasureType() {
        return measureType;
    }

    public void setMeasureType(Short measureType) {
        this.measureType = measureType;
    }

    public Float getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(Float bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getBloodSugarArea() {
        return bloodSugarArea;
    }

    public void setBloodSugarArea(String bloodSugarArea) {
        this.bloodSugarArea = bloodSugarArea == null ? null : bloodSugarArea.trim();
    }

    public String getBloodSugarStatusDescription() {
        return bloodSugarStatusDescription;
    }

    public void setBloodSugarStatusDescription(String bloodSugarStatusDescription) {
        this.bloodSugarStatusDescription = bloodSugarStatusDescription == null ? null : bloodSugarStatusDescription.trim();
    }

    public Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(Date measureDate) {
        this.measureDate = measureDate;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Short getDataType() {
        return dataType;
    }

    public void setDataType(Short dataType) {
        this.dataType = dataType;
    }
}