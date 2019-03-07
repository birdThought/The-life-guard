package com.lifeshs.po;

import java.util.Date;

public class UaPO {
    private Integer id;

    private Integer userId;

    private Long status;

    private Float UA;

    private String UAStatusDescription;

    private String UAArea;

    private Date measureDate;

    private String deviceType;

    private Date createDate;

    private Short dataType;

    public UaPO(Integer id, Integer userId, Long status, Float UA, String UAStatusDescription, String UAArea, Date measureDate, String deviceType, Date createDate, Short dataType) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.UA = UA;
        this.UAStatusDescription = UAStatusDescription;
        this.UAArea = UAArea;
        this.measureDate = measureDate;
        this.deviceType = deviceType;
        this.createDate = createDate;
        this.dataType = dataType;
    }

    public UaPO() {
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

    public Float getUA() {
        return UA;
    }

    public void setUA(Float UA) {
        this.UA = UA;
    }

    public String getUAStatusDescription() {
        return UAStatusDescription;
    }

    public void setUAStatusDescription(String UAStatusDescription) {
        this.UAStatusDescription = UAStatusDescription == null ? null : UAStatusDescription.trim();
    }

    public String getUAArea() {
        return UAArea;
    }

    public void setUAArea(String UAArea) {
        this.UAArea = UAArea == null ? null : UAArea.trim();
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