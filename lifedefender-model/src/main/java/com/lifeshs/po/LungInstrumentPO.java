package com.lifeshs.po;

import java.util.Date;

public class LungInstrumentPO {
    private Integer id;

    private Integer userId;

    private Long status;

    private Short vitalCapacity;

    private String vitalCapacityArea;

    private String vitalCapacityStatusDescription;

    private Date measureDate;

    private String deviceType;

    private Date createDate;

    private Short dataType;

    public LungInstrumentPO(Integer id, Integer userId, Long status, Short vitalCapacity, String vitalCapacityArea, String vitalCapacityStatusDescription, Date measureDate, String deviceType, Date createDate, Short dataType) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.vitalCapacity = vitalCapacity;
        this.vitalCapacityArea = vitalCapacityArea;
        this.vitalCapacityStatusDescription = vitalCapacityStatusDescription;
        this.measureDate = measureDate;
        this.deviceType = deviceType;
        this.createDate = createDate;
        this.dataType = dataType;
    }

    public LungInstrumentPO() {
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

    public Short getVitalCapacity() {
        return vitalCapacity;
    }

    public void setVitalCapacity(Short vitalCapacity) {
        this.vitalCapacity = vitalCapacity;
    }

    public String getVitalCapacityArea() {
        return vitalCapacityArea;
    }

    public void setVitalCapacityArea(String vitalCapacityArea) {
        this.vitalCapacityArea = vitalCapacityArea == null ? null : vitalCapacityArea.trim();
    }

    public String getVitalCapacityStatusDescription() {
        return vitalCapacityStatusDescription;
    }

    public void setVitalCapacityStatusDescription(String vitalCapacityStatusDescription) {
        this.vitalCapacityStatusDescription = vitalCapacityStatusDescription == null ? null : vitalCapacityStatusDescription.trim();
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