package com.lifeshs.po;

import java.util.Date;

public class BloodPressurePO {
    private Integer id;

    private Integer userId;

    private Long status;

    private Short diastolic;

    private String diastolicArea;

    private String diastolicStatusDescription;

    private Short systolic;

    private String systolicArea;

    private String systolicStatusDescription;

    private Short heartRate;

    private String heartRateArea;

    private String heartRateStatusDescription;

    private Date measureDate;

    private String deviceType;

    private Date createDate;

    private Short dataType;

    public BloodPressurePO(Integer id, Integer userId, Long status, Short diastolic, String diastolicArea, String diastolicStatusDescription, Short systolic, String systolicArea, String systolicStatusDescription, Short heartRate, String heartRateArea, String heartRateStatusDescription, Date measureDate, String deviceType, Date createDate, Short dataType) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.diastolic = diastolic;
        this.diastolicArea = diastolicArea;
        this.diastolicStatusDescription = diastolicStatusDescription;
        this.systolic = systolic;
        this.systolicArea = systolicArea;
        this.systolicStatusDescription = systolicStatusDescription;
        this.heartRate = heartRate;
        this.heartRateArea = heartRateArea;
        this.heartRateStatusDescription = heartRateStatusDescription;
        this.measureDate = measureDate;
        this.deviceType = deviceType;
        this.createDate = createDate;
        this.dataType = dataType;
    }

    public BloodPressurePO() {
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

    public Short getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Short diastolic) {
        this.diastolic = diastolic;
    }

    public String getDiastolicArea() {
        return diastolicArea;
    }

    public void setDiastolicArea(String diastolicArea) {
        this.diastolicArea = diastolicArea == null ? null : diastolicArea.trim();
    }

    public String getDiastolicStatusDescription() {
        return diastolicStatusDescription;
    }

    public void setDiastolicStatusDescription(String diastolicStatusDescription) {
        this.diastolicStatusDescription = diastolicStatusDescription == null ? null : diastolicStatusDescription.trim();
    }

    public Short getSystolic() {
        return systolic;
    }

    public void setSystolic(Short systolic) {
        this.systolic = systolic;
    }

    public String getSystolicArea() {
        return systolicArea;
    }

    public void setSystolicArea(String systolicArea) {
        this.systolicArea = systolicArea == null ? null : systolicArea.trim();
    }

    public String getSystolicStatusDescription() {
        return systolicStatusDescription;
    }

    public void setSystolicStatusDescription(String systolicStatusDescription) {
        this.systolicStatusDescription = systolicStatusDescription == null ? null : systolicStatusDescription.trim();
    }

    public Short getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Short heartRate) {
        this.heartRate = heartRate;
    }

    public String getHeartRateArea() {
        return heartRateArea;
    }

    public void setHeartRateArea(String heartRateArea) {
        this.heartRateArea = heartRateArea == null ? null : heartRateArea.trim();
    }

    public String getHeartRateStatusDescription() {
        return heartRateStatusDescription;
    }

    public void setHeartRateStatusDescription(String heartRateStatusDescription) {
        this.heartRateStatusDescription = heartRateStatusDescription == null ? null : heartRateStatusDescription.trim();
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