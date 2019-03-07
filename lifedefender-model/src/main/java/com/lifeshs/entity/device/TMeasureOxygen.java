package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.lifeshs.common.constants.common.HealthRank;

/**
 * t_measure_oxygen
 */
@Table(name = "t_measure_oxygen", schema = "")
@SuppressWarnings("serial")
public class TMeasureOxygen implements Serializable {
    /** 血氧仪 */
    private Integer id;

    /** 用户设备ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 血氧饱和度 */
    private Integer saturation;

    /** 血氧饱和度正常范围 */
    private String saturationArea;
    
    private HealthRank saturationStatus;

    /** 血氧饱和度状态描述 */
    private String saturationStatusDescription;

    /** 心率 */
    private Integer heartRate;

    /** 心率正常范围 */
    private String heartRateArea;
    
    private HealthRank heartRateStatus;

    /** 心率状态描述 */
    private String heartRateStatusDescription;

    /** 测量时间 */
    private java.util.Date measureDate;

    /** 终端类型 */
    private String deviceType;

    /** 创建时间 */
    private java.util.Date createDate;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    public TMeasureOxygen() {
        super();
    }

    public TMeasureOxygen(Integer id, Integer userId, Long status, Integer saturation, String saturationArea,
            String saturationStatusDescription, Integer heartRate, String heartRateArea,
            String heartRateStatusDescription, java.util.Date measureDate, String deviceType, java.util.Date createDate,
            Integer dataType) {
        super();
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.saturation = saturation;
        this.saturationArea = saturationArea;
        this.saturationStatusDescription = saturationStatusDescription;
        this.heartRate = heartRate;
        this.heartRateArea = heartRateArea;
        this.heartRateStatusDescription = heartRateStatusDescription;
        this.measureDate = measureDate;
        this.deviceType = deviceType;
        this.createDate = createDate;
        this.dataType = dataType;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "userId", nullable = false, length = 11)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "status", nullable = false, length = 6)
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Column(name = "saturation", nullable = false, length = 6)
    public Integer getSaturation() {
        return saturation;
    }

    public void setSaturation(Integer saturation) {
        this.saturation = saturation;
    }

    @Column(name = "saturationArea", nullable = false, length = 20)
    public String getSaturationArea() {
        return saturationArea;
    }

    public void setSaturationArea(String saturationArea) {
        this.saturationArea = saturationArea;
    }

    @Column(name = "saturationStatusDescription", nullable = false, length = 500)
    public String getSaturationStatusDescription() {
        return saturationStatusDescription;
    }

    public void setSaturationStatusDescription(String saturationStatusDescription) {
        this.saturationStatusDescription = saturationStatusDescription;
    }

    @Column(name = "heartRate", nullable = false, length = 6)
    public Integer getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Integer heartRate) {
        this.heartRate = heartRate;
    }

    @Column(name = "heartRateArea", nullable = false, length = 20)
    public String getHeartRateArea() {
        return heartRateArea;
    }

    public void setHeartRateArea(String heartRateArea) {
        this.heartRateArea = heartRateArea;
    }

    @Column(name = "heartRateStatusDescription", nullable = false, length = 500)
    public String getHeartRateStatusDescription() {
        return heartRateStatusDescription;
    }

    public void setHeartRateStatusDescription(String heartRateStatusDescription) {
        this.heartRateStatusDescription = heartRateStatusDescription;
    }

    @Column(name = "measureDate", nullable = true, length = 19)
    public java.util.Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(java.util.Date measureDate) {
        this.measureDate = measureDate;
    }

    @Column(name = "deviceType", nullable = false, length = 20)
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "dataType", nullable = false, length = 2)
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public HealthRank getSaturationStatus() {
        return saturationStatus;
    }

    public void setSaturationStatus(HealthRank saturationStatus) {
        this.saturationStatus = saturationStatus;
    }

    public HealthRank getHeartRateStatus() {
        return heartRateStatus;
    }

    public void setHeartRateStatus(HealthRank heartRateStatus) {
        this.heartRateStatus = heartRateStatus;
    }

}