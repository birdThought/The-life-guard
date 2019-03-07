package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_measure_temperature
 */
@Table(name = "t_measure_temperature", schema = "")
@SuppressWarnings("serial")
public class TMeasureTemperature implements Serializable {
    /** 体温 */
    private Integer id;

    /** 用户设备ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 体温 */
    private Float temperature;

    /** 体温正常值范围 */
    private String temperatureArea;

    /** 体温状态描述 */
    private String temperatureStatusDescription;

    /** 测量时间 */
    private java.util.Date measureDate;

    /** 终端类型 */
    private String deviceType;

    /** 创建时间 */
    private java.util.Date createDate;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    public TMeasureTemperature() {
        super();
    }

    public TMeasureTemperature(Integer id, Integer userId, Long status, Float temperature, String temperatureArea,
            String temperatureStatusDescription, java.util.Date measureDate, String deviceType,
            java.util.Date createDate, Integer dataType) {
        super();
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.temperature = temperature;
        this.temperatureArea = temperatureArea;
        this.temperatureStatusDescription = temperatureStatusDescription;
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

    @Column(name = "temperature", nullable = false, length = 6)
    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    @Column(name = "temperatureArea", nullable = false, length = 20)
    public String getTemperatureArea() {
        return temperatureArea;
    }

    public void setTemperatureArea(String temperatureArea) {
        this.temperatureArea = temperatureArea;
    }

    @Column(name = "temperatureStatusDescription", nullable = false, length = 500)
    public String getTemperatureStatusDescription() {
        return temperatureStatusDescription;
    }

    public void setTemperatureStatusDescription(String temperatureStatusDescription) {
        this.temperatureStatusDescription = temperatureStatusDescription;
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

}