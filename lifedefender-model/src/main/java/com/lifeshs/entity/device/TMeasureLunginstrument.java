package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.lifeshs.common.constants.common.HealthRank;

/**
 * t_measure_lunginstrument
 */
@Table(name = "t_measure_lunginstrument", schema = "")
@SuppressWarnings("serial")
public class TMeasureLunginstrument implements Serializable {
    /** 肺活仪 */
    private Integer id;

    /** 用户设备ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 肺活量 */
    private Integer vitalCapacity;

    /** 肺活量正常值范围 */
    private String vitalCapacityArea;

    private HealthRank vitalCapacityStatus;
    
    /** 肺活量状态描述 */
    private String vitalCapacityStatusDescription;

    /** 峰流速 */
    private Float pef;
    
    /** 平均流速 */
    private Float af;
    
    /** 测量时间 */
    private java.util.Date measureDate;

    /** 终端类型 */
    private String deviceType;

    /** 创建时间 */
    private java.util.Date createDate;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    public TMeasureLunginstrument() {
        super();
    }

    public TMeasureLunginstrument(Integer id, Integer userId, Long status, Integer vitalCapacity,
            String vitalCapacityArea, String vitalCapacityStatusDescription, java.util.Date measureDate,
            String deviceType, java.util.Date createDate, Integer dataType) {
        super();
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

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "userId", nullable = true, length = 11)
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

    @Column(name = "vitalCapacity", nullable = false, length = 6)
    public Integer getVitalCapacity() {
        return vitalCapacity;
    }

    public void setVitalCapacity(Integer vitalCapacity) {
        this.vitalCapacity = vitalCapacity;
    }

    @Column(name = "vitalCapacityArea", nullable = false, length = 20)
    public String getVitalCapacityArea() {
        return vitalCapacityArea;
    }

    public void setVitalCapacityArea(String vitalCapacityArea) {
        this.vitalCapacityArea = vitalCapacityArea;
    }

    @Column(name = "vitalCapacityStatusDescription", nullable = false, length = 500)
    public String getVitalCapacityStatusDescription() {
        return vitalCapacityStatusDescription;
    }

    public void setVitalCapacityStatusDescription(String vitalCapacityStatusDescription) {
        this.vitalCapacityStatusDescription = vitalCapacityStatusDescription;
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

    public HealthRank getVitalCapacityStatus() {
        return vitalCapacityStatus;
    }

    public void setVitalCapacityStatus(HealthRank vitalCapacityStatus) {
        this.vitalCapacityStatus = vitalCapacityStatus;
    }

    @Column(name = "pef")
    public Float getPef() {
        return pef;
    }

    public void setPef(Float pef) {
        this.pef = pef;
    }
    
    @Column(name = "af")
    public Float getAf() {
        return af;
    }
    
    public void setAf(Float af) {
        this.af = af;
    }
}