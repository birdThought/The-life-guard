package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.lifeshs.common.constants.common.HealthRank;

/**
 * t_measure_bloodpressure
 */
@Table(name = "t_measure_bloodpressure", schema = "")
@SuppressWarnings("serial")
public class TMeasureBloodpressure implements Serializable {
    /** 血压计 */
    private Integer id;

    /** 用户设备ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 舒张压 */
    private Integer diastolic;

    /** 舒张压正常范围 */
    private String diastolicArea;
    
    private HealthRank diastolicStatus;

    /** 舒张压状态描述 */
    private String diastolicStatusDescription;

    /** 收缩压 */
    private Integer systolic;

    /** 收缩压正常范围 */
    private String systolicArea;
    
    private HealthRank systolicStatus;

    /** 收缩压状态描述 */
    private String systolicStatusDescription;

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

    public TMeasureBloodpressure() {
        super();
    }

    public TMeasureBloodpressure(Integer id, Integer userId, Long status, Integer diastolic, String diastolicArea,
            String diastolicStatusDescription, Integer systolic, String systolicArea, String systolicStatusDescription,
            Integer heartRate, String heartRateArea, String heartRateStatusDescription, java.util.Date measureDate,
            String deviceType, java.util.Date createDate, Integer dataType) {
        super();
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

    @Column(name = "diastolic", nullable = false, length = 6)
    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    @Column(name = "diastolicArea", nullable = false, length = 20)
    public String getDiastolicArea() {
        return diastolicArea;
    }

    public void setDiastolicArea(String diastolicArea) {
        this.diastolicArea = diastolicArea;
    }

    @Column(name = "diastolicStatusDescription", nullable = false, length = 500)
    public String getDiastolicStatusDescription() {
        return diastolicStatusDescription;
    }

    public void setDiastolicStatusDescription(String diastolicStatusDescription) {
        this.diastolicStatusDescription = diastolicStatusDescription;
    }

    @Column(name = "systolic", nullable = false, length = 6)
    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    @Column(name = "systolicArea", nullable = false, length = 20)
    public String getSystolicArea() {
        return systolicArea;
    }

    public void setSystolicArea(String systolicArea) {
        this.systolicArea = systolicArea;
    }

    @Column(name = "systolicStatusDescription", nullable = false, length = 500)
    public String getSystolicStatusDescription() {
        return systolicStatusDescription;
    }

    public void setSystolicStatusDescription(String systolicStatusDescription) {
        this.systolicStatusDescription = systolicStatusDescription;
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

    public HealthRank getDiastolicStatus() {
        return diastolicStatus;
    }

    public void setDiastolicStatus(HealthRank diastolicStatus) {
        this.diastolicStatus = diastolicStatus;
    }

    public HealthRank getSystolicStatus() {
        return systolicStatus;
    }

    public void setSystolicStatus(HealthRank systolicStatus) {
        this.systolicStatus = systolicStatus;
    }

    public HealthRank getHeartRateStatus() {
        return heartRateStatus;
    }

    public void setHeartRateStatus(HealthRank heartRateStatus) {
        this.heartRateStatus = heartRateStatus;
    }

}