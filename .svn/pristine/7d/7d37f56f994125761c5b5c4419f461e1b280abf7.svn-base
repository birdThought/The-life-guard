package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.lifeshs.common.constants.common.HealthRank;

/**
 * t_measure_glucometer
 */
@Table(name = "t_measure_glucometer", schema = "")
@SuppressWarnings("serial")
public class TMeasureGlucometer implements Serializable {
    /** 血糖仪 */
    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 测量类型：早餐前_1,早餐后_2,午餐前_3,午餐后_4,晚餐前_5,晚餐后_6 */
    private Integer measureType;

    /** 血糖 */
    private Float bloodSugar;

    /** 血糖正常值范围 */
    private String bloodSugarArea;
    
    private HealthRank bloodSugarStatus;

    /** 血糖状态描述 */
    private String bloodSugarStatusDescription;

    /** 测量时间 */
    private java.util.Date measureDate;

    /** 终端类型 */
    private String deviceType;

    /** 创建时间 */
    private java.util.Date createDate;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    public TMeasureGlucometer() {
        super();
    }

    public TMeasureGlucometer(Integer id, Integer userId, Long status, Integer measureType, Float bloodSugar,
            String bloodSugarArea, String bloodSugarStatusDescription, java.util.Date measureDate, String deviceType,
            java.util.Date createDate, Integer dataType) {
        super();
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

    @Column(name = "measureType", nullable = false, length = 6)
    public Integer getMeasureType() {
        return measureType;
    }

    public void setMeasureType(Integer measureType) {
        this.measureType = measureType;
    }

    @Column(name = "bloodSugar", nullable = false, length = 6)
    public Float getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(Float bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    @Column(name = "bloodSugarArea", nullable = false, length = 20)
    public String getBloodSugarArea() {
        return bloodSugarArea;
    }

    public void setBloodSugarArea(String bloodSugarArea) {
        this.bloodSugarArea = bloodSugarArea;
    }

    @Column(name = "bloodSugarStatusDescription", nullable = false, length = 500)
    public String getBloodSugarStatusDescription() {
        return bloodSugarStatusDescription;
    }

    public void setBloodSugarStatusDescription(String bloodSugarStatusDescription) {
        this.bloodSugarStatusDescription = bloodSugarStatusDescription;
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

    public HealthRank getBloodSugarStatus() {
        return bloodSugarStatus;
    }

    public void setBloodSugarStatus(HealthRank bloodSugarStatus) {
        this.bloodSugarStatus = bloodSugarStatus;
    }

}