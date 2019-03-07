package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_measure_ua
 */
@Table(name = "t_measure_ua", schema = "")
@SuppressWarnings("serial")
public class TMeasureUa implements Serializable {

    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 尿酸 */
    private Float uA;

    /** 尿酸状态描述 */
    private String uAStatusDescription;

    /** 尿酸正常范围 */
    private String uAArea;

    /** 测量日期 */
    private java.util.Date measureDate;

    /** 终端类型 */
    private String deviceType;

    /** 创建日期 */
    private java.util.Date createDate;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    public TMeasureUa() {
        super();
    }

    public TMeasureUa(Integer id, Integer userId, Long status, Float uA, String uAStatusDescription, String uAArea,
            java.util.Date measureDate, String deviceType, java.util.Date createDate, Integer dataType) {
        super();
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.uA = uA;
        this.uAStatusDescription = uAStatusDescription;
        this.uAArea = uAArea;
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

    @Column(name = "status", nullable = false, length = 19)
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    @Column(name = "UA", nullable = false, length = 5)
    public Float getUA() {
        return uA;
    }

    public void setUA(Float uA) {
        this.uA = uA;
    }

    @Column(name = "UAStatusDescription", nullable = false, length = 500)
    public String getUAStatusDescription() {
        return uAStatusDescription;
    }

    public void setUAStatusDescription(String uAStatusDescription) {
        this.uAStatusDescription = uAStatusDescription;
    }

    @Column(name = "UAArea", nullable = false, length = 20)
    public String getUAArea() {
        return uAArea;
    }

    public void setUAArea(String uAArea) {
        this.uAArea = uAArea;
    }

    @Column(name = "measureDate", nullable = false, length = 19)
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

    @Override
    public String toString() {
        return "TMeasureUa [id=" + id + ", userId=" + userId + ", status=" + status + ", uA=" + uA
                + ", uAStatusDescription=" + uAStatusDescription + ", uAArea=" + uAArea + ", measureDate=" + measureDate
                + ", deviceType=" + deviceType + ", createDate=" + createDate + ", dataType=" + dataType + "]";
    }
}
