package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_measure_bloodlipid
 */
@Table(name = "t_measure_bloodlipid", schema = "")
@SuppressWarnings("serial")
public class TMeasureBloodlipid implements Serializable {

    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 高密度脂蛋白胆固醇 */
    private Float hDL;

    /** 高密度脂蛋白胆固醇健康范围 */
    private String hDLArea;

    /** 高密度脂蛋白胆固醇状态描述 */
    private String hDLStatusDescription;

    /** 低密度脂蛋白胆固醇 */
    private Double lDL;

    /** 低密度脂蛋白胆固醇健康范围值 */
    private String lDLArea;

    /** 低密度脂蛋白胆固醇状态描述 */
    private String lDLStatusDescription;

    /** 甘油三酯 */
    private Double tG;

    /** 甘油三酯健康范围 */
    private String tGArea;

    /** 甘油三酯状态描述 */
    private String tGStatusDescription;

    /** 总胆固醇 */
    private Double tC;

    /** 总胆固醇健康范围 */
    private String tCArea;

    /** 总胆固醇状态描述 */
    private String tCStatusDescription;
    
    /** 血脂比值 */
    private Float bloodLipidRatio;
    
    /** 血脂比值健康范围 */
    private String bloodLipidRatioArea;
    
    /** 血脂比值状态描述 */
    private String bloodLipidRatioStatusDescription;

    /** 测量日期 */
    private java.util.Date measureDate;

    /** 创建日期 */
    private java.util.Date createDate;

    /** 终端类型 */
    private String deviceType;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    public TMeasureBloodlipid() {
        super();
    }

    public TMeasureBloodlipid(Integer id, Integer userId, Long status, Float hDL, String hDLArea,
            String hDLStatusDescription, Double lDL, String lDLArea, String lDLStatusDescription, Double tG,
            String tGArea, String tGStatusDescription, Double tC, String tCArea, String tCStatusDescription,
            java.util.Date measureDate, java.util.Date createDate, String deviceType, Integer dataType) {
        super();
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.hDL = hDL;
        this.hDLArea = hDLArea;
        this.hDLStatusDescription = hDLStatusDescription;
        this.lDL = lDL;
        this.lDLArea = lDLArea;
        this.lDLStatusDescription = lDLStatusDescription;
        this.tG = tG;
        this.tGArea = tGArea;
        this.tGStatusDescription = tGStatusDescription;
        this.tC = tC;
        this.tCArea = tCArea;
        this.tCStatusDescription = tCStatusDescription;
        this.measureDate = measureDate;
        this.createDate = createDate;
        this.deviceType = deviceType;
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

    @Column(name = "HDL", nullable = false, length = 5)
    public Float getHDL() {
        return hDL;
    }

    public void setHDL(Float hDL) {
        this.hDL = hDL;
    }

    @Column(name = "HDLArea", nullable = false, length = 20)
    public String getHDLArea() {
        return hDLArea;
    }

    public void setHDLArea(String hDLArea) {
        this.hDLArea = hDLArea;
    }

    @Column(name = "HDLStatusDescription", nullable = false, length = 500)
    public String getHDLStatusDescription() {
        return hDLStatusDescription;
    }

    public void setHDLStatusDescription(String hDLStatusDescription) {
        this.hDLStatusDescription = hDLStatusDescription;
    }

    @Column(name = "LDL", nullable = false, length = 5)
    public Double getLDL() {
        return lDL;
    }

    public void setLDL(Double lDL) {
        this.lDL = lDL;
    }

    @Column(name = "LDLArea", nullable = false, length = 20)
    public String getLDLArea() {
        return lDLArea;
    }

    public void setLDLArea(String lDLArea) {
        this.lDLArea = lDLArea;
    }

    @Column(name = "LDLStatusDescription", nullable = false, length = 500)
    public String getLDLStatusDescription() {
        return lDLStatusDescription;
    }

    public void setLDLStatusDescription(String lDLStatusDescription) {
        this.lDLStatusDescription = lDLStatusDescription;
    }

    @Column(name = "TG", nullable = false, length = 5)
    public Double getTG() {
        return tG;
    }

    public void setTG(Double tG) {
        this.tG = tG;
    }

    @Column(name = "TGArea", nullable = false, length = 20)
    public String getTGArea() {
        return tGArea;
    }

    public void setTGArea(String tGArea) {
        this.tGArea = tGArea;
    }

    @Column(name = "TGStatusDescription", nullable = false, length = 500)
    public String getTGStatusDescription() {
        return tGStatusDescription;
    }

    public void setTGStatusDescription(String tGStatusDescription) {
        this.tGStatusDescription = tGStatusDescription;
    }

    @Column(name = "TC", nullable = false, length = 5)
    public Double getTC() {
        return tC;
    }

    public void setTC(Double tC) {
        this.tC = tC;
    }

    @Column(name = "TCArea", nullable = false, length = 20)
    public String getTCArea() {
        return tCArea;
    }

    public void setTCArea(String tCArea) {
        this.tCArea = tCArea;
    }

    @Column(name = "TCStatusDescription", nullable = false, length = 500)
    public String getTCStatusDescription() {
        return tCStatusDescription;
    }

    public void setTCStatusDescription(String tCStatusDescription) {
        this.tCStatusDescription = tCStatusDescription;
    }

    @Column(name = "measureDate", nullable = false, length = 19)
    public java.util.Date getMeasureDate() {
        return measureDate;
    }

    public void setMeasureDate(java.util.Date measureDate) {
        this.measureDate = measureDate;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "deviceType", nullable = false, length = 20)
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Column(name = "dataType", nullable = false, length = 2)
    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    
    @Column(name = "bloodLipidRatio", nullable = false, length = 5)
    public Float getBloodLipidRatio() {
        return bloodLipidRatio;
    }

    public void setBloodLipidRatio(Float bloodLipidRatio) {
        this.bloodLipidRatio = bloodLipidRatio;
    }

    @Column(name = "bloodLipidRatioArea", nullable = false, length = 20)
    public String getBloodLipidRatioArea() {
        return bloodLipidRatioArea;
    }

    public void setBloodLipidRatioArea(String bloodLipidRatioArea) {
        this.bloodLipidRatioArea = bloodLipidRatioArea;
    }

    @Column(name = "bloodLipidStatusDescription", nullable = false, length = 500)
    public String getBloodLipidRatioStatusDescription() {
        return bloodLipidRatioStatusDescription;
    }

    public void setBloodLipidRatioStatusDescription(String bloodLipidRatioStatusDescription) {
        this.bloodLipidRatioStatusDescription = bloodLipidRatioStatusDescription;
    }

}
