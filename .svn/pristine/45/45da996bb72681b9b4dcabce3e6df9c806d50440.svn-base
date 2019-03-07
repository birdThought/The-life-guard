package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_measure_uran
 */
@Table(name = "t_measure_uran", schema = "")
@SuppressWarnings("serial")
public class TMeasureUran implements Serializable {

    private Integer id;

    /** 用户ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 白细胞 */
    private String lEU;

    /** 白细胞状态描述 */
    private String lEUStatusDescription;

    /** 亚硝酸盐 */
    private String nIT;

    /** 亚硝酸盐状态描述 */
    private String nITStatusDescription;

    /** 尿胆原 */
    private String uBG;

    /** 尿胆原状态描述 */
    private String uBGStatusDescription;

    /** 蛋白质 */
    private String pRO;

    /** 蛋白质状态描述 */
    private String pROStatusDescription;

    /** pH值 */
    private Float pH;

    /** pH状态描述 */
    private String pHStatusDescription;

    /** 潜血 */
    private String bLD;

    /** 潜血状态描述 */
    private String bLDStatusDescription;

    /** 比重 */
    private Float sG;

    /** 比重状态描述 */
    private String sGStatusDescription;

    /** 比重健康范围 */
    private String sGArea;

    /** 酮体 */
    private String kET;

    /** 酮体状态描述 */
    private String kETStatusDescription;

    /** 胆红素 */
    private String bIL;

    /** 胆红素状态描述 */
    private String bILStatusDescription;

    /** 葡萄糖 */
    private String gLU;

    /** 葡萄糖状态描述 */
    private String gLUStatusDescription;

    /** 维生素C */
    private String vC;

    /** 维生素C状态描述 */
    private String vCStatusDescription;

    /** 测量时间 */
    private java.util.Date measureDate;

    /** 终端类型 */
    private String deviceType;

    /** 创建时间 */
    private java.util.Date createDate;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    public TMeasureUran() {
        super();
    }

    public TMeasureUran(Integer id, Integer userId, Long status, String lEU, String lEUStatusDescription, String nIT,
            String nITStatusDescription, String uBG, String uBGStatusDescription, String pRO,
            String pROStatusDescription, Float pH, String pHStatusDescription, String bLD, String bLDStatusDescription,
            Float sG, String sGStatusDescription, String sGArea, String kET, String kETStatusDescription, String bIL,
            String bILStatusDescription, String gLU, String gLUStatusDescription, String vC, String vCStatusDescription,
            java.util.Date measureDate, String deviceType, java.util.Date createDate, Integer dataType) {
        super();
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.lEU = lEU;
        this.lEUStatusDescription = lEUStatusDescription;
        this.nIT = nIT;
        this.nITStatusDescription = nITStatusDescription;
        this.uBG = uBG;
        this.uBGStatusDescription = uBGStatusDescription;
        this.pRO = pRO;
        this.pROStatusDescription = pROStatusDescription;
        this.pH = pH;
        this.pHStatusDescription = pHStatusDescription;
        this.bLD = bLD;
        this.bLDStatusDescription = bLDStatusDescription;
        this.sG = sG;
        this.sGStatusDescription = sGStatusDescription;
        this.sGArea = sGArea;
        this.kET = kET;
        this.kETStatusDescription = kETStatusDescription;
        this.bIL = bIL;
        this.bILStatusDescription = bILStatusDescription;
        this.gLU = gLU;
        this.gLUStatusDescription = gLUStatusDescription;
        this.vC = vC;
        this.vCStatusDescription = vCStatusDescription;
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

    @Column(name = "LEU", nullable = false, length = 5)
    public String getLEU() {
        return lEU;
    }

    public void setLEU(String lEU) {
        this.lEU = lEU;
    }

    @Column(name = "LEUStatusDescription", nullable = false, length = 500)
    public String getLEUStatusDescription() {
        return lEUStatusDescription;
    }

    public void setLEUStatusDescription(String lEUStatusDescription) {
        this.lEUStatusDescription = lEUStatusDescription;
    }

    @Column(name = "NIT", nullable = false, length = 5)
    public String getNIT() {
        return nIT;
    }

    public void setNIT(String nIT) {
        this.nIT = nIT;
    }

    @Column(name = "NITStatusDescription", nullable = false, length = 500)
    public String getNITStatusDescription() {
        return nITStatusDescription;
    }

    public void setNITStatusDescription(String nITStatusDescription) {
        this.nITStatusDescription = nITStatusDescription;
    }

    @Column(name = "UBG", nullable = false, length = 5)
    public String getUBG() {
        return uBG;
    }

    public void setUBG(String uBG) {
        this.uBG = uBG;
    }

    @Column(name = "UBGStatusDescription", nullable = false, length = 500)
    public String getUBGStatusDescription() {
        return uBGStatusDescription;
    }

    public void setUBGStatusDescription(String uBGStatusDescription) {
        this.uBGStatusDescription = uBGStatusDescription;
    }

    @Column(name = "PRO", nullable = false, length = 5)
    public String getPRO() {
        return pRO;
    }

    public void setPRO(String pRO) {
        this.pRO = pRO;
    }

    @Column(name = "PROStatusDescription", nullable = false, length = 500)
    public String getPROStatusDescription() {
        return pROStatusDescription;
    }

    public void setPROStatusDescription(String pROStatusDescription) {
        this.pROStatusDescription = pROStatusDescription;
    }

    @Column(name = "pH", nullable = false, length = 3)
    public Float getPH() {
        return pH;
    }

    public void setPH(Float pH) {
        this.pH = pH;
    }

    @Column(name = "pHStatusDescription", nullable = false, length = 500)
    public String getPHStatusDescription() {
        return pHStatusDescription;
    }

    public void setPHStatusDescription(String pHStatusDescription) {
        this.pHStatusDescription = pHStatusDescription;
    }

    @Column(name = "BLD", nullable = false, length = 5)
    public String getBLD() {
        return bLD;
    }

    public void setBLD(String bLD) {
        this.bLD = bLD;
    }

    @Column(name = "BLDStatusDescription", nullable = false, length = 500)
    public String getBLDStatusDescription() {
        return bLDStatusDescription;
    }

    public void setBLDStatusDescription(String bLDStatusDescription) {
        this.bLDStatusDescription = bLDStatusDescription;
    }

    @Column(name = "SG", nullable = false, length = 6)
    public Float getSG() {
        return sG;
    }

    public void setSG(Float sG) {
        this.sG = sG;
    }

    @Column(name = "SGStatusDescription", nullable = false, length = 500)
    public String getSGStatusDescription() {
        return sGStatusDescription;
    }

    public void setSGStatusDescription(String sGStatusDescription) {
        this.sGStatusDescription = sGStatusDescription;
    }

    @Column(name = "SGArea", nullable = false, length = 20)
    public String getSGArea() {
        return sGArea;
    }

    public void setSGArea(String sGArea) {
        this.sGArea = sGArea;
    }

    @Column(name = "KET", nullable = false, length = 5)
    public String getKET() {
        return kET;
    }

    public void setKET(String kET) {
        this.kET = kET;
    }

    @Column(name = "KETStatusDescription", nullable = false, length = 500)
    public String getKETStatusDescription() {
        return kETStatusDescription;
    }

    public void setKETStatusDescription(String kETStatusDescription) {
        this.kETStatusDescription = kETStatusDescription;
    }

    @Column(name = "BIL", nullable = false, length = 5)
    public String getBIL() {
        return bIL;
    }

    public void setBIL(String bIL) {
        this.bIL = bIL;
    }

    @Column(name = "BILStatusDescription", nullable = false, length = 500)
    public String getBILStatusDescription() {
        return bILStatusDescription;
    }

    public void setBILStatusDescription(String bILStatusDescription) {
        this.bILStatusDescription = bILStatusDescription;
    }

    @Column(name = "GLU", nullable = false, length = 5)
    public String getGLU() {
        return gLU;
    }

    public void setGLU(String gLU) {
        this.gLU = gLU;
    }

    @Column(name = "GLUStatusDescription", nullable = false, length = 500)
    public String getGLUStatusDescription() {
        return gLUStatusDescription;
    }

    public void setGLUStatusDescription(String gLUStatusDescription) {
        this.gLUStatusDescription = gLUStatusDescription;
    }

    @Column(name = "VC", nullable = false, length = 5)
    public String getVC() {
        return vC;
    }

    public void setVC(String vC) {
        this.vC = vC;
    }

    @Column(name = "VCStatusDescription", nullable = false, length = 500)
    public String getVCStatusDescription() {
        return vCStatusDescription;
    }

    public void setVCStatusDescription(String vCStatusDescription) {
        this.vCStatusDescription = vCStatusDescription;
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
        return "TMeasureUran [id=" + id + ", userId=" + userId + ", status=" + status + ", lEU=" + lEU
                + ", lEUStatusDescription=" + lEUStatusDescription + ", nIT=" + nIT + ", nITStatusDescription="
                + nITStatusDescription + ", uBG=" + uBG + ", uBGStatusDescription=" + uBGStatusDescription + ", pRO="
                + pRO + ", pROStatusDescription=" + pROStatusDescription + ", pH=" + pH + ", pHStatusDescription="
                + pHStatusDescription + ", bLD=" + bLD + ", bLDStatusDescription=" + bLDStatusDescription + ", sG=" + sG
                + ", sGStatusDescription=" + sGStatusDescription + ", sGArea=" + sGArea + ", kET=" + kET
                + ", kETStatusDescription=" + kETStatusDescription + ", bIL=" + bIL + ", bILStatusDescription="
                + bILStatusDescription + ", gLU=" + gLU + ", gLUStatusDescription=" + gLUStatusDescription + ", vC="
                + vC + ", vCStatusDescription=" + vCStatusDescription + ", measureDate=" + measureDate + ", deviceType="
                + deviceType + ", createDate=" + createDate + ", dataType=" + dataType + "]";
    }
}
