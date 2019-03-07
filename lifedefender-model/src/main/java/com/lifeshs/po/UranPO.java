package com.lifeshs.po;

import java.util.Date;

public class UranPO {
    private Integer id;

    private Integer userId;

    private Long status;

    private String LEU;

    private String LEUStatusDescription;

    private String NIT;

    private String NITStatusDescription;

    private String UBG;

    private String UBGStatusDescription;

    private String PRO;

    private String PROStatusDescription;

    private Float pH;

    private String pHStatusDescription;

    private String pHArea;

    private String BLD;

    private String BLDStatusDescription;

    private Float SG;

    private String SGStatusDescription;

    private String SGArea;

    private String KET;

    private String KETStatusDescription;

    private String BIL;

    private String BILStatusDescription;

    private String GLU;

    private String GLUStatusDescription;

    private String VC;

    private String VCStatusDescription;

    private Date measureDate;

    private String deviceType;

    private Date createDate;

    private Short dataType;

    public UranPO(Integer id, Integer userId, Long status, String LEU, String LEUStatusDescription, String NIT, String NITStatusDescription, String UBG, String UBGStatusDescription, String PRO, String PROStatusDescription, Float pH, String pHStatusDescription, String pHArea, String BLD, String BLDStatusDescription, Float SG, String SGStatusDescription, String SGArea, String KET, String KETStatusDescription, String BIL, String BILStatusDescription, String GLU, String GLUStatusDescription, String VC, String VCStatusDescription, Date measureDate, String deviceType, Date createDate, Short dataType) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.LEU = LEU;
        this.LEUStatusDescription = LEUStatusDescription;
        this.NIT = NIT;
        this.NITStatusDescription = NITStatusDescription;
        this.UBG = UBG;
        this.UBGStatusDescription = UBGStatusDescription;
        this.PRO = PRO;
        this.PROStatusDescription = PROStatusDescription;
        this.pH = pH;
        this.pHStatusDescription = pHStatusDescription;
        this.pHArea = pHArea;
        this.BLD = BLD;
        this.BLDStatusDescription = BLDStatusDescription;
        this.SG = SG;
        this.SGStatusDescription = SGStatusDescription;
        this.SGArea = SGArea;
        this.KET = KET;
        this.KETStatusDescription = KETStatusDescription;
        this.BIL = BIL;
        this.BILStatusDescription = BILStatusDescription;
        this.GLU = GLU;
        this.GLUStatusDescription = GLUStatusDescription;
        this.VC = VC;
        this.VCStatusDescription = VCStatusDescription;
        this.measureDate = measureDate;
        this.deviceType = deviceType;
        this.createDate = createDate;
        this.dataType = dataType;
    }

    public UranPO() {
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

    public String getLEU() {
        return LEU;
    }

    public void setLEU(String LEU) {
        this.LEU = LEU == null ? null : LEU.trim();
    }

    public String getLEUStatusDescription() {
        return LEUStatusDescription;
    }

    public void setLEUStatusDescription(String LEUStatusDescription) {
        this.LEUStatusDescription = LEUStatusDescription == null ? null : LEUStatusDescription.trim();
    }

    public String getNIT() {
        return NIT;
    }

    public void setNIT(String NIT) {
        this.NIT = NIT == null ? null : NIT.trim();
    }

    public String getNITStatusDescription() {
        return NITStatusDescription;
    }

    public void setNITStatusDescription(String NITStatusDescription) {
        this.NITStatusDescription = NITStatusDescription == null ? null : NITStatusDescription.trim();
    }

    public String getUBG() {
        return UBG;
    }

    public void setUBG(String UBG) {
        this.UBG = UBG == null ? null : UBG.trim();
    }

    public String getUBGStatusDescription() {
        return UBGStatusDescription;
    }

    public void setUBGStatusDescription(String UBGStatusDescription) {
        this.UBGStatusDescription = UBGStatusDescription == null ? null : UBGStatusDescription.trim();
    }

    public String getPRO() {
        return PRO;
    }

    public void setPRO(String PRO) {
        this.PRO = PRO == null ? null : PRO.trim();
    }

    public String getPROStatusDescription() {
        return PROStatusDescription;
    }

    public void setPROStatusDescription(String PROStatusDescription) {
        this.PROStatusDescription = PROStatusDescription == null ? null : PROStatusDescription.trim();
    }

    public Float getpH() {
        return pH;
    }

    public void setpH(Float pH) {
        this.pH = pH;
    }

    public String getpHStatusDescription() {
        return pHStatusDescription;
    }

    public void setpHStatusDescription(String pHStatusDescription) {
        this.pHStatusDescription = pHStatusDescription == null ? null : pHStatusDescription.trim();
    }

    public String getpHArea() {
        return pHArea;
    }

    public void setpHArea(String pHArea) {
        this.pHArea = pHArea == null ? null : pHArea.trim();
    }

    public String getBLD() {
        return BLD;
    }

    public void setBLD(String BLD) {
        this.BLD = BLD == null ? null : BLD.trim();
    }

    public String getBLDStatusDescription() {
        return BLDStatusDescription;
    }

    public void setBLDStatusDescription(String BLDStatusDescription) {
        this.BLDStatusDescription = BLDStatusDescription == null ? null : BLDStatusDescription.trim();
    }

    public Float getSG() {
        return SG;
    }

    public void setSG(Float SG) {
        this.SG = SG;
    }

    public String getSGStatusDescription() {
        return SGStatusDescription;
    }

    public void setSGStatusDescription(String SGStatusDescription) {
        this.SGStatusDescription = SGStatusDescription == null ? null : SGStatusDescription.trim();
    }

    public String getSGArea() {
        return SGArea;
    }

    public void setSGArea(String SGArea) {
        this.SGArea = SGArea == null ? null : SGArea.trim();
    }

    public String getKET() {
        return KET;
    }

    public void setKET(String KET) {
        this.KET = KET == null ? null : KET.trim();
    }

    public String getKETStatusDescription() {
        return KETStatusDescription;
    }

    public void setKETStatusDescription(String KETStatusDescription) {
        this.KETStatusDescription = KETStatusDescription == null ? null : KETStatusDescription.trim();
    }

    public String getBIL() {
        return BIL;
    }

    public void setBIL(String BIL) {
        this.BIL = BIL == null ? null : BIL.trim();
    }

    public String getBILStatusDescription() {
        return BILStatusDescription;
    }

    public void setBILStatusDescription(String BILStatusDescription) {
        this.BILStatusDescription = BILStatusDescription == null ? null : BILStatusDescription.trim();
    }

    public String getGLU() {
        return GLU;
    }

    public void setGLU(String GLU) {
        this.GLU = GLU == null ? null : GLU.trim();
    }

    public String getGLUStatusDescription() {
        return GLUStatusDescription;
    }

    public void setGLUStatusDescription(String GLUStatusDescription) {
        this.GLUStatusDescription = GLUStatusDescription == null ? null : GLUStatusDescription.trim();
    }

    public String getVC() {
        return VC;
    }

    public void setVC(String VC) {
        this.VC = VC == null ? null : VC.trim();
    }

    public String getVCStatusDescription() {
        return VCStatusDescription;
    }

    public void setVCStatusDescription(String VCStatusDescription) {
        this.VCStatusDescription = VCStatusDescription == null ? null : VCStatusDescription.trim();
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