package com.lifeshs.entity.device;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.lifeshs.common.constants.common.HealthRank;

/**
 * t_measure_bodyfatscale
 */
@Table(name = "t_measure_bodyfatscale", schema = "")
@SuppressWarnings("serial")
public class TMeasureBodyfatscale implements Serializable {
    /** 体脂秤 */
    private Integer id;

    /** 用户设备ID */
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 体重 */
    private Float weight;

    /** 体重正常范围 */
    private String weightArea;

    private HealthRank weightStatus;

    /** 体重状态描述 */
    private String weightStatusDescription;

    /** 体脂肪率 */
    private Float axungeRatio;

    /** 体脂肪率正常范围 */
    private String axungeRatioArea;

    private HealthRank axungeRatioStatus;

    /** 体脂肪率状态描述 */
    private String axungeRatioStatusDescription;

    /** 腰臀比 */
    private Float wHR;

    /** 腰臀比正常范围 */
    private String wHRArea;

    private HealthRank wHRStatus;

    /** 腰臀比状态描述 */
    private String wHRStatusDescription;

    /** BMI */
    private Float bMI;

    /** BMI正常范围 */
    private String bMIArea;

    private HealthRank bMIStatus;

    /** BMI状态描述 */
    private String bMIStatusDescription;

    /** 去脂体重 */
    private Float fatFreeWeight;

    /** 去脂体重正常范围 */
    private String fatFreeWeightArea;

    private HealthRank fatFreeWeightStatus;

    /** 去脂体重状态描述 */
    private String fatFreeWeightStatusDescription;

    /** 人体肌肉 */
    private Float muscle;

    /** 人体肌肉正常范围 */
    private String muscleArea;

    private HealthRank muscleStatus;

    /** 人体肌肉状态描述 */
    private String muscleStatusDescription;

    /** 人体水份 */
    private Float moisture;

    /** 人体水份正常范围 */
    private String moistureArea;

    private HealthRank moistureStatus;

    /** 人体水份状态描述 */
    private String moistureStatusDescription;

    /** 骨骼重量 */
    private Float boneWeight;

    /** 骨骼重量正常范围 */
    private String boneWeightArea;

    private HealthRank boneWeightStatus;

    /** 骨骼重量状态描述 */
    private String boneWeightStatusDescription;

    /** 体年龄 */
    private Integer bodyage;

    /** 体年龄正常范围 */
    private String bodyageArea;

    private HealthRank bodyageStatus;

    /** 体年龄状态描述 */
    private String bodyageStatusDescription;

    /** 基础代谢 */
    private Float baseMetabolism;

    /** 基础代谢正常范围 */
    private String baseMetabolismArea;

    private HealthRank baseMetabolismStatus;

    /** 基础代谢状态描述 */
    private String baseMetabolismStatusDescription;

    /** 蛋白质 */
    private Float proteide;

    /** 蛋白质正常范围 */
    private String proteideArea;

    private HealthRank proteideStatus;

    /** 蛋白质状态描述 */
    private String proteideStatusDescription;

    /** 内脏脂肪 */
    private Float visceralFat;

    /** 内脏脂肪正常范围 */
    private String visceralFatArea;

    private HealthRank visceralFatStatus;

    /** 内脏脂肪状态描述 */
    private String visceralFatStatusDescription;

    /** 测量时间 */
    private java.util.Date measureDate;

    /** 终端类型 */
    private String deviceType;

    /** 创建时间 */
    private java.util.Date createDate;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;

    public TMeasureBodyfatscale() {
        super();
    }

    public TMeasureBodyfatscale(Integer id, Integer userId, Long status, Float weight, String weightArea,
            String weightStatusDescription, Float axungeRatio, String axungeRatioArea,
            String axungeRatioStatusDescription, Float wHR, String wHRArea, String wHRStatusDescription, Float bMI,
            String bMIArea, String bMIStatusDescription, Float fatFreeWeight, String fatFreeWeightArea,
            String fatFreeWeightStatusDescription, Float muscle, String muscleArea, String muscleStatusDescription,
            Float moisture, String moistureArea, String moistureStatusDescription, Float boneWeight,
            String boneWeightArea, String boneWeightStatusDescription, Integer bodyage, String bodyageArea,
            String bodyageStatusDescription, Float baseMetabolism, String baseMetabolismArea,
            String baseMetabolismStatusDescription, Float proteide, String proteideArea,
            String proteideStatusDescription, Float visceralFat, String visceralFatArea,
            String visceralFatStatusDescription, java.util.Date measureDate, String deviceType,
            java.util.Date createDate, Integer dataType) {
        super();
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.weight = weight;
        this.weightArea = weightArea;
        this.weightStatusDescription = weightStatusDescription;
        this.axungeRatio = axungeRatio;
        this.axungeRatioArea = axungeRatioArea;
        this.axungeRatioStatusDescription = axungeRatioStatusDescription;
        this.wHR = wHR;
        this.wHRArea = wHRArea;
        this.wHRStatusDescription = wHRStatusDescription;
        this.bMI = bMI;
        this.bMIArea = bMIArea;
        this.bMIStatusDescription = bMIStatusDescription;
        this.fatFreeWeight = fatFreeWeight;
        this.fatFreeWeightArea = fatFreeWeightArea;
        this.fatFreeWeightStatusDescription = fatFreeWeightStatusDescription;
        this.muscle = muscle;
        this.muscleArea = muscleArea;
        this.muscleStatusDescription = muscleStatusDescription;
        this.moisture = moisture;
        this.moistureArea = moistureArea;
        this.moistureStatusDescription = moistureStatusDescription;
        this.boneWeight = boneWeight;
        this.boneWeightArea = boneWeightArea;
        this.boneWeightStatusDescription = boneWeightStatusDescription;
        this.bodyage = bodyage;
        this.bodyageArea = bodyageArea;
        this.bodyageStatusDescription = bodyageStatusDescription;
        this.baseMetabolism = baseMetabolism;
        this.baseMetabolismArea = baseMetabolismArea;
        this.baseMetabolismStatusDescription = baseMetabolismStatusDescription;
        this.proteide = proteide;
        this.proteideArea = proteideArea;
        this.proteideStatusDescription = proteideStatusDescription;
        this.visceralFat = visceralFat;
        this.visceralFatArea = visceralFatArea;
        this.visceralFatStatusDescription = visceralFatStatusDescription;
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

    @Column(name = "weight", nullable = false, length = 6)
    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    @Column(name = "weightArea", nullable = false, length = 20)
    public String getWeightArea() {
        return weightArea;
    }

    public void setWeightArea(String weightArea) {
        this.weightArea = weightArea;
    }

    @Column(name = "weightStatusDescription", nullable = false, length = 500)
    public String getWeightStatusDescription() {
        return weightStatusDescription;
    }

    public void setWeightStatusDescription(String weightStatusDescription) {
        this.weightStatusDescription = weightStatusDescription;
    }

    @Column(name = "axungeRatio", nullable = false, length = 6)
    public Float getAxungeRatio() {
        return axungeRatio;
    }

    public void setAxungeRatio(Float axungeRatio) {
        this.axungeRatio = axungeRatio;
    }

    @Column(name = "axungeRatioArea", nullable = false, length = 20)
    public String getAxungeRatioArea() {
        return axungeRatioArea;
    }

    public void setAxungeRatioArea(String axungeRatioArea) {
        this.axungeRatioArea = axungeRatioArea;
    }

    @Column(name = "axungeRatioStatusDescription", nullable = false, length = 500)
    public String getAxungeRatioStatusDescription() {
        return axungeRatioStatusDescription;
    }

    public void setAxungeRatioStatusDescription(String axungeRatioStatusDescription) {
        this.axungeRatioStatusDescription = axungeRatioStatusDescription;
    }

    @Column(name = "WHR", nullable = false, length = 6)
    public Float getWHR() {
        return wHR;
    }

    public void setWHR(Float wHR) {
        this.wHR = wHR;
    }

    @Column(name = "WHRArea", nullable = false, length = 20)
    public String getWHRArea() {
        return wHRArea;
    }

    public void setWHRArea(String wHRArea) {
        this.wHRArea = wHRArea;
    }

    @Column(name = "WHRStatusDescription", nullable = false, length = 500)
    public String getWHRStatusDescription() {
        return wHRStatusDescription;
    }

    public void setWHRStatusDescription(String wHRStatusDescription) {
        this.wHRStatusDescription = wHRStatusDescription;
    }

    @Column(name = "BMI", nullable = false, length = 6)
    public Float getBMI() {
        return bMI;
    }

    public void setBMI(Float bMI) {
        this.bMI = bMI;
    }

    @Column(name = "BMIArea", nullable = false, length = 20)
    public String getBMIArea() {
        return bMIArea;
    }

    public void setBMIArea(String bMIArea) {
        this.bMIArea = bMIArea;
    }

    @Column(name = "BMIStatusDescription", nullable = false, length = 500)
    public String getBMIStatusDescription() {
        return bMIStatusDescription;
    }

    public void setBMIStatusDescription(String bMIStatusDescription) {
        this.bMIStatusDescription = bMIStatusDescription;
    }

    @Column(name = "fatFreeWeight", nullable = false, length = 6)
    public Float getFatFreeWeight() {
        return fatFreeWeight;
    }

    public void setFatFreeWeight(Float fatFreeWeight) {
        this.fatFreeWeight = fatFreeWeight;
    }

    @Column(name = "fatFreeWeightArea", nullable = false, length = 20)
    public String getFatFreeWeightArea() {
        return fatFreeWeightArea;
    }

    public void setFatFreeWeightArea(String fatFreeWeightArea) {
        this.fatFreeWeightArea = fatFreeWeightArea;
    }

    @Column(name = "fatFreeWeightStatusDescription", nullable = false, length = 500)
    public String getFatFreeWeightStatusDescription() {
        return fatFreeWeightStatusDescription;
    }

    public void setFatFreeWeightStatusDescription(String fatFreeWeightStatusDescription) {
        this.fatFreeWeightStatusDescription = fatFreeWeightStatusDescription;
    }

    @Column(name = "muscle", nullable = false, length = 6)
    public Float getMuscle() {
        return muscle;
    }

    public void setMuscle(Float muscle) {
        this.muscle = muscle;
    }

    @Column(name = "muscleArea", nullable = false, length = 20)
    public String getMuscleArea() {
        return muscleArea;
    }

    public void setMuscleArea(String muscleArea) {
        this.muscleArea = muscleArea;
    }

    @Column(name = "muscleStatusDescription", nullable = false, length = 500)
    public String getMuscleStatusDescription() {
        return muscleStatusDescription;
    }

    public void setMuscleStatusDescription(String muscleStatusDescription) {
        this.muscleStatusDescription = muscleStatusDescription;
    }

    @Column(name = "moisture", nullable = false, length = 6)
    public Float getMoisture() {
        return moisture;
    }

    public void setMoisture(Float moisture) {
        this.moisture = moisture;
    }

    @Column(name = "moistureArea", nullable = false, length = 20)
    public String getMoistureArea() {
        return moistureArea;
    }

    public void setMoistureArea(String moistureArea) {
        this.moistureArea = moistureArea;
    }

    @Column(name = "moistureStatusDescription", nullable = false, length = 500)
    public String getMoistureStatusDescription() {
        return moistureStatusDescription;
    }

    public void setMoistureStatusDescription(String moistureStatusDescription) {
        this.moistureStatusDescription = moistureStatusDescription;
    }

    @Column(name = "boneWeight", nullable = false, length = 6)
    public Float getBoneWeight() {
        return boneWeight;
    }

    public void setBoneWeight(Float boneWeight) {
        this.boneWeight = boneWeight;
    }

    @Column(name = "boneWeightArea", nullable = false, length = 20)
    public String getBoneWeightArea() {
        return boneWeightArea;
    }

    public void setBoneWeightArea(String boneWeightArea) {
        this.boneWeightArea = boneWeightArea;
    }

    @Column(name = "boneWeightStatusDescription", nullable = false, length = 500)
    public String getBoneWeightStatusDescription() {
        return boneWeightStatusDescription;
    }

    public void setBoneWeightStatusDescription(String boneWeightStatusDescription) {
        this.boneWeightStatusDescription = boneWeightStatusDescription;
    }

    @Column(name = "bodyage", nullable = false, length = 6)
    public Integer getBodyage() {
        return bodyage;
    }

    public void setBodyage(Integer bodyage) {
        this.bodyage = bodyage;
    }

    @Column(name = "bodyageArea", nullable = false, length = 20)
    public String getBodyageArea() {
        return bodyageArea;
    }

    public void setBodyageArea(String bodyageArea) {
        this.bodyageArea = bodyageArea;
    }

    @Column(name = "bodyageStatusDescription", nullable = false, length = 500)
    public String getBodyageStatusDescription() {
        return bodyageStatusDescription;
    }

    public void setBodyageStatusDescription(String bodyageStatusDescription) {
        this.bodyageStatusDescription = bodyageStatusDescription;
    }

    @Column(name = "baseMetabolism", nullable = false, length = 10)
    public Float getBaseMetabolism() {
        return baseMetabolism;
    }

    public void setBaseMetabolism(Float baseMetabolism) {
        this.baseMetabolism = baseMetabolism;
    }

    @Column(name = "baseMetabolismArea", nullable = false, length = 20)
    public String getBaseMetabolismArea() {
        return baseMetabolismArea;
    }

    public void setBaseMetabolismArea(String baseMetabolismArea) {
        this.baseMetabolismArea = baseMetabolismArea;
    }

    @Column(name = "baseMetabolismStatusDescription", nullable = false, length = 500)
    public String getBaseMetabolismStatusDescription() {
        return baseMetabolismStatusDescription;
    }

    public void setBaseMetabolismStatusDescription(String baseMetabolismStatusDescription) {
        this.baseMetabolismStatusDescription = baseMetabolismStatusDescription;
    }

    @Column(name = "proteide", nullable = false, length = 6)
    public Float getProteide() {
        return proteide;
    }

    public void setProteide(Float proteide) {
        this.proteide = proteide;
    }

    @Column(name = "proteideArea", nullable = false, length = 20)
    public String getProteideArea() {
        return proteideArea;
    }

    public void setProteideArea(String proteideArea) {
        this.proteideArea = proteideArea;
    }

    @Column(name = "proteideStatusDescription", nullable = false, length = 500)
    public String getProteideStatusDescription() {
        return proteideStatusDescription;
    }

    public void setProteideStatusDescription(String proteideStatusDescription) {
        this.proteideStatusDescription = proteideStatusDescription;
    }

    @Column(name = "visceralFat", nullable = false, length = 6)
    public Float getVisceralFat() {
        return visceralFat;
    }

    public void setVisceralFat(Float visceralFat) {
        this.visceralFat = visceralFat;
    }

    @Column(name = "visceralFatArea", nullable = false, length = 20)
    public String getVisceralFatArea() {
        return visceralFatArea;
    }

    public void setVisceralFatArea(String visceralFatArea) {
        this.visceralFatArea = visceralFatArea;
    }

    @Column(name = "visceralFatStatusDescription", nullable = false, length = 500)
    public String getVisceralFatStatusDescription() {
        return visceralFatStatusDescription;
    }

    public void setVisceralFatStatusDescription(String visceralFatStatusDescription) {
        this.visceralFatStatusDescription = visceralFatStatusDescription;
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

    public HealthRank getWeightStatus() {
        return weightStatus;
    }

    public void setWeightStatus(HealthRank weightStatus) {
        this.weightStatus = weightStatus;
    }

    public HealthRank getAxungeRatioStatus() {
        return axungeRatioStatus;
    }

    public void setAxungeRatioStatus(HealthRank axungeRatioStatus) {
        this.axungeRatioStatus = axungeRatioStatus;
    }

    public HealthRank getWHRStatus() {
        return wHRStatus;
    }

    public void setWHRStatus(HealthRank wHRStatus) {
        this.wHRStatus = wHRStatus;
    }

    public HealthRank getBMIStatus() {
        return bMIStatus;
    }

    public void setBMIStatus(HealthRank bMIStatus) {
        this.bMIStatus = bMIStatus;
    }

    public HealthRank getFatFreeWeightStatus() {
        return fatFreeWeightStatus;
    }

    public void setFatFreeWeightStatus(HealthRank fatFreeWeightStatus) {
        this.fatFreeWeightStatus = fatFreeWeightStatus;
    }

    public HealthRank getMuscleStatus() {
        return muscleStatus;
    }

    public void setMuscleStatus(HealthRank muscleStatus) {
        this.muscleStatus = muscleStatus;
    }

    public HealthRank getMoistureStatus() {
        return moistureStatus;
    }

    public void setMoistureStatus(HealthRank moistureStatus) {
        this.moistureStatus = moistureStatus;
    }

    public HealthRank getBoneWeightStatus() {
        return boneWeightStatus;
    }

    public void setBoneWeightStatus(HealthRank boneWeightStatus) {
        this.boneWeightStatus = boneWeightStatus;
    }

    public HealthRank getBodyageStatus() {
        return bodyageStatus;
    }

    public void setBodyageStatus(HealthRank bodyageStatus) {
        this.bodyageStatus = bodyageStatus;
    }

    public HealthRank getBaseMetabolismStatus() {
        return baseMetabolismStatus;
    }

    public void setBaseMetabolismStatus(HealthRank baseMetabolismStatus) {
        this.baseMetabolismStatus = baseMetabolismStatus;
    }

    public HealthRank getProteideStatus() {
        return proteideStatus;
    }

    public void setProteideStatus(HealthRank proteideStatus) {
        this.proteideStatus = proteideStatus;
    }

    public HealthRank getVisceralFatStatus() {
        return visceralFatStatus;
    }

    public void setVisceralFatStatus(HealthRank visceralFatStatus) {
        this.visceralFatStatus = visceralFatStatus;
    }

}