package com.lifeshs.po;

import java.util.Date;

public class BodyFatScalePO {
    private Integer id;

    private Integer userId;

    private Long status;

    private Float weight;

    private String weightArea;

    private String weightStatusDescription;

    private Float axungeRatio;

    private String axungeRatioArea;

    private String axungeRatioStatusDescription;

    private Float WHR;

    private String WHRArea;

    private String WHRStatusDescription;

    private Float BMI;

    private String BMIArea;

    private String BMIStatusDescription;

    private Float fatFreeWeight;

    private String fatFreeWeightArea;

    private String fatFreeWeightStatusDescription;

    private Float muscle;

    private String muscleArea;

    private String muscleStatusDescription;

    private Float moisture;

    private String moistureArea;

    private String moistureStatusDescription;

    private Float boneWeight;

    private String boneWeightArea;

    private String boneWeightStatusDescription;

    private Short bodyage;

    private String bodyageArea;

    private String bodyageStatusDescription;

    private Float baseMetabolism;

    private String baseMetabolismArea;

    private String baseMetabolismStatusDescription;

    private Float proteide;

    private String proteideArea;

    private String proteideStatusDescription;

    private Float visceralFat;

    private String visceralFatArea;

    private String visceralFatStatusDescription;

    private Date measureDate;

    private String deviceType;

    private Date createDate;

    private Short dataType;

    public BodyFatScalePO(Integer id, Integer userId, Long status, Float weight, String weightArea, String weightStatusDescription, Float axungeRatio, String axungeRatioArea, String axungeRatioStatusDescription, Float WHR, String WHRArea, String WHRStatusDescription, Float BMI, String BMIArea, String BMIStatusDescription, Float fatFreeWeight, String fatFreeWeightArea, String fatFreeWeightStatusDescription, Float muscle, String muscleArea, String muscleStatusDescription, Float moisture, String moistureArea, String moistureStatusDescription, Float boneWeight, String boneWeightArea, String boneWeightStatusDescription, Short bodyage, String bodyageArea, String bodyageStatusDescription, Float baseMetabolism, String baseMetabolismArea, String baseMetabolismStatusDescription, Float proteide, String proteideArea, String proteideStatusDescription, Float visceralFat, String visceralFatArea, String visceralFatStatusDescription, Date measureDate, String deviceType, Date createDate, Short dataType) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.weight = weight;
        this.weightArea = weightArea;
        this.weightStatusDescription = weightStatusDescription;
        this.axungeRatio = axungeRatio;
        this.axungeRatioArea = axungeRatioArea;
        this.axungeRatioStatusDescription = axungeRatioStatusDescription;
        this.WHR = WHR;
        this.WHRArea = WHRArea;
        this.WHRStatusDescription = WHRStatusDescription;
        this.BMI = BMI;
        this.BMIArea = BMIArea;
        this.BMIStatusDescription = BMIStatusDescription;
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

    public BodyFatScalePO() {
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

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getWeightArea() {
        return weightArea;
    }

    public void setWeightArea(String weightArea) {
        this.weightArea = weightArea == null ? null : weightArea.trim();
    }

    public String getWeightStatusDescription() {
        return weightStatusDescription;
    }

    public void setWeightStatusDescription(String weightStatusDescription) {
        this.weightStatusDescription = weightStatusDescription == null ? null : weightStatusDescription.trim();
    }

    public Float getAxungeRatio() {
        return axungeRatio;
    }

    public void setAxungeRatio(Float axungeRatio) {
        this.axungeRatio = axungeRatio;
    }

    public String getAxungeRatioArea() {
        return axungeRatioArea;
    }

    public void setAxungeRatioArea(String axungeRatioArea) {
        this.axungeRatioArea = axungeRatioArea == null ? null : axungeRatioArea.trim();
    }

    public String getAxungeRatioStatusDescription() {
        return axungeRatioStatusDescription;
    }

    public void setAxungeRatioStatusDescription(String axungeRatioStatusDescription) {
        this.axungeRatioStatusDescription = axungeRatioStatusDescription == null ? null : axungeRatioStatusDescription.trim();
    }

    public Float getWHR() {
        return WHR;
    }

    public void setWHR(Float WHR) {
        this.WHR = WHR;
    }

    public String getWHRArea() {
        return WHRArea;
    }

    public void setWHRArea(String WHRArea) {
        this.WHRArea = WHRArea == null ? null : WHRArea.trim();
    }

    public String getWHRStatusDescription() {
        return WHRStatusDescription;
    }

    public void setWHRStatusDescription(String WHRStatusDescription) {
        this.WHRStatusDescription = WHRStatusDescription == null ? null : WHRStatusDescription.trim();
    }

    public Float getBMI() {
        return BMI;
    }

    public void setBMI(Float BMI) {
        this.BMI = BMI;
    }

    public String getBMIArea() {
        return BMIArea;
    }

    public void setBMIArea(String BMIArea) {
        this.BMIArea = BMIArea == null ? null : BMIArea.trim();
    }

    public String getBMIStatusDescription() {
        return BMIStatusDescription;
    }

    public void setBMIStatusDescription(String BMIStatusDescription) {
        this.BMIStatusDescription = BMIStatusDescription == null ? null : BMIStatusDescription.trim();
    }

    public Float getFatFreeWeight() {
        return fatFreeWeight;
    }

    public void setFatFreeWeight(Float fatFreeWeight) {
        this.fatFreeWeight = fatFreeWeight;
    }

    public String getFatFreeWeightArea() {
        return fatFreeWeightArea;
    }

    public void setFatFreeWeightArea(String fatFreeWeightArea) {
        this.fatFreeWeightArea = fatFreeWeightArea == null ? null : fatFreeWeightArea.trim();
    }

    public String getFatFreeWeightStatusDescription() {
        return fatFreeWeightStatusDescription;
    }

    public void setFatFreeWeightStatusDescription(String fatFreeWeightStatusDescription) {
        this.fatFreeWeightStatusDescription = fatFreeWeightStatusDescription == null ? null : fatFreeWeightStatusDescription.trim();
    }

    public Float getMuscle() {
        return muscle;
    }

    public void setMuscle(Float muscle) {
        this.muscle = muscle;
    }

    public String getMuscleArea() {
        return muscleArea;
    }

    public void setMuscleArea(String muscleArea) {
        this.muscleArea = muscleArea == null ? null : muscleArea.trim();
    }

    public String getMuscleStatusDescription() {
        return muscleStatusDescription;
    }

    public void setMuscleStatusDescription(String muscleStatusDescription) {
        this.muscleStatusDescription = muscleStatusDescription == null ? null : muscleStatusDescription.trim();
    }

    public Float getMoisture() {
        return moisture;
    }

    public void setMoisture(Float moisture) {
        this.moisture = moisture;
    }

    public String getMoistureArea() {
        return moistureArea;
    }

    public void setMoistureArea(String moistureArea) {
        this.moistureArea = moistureArea == null ? null : moistureArea.trim();
    }

    public String getMoistureStatusDescription() {
        return moistureStatusDescription;
    }

    public void setMoistureStatusDescription(String moistureStatusDescription) {
        this.moistureStatusDescription = moistureStatusDescription == null ? null : moistureStatusDescription.trim();
    }

    public Float getBoneWeight() {
        return boneWeight;
    }

    public void setBoneWeight(Float boneWeight) {
        this.boneWeight = boneWeight;
    }

    public String getBoneWeightArea() {
        return boneWeightArea;
    }

    public void setBoneWeightArea(String boneWeightArea) {
        this.boneWeightArea = boneWeightArea == null ? null : boneWeightArea.trim();
    }

    public String getBoneWeightStatusDescription() {
        return boneWeightStatusDescription;
    }

    public void setBoneWeightStatusDescription(String boneWeightStatusDescription) {
        this.boneWeightStatusDescription = boneWeightStatusDescription == null ? null : boneWeightStatusDescription.trim();
    }

    public Short getBodyage() {
        return bodyage;
    }

    public void setBodyage(Short bodyage) {
        this.bodyage = bodyage;
    }

    public String getBodyageArea() {
        return bodyageArea;
    }

    public void setBodyageArea(String bodyageArea) {
        this.bodyageArea = bodyageArea == null ? null : bodyageArea.trim();
    }

    public String getBodyageStatusDescription() {
        return bodyageStatusDescription;
    }

    public void setBodyageStatusDescription(String bodyageStatusDescription) {
        this.bodyageStatusDescription = bodyageStatusDescription == null ? null : bodyageStatusDescription.trim();
    }

    public Float getBaseMetabolism() {
        return baseMetabolism;
    }

    public void setBaseMetabolism(Float baseMetabolism) {
        this.baseMetabolism = baseMetabolism;
    }

    public String getBaseMetabolismArea() {
        return baseMetabolismArea;
    }

    public void setBaseMetabolismArea(String baseMetabolismArea) {
        this.baseMetabolismArea = baseMetabolismArea == null ? null : baseMetabolismArea.trim();
    }

    public String getBaseMetabolismStatusDescription() {
        return baseMetabolismStatusDescription;
    }

    public void setBaseMetabolismStatusDescription(String baseMetabolismStatusDescription) {
        this.baseMetabolismStatusDescription = baseMetabolismStatusDescription == null ? null : baseMetabolismStatusDescription.trim();
    }

    public Float getProteide() {
        return proteide;
    }

    public void setProteide(Float proteide) {
        this.proteide = proteide;
    }

    public String getProteideArea() {
        return proteideArea;
    }

    public void setProteideArea(String proteideArea) {
        this.proteideArea = proteideArea == null ? null : proteideArea.trim();
    }

    public String getProteideStatusDescription() {
        return proteideStatusDescription;
    }

    public void setProteideStatusDescription(String proteideStatusDescription) {
        this.proteideStatusDescription = proteideStatusDescription == null ? null : proteideStatusDescription.trim();
    }

    public Float getVisceralFat() {
        return visceralFat;
    }

    public void setVisceralFat(Float visceralFat) {
        this.visceralFat = visceralFat;
    }

    public String getVisceralFatArea() {
        return visceralFatArea;
    }

    public void setVisceralFatArea(String visceralFatArea) {
        this.visceralFatArea = visceralFatArea == null ? null : visceralFatArea.trim();
    }

    public String getVisceralFatStatusDescription() {
        return visceralFatStatusDescription;
    }

    public void setVisceralFatStatusDescription(String visceralFatStatusDescription) {
        this.visceralFatStatusDescription = visceralFatStatusDescription == null ? null : visceralFatStatusDescription.trim();
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