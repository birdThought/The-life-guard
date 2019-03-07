package com.lifeshs.pojo.member;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.lifeshs.pojo.paper.PaperOptionDTO;

import lombok.Data;

/**
 * 用户个人档案DTO
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月17日 上午11:14:32
 */
public @Data class UserRecordDTO {

    private int id;
    /** 用户ID */
    private int userId;
    /** 身高 */
    private Float height;
    /** 体重 */
    private Float weight;
    /** 性别，0_女，1_男 */
    private Boolean gender;
    /** 生日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;
    /** 腰围 */
    private Float waist;
    /** 胸围 */
    private Float bust;
    /** 臀围 */
    private Float hip;
    /** 心率是否异常 */
    private Boolean heartRateStatus;
    /** 血压（收缩压/舒张压）是否异常 */
    private Boolean bloodPressureStatus;
    /** 血氧饱和度是否异常 */
    private Boolean saturationStatus;
    /** 肺活量分数 */
    private Integer vitalCapacityScore;
    /** 腰臀比是否异常 */
    private Boolean WHRStatus;
    /** BMI状态,低_1,偏低_2,正常_3,偏高_4,高_5 */
    private Integer BMIRankStatus;
    /** 基础水分是否异常 */
    private Boolean baseMetabolismStatus;
    /** 体质测试结果 */
    private String corporeityResult;
    /** 体质测试score */
    private String corporeityScore;
    /** 亚健康症状分数 */
    private Integer subHealthSymptomScore;
    /** 亚健康健康指数 */
    private Integer subHealthSymptomHealthPoint;
    /** 饮食问卷选项勾选ID */
    private Integer dietQuestionnaireOptionId;
    /** 饮食问卷选项 */
    private PaperOptionDTO dietQuestionnaireOption;
    /** 运动问卷选项勾选ID */
    private Integer sportQuestionnaireOptionId;
    /** 运动问卷选项 */
    private PaperOptionDTO sportQuestionnaireOption;
    /** 心理健康问卷选项勾选ID */
    private Integer mentalHealthQuestionnaireOptionId;
    /** 心理健康问卷选项 */
    private PaperOptionDTO mentalHealthQuestionnaireOption;
    /** 睡眠时长，单位小时 */
    private Float sleepHour;
    /** 修改日期 */
    private Date modifyDate;
    /** 心率值 */
    private Integer heartRate;
    /** 舒张压值 */
    private Integer diastolic;
    /** 收缩压值 */
    private Integer systolic;
    /** 血氧值 */
    private Integer saturation;
    /** 肺活量值 */
    private Integer vitalCapacity;
    /** 腰臀比值 */
    private Float WHR;
    /** BMI值 */
    private Float BMI;
    /** 基础代谢值 */
    private Float baseMetabolism;
    /** 中风风险分数 */
    private Integer strokeRiskScore;
}
