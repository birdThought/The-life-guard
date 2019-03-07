package com.lifeshs.pojo.healthDevice;

import java.util.Date;

import com.lifeshs.common.constants.common.HealthRank;

import lombok.Data;

/**
 * 血脂仪
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年4月12日 下午4:51:18
 */
public @Data class BloodLipidDTO {

    private Integer id;
    /* 用户ID */
    private Integer userId;
    /* 状态：正常_0,异常_项目和 */
    private Long status;
    /* 高密度脂蛋白胆固醇 */
    private Float HDL;
    /* 高密度脂蛋白胆固醇健康范围 */
    private String HDLArea;
    /* 高密度脂蛋白胆固醇状态描述 */
    private String HDLStatusDescription;
    /* 高密度脂蛋白胆固醇级别 */
    private HealthRank HDLRank;
    
    private Boolean HDLStatus;
    /* 低密度脂蛋白胆固醇 */
    private Float LDL;
    /* 低密度脂蛋白胆固醇健康范围值 */
    private String LDLArea;
    /* 低密度脂蛋白胆固醇状态描述 */
    private String LDLStatusDescription;
    /* 低密度脂蛋白胆固醇级别 */
    private HealthRank LDLRank;

    private Boolean LDLStatus;
    /* 甘油三酯 */
    private Float TG;
    /* 甘油三酯健康范围 */
    private String TGArea;
    /* 甘油三酯状态描述 */
    private String TGStatusDescription;
    /* 甘油三酯级别 */
    private HealthRank TGRank;

    private Boolean TGStatus;

    /* 总胆固醇 */
    private Float TC;
    /* 总胆固醇健康范围 */
    private String TCArea;
    /* 总胆固醇状态描述 */
    private String TCStatusDescription;
    /* 总胆固醇级别 */
    private HealthRank TCRank;

    private Boolean TCStatus;
    /** 血脂比值 */
    private Float bloodLipidRatio;
    /** 血脂比值健康范围 */
    private String bloodLipidRatioArea;
    /** 血脂比值状态描述 */
    private String bloodLipidRatioStatusDescription;
    /** 血脂比值级别 */
    private HealthRank bloodLipidRatioRank;

    private Boolean bloodLipidRatioStatus;

    /* 测量日期 */
    private Date measureDate;
    /* 终端类型 */
    private String deviceType;
    /* 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    private Integer dataType;
}
