package com.lifeshs.pojo.healthDevice;

import com.alibaba.fastjson.annotation.JSONField;
import com.lifeshs.common.constants.common.FMRank;
import com.lifeshs.common.constants.common.HealthRank;

import lombok.Data;

/**
 * 尿液分析仪DTO
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年4月18日 下午5:03:49
 */
public @Data class UranDTO {

    @JSONField(serialize = false)
    private Integer id;

    /** 用户ID */
    @JSONField(serialize = false)
    private Integer userId;

    /** 状态：正常_0,异常_项目和 */
    private Long status;

    /** 白细胞 */
    private String LEU;

    /** 白细胞状态描述 */
    private String LEUStatusDescription;

    @JSONField(serialize = false)
    private FMRank LEURank;

    private Boolean LEUStatus;

    /** 亚硝酸盐 */
    private String NIT;

    /** 亚硝酸盐状态描述 */
    private String NITStatusDescription;

    @JSONField(serialize = false)
    private FMRank NITRank;

    private Boolean NITStatus;

    /** 尿胆原 */
    private String UBG;

    /** 尿胆原状态描述 */
    private String UBGStatusDescription;

    @JSONField(serialize = false)
    private FMRank UBGRank;

    private Boolean UBGStatus;

    /** 蛋白质 */
    private String PRO;

    /** 蛋白质状态描述 */
    private String PROStatusDescription;

    @JSONField(serialize = false)
    private FMRank PRORank;

    private Boolean PROStatus;

    /** pH值 */
    private Float pH;

    /** pH状态描述 */
    private String pHStatusDescription;

    /** pH健康范围值 */
    private String pHArea;

    private HealthRank phRank;

    private Boolean phStatus;

    /** 潜血 */
    private String BLD;

    /** 潜血状态描述 */
    private String BLDStatusDescription;

    @JSONField(serialize = false)
    private FMRank BLDRank;

    private Boolean BLDStatus;

    /** 比重 */
    private Float SG;

    /** 比重状态描述 */
    private String SGStatusDescription;

    /** 比重健康范围 */
    private String SGArea;

    @JSONField(serialize = false)
    private HealthRank sgRank;

    private Boolean sgStatus;

    /** 酮体 */
    private String KET;

    /** 酮体状态描述 */
    private String KETStatusDescription;

    @JSONField(serialize = false)
    private FMRank KETRank;

    private Boolean KETStatus;

    /** 胆红素 */
    private String BIL;

    /** 胆红素状态描述 */
    private String BILStatusDescription;

    @JSONField(serialize = false)
    private FMRank BILRank;

    private Boolean BILStatus;

    /** 葡萄糖 */
    private String GLU;

    /** 葡萄糖状态描述 */
    private String GLUStatusDescription;

    @JSONField(serialize = false)
    private FMRank GLURank;

    private Boolean GLUStatus;

    /** 维生素C */
    private String VC;

    /** 维生素C状态描述 */
    private String VCStatusDescription;

    @JSONField(serialize = false)
    private FMRank VCRank;

    private Boolean VCStatus;

    /** 测量时间 */
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private java.util.Date measureDate;

    /** 终端类型 */
    @JSONField(serialize = false)
    private String deviceType;

    /** 数据类型，1代表实时数据，2代表定时数据，3代表手动数据,4代表睡眠数据 */
    @JSONField(serialize = false)
    private Integer dataType;
}
