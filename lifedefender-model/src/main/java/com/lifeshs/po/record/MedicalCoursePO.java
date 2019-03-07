package com.lifeshs.po.record;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  健康档案（病程）
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月14日 上午10:07:42
 */
public @Data class MedicalCoursePO {

    private Integer id;
    /** 病历ID */
    @JSONField(serialize=false)
    private Integer medicalId;
    /** 病程类型（首诊、复诊、出院） */
    private String courseType;
    /** 备注 */
    private String remark;
    /** 就诊日期 */
    private Date visitingDate;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
}
