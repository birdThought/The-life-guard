package com.lifeshs.po.record;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  健康档案（病历）
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月14日 上午10:05:01
 */
public @Data class MedicalPO {

    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 标题 */
    private String title;
    /** 就诊日期 */
    private Date visitingDate;
    /** 就诊医院 */
    private String hospital;
    /** 科室ID */
    @JSONField(serialize=false)
    private Integer departmentId;
    /** 医生诊断 */
    private String doctorDiagnosis;
    /** 基本病情 */
    private String basicCondition;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
}
