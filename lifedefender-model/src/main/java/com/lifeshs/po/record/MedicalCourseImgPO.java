package com.lifeshs.po.record;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  健康档案（病程图片）
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月14日 上午10:10:08
 */
public @Data class MedicalCourseImgPO {

    private Integer id;
    /** 病程id */
    @JSONField(serialize=false)
    private Integer medicalCourseId;
    /** 图片路径 */
    private String img;
    /** 创建日期 */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /** 修改日期 */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;
}
