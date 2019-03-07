package com.lifeshs.po.record;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  体检报告图片
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年9月11日 上午10:53:54
 */
public @Data class PhysicalImgPO {

    private Integer id;
    /** 体检报告id */
    @JSONField(serialize=false)
    private Integer physicalId;
    /** 图片路径 */
    private String img;
    /** 创建日期 */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    /** 修改日期 */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date modifyDate;
}
