package com.lifeshs.po.measure;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 *  用户测量数据分析
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年10月30日 下午3:16:18
 */
@Data
public class MeasureAnalysisPO {

    private Integer id;
    /** 用户id */
    private Integer userId;
    /** 客服id */
    private Integer customerUserId;
    /** 医生签名 */
    private String doctorSign;
    /** 测量日期 */
    @JSONField(format = "yyyy-MM-dd")
    private Date measureDate;
    /** 健康设备类型 */
    private int healthProduct;
    /** 回复内容 */
    private String reply;
    /** 是否已读，0_未读，1_已读 */
    private Boolean read;
    /** 创建日期 */
    private Date createDate;
    /** 修改日期 */
    private Date modifyDate;
    /** 是否已删除(0：未删除；1：已删除) */
    private Boolean deleted;
}
