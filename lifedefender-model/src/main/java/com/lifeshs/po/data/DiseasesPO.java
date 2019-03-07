package com.lifeshs.po.data;

import lombok.Data;

import java.util.Date;

/*
 * 病种实体PO
 * author: wenxian.cai
 * date: 2017/8/16 14:23
 */
public @Data class DiseasesPO {

    /** 主键id */
    private Integer id;

    /** 病种名称 */
    private String name;

    /** 创建时间 */
    private Date createDate;

    /** 修改时间 */
    private Date modifyDate;

    /** 是否已删除(0：未删除；1：已删除) */
    private Integer deleted;
}
