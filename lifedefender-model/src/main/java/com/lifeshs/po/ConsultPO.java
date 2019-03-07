package com.lifeshs.po;

import lombok.Data;

import java.util.Date;

/*
 * 健康咨询
 * date: 2017/8/14 16:10
 */
public @Data class ConsultPO {

    /**主键*/
    private Integer id;

    /**随机码*/
    private String serialId;

    /**项目code*/
    private String code;

    /**名称*/
    private String name;

    /**状态*/
    private Integer status;

    /**机构id*/
    private Integer orgId;

    /**服务id*/
    private Integer serveId;

    /**修改时间*/
    private Date modifyDate;

    /**产品图片*/
    private String image;

    /**服务类型（线上：1； 线下：2；上门：3）*/
    private Integer projectType;

//    /**收费模式（1_按次，2_按月，3_按年）*/
//    private Integer chargeMode;
}
