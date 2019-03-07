package com.lifeshs.po.data;

import java.util.Date;

import lombok.Data;

/**
 *  app的banner
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月23日 上午10:40:21
 */
public @Data class AppBannerPO {

    private Integer id;
    /** 图片网络地址 */
    private String netPath;
    /** 1_首页，2_服务页 */
    private Integer type;
    /** 权重 */
    private Integer weight;
    /** 自定义内容 */
    private String custom;
    /** 修改日期 */
    private Date modifyDate;
    /** 创建日期 */
    private Date createDate;
    /** 是否已删除 */
    private Boolean deleted;
}
