package com.lifeshs.po.org;

import lombok.Data;

/**
 * 会员服务类
 * @author Administrator
 * @create 2018-02-03
 * 17:25
 * @desc
 */
public @Data class TOrgServePO {

    private Integer id;

    /** 机构服务ID */
    private Integer orgId;

    /** 服务ID */
    private Integer serveId;

    /** 0_免费，1_收费 */
    private Boolean hasFree;

    /** 免费时长（月），0无限制 */
    private Integer freeDate;

    /** 有按次收费：1_是，0_否 */
    private Boolean hasTime;

    /** 按次的价格 */
    private Integer timePrice;

    /** 有按月收费：1_是，0_否 */
    private Boolean hasMonth;

    /** 按月的价格 */
    private Integer monthPrice;

    /** 有按年收费：1_是，0_否 */
    private Boolean hasYear;

    /** 按年的价格 */
    private Integer yearPrice;

    /** 服务分类 */
    private String classify;

    /** 服务介绍 */
    private String about;

    /** 创建人 */
    private String creater;

    /** 创建时间 */
    private java.util.Date createDate;

    /**
     * 服务子表
     */
    private TServerPO serve;
}
