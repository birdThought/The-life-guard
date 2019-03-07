package com.lifeshs.pojo.org.server;

import lombok.Data;

/**
 * 服务详情
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月4日 上午11:35:02
 */
public @Data class ServeDetailDTO {

    private Integer orgServeId;

    private Integer serveId;

    private String name;

    private String code;

    private String about;
    
    private String orgServeAbout;

    private String image;

    private Integer serveType;

    private String classify;

    private Boolean hasFree;

    private Boolean hasTime;

    private Integer timePrice;

    private Boolean hasMonth;

    private Integer monthPrice;

    private Boolean hasYear;

    private Integer yearPrice;

    private String serveGroupName;
    /** 课堂描述 */
    private String description;
}
