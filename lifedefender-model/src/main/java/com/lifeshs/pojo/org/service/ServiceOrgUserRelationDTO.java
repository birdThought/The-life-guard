package com.lifeshs.pojo.org.service;

import lombok.Data;

/**
 * 服务项目-服务师关系
 *
 * @author wenxian.cai
 * @create 2017-05-18 14:51
 **/

public @Data class ServiceOrgUserRelationDTO {

    /**服务项目code*/
    private String projectCode;

    /**服务师id*/
    private Integer orgUserId;

    /**按次价格*/
    private Double price;
    
    /**按月价格*/
    private Double monthPrice;
    
    /**按年价格*/
    private Double yearPrice;
    
    /**环信code*/
    private String userCode;
    
//    /**收费模式（1_按次，2_按月，3_按年）*/
//    private Integer chargeMode;

    /**真实姓名*/
    private String realName;

    /**照片*/
    private String photo;
}
