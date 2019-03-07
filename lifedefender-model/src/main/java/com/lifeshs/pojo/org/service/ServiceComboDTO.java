package com.lifeshs.pojo.org.service;/**
 * Created by Administrator on 2017/5/18.
 */

import lombok.Data;

/**
 * 服务项目-套餐
 *
 * @author wenxian.cai
 * @create 2017-05-18 16:14
 **/

public @Data class ServiceComboDTO {

    /**主键*/
    private Integer id;

    /**服务项目code*/
    private String projectCode;

    /**套餐价格*/
    private Double price;

    /**套餐名称*/
    private String name;

    /**套餐描述(json串形式)*/
    //private String description;//2018.9.28 update, app调用接口会自动转换deion
    private String introduce;
    
    /** 门市价 */
    private Double marketPrice;
    
    /** 所属的项目 */
    private VisitServiceDTO visitServe;
}
