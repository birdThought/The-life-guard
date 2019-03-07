package com.lifeshs.pojo.org.profile;
import lombok.Data;

/**
 * 门店信息
 *
 * @author wenxian.cai
 * @create 2017-06-08 17:56
 **/

public @Data class OrgProfileDTO {

    /**门店id*/
    private Integer id;

    /**门店logo*/
    private String logo;

    /**门店名称*/
    private String  orgName;

    /**门店法人*/
    private String legalPerson;

    /**门店联系方式*/
    private String contactInformation;

    /**门店联系人*/
    private String contacts;

    /**门店分类*/
    private String orgType;

    /**门店简介*/
    private String about;

    /**省份*/
    private String province;

    /**城市*/
    private String city;

    /**区*/
    private String district;

    /**省份*/
    private String provinceCode;

    /**城市*/
    private String cityCode;

    /**区*/
    private String districtCode;

    /**街道*/
    private String street;

    /**经度*/
    private String longitude;

    /**纬度*/
    private String latitude;

    /**门店详细介绍(富文本)*/
    private String detail;

    /**门店类型（1：门店； 2：个体门店）*/
    private Integer type;

}
