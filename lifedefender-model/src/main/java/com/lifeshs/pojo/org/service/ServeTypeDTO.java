package com.lifeshs.pojo.org.service;

import lombok.Data;

import java.util.List;

/**
 * 服务类型
 *
 * @author wenxian.cai
 * @create 2017-05-22 15:13
 **/

public @Data class ServeTypeDTO {

    /**服务id*/
    private int id;

    /**服务名称*/
    private String name;

    /**服务名称*/
    private String code;

    /**二级服务集合*/
    List<ServeTypeDTO> secondaryServe;

    /**二级服务id*//*
    private int secondaryId;

    *//**二级服务名称*//*
    private int secondaryName;*/
}
