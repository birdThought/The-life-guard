package com.lifeshs.pojo.org.service;/**
 * Created by Administrator on 2017/5/18.
 */

import lombok.Data;

/**
 * 服务项目-媒体资料
 *
 * @author wenxian.cai
 * @create 2017-05-18 17:00
 **/

public @Data class ServiceMediaDTO {

    /**主键*/
    private int id;

    /**项目唯一code*/
    private String projectCode;

    /**图片路径1*/
    private String pictureOne;

    /**图片路径2*/
    private String pictureTwo;

    /**图片路径3*/
    private String pictureThree;

    /**图片路径4*/
    private String pictureFour;

    /**视频路径1*/
    private String videoOne;

    /**视频路径2*/
    private String videoTwo;

}
