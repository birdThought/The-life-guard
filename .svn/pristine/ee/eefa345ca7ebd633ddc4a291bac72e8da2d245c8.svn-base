package com.lifeshs.pojo.org.service;

import com.lifeshs.pojo.org.group.CourseTimeDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 健康课堂服务项目
 *
 * @author wenxian.cai
 * @create 2017-05-18 11:08
 **/

public @Data class LessonServiceDTO {

    /**主键*/
    private Integer id;

    /**唯一随机码*/
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

    /**价格*/
    private Double price;

    /**课堂id*/
    private Integer lessonId;

    /**课堂image*/
    private String image;

    /**服务类型（线上：1； 线下：2；上门：3）*/
    private Integer projectType;

    /**修改时间*/
    private Date modifyDate;

    /**课堂二维码*/
    private String qrCode;

    /**描述*/
//    private String description;//2018.9.5 update, app调用接口会自动转换deion
    private String introduce;

    /**创建者id*/
    private Integer creatorId;

    /**课堂开始时间*/
    private Date startDate;

    /**课堂结束时间*/
    private Date endDate;

    /**环信id*/
    private String huanxinId;

    /**是否禁言*/
    private boolean silence;

    /**服务师集合*/
    private List<ServiceOrgUserRelationDTO> orgUser;

    /** 课程时间(json串形式)
     * [{
     *      "weeks":"",
     *      "startTime": ""
     *      }]
     * */
    private String lessonTime;










}
