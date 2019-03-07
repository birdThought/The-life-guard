package com.lifeshs.pojo.org.service;

import java.util.Date;
import java.util.List;

import com.lifeshs.pojo.order.v2.OrderDTO;
import com.lifeshs.pojo.org.v2.OrgDTO;

import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import lombok.Data;

/**
 * 上门服务-居家养老服务
 *
 * @author wenxian.cai
 * @create 2017-05-18 15:59
 **/

public @Data class VisitServiceDTO {

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

    /**服务富文本描述*/
    //private String description; //2018.9.28 update, app调用接口会自动转换deion
    private String introduce;

    /**服务使用人群*/
    private String userType;

    /**服务预约信息*/
    private String appointment;

    /**产品图片*/
    private String image;

    /**修改时间*/
    private Date modifyDate;

    /**服务师集合*/
    private List<ServiceOrgUserRelationDTO> orgUser;

    /**套餐集合*/
    private List<ServiceComboDTO> combo;

    /**相关媒体资料*/
    private ServiceMediaDTO media;

    /**服务类型（线上：1； 线下：2；上门：3）*/
    private Integer projectType;
    
    /** 所属机构 */
    private OrgDTO org;
    
    /** 所属服务 */
    private ServeTypeSecondDTO serveType;
    
    /** 已完成的订单（线下与上门服务的已完成状态，意味着该订单已经评价过的，该项用于统计服务分数） */
    private List<OrderDTO> orderComplete;
}
