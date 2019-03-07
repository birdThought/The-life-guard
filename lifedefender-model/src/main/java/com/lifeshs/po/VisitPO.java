package com.lifeshs.po;

import com.lifeshs.pojo.org.service.ServiceMediaDTO;
import com.lifeshs.vo.serve.visit.ComboVO;
import lombok.Data;

import java.util.Date;

/*
 * t_project_visit表（线下、上门服务）
 * date: 2017/8/15 9:55
 */
public @Data class VisitPO {

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

    /**描述*/
    private String introduce;

    /**使用人群*/
    private String userType;

    /**预约信息*/
    private String appointment;

    /**产品图片*/
    private String image;

    /**服务类型（线上：1； 线下：2；上门：3）*/
    private Integer projectType;

    /**创建时间*/
    private Date createDate;

    /**修改时间*/
    private Date modifyDate;

    private ComboVO comboVO;

    private ServiceMediaDTO serviceMediaDTO;
}
