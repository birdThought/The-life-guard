package com.lifeshs.pojo.org.service;/**
 * Created by Administrator on 2017/5/18.
 */

import com.lifeshs.pojo.org.OrgUserDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 健康咨询服务项目
 *
 * @author wenxian.cai
 * @create 2017-05-18 14:41
 **/

public @Data class ConsultServiceDTO {

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

    /**修改时间*/
    private Date modifyDate;

    /**产品图片*/
    private String image;

    /**服务师集合*/
    private List<ServiceOrgUserRelationDTO> orgUser;

    /**服务类型（线上：1； 线下：2；上门：3）*/
    private Integer projectType;

//    /**收费模式（1_按次，2_按月，3_按年）*/
//    private Integer chargeMode;
}
