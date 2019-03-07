package com.lifeshs.pojo.org;

import lombok.Data;

import java.util.Date;

/**
 * 机构会员
 *
 * @author wenxian.cai
 * @create 2017-05-05 10:54
 **/

public @Data class OrgMemberDTO {

    /**订单id*/
    private int orderId;

    /**机构*/
    private int orgId;

    /**用户id*/
    private int userId;

    /**用户名称*/
    private String userName;

    /**真实姓名*/
    private String realName;

    /**用户电话号码*/
    private String mobile;

    /**用户头像*/
    private String photo;

    /**用户性别*/
    private boolean gender;

    /**用户生日*/
    private Date birthday;

    /**用户生日*/
    private String userCode;

    /**最近一次服务名称*/
    private String projectName;

    /**服务code*/
    private String code;

    /**腰围*/
    private String waist;

    /**胸围*/
    private String bust;

    /**臀围*/
    private String hip;

    /**身高*/
    private String height;

    /**用户备注*/
    private String userRemark;

    /**serveId*/
    private int serveId;

    /**用户病种id*/
    private int userDiseasesId;

    /**用户病种名称*/
    private String userDiseasesName;

    /**项目类型(1：咨询；2：线下；3：上门；4：课堂)*/
    private int projectType;
}
