package com.lifeshs.pojo.org.employee;

import java.util.Date;
import java.util.List;

import com.lifeshs.pojo.org.v2.OrgDTO;

import lombok.Data;

/**
 * 机构-员工管理-员工DTO
 *    com.lifeshs.pojo.org.employee.EmployeeDTO
 * @author wenxian.cai
 * @create 2017-06-02 15:24
 **/

public @Data class EmployeeDTO {

    /** id */
    private Integer id;

    /** 用户code */
    private String userCode;

    /** 用户昵称（账户） */
    private String userName;
    
    // 用户编号 
    private String userNo;
    
    // 引荐人ID 
    private String parentId;

    /** 头像 */
    private String photo;

    /** 真实姓名 */
    private String realName;

    /** 性别 */
    private boolean gender;

    /** 生日 */
    private Date birthday;

    /** 电话 */
    private String mobile;

    /** 用户类型 */
    private String userType;

    /** 工作状态（正常_0,停用_1,注销_2,离职_4） */
    private int status;

    /** 地址 */
    private String address;

    /** 专业特长（擅长） */
    private String expertise;

    /** 个人简介 */
    private String about;

    /** 职称 */
    private String professionalName;

    /** 服务项目 */
    private List<String> project;

    /** 会员数量 */
    private Integer countOfMember;

    /** 订单数量 */
    private Integer countOfOrder;

    /** 本月收益 */
    private Double earning;

    /** 所属机构 */
    private OrgDTO org;
    
    /** 邮箱 */
    private String email;
    
    /** 身份证 */
    private String idCard;
}
