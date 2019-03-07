package com.lifeshs.pojo.org;

import java.util.ArrayList;
import java.util.List;

import com.lifeshs.pojo.order.OrderDTO;
import com.lifeshs.pojo.org.server.OrgServeDTO;

import lombok.Data;

/**
 *  机构
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年3月8日 上午9:51:30
 */
public @Data class OrgDTO {

    private Integer id;

    /** 机构名称 */
    private String orgName;

    /** 机构LOGO */
    private String logo;

    /** 是否企业验证:已审核_1,未审核_0,未通过_2 */
    private Integer orgVerified;

    /** 审核不通过理由 */
    private String verifiedCause;

    /** 机构服务电话 */
    private String tel;

    /** 机构性质 */
    private String orgType;

    /** 联系人姓名 */
    private String contacts;

    /** 联系方式 */
    private String contactInformation;

    /** 营业执照路径 */
    private String businessLicense;

    /** 结算帐户类型 */
    private Integer accountType;

    /** 结算帐户 */
    private String account;

    /** 状态,正常_0,停用_1 */
    private Integer status;

    /** 机构类型,管理机构_0,服务机构_1,个体机构_2 */
    private Integer type;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 县(区) */
    private String district;

    /** 街道地址 */
    private String street;

    /** 经度(建议不超过.后六位) */
    private String longitude;

    /** 纬度(建议不超过.后六位) */
    private String latitude;

    /** 机构简介 */
    private String about;

    /** 机构详细介绍(html) */
    private String detail;

    /** 创建日期 */
    private java.util.Date createDate;

    /** 修改时间 */
    private java.util.Date modifyDate;

    /** 是否推荐机构 */
    private Boolean recommend;

    private List<OrgServeDTO> orgServes; // serveName 的集合

    /** 父机构 */
    private OrgDTO parentOrg;

    /** 营业执照号 */
    private String businessLicenseNumber;
    
    /** 营业执照路径2 */
    private String businessLicenseTwo;
    
    /** 从事领域 */
    private String workField;
    
    /** 公司对公账号 */
    private String bankAccount;
    
    /** 开户所在地区 */
    private String bankDistrict;
    
    /** 开户支行名称 */
    private String bankBranch;
    
    /** 法人代表 */
    private String legalPerson;
    
    /** 法人性别 */
    private Boolean legalPersonGender;
    
    /** 法人身份证 */
    private String legalPersonIdCard;
    
    /** 法人身份证照片路径1 */
    private String legalPersonPicOne;
    
    /** 法人身份证照片路径2 */
    private String legalPersonPicTwo;

    /**
     *  获取机构下所有服务的消费人次
     *  @author yuhang.weng 
     *	@DateTime 2017年3月8日 下午1:49:42
     *
     *  @return
     */
    public Integer getOrgConsumePersonTime() {
        /** 将所有有效的订单获取后存放到集合中 */
        List<OrderDTO> orderDTOs = new ArrayList<>();
        for (OrgServeDTO orgServeDTO : getOrgServes()) {
            orderDTOs.addAll(orgServeDTO.getValidOrders());
        }
        List<Integer> userIds = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOs) {
            int userId = orderDTO.getUserId();
            if (!userIds.contains(userId)) {
                userIds.add(userId);
            }
        }
        return userIds.size();
    }
}
