package com.lifeshs.po;

import com.lifeshs.po.org.TOrgServePO;
import com.lifeshs.po.org.TServerPO;
import lombok.Data;

import java.util.Set;

/**
 * 机构实体类
 *
 */
@Data
public class OrgPO {
    private Integer id;
    
    /** 机构编号 */
    private String userNo;
    
    /** 推荐人 */
    private String parentId;

    /** 机构名称 */
    private String orgName;

    /** 机构编码 */
    private String orgCode;

    /** 机构LOGO */
    private String logo;

    /** 是否企业验证:已审核_1,未审核_0,未通过_2,后台不显示审核_3 */
    private Integer orgVerified;

    /** 审核不通过理由 */
    private String verifiedCause;

    /** 机构服务电话 */
    private String tel;

    /** 服务性质 */
    private String orgType;

    /** 联系人姓名 */
    private String contacts;

    /** 联系方式 */
    private String contactInformation;

    /** 营业执照路径1 */
    private String businessLicense;

    /** 结算帐户类型 */
    private Integer accountType;

    /** 结算帐户 */
    private String account;

    /** 状态,正常_0,停用_1,未审核_3,未通过_4 */
    private Integer status;

    /** 机构类型,管理机构_0,服务机构_1,个体机构_2 */
    private Integer type;

    /** 父机构ID，没有为0 */
    private Integer parent;

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
    private Boolean isRecommend;

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
    private Integer legalPersonGender;

    /** 法人身份证 */
    private String legalPersonIdCard;

    /** 法人身份证照片路径1 */
    private String legalPersonPicOne;

    /** 法人身份证照片路径2 */
    private String legalPersonPicTwo;


    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public void setVerifiedCause(String verifiedCause) {
        this.verifiedCause = verifiedCause == null ? null : verifiedCause.trim();
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation == null ? null : contactInformation.trim();
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense == null ? null : businessLicense.trim();
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public void setAbout(String about) {
        this.about = about == null ? null : about.trim();
    }

    public void setWorkField(String workField) {
        this.workField = workField == null ? null : workField.trim();
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public void setBankDistrict(String bankDistrict) {
        this.bankDistrict = bankDistrict == null ? null : bankDistrict.trim();
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch == null ? null : bankBranch.trim();
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson == null ? null : legalPerson.trim();
    }

    public void setLegalPersonIdCard(String legalPersonIdCard) {
        this.legalPersonIdCard = legalPersonIdCard == null ? null : legalPersonIdCard.trim();
    }

    public void setLegalPersonPicOne(String legalPersonPicOne) {
        this.legalPersonPicOne = legalPersonPicOne == null ? null : legalPersonPicOne.trim();
    }

    public void setLegalPersonPicTwo(String legalPersonPicTwo) {
        this.legalPersonPicTwo = legalPersonPicTwo == null ? null : legalPersonPicTwo.trim();
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}