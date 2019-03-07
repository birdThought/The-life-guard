package com.lifeshs.dto.manager.register;

import lombok.Data;

/**
 *  个体门店注册
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月9日 下午5:53:10
 */
public @Data class IndividualStoreDTO {
    /** 实名认证用户基础信息 */
    private Basic basic;
    /** 个人资格认证 */
    private PersonalQulification personalQulification;
    /** 店铺简介 */
    private Store store;
    /** 账号申请 */
    private Account account;
}

/**
 * 实名认证用户基础信息
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月9日 下午5:32:09
 */
class Basic {
    /** 头像 */
    private String photo;
    /** 真实姓名 */
    private String realName;
    /** 身份证号码 */
    private String idCardNumber;
    /** 身份证正面照 */
    private String idCardPicFront;
    /** 身份证反面照 */
    private String idCardPicBack;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardPicFront() {
        return idCardPicFront;
    }

    public void setIdCardPicFront(String idCardPicFront) {
        this.idCardPicFront = idCardPicFront;
    }

    public String getIdCardPicBack() {
        return idCardPicBack;
    }

    public void setIdCardPicBack(String idCardPicBack) {
        this.idCardPicBack = idCardPicBack;
    }
}

/**
 * 个人资格认证
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月9日 下午5:33:14
 */
class PersonalQulification {
    /** 工作地址 */
    private WorkAddress workAddress;
    /** 职称 */
    private String professionalName;
    /** 职业资格照 */
    private String professionPic;
    /** 擅长领域 */
    private String expertField;
    /** 个人简介 */
    private String about;

    public WorkAddress getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(WorkAddress workAddress) {
        this.workAddress = workAddress;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public String getProfessionPic() {
        return professionPic;
    }

    public void setProfessionPic(String professionPic) {
        this.professionPic = professionPic;
    }

    public String getExpertField() {
        return expertField;
    }

    public void setExpertField(String expertField) {
        this.expertField = expertField;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}

/**
 *  工作地址
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月9日 下午5:50:10
 */
class WorkAddress {
    /** 地区 */
    private Area area;
    /** 详细地址 */
    private String address;
    /** 经度 */
    private double longitude;
    /** 纬度 */
    private double latitude;

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

/**
 *  地区
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年8月9日 下午5:49:53
 */
class Area {
    /** 省 */
    private String province;
    /** 市 */
    private String city;
    /** 县 */
    private String county;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }
}

/**
 * 店铺简介
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月9日 下午5:43:44
 */
class Store {
    /** 店铺名称 */
    private String orgName;
    /** 店铺分类 */
    private String orgType;
    /** 从事领域 */
    private String workField;
    /** 店铺简介 */
    private String about;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getWorkField() {
        return workField;
    }

    public void setWorkField(String workField) {
        this.workField = workField;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}

/**
 * 账号申请
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年8月9日 下午5:43:27
 */
class Account {
    /** 登录账号 */
    private String userName;
    /** 登录密码 */
    private String password;
    /** 手机号码 */
    private String mobile;
    /** 验证码 */
    private String verifyCode;
    /** 申请类型 */
    private Integer userType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}