package com.lifeshs.entity.org.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * t_org_user
 */
@Table(name = "t_org_user", schema = "")
@SuppressWarnings("serial")
public class TOrgUser implements Serializable {

    private Integer id;
    
    private String userNo;
    
    
    /** 用户编号 */
    private String userCode;

    /** 用户登录名 */
    private String userName;

    /** 登录密码 */
    private String password;
    
    //添加  引荐人ID     parentId
    private String parentId;

	/** app token(也可用于web自动登录) */
    private String token;

    /** 真实姓名 */
    private String realName;

    /** 手机号码 */
    private String mobile;

    /** 0:未验证,1:已验证 */
    private Boolean mobileVerified;

    /** 用户邮箱 */
    private String email;

    /** 0:未验证,1:已验证 */
    private Boolean emailVerified;

    /** 性别0表示女,1表示男 */
    private Boolean sex;

    /** 出生日期 */
    @JSONField(format = "yyyy-MM-dd")
    private java.util.Date birthday;

    /** 工作电话 */
    private String tel;

    /** 家庭地址 */
    private String address;

    /** 微信openid */
    private String openId;

    /** 状态：正常_0,停用_1,注销_2,离职_4 */
    private Integer status;

    /** 用户类型:管理员_0,服务师_1,都有_2 */
    private Integer userType;

    /** 权限集合(可勾选32项) */
    private Integer permissionSet;

    /** 机构ID */
    private Integer orgId;

    /** 个人照片路径 */
    private String photo;

    /** 个人简介 */
    private String about;

    /** 个人详细介绍(html) */
    private String detail;

    /** 创建日期 */
    private java.util.Date createDate;

    /** 修改时间 */
    private java.util.Date modifyDate;

    /** 身份证 */
    private String idCard;

    /** 身份证件照路径1 */
    private String idCardPicOne;

    /** 身份证件照路径2 */
    private String idCardPicTwo;

    /** 专业特长 */
    private String expertise;

    /**职称*/
    private String professionalName;
    
    private String professionalPic;

    public TOrgUser() {
        super();
    }

    public TOrgUser(Integer id,String userNo,String parentId, String userCode, String userName, String password, String token, String realName,
            String mobile, Boolean mobileVerified, String email, Boolean emailVerified, Boolean sex,
            java.util.Date birthday, String tel, String address, String openId, Integer status, Integer userType,
            Integer permissionSet, Integer orgId, String photo, String about, String detail, java.util.Date createDate,
            java.util.Date modifyDate, String idCard, String idCardPicOne, String idCardPicTwo, String expertise,
            String professionalName, String professionalPic) {
        super();
        this.id = id;
        this.userNo = userNo;
        this.parentId = parentId;
        this.userCode = userCode;
        this.userName = userName;
        this.password = password;
        this.token = token;
        this.realName = realName;
        this.mobile = mobile;
        this.mobileVerified = mobileVerified;
        this.email = email;
        this.emailVerified = emailVerified;
        this.sex = sex;
        this.birthday = birthday;
        this.tel = tel;
        this.address = address;
        this.openId = openId;
        this.status = status;
        this.userType = userType;
        this.permissionSet = permissionSet;
        this.orgId = orgId;
        this.photo = photo;
        this.about = about;
        this.detail = detail;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.idCard = idCard;
        this.idCardPicOne = idCardPicOne;
        this.idCardPicTwo = idCardPicTwo;
        this.expertise = expertise;
        this.professionalName = professionalName;
        this.professionalPic = professionalPic;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name = "userNo", nullable = false, length = 11)
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Column(name = "parentId", nullable = false, length = 11)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Column(name = "userCode", nullable = true, length = 6)
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Column(name = "userName", nullable = true, length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "password", nullable = true, length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "token", nullable = false, length = 16)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "realName", nullable = false, length = 30)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "mobile", nullable = false, length = 18)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "mobileVerified", nullable = false, length = 1)
    public Boolean getMobileVerified() {
        return mobileVerified;
    }

    public void setMobileVerified(Boolean mobileVerified) {
        this.mobileVerified = mobileVerified;
    }

    @Column(name = "email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "emailVerified", nullable = false, length = 1)
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @Column(name = "sex", nullable = false, length = 1)
    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    @Column(name = "birthday", nullable = false, length = 10)
    public java.util.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    @Column(name = "tel", nullable = false, length = 15)
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "address", nullable = false, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "openId", nullable = false, length = 30)
    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    @Column(name = "status", nullable = false, length = 4)
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "userType", nullable = false, length = 4)
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Column(name = "permissionSet", nullable = false, length = 11)
    public Integer getPermissionSet() {
        return permissionSet;
    }

    public void setPermissionSet(Integer permissionSet) {
        this.permissionSet = permissionSet;
    }

    @Column(name = "orgId", nullable = false, length = 11)
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    @Column(name = "photo", nullable = false, length = 200)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "about", nullable = false, length = 200)
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    @Column(name = "detail", nullable = false, length = 21845)
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "modifyDate", nullable = false, length = 19)
    public java.util.Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(java.util.Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    @Column(name = "idCard", nullable = false, length = 25)
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Column(name = "idCardPicOne", nullable = false, length = 200)
    public String getIdCardPicOne() {
        return idCardPicOne;
    }

    public void setIdCardPicOne(String idCardPicOne) {
        this.idCardPicOne = idCardPicOne;
    }

    @Column(name = "idCardPicTwo", nullable = false, length = 200)
    public String getIdCardPicTwo() {
        return idCardPicTwo;
    }

    public void setIdCardPicTwo(String idCardPicTwo) {
        this.idCardPicTwo = idCardPicTwo;
    }

    @Column(name = "expertise", nullable = false, length = 200)
    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    @Column(name = "professionalName", nullable = false, length = 32)
    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    @Column(name = "professionalPic", nullable = false, length = 200)
    public String getProfessionalPic() {
        return professionalPic;
    }

    public void setProfessionalPic(String professionalPic) {
        this.professionalPic = professionalPic;
    }

    

    @Override
    public String toString() {
        return "TOrgUser [id=" + id + ",userNo="+userNo+ ",parentId=" + parentId + ", userCode=" + userCode + ", userName=" + userName + ", password=" + password
                + ", token=" + token + ", realName=" + realName + ", mobile=" + mobile + ", mobileVerified="
                + mobileVerified + ", email=" + email + ", emailVerified=" + emailVerified + ", sex=" + sex
                + ", birthday=" + birthday + ", tel=" + tel + ", address=" + address + ", openId=" + openId
                + ", status=" + status + ", userType=" + userType + ", permissionSet=" + permissionSet + ", orgId="
                + orgId + ", photo=" + photo + ", about=" + about + ", detail=" + detail + ", createDate=" + createDate
                + ", modifyDate=" + modifyDate + ", idCard=" + idCard + ", idCardPicOne=" + idCardPicOne
                + ", idCardPicTwo=" + idCardPicTwo + ", expertise=" + expertise + ",professionalName="+professionalName
                +",professionalPic="+professionalPic+"]";
    }

}