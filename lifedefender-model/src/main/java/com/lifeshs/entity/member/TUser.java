package com.lifeshs.entity.member;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_user
 */
@Table(name = "t_user", schema = "")
@SuppressWarnings("serial")
public class TUser implements Serializable {

    private Integer id;

    /** 用户编号 */
    private String userCode;

    /** 用户登录名 */
    private String userName;

    /** 登录密码 */
    private String password;

    /** 用户头像保存相对路径 */
    private String photo;

    /** app token(也可用于web自动登录) */
    private String token;

    /** 手机号码 */
    private String mobile;

    /** 0:未验证,1:已验证 */
    private Boolean mobileVerified;

    /** 用户邮箱 */
    private String email;

    /** 0:未验证,1:已验证 */
    private Boolean emailVerified;

    /** 真实姓名 */
    private String realName;

    /** 省 */
    private String province;

    /** 市 */
    private String city;

    /** 县(区) */
    private String county;

    /** 街道地址 */
    private String street;

    /** 微信openid */
    private String openId;

    /** 状态：正常_0,禁用_1 */
    private Integer status;

    /** 积分（保留） */
    private Integer integral;

    /** 等级（保留） */
    private Integer grade;

    /** 勾选的健康包产品 */
    private Integer healthProduct;

    /** 勾选的健康预警 */
    private Integer healthWarning;

    /** 无异常数据_0,有异常_加设备的值 */
    private Integer hasWarning;

    /** 家庭组 */
    private String groupKey;

    /** 创建日期 */
    private java.util.Date createDate;

    /** 修改时间 */
    private java.util.Date modifyDate;

    public TUser() {
        super();
    }

    public TUser(Integer id, String userCode, String userName, String password, String photo, String token,
            String mobile, Boolean mobileVerified, String email, Boolean emailVerified, String realName, Boolean sex,
            java.util.Date birthday, Double height, Double weight, Double waist, Double bust, Double hip,
            String province, String city, String county, String street, String openId, Integer status, Integer integral,
            Integer grade, Integer healthProduct, Integer healthWarning, Integer hasWarning, String groupKey,
            java.util.Date createDate, java.util.Date modifyDate) {
        super();
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.password = password;
        this.photo = photo;
        this.token = token;
        this.mobile = mobile;
        this.mobileVerified = mobileVerified;
        this.email = email;
        this.emailVerified = emailVerified;
        this.realName = realName;
        this.province = province;
        this.city = city;
        this.county = county;
        this.street = street;
        this.openId = openId;
        this.status = status;
        this.integral = integral;
        this.grade = grade;
        this.healthProduct = healthProduct;
        this.healthWarning = healthWarning;
        this.hasWarning = hasWarning;
        this.groupKey = groupKey;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "userCode", nullable = true, length = 8)
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

    @Column(name = "password", nullable = true, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "photo", nullable = false, length = 150)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "token", nullable = false, length = 16)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "mobile", nullable = false, length = 20)
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

    @Column(name = "realName", nullable = false, length = 18)
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Column(name = "province", nullable = false, length = 10)
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "city", nullable = false, length = 20)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "county", nullable = false, length = 20)
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Column(name = "street", nullable = false, length = 50)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    @Column(name = "integral", nullable = false, length = 11)
    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Column(name = "grade", nullable = false, length = 4)
    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    @Column(name = "healthProduct", nullable = false, length = 6)
    public Integer getHealthProduct() {
        return healthProduct;
    }

    public void setHealthProduct(Integer healthProduct) {
        this.healthProduct = healthProduct;
    }

    @Column(name = "healthWarning", nullable = false, length = 6)
    public Integer getHealthWarning() {
        return healthWarning;
    }

    public void setHealthWarning(Integer healthWarning) {
        this.healthWarning = healthWarning;
    }

    @Column(name = "hasWarning", nullable = false, length = 6)
    public Integer getHasWarning() {
        return hasWarning;
    }

    public void setHasWarning(Integer hasWarning) {
        this.hasWarning = hasWarning;
    }

    @Column(name = "groupKey", nullable = false, length = 32)
    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
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

}