package com.lifeshs.vo;

import java.util.Date;

/**
 * 管理APP视图实体
 *
 * 2017-09-05 14:53:14
 * @author wuj
 */
public class MemberVO {

    /**
     * 基本属性,后期有需求可继续添加
     *
     * 备注: id 这个字段的set函数不能动,因为userId实际上也是通过{@code setId()}获取,这么做的目的是方便IOS解析
     *
     */
    private Integer id; // 会员ID

    private String userId; // 方便前端处理

    private String realName; // 真实姓名

    private String mobile; // 联系电话

    private String userDiseasesName; // 病种名称

    private String photo; // 用户头像

    private String serveId; // 服务ID

    private String userName;

    /**用户性别*/
    private boolean gender;

    /**用户生日*/
    private Date birthday;

    /**用户生日*/
    private String userCode;

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

    /**用户病种id*/
    private int userDiseasesId;

    /**
     * 订单ID : 对应orderId,userDiseasesName,userRemark 这三个字段是相关联的 ,
     * 这个对象的三个字段,orderID,userDiseasesName,userRemark与其它字段都是非关联的关系,
     * 如果想要通过orderId来修改找到其它字段,那么请使用currentOrderId
     */
    private Integer orderId;

    /**
     * 该用户订单的项目类型(1：咨询；2：线下；3：上门；4：课堂)
     */
    private Integer projectType;

    /**
     * 当前订单ID,与orderId正好相反,currentOrderId对应除开orderID,userDiseasesName,userRemark这三个字段的其它field
     */
    private Integer currentOrderId;

    public int getUserDiseasesId() {
        return userDiseasesId;
    }

    public void setUserDiseasesId(int userDiseasesId) {
        this.userDiseasesId = userDiseasesId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getId() {
        return id;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public Integer getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(Integer currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public void setId(Integer id) {
        this.userId = id.toString();
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserDiseasesName() {
        return userDiseasesName;
    }

    public void setUserDiseasesName(String userDiseasesName) {
        this.userDiseasesName = userDiseasesName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getServeId() {
        return serveId;
    }

    public void setServeId(String serveId) {
        this.serveId = serveId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getBust() {
        return bust;
    }

    public void setBust(String bust) {
        this.bust = bust;
    }

    public String getHip() {
        return hip;
    }

    public void setHip(String hip) {
        this.hip = hip;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getProjectType() {
        return projectType;
    }

    public void setProjectType(Integer projectType) {
        this.projectType = projectType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberVO memberVO = (MemberVO) o;

        return id != null ? id.equals(memberVO.id) : memberVO.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
