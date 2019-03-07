package com.lifeshs.dto.manager.serve;

/**
 * 健康课堂的成员信息
 * Created by dengfeng on 2017/7/5 0005.
 */
public class LessonUserInfo {
    private String userName;
    private String photo;      // 头像
    private String realName;      // 名称
    private int userId;       // 用户id
    private String userCode;  // 用户环信账号
    private String mobile;    // 手机
    private boolean sex;     // 性别，0_女，1_男
    private String email;    //  邮箱
    //workPhone   //    工作电话

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
