package com.lifeshs.dto.manager.member;

/**
 * 得到员工列表的返回数据类
 * Created by dengfeng on 2017/7/3 0003.
 */
public class GetMemberListData {
    private int id;
    private String realName;
    private String mobile;
    private String photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
