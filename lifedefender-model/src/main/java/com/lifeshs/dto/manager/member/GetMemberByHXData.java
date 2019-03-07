package com.lifeshs.dto.manager.member;

/**
 * 通过环信CODE得到用户信息的返回
 * Created by dengfeng on 2017/7/22 0022.
 */
public class GetMemberByHXData {
    private int userId;
    private String huanxinId;
    private String realName;
    private String photo;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHuanxinId() {
        return huanxinId;
    }

    public void setHuanxinId(String huanxinId) {
        this.huanxinId = huanxinId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
