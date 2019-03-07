package com.lifeshs.dto.manager.serve;

/**
 * 通过环信CODE得到课堂信息的返回
 * Created by dengfeng on 2017/7/22 0022.
 */
public class GetLessonByHXData {
    private String code;
    private String huanxinId;
    private String photo;
    private String projectCode;
    private String name;
    private String userCount;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHuanxinId() {
        return huanxinId;
    }

    public void setHuanxinId(String huanxinId) {
        this.huanxinId = huanxinId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserCount() {
        return userCount;
    }

    public void setUserCount(String userCount) {
        this.userCount = userCount;
    }
}
