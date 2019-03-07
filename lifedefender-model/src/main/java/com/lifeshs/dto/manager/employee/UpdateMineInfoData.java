package com.lifeshs.dto.manager.employee;

/**
 * 更新个人信息的提交数据
 * Created by dengfeng on 2017/7/6 0006.
 */
public class UpdateMineInfoData {
    private String address;     //联系地址
    private String about;      //个人简介
    private String expertise;  //专业特长

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
}
