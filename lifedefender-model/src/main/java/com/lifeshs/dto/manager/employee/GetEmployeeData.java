package com.lifeshs.dto.manager.employee;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;
import java.util.List;

/**
 * 得到员工列表的返回数据类
 * Created by dengfeng on 2017/7/3 0003.
 */
public class GetEmployeeData {
    private int id;
    private String realName;
    private String mobile;
    private String photo;
    @JSONField(format="yyyy-MM-dd")
    private Date birthday;
    private String professionalName;
    private int status;
    private String address;
    private List<String> projectList;
    private boolean sex;
    private int memberNumber;
    private int orderNumber;
    private int charge;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProfessionalName() {
        return professionalName;
    }

    public void setProfessionalName(String professionalName) {
        this.professionalName = professionalName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }
}
