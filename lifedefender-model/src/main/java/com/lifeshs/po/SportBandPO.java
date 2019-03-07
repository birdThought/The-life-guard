package com.lifeshs.po;

import java.util.Date;

public class SportBandPO {
    private Integer id;

    private Integer userId;

    private Date date;

    private Integer steps;

    private Integer mileage;

    private Integer kcal;

    private Short sleepDuration;

    private Short shallowDuration;

    private Short deepDuration;

    private Short wakeupDuration;

    private String deviceType;

    private Date createDate;

    public SportBandPO(Integer id, Integer userId, Date date, Integer steps, Integer mileage, Integer kcal, Short sleepDuration, Short shallowDuration, Short deepDuration, Short wakeupDuration, String deviceType, Date createDate) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.steps = steps;
        this.mileage = mileage;
        this.kcal = kcal;
        this.sleepDuration = sleepDuration;
        this.shallowDuration = shallowDuration;
        this.deepDuration = deepDuration;
        this.wakeupDuration = wakeupDuration;
        this.deviceType = deviceType;
        this.createDate = createDate;
    }

    public SportBandPO() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSteps() {
        return steps;
    }

    public void setSteps(Integer steps) {
        this.steps = steps;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getKcal() {
        return kcal;
    }

    public void setKcal(Integer kcal) {
        this.kcal = kcal;
    }

    public Short getSleepDuration() {
        return sleepDuration;
    }

    public void setSleepDuration(Short sleepDuration) {
        this.sleepDuration = sleepDuration;
    }

    public Short getShallowDuration() {
        return shallowDuration;
    }

    public void setShallowDuration(Short shallowDuration) {
        this.shallowDuration = shallowDuration;
    }

    public Short getDeepDuration() {
        return deepDuration;
    }

    public void setDeepDuration(Short deepDuration) {
        this.deepDuration = deepDuration;
    }

    public Short getWakeupDuration() {
        return wakeupDuration;
    }

    public void setWakeupDuration(Short wakeupDuration) {
        this.wakeupDuration = wakeupDuration;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}