package com.lifeshs.po;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 机构的服务项目
 * Created by dengfeng on 2017/7/3 0003.
 */
public class LessonPO {
    private Integer id;
    private String serialId;
    private String code;
    private String name;
    private int status;
    private int orgId;
    private int serveId;
    private BigDecimal price;
    private int lessonId;
    private String image;
    private int projectType;
    private Date createDate;
    private Date modifyDate;
    private String huanxinId;
    private String qrCode;
    private String introduce;
    private int creatorId;
    private Date startDate;
    private Date endDate;
    private int silence;
    private String lessonTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrgId() {
        return orgId;
    }

    public void setOrgId(int orgId) {
        this.orgId = orgId;
    }

    public int getServeId() {
        return serveId;
    }

    public void setServeId(int serveId) {
        this.serveId = serveId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProjectType() {
        return projectType;
    }

    public void setProjectType(int projectType) {
        this.projectType = projectType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getHuanxinId() {
        return huanxinId;
    }

    public void setHuanxinId(String huanxinId) {
        this.huanxinId = huanxinId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getSilence() {
        return silence;
    }

    public void setSilence(int silence) {
        this.silence = silence;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }
}
