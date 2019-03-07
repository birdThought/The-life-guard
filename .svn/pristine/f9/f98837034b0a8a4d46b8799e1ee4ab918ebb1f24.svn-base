package com.lifeshs.entity.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_org_group
 */
@Table(name = "t_org_group", schema = "")
@SuppressWarnings("serial")
public class TOrgGroup implements Serializable {
    /** 机构内部群组 */
    private Integer id;

    /** 机构服务d */
    private Integer orgServeId;

    /** 上级，第一级为空 */
    private Integer parentId;

    /** 群组名称 */
    private String name;

    /** 二维码地址 */
    private String qrCode;

    /** 备注 */
    private Integer remark;

    /** 群组创建日期 */
    private java.util.Date createDate;

    /** 0_普通群组、1_默认分组 */
    private Boolean defaultGroup;

    /** 群组头像路径 */
    private String photo;

    /** 环信群组ID */
    private String huanxinId;

    /** 0_正常聊天,1_禁言 */
    private Boolean silence;

    /** 群组描述 */
    private String description;

    /** 群组创建者ID（机构用户） */
    private Integer creatorId;

    public TOrgGroup() {
        super();
    }

    public TOrgGroup(Integer id, Integer orgServeId, Integer parentId, String name, String qrCode, Integer remark,
            java.util.Date createDate, Boolean defaultGroup, String photo, String huanxinId, Boolean silence,
            String description, Integer creatorId) {
        super();
        this.id = id;
        this.orgServeId = orgServeId;
        this.parentId = parentId;
        this.name = name;
        this.qrCode = qrCode;
        this.remark = remark;
        this.createDate = createDate;
        this.defaultGroup = defaultGroup;
        this.photo = photo;
        this.huanxinId = huanxinId;
        this.silence = silence;
        this.description = description;
        this.creatorId = creatorId;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "orgServeId", nullable = true, length = 11)
    public Integer getOrgServeId() {
        return orgServeId;
    }

    public void setOrgServeId(Integer orgServeId) {
        this.orgServeId = orgServeId;
    }

    @Column(name = "parentId", nullable = false, length = 11)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "qrCode", nullable = false, length = 150)
    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Column(name = "remark", nullable = false, length = 11)
    public Integer getRemark() {
        return remark;
    }

    public void setRemark(Integer remark) {
        this.remark = remark;
    }

    @Column(name = "createDate", nullable = false, length = 10)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "defaultGroup", nullable = false, length = 1)
    public Boolean getDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(Boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    @Column(name = "photo", nullable = false, length = 150)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Column(name = "huanxinId", nullable = false, length = 20)
    public String getHuanxinId() {
        return huanxinId;
    }

    public void setHuanxinId(String huanxinId) {
        this.huanxinId = huanxinId;
    }

    @Column(name = "silence", nullable = false, length = 1)
    public Boolean getSilence() {
        return silence;
    }

    public void setSilence(Boolean silence) {
        this.silence = silence;
    }

    @Column(name = "description", nullable = false, length = 500)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "creatorId", nullable = false, length = 11)
    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

}