package com.lifeshs.entity.org;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_org_group_orguser
 */
@Table(name = "t_org_group_orguser", schema = "")
@SuppressWarnings("serial")
public class TOrgGroupOrguser implements Serializable {
    /** 群组所属服务师 */
    private Integer id;

    /** 群组ID */
    private Integer groupId;

    /** 服务师ID */
    private Integer orgUserId;

    /** 创建时间 */
    private java.util.Date createDate;

    public TOrgGroupOrguser() {
        super();
    }

    public TOrgGroupOrguser(Integer id, Integer groupId, Integer orgUserId, java.util.Date createDate) {
        super();
        this.id = id;
        this.groupId = groupId;
        this.orgUserId = orgUserId;
        this.createDate = createDate;
    }

    @Column(name = "id", nullable = true, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "groupId", nullable = false, length = 11)
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Column(name = "orgUserId", nullable = false, length = 11)
    public Integer getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(Integer orgUserId) {
        this.orgUserId = orgUserId;
    }

    @Column(name = "createDate", nullable = false, length = 19)
    public java.util.Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.util.Date createDate) {
        this.createDate = createDate;
    }

}