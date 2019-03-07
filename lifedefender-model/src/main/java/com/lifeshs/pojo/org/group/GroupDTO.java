package com.lifeshs.pojo.org.group;

import java.util.List;

import com.lifeshs.pojo.org.OrgUserDTO;

/**
 * 群组
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月8日 下午3:03:38
 */
public class GroupDTO {

    private Integer id;

    /** 群组环信ID */
    private String huanxinId;

    /** 群组名称 */
    private String name;

    /** 0_普通群组、1_默认分组 */
    private Boolean defaultGroup;

    /** 群组头像路径 */
    private String photo;

    /** 0_正常聊天,1_禁言 */
    private Boolean silence;

    /** 群组描述 */
    private String description;

    /** 群组下的服务师集合 */
    private List<OrgUserDTO> orgUsers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHuanxinId() {
        return huanxinId;
    }

    public void setHuanxinId(String huanxinId) {
        this.huanxinId = huanxinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(Boolean defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getSilence() {
        return silence;
    }

    public void setSilence(Boolean silence) {
        this.silence = silence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OrgUserDTO> getOrgUsers() {
        return orgUsers;
    }

    public void setOrgUsers(List<OrgUserDTO> orgUsers) {
        this.orgUsers = orgUsers;
    }

    @Override
    public String toString() {
        return "GroupDTO [id=" + id + ", huanxinId=" + huanxinId + ", name=" + name + ", defaultGroup=" + defaultGroup
                + ", photo=" + photo + ", silence=" + silence + ", description=" + description + ", orgUsers="
                + orgUsers + "]";
    }

}
