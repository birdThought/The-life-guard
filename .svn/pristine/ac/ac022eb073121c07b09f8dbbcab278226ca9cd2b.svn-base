package com.lifeshs.pojo.huanxin;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 环信群组
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年2月24日 下午4:35:32
 */
public class GroupDTO {

    /** 群组ID */
    private String id;
    /** 名称 */
    @JSONField(name = "groupname")
    private String groupName;

    /** 描述 */
    private String desc;

    /** 是否公开 */
    @JSONField(name = "public")
    private boolean publicGroup;

    /** 最大成员数 */
    private int maxusers;

    /** 进群是否需要群主批准 */
    private boolean approval;

    /** 群主环信ID */
    private String owner;

    /** 群成员环信ID */
    private List<String> members;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    /** 群组名称 */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDesc() {
        return desc;
    }

    /** 群组描述 */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isPublicGroup() {
        return publicGroup;
    }

    /** 是否公开 */
    public void setPublicGroup(boolean publicGroup) {
        this.publicGroup = publicGroup;
    }

    public int getMaxusers() {
        return maxusers;
    }

    /** 群组最大群员数，默认200，最大值为2000 */
    public void setMaxusers(int maxusers) {
        this.maxusers = maxusers;
    }

    public boolean isApproval() {
        return approval;
    }

    /**
     * 进群是否需要群主批准
     */
    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getOwner() {
        return owner;
    }

    /** 管理员环信ID */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getMembers() {
        return members;
    }

    /** 群组成员ID集合 */
    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "GroupDTO [id=" + id + ", groupName=" + groupName + ", desc=" + desc + ", publicGroup=" + publicGroup
                + ", maxusers=" + maxusers + ", approval=" + approval + ", owner=" + owner + ", members=" + members
                + "]";
    }
}
