package com.lifeshs.pojo.org.server;

import java.util.Date;

/**
 * 服务群组的基本信息
 * 
 * @author zhansi.Xu
 * @DateTime 2016年9月8日
 * @Comment
 */
public class OrgServerGroupBase {
	private Integer id;// 群组的id
	private String name;// 群组名字
	private int memberCount;// 该群组人数
	private Date createDate;// 创建日期
	private Integer orgServeId;//机构服务Id

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getOrgServeId() {
		return orgServeId;
	}

	public void setOrgServeId(Integer orgServeId) {
		this.orgServeId = orgServeId;
	}

	@Override
	public String toString() {
		return "OrgServerGroupBase [id=" + id + ", name=" + name + ", memberCount=" + memberCount + ", createDate="
				+ createDate + "]";
	}
}
