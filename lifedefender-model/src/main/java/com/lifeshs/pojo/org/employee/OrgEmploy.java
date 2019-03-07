package com.lifeshs.pojo.org.employee;

import java.io.Serializable;

/**
 * 员工pojo
 * 
 * @author zhansi.Xu
 * @DateTime 2016年9月9日
 * @Comment
 */
@SuppressWarnings("serial")
public class OrgEmploy implements Serializable{
	private Integer id;// 主键id
	private int userType;// 1代表服务师，0代表管理员
	private String realName;// 真实姓名
	private String userName;// 用户名
	private String orgName;//所在的机构名字
	private boolean sex;//性别
	private String serveGroup;//所服务的群组
	private String photo;//该员工的头像
	public int groupCount;//服务的群组数量
	private String groupNames;
	public int getGroupCount() {
		return groupCount;
	}

	public void setGroupCount(int groupCount) {
		this.groupCount = groupCount;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}
	public String getOrgName() {
		return orgName;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getServeGroup() {
		return serveGroup;
	}

	public void setServeGroup(String serveGroup) {
		this.serveGroup = serveGroup;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupNames() {
		return groupNames;
	}

	public void setGroupNames(String groupNames) {
		this.groupNames = groupNames;
	}
}
