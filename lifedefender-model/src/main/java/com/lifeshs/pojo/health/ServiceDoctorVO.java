package com.lifeshs.pojo.health;

import java.util.List;

public class ServiceDoctorVO {

	private int id;
	private String photo;
	private String realName;
	private String about;
	private String detail;

	private String orgName;
	
	private String classify;

	private List<OrgServeVO> serves;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public List<OrgServeVO> getServes() {
		return serves;
	}

	public void setServes(List<OrgServeVO> serves) {
		this.serves = serves;
	}
}
