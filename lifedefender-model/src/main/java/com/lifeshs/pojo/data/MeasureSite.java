package com.lifeshs.pojo.data;

import java.util.Date;

public class MeasureSite {
	
	 private Integer id;
	 private String name;
	 private Double longitude;
	 private Double latitude;
	 private String cityAreaCode;
	 private String logo;
	 private String banner;
	 private String phoneNumber;
	 private Date createDate;
	 private Integer free;
	 private String address;
	 private Integer orgId;
	 private String statusMap;
	 		 
	 
	 
	public MeasureSite() {
		super();
		// TODO Auto-generated constructor stub
	}



	public MeasureSite(Integer id, String name, Double longitude, Double latitude, String cityAreaCode, String logo,
			String banner, String phoneNumber, Date createDate, Integer free, String address, Integer orgId,
			String statusMap) {
		super();
		this.id = id;
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
		this.cityAreaCode = cityAreaCode;
		this.logo = logo;
		this.banner = banner;
		this.phoneNumber = phoneNumber;
		this.createDate = createDate;
		this.free = free;
		this.address = address;
		this.orgId = orgId;
		this.statusMap = statusMap;
	}



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



	public Double getLongitude() {
		return longitude;
	}



	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}



	public Double getLatitude() {
		return latitude;
	}



	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}



	public String getCityAreaCode() {
		return cityAreaCode;
	}



	public void setCityAreaCode(String cityAreaCode) {
		this.cityAreaCode = cityAreaCode;
	}



	public String getLogo() {
		return logo;
	}



	public void setLogo(String logo) {
		this.logo = logo;
	}



	public String getBanner() {
		return banner;
	}



	public void setBanner(String banner) {
		this.banner = banner;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Integer getFree() {
		return free;
	}



	public void setFree(Integer free) {
		this.free = free;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Integer getOrgId() {
		return orgId;
	}



	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}



	public String getStatusMap() {
		return statusMap;
	}



	public void setStatusMap(String statusMap) {
		this.statusMap = statusMap;
	}
	
	 
	 
}
