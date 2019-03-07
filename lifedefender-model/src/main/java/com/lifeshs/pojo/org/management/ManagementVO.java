package com.lifeshs.pojo.org.management;

/**
 * 管理机构、服务机构VO
 * 
 * @author yuhang.weng
 * @DateTime 2016年9月5日 下午3:51:54
 */
public class ManagementVO {

	/** id */
	private Integer id;
	/** 用户名 */
	private String userName;
	/** 密码 */
	private String password;

	/** 机构名 */
	private String orgName;
	/** 机构性质 */
	private String orgType;
	/** 机构类型 */
	private Integer type;
	/** 联系人 */
	private String contact;
	/** 联系号码 */
	private String contactInformation;
	/** 省 */
	private String province;
	/** 市 */
	private String city;
	/** 县（区） */
	private String district;
	/** 详细地址 */
	private String street;
	/** 营业执照图片保存路径 */
	private String businessLicense;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
}
