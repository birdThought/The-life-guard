package com.lifeshs.entity.org;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_org
 */
@Table(name = "t_org", schema = "")
@SuppressWarnings("serial")
public class TOrg implements Serializable{
	
	private Integer id;
	
	private String userNo;
	
	private String parentId;
	
	/**机构名称*/
	private String orgName;
	
	/**机构编码*/
	private String orgCode;
	
	/**机构LOGO*/
	private String logo;
	
	/**是否企业验证:已审核_1,未审核_0,未通过_2,后台不显示审核_3*/
	private Integer orgVerified;
	
	/**审核不通过理由*/
	private String verifiedCause;
	
	/**机构服务电话*/
	private String tel;
	
	/**服务性质*/
	private String orgType;
	
	/**联系人姓名*/
	private String contacts;
	
	/**联系方式*/
	private String contactInformation;
	
	/**营业执照路径1*/
	private String businessLicense;
	
	/**结算帐户类型*/
	private Integer accountType;
	
	/**结算帐户*/
	private String account;
	
	/**状态,正常_0,停用_1,未审核_3,未通过_4*/
	private Integer status;
	
	/**机构类型,管理机构_0,服务机构_1,个体机构_2*/
	private Integer type;
	
	/**父机构ID，没有为0*/
	private Integer parent;
	
	/**省*/
	private String province;
	
	/**市*/
	private String city;
	
	/**县(区)*/
	private String district;
	
	/**街道地址*/
	private String street;
	
	/**经度(建议不超过.后六位)*/
	private String longitude;
	
	/**纬度(建议不超过.后六位)*/
	private String latitude;
	
	/**机构简介*/
	private String about;
	
	/**机构详细介绍(html)*/
	private String detail;
	
	/**创建日期*/
	private java.util.Date createDate;
	
	/**修改时间*/
	private java.util.Date modifyDate;
	
	/**是否推荐机构*/
	private Boolean isRecommend;
	
	/**营业执照号*/
	private String businessLicenseNumber;
	
	/**营业执照路径2*/
	private String businessLicenseTwo;
	
	/**从事领域*/
	private String workField;
	
	/**银行开户名*/
	private String bankName;
	
	/**公司对公账号*/
	private String bankAccount;
	
	/**开户所在地区*/
	private String bankDistrict;
	
	/**开户支行名称*/
	private String bankBranch;
	
	/**法人代表*/
	private String legalPerson;
	
	/**法人性别*/
	private Boolean legalPersonGender;
	
	/**法人身份证*/
	private String legalPersonIdCard;
	
	/**法人身份证照片路径1*/
	private String legalPersonPicOne;
	
	/**法人身份证照片路径2*/
	private String legalPersonPicTwo;
	
	/**地图状态*/
	private Integer statusMap;
	
	

	public TOrg() {
		super();
	}
	
	
	
	
	public TOrg(Integer id,String userNo,String parentId, String orgName, String orgCode, String logo, Integer orgVerified, String verifiedCause,
			String tel, String orgType, String contacts, String contactInformation, String businessLicense,
			Integer accountType, String account, Integer status, Integer type, Integer parent, String province,
			String city, String district, String street, String longitude, String latitude, String about, String detail,
			Date createDate, Date modifyDate, Boolean isRecommend, String businessLicenseNumber,
			String businessLicenseTwo, String workField, String bankName, String bankAccount, String bankDistrict,
			String bankBranch, String legalPerson, Boolean legalPersonGender, String legalPersonIdCard,
			String legalPersonPicOne, String legalPersonPicTwo, Integer statusMap) {
		super();
		this.id = id;
		this.userNo = userNo;
		this.parentId = parentId;
		this.orgName = orgName;
		this.orgCode = orgCode;
		this.logo = logo;
		this.orgVerified = orgVerified;
		this.verifiedCause = verifiedCause;
		this.tel = tel;
		this.orgType = orgType;
		this.contacts = contacts;
		this.contactInformation = contactInformation;
		this.businessLicense = businessLicense;
		this.accountType = accountType;
		this.account = account;
		this.status = status;
		this.type = type;
		this.parent = parent;
		this.province = province;
		this.city = city;
		this.district = district;
		this.street = street;
		this.longitude = longitude;
		this.latitude = latitude;
		this.about = about;
		this.detail = detail;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.isRecommend = isRecommend;
		this.businessLicenseNumber = businessLicenseNumber;
		this.businessLicenseTwo = businessLicenseTwo;
		this.workField = workField;
		this.bankName = bankName;
		this.bankAccount = bankAccount;
		this.bankDistrict = bankDistrict;
		this.bankBranch = bankBranch;
		this.legalPerson = legalPerson;
		this.legalPersonGender = legalPersonGender;
		this.legalPersonIdCard = legalPersonIdCard;
		this.legalPersonPicOne = legalPersonPicOne;
		this.legalPersonPicTwo = legalPersonPicTwo;
		this.statusMap = statusMap;
	}




	@Column(name ="statusMap",nullable=true,length=11)
	public Integer getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Integer statusMap) {
		this.statusMap = statusMap;
	}
	
	
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name ="userNo",nullable=false,length=32)
    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Column(name ="parentId",nullable=false,length=11)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
	@Column(name ="orgName",nullable=true,length=30)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name ="orgCode",nullable=false,length=20)
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	@Column(name ="logo",nullable=false,length=150)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Column(name ="orgVerified",nullable=false,length=2)
	public Integer getOrgVerified() {
		return orgVerified;
	}

	public void setOrgVerified(Integer orgVerified) {
		this.orgVerified = orgVerified;
	}
	@Column(name ="verifiedCause",nullable=false,length=200)
	public String getVerifiedCause() {
		return verifiedCause;
	}

	public void setVerifiedCause(String verifiedCause) {
		this.verifiedCause = verifiedCause;
	}
	@Column(name ="tel",nullable=false,length=15)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name ="orgType",nullable=false,length=10)
	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	@Column(name ="contacts",nullable=false,length=20)
	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	@Column(name ="contactInformation",nullable=false,length=30)
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	@Column(name ="businessLicense",nullable=false,length=200)
	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	@Column(name ="accountType",nullable=false,length=4)
	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	@Column(name ="account",nullable=false,length=50)
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	@Column(name ="status",nullable=false,length=4)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Column(name ="type",nullable=false,length=4)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name ="parent",nullable=false,length=11)
	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}
	@Column(name ="province",nullable=false,length=2)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	@Column(name ="city",nullable=false,length=2)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Column(name ="district",nullable=false,length=2)
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	@Column(name ="street",nullable=false,length=100)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name ="longitude",nullable=false,length=12)
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Column(name ="latitude",nullable=false,length=12)
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	@Column(name ="about",nullable=false,length=500)
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
	@Column(name ="detail",nullable=false,length=21845)
	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Column(name ="createDate",nullable=false,length=19)
	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	@Column(name ="modifyDate",nullable=false,length=19)
	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	@Column(name ="isRecommend",nullable=false,length=1)
	public Boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	@Column(name ="businessLicenseNumber",nullable=false,length=32)
	public String getBusinessLicenseNumber() {
		return businessLicenseNumber;
	}

	public void setBusinessLicenseNumber(String businessLicenseNumber) {
		this.businessLicenseNumber = businessLicenseNumber;
	}
	@Column(name ="businessLicenseTwo",nullable=false,length=200)
	public String getBusinessLicenseTwo() {
		return businessLicenseTwo;
	}

	public void setBusinessLicenseTwo(String businessLicenseTwo) {
		this.businessLicenseTwo = businessLicenseTwo;
	}
	@Column(name ="workField",nullable=false,length=32)
	public String getWorkField() {
		return workField;
	}

	public void setWorkField(String workField) {
		this.workField = workField;
	}
	@Column(name ="bankName",nullable=false,length=32)
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	@Column(name ="bankAccount",nullable=false,length=20)
	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	@Column(name ="bankDistrict",nullable=false,length=32)
	public String getBankDistrict() {
		return bankDistrict;
	}

	public void setBankDistrict(String bankDistrict) {
		this.bankDistrict = bankDistrict;
	}
	@Column(name ="bankBranch",nullable=false,length=32)
	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}
	@Column(name ="legalPerson",nullable=false,length=20)
	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	@Column(name ="legalPersonGender",nullable=false,length=2)
	public Boolean getLegalPersonGender() {
		return legalPersonGender;
	}

	public void setLegalPersonGender(Boolean legalPersonGender) {
		this.legalPersonGender = legalPersonGender;
	}
	@Column(name ="legalPersonIdCard",nullable=false,length=32)
	public String getLegalPersonIdCard() {
		return legalPersonIdCard;
	}

	public void setLegalPersonIdCard(String legalPersonIdCard) {
		this.legalPersonIdCard = legalPersonIdCard;
	}
	@Column(name ="legalPersonPicOne",nullable=false,length=200)
	public String getLegalPersonPicOne() {
		return legalPersonPicOne;
	}

	public void setLegalPersonPicOne(String legalPersonPicOne) {
		this.legalPersonPicOne = legalPersonPicOne;
	}
	@Column(name ="legalPersonPicTwo",nullable=false,length=200)
	public String getLegalPersonPicTwo() {
		return legalPersonPicTwo;
	}

	public void setLegalPersonPicTwo(String legalPersonPicTwo) {
		this.legalPersonPicTwo = legalPersonPicTwo;
	}
	
}