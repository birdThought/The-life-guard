package com.lifeshs.pojo.health;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.lifeshs.utils.ListUtil;

/**
 * Created by XuZhanSi on 2016/10/17 0017.
 */
public class ServiceItem implements Serializable {
	/** 描述 */
	private static final long serialVersionUID = 6688190995117235527L;

	private Integer orgId;// 服务机构ID
	private String orgName;// 服务机构名称
	private String street;// 服务机构街道地址
	private String logo;// 服务机构的logo
	private String serviceList;// 服务机构开通的服务
	private String classify;// 服务机构的分类
	private String about;
	private Integer count;
	private String address;
	private String provinceCode;
	private String cityCode;
	private String districtCode;

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getServiceList() {
		StringBuffer serviceList = new StringBuffer();

		if (this.serviceList != null && !this.serviceList.equals("")) {
			String[] services = this.serviceList.split(",");
			List<String> serviceList_tmp = new ArrayList<>();
			for (String service : services) {
				serviceList_tmp.add(service);
			}
			serviceList_tmp = ListUtil.removeRepeatElement(serviceList_tmp, String.class);

			for (String service : serviceList_tmp) {
				serviceList.append(service + ",");
			}
			if (serviceList.length() > 0) {
				serviceList = new StringBuffer(serviceList.substring(0, serviceList.length() - 1));
			}
		}

		return serviceList.toString();
	}

	public void setServiceList(String serviceList) {
		this.serviceList = serviceList;
	}

	public String getClassify() {
		return classify;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

}
