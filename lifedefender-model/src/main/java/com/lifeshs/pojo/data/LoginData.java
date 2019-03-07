package com.lifeshs.pojo.data;

import org.apache.commons.lang3.StringUtils;

public class LoginData {

	private Boolean redirect;

	private String redirectUrl;

	private String urlType;

	private Boolean checkUserType;

	private Boolean accessOrgUser;

	private Boolean accessUser;

	public LoginData() {
		redirect = false;
		checkUserType = false;
		accessOrgUser = false;
		accessUser = false;
	}

	public Boolean getRedirect() {
		return redirect;
	}

	public void setRedirect(Boolean redirect) {
		this.redirect = redirect;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getUrlType() {
		return urlType;
	}

	public void setUrlType(String urlType) {
		if (StringUtils.equalsIgnoreCase(urlType, "gobuy")) {
			redirect = true;
			checkUserType = true;
			accessOrgUser = false;
			accessUser = true;
		}
		this.urlType = urlType;
	}

	public Boolean getCheckUserType() {
		return checkUserType;
	}

	public void setCheckUserType(Boolean checkUserType) {
		this.checkUserType = checkUserType;
	}

	public Boolean getAccessOrgUser() {
		return accessOrgUser;
	}

	public Boolean getAccessUser() {
		return accessUser;
	}
}
