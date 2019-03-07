package com.lifeshs.pojo.org.server;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 *  会员基本信息（详细）
 *  @author yuhang.weng  
 *  @DateTime 2016年9月14日 下午5:10:58
 */
public class OrgMemberMessageDetailVO extends OrgMemberBase {

	/** 头像保存路径 */
	private String photo;
	
	/** 生日 */
	private Date birthday;
	
	/** 当前服务名称 */
	private String currentServeName;
	
	/** 消费模式 */
	private String consumeMode;
	
	/** 收费方式 */
	private Integer chargeMode;
	
	/** 价格 */
	private Integer price;
	
	/** 组名称名称 */
	private String groupName;
	
	/** 服务师名字 */
	private String serveUserName;
	
	/** 用户账号 */
	private String userName;
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		if(photo == null || StringUtils.isBlank(photo)) {
			photo = "static/images/head.png";
		}
		this.photo = photo;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCurrentServeName() {
		return currentServeName;
	}

	public void setCurrentServeName(String currentServeName) {
		this.currentServeName = currentServeName;
	}

	public String getConsumeMode() {
		return consumeMode;
	}

	public void setConsumeMode(String consumeMode) {
		this.consumeMode = consumeMode;
	}

	public Integer getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(Integer chargeMode) {
		this.chargeMode = chargeMode;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getServeUserName() {
		return serveUserName;
	}

	public void setServeUserName(String serveUserName) {
		this.serveUserName = serveUserName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
