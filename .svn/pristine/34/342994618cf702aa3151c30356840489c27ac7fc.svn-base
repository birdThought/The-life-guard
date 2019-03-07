package com.lifeshs.entity.report;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * t_report
 */
@Table(name = "t_report", schema = "")
@SuppressWarnings("serial")
public class TReport implements Serializable {

	private Integer id;

	/** 留言 */
	private String message;

	/** 联系方式 */
	private String contactInformation;

	/** 是否已读 */
	private Boolean isRead;

	/** 用户类型，0_会员，1_机构用户 */
	private Integer userType;


	/**
	 * 用户Id
	 */
	private Integer userId;
	/**
	 * 回复内容
	 */
	private String reply;

	/** 创建日期 */
	private java.util.Date createDate;

	public TReport() {
		super();
	}

	public TReport(Integer id, String message, String contactInformation, Boolean isRead, java.util.Date createDate) {
		super();
		this.id = id;
		this.message = message;
		this.contactInformation = contactInformation;
		this.isRead = isRead;
		this.createDate = createDate;
	}

	@Column(name = "id", nullable = true, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name ="message",nullable=false,length=16383)
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "contactInformation", nullable = false, length = 30)
	public String getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}

	@Column(name = "isRead", nullable = false, length = 1)
	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	@Column(name = "createDate", nullable = false, length = 19)
	public java.util.Date getCreateDate() {
		return createDate;
	}

	@Column(name = "userId")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name = "reply")
	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}
	@Column(name = "userType")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

}