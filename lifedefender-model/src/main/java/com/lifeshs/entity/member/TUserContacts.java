package com.lifeshs.entity.member;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Table;

import com.lifeshs.common.constants.common.ContactTerminalType;

/**
 * t_user_contacts
 */
@Table(name = "t_user_contacts", schema = "")
@SuppressWarnings("serial")
public class TUserContacts implements Serializable {

	/** 用户联系人(预警、亲情号) */
	private Integer id;

	/** 用户ID */
	private Integer userId;

	/** 联系人姓名 */
	private String name;

	/** 联系号码(亲情号必须是手机) */
	private String contactNumber;

	// /** 是否预警号码 */
	// private Boolean isSOS;
	//
	// /** 是否亲情号码 */
	// private Boolean isFamily;

	/** 是否为接受短信号码 */
	private Boolean receiveSMS;

	/** 终端类型 */
	private Integer contactType;

	private String terminalName;

	private Boolean isC3SOS;

	/** 1表示该号码为SOS */
	private int sos;

	/** 1表示该号码为family */
	private int family;

	public TUserContacts() {
		super();
	}

	public TUserContacts(Integer id, Integer userId, String name, String contactNumber, Boolean isSOS,
			Boolean isFamily) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.contactNumber = contactNumber;
		// this.isSOS = isSOS;
		// this.isFamily = isFamily;
	}

	@Column(name = "id", nullable = true, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userId", nullable = true, length = 11)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "name", nullable = false, length = 10)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "contactNumber", nullable = false, length = 15)
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	// @Column(name = "isSOS", nullable = false, length = 1)
	// public Boolean getIsSOS() {
	// return isSOS;
	// }
	//
	// public void setIsSOS(Boolean isSOS) {
	// this.isSOS = isSOS;
	// }
	//
	// @Column(name = "isFamily", nullable = false, length = 1)
	// public Boolean getIsFamily() {
	// return isFamily;
	// }
	//
	// public void setIsFamily(Boolean isFamily) {
	// this.isFamily = isFamily;
	// }

	@Column(name = "receiveSMS", nullable = false, length = 1)
	public Boolean getReceiveSMS() {
		return receiveSMS;
	}

	public void setReceiveSMS(Boolean receiveSMS) {
		this.receiveSMS = receiveSMS;
	}

	@Column(name = "contactType", nullable = false, length = 1)
	public Integer getContactType() {
		return contactType;
	}

	public void setContactType(Integer contactType) {
		this.contactType = contactType;

		StringBuffer sb = new StringBuffer("");

		ContactTerminalType hf = ContactTerminalType.HEALTH_PACKAGE_FAMILY;
		ContactTerminalType cf = ContactTerminalType.C3_FAMILY;
		ContactTerminalType cs = ContactTerminalType.C3_SOS;

		sos = 0;
		family = 0;
		isC3SOS = false;

		if ((contactType | hf.value()) == contactType) {
			sb.append(hf.getName() + ",");
			sos = 1;
		}
		if ((contactType | cf.value()) == contactType) {
			sb.append(cf.getName() + ",");
			family = 1;
		}
		if ((contactType | cs.value()) == contactType) {
			sb.append(cs.getName() + ",");
			isC3SOS = true;
			sos = 1;
		}

		if (sb.length() > 0) {
			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		}

		terminalName = sb.toString();
	}

	public String getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

	public Boolean getIsC3SOS() {
		return isC3SOS;
	}

	public void setIsC3SOS(Boolean isC3SOS) {
		this.isC3SOS = isC3SOS;
	}

	public int getSos() {
		return sos;
	}

	public void setSos(int sos) {
		this.sos = sos;
	}

	public int getFamily() {
		return family;
	}

	public void setFamily(int family) {
		this.family = family;
	}
}