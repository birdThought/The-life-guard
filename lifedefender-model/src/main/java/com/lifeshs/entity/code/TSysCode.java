package com.lifeshs.entity.code;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * t_sys_code
 */
@Table(name = "t_sys_code", schema = "")
@SuppressWarnings("serial")
public class TSysCode implements Serializable{
	
	private Integer id;
	
	/**会员编码*/
	private String memberCode;
	
	/**机构用户编码*/
	private String orgUserCode;
	
	/**机构编码*/
	private String orgCode;
	
	public TSysCode() {
		super();
	}
	
	public TSysCode(Integer id, String memberCode, String orgUserCode, String orgCode) {
		super();
		this.id = id; 
		this.memberCode = memberCode; 
		this.orgUserCode = orgUserCode; 
		this.orgCode = orgCode; 
	}
	
	@Column(name ="id",nullable=true,length=11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name ="memberCode",nullable=false,length=8)
	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	@Column(name ="orgUserCode",nullable=false,length=6)
	public String getOrgUserCode() {
		return orgUserCode;
	}

	public void setOrgUserCode(String orgUserCode) {
		this.orgUserCode = orgUserCode;
	}
	@Column(name ="orgCode",nullable=false,length=5)
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
}