package com.lifeshs.pojo.member.contact;

import java.util.List;

import com.lifeshs.entity.member.TUserContacts;

/**
 *  版权归
 *  TODO 联系人传输层
 *  @author wenxian.cai 
 *  @datetime 2016年12月25日下午2:28:48
 */
public class ContactDto {
	
	/*联系人实体类*/
	public TUserContacts userContacts;
	
	/*终端类型*/
	public List terminalType;

	public TUserContacts getUserContacts() {
		return userContacts;
	}

	public void setUserContacts(TUserContacts userContacts) {
		this.userContacts = userContacts;
	}

	public List getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(List terminalType) {
		this.terminalType = terminalType;
	}
	
	
}
