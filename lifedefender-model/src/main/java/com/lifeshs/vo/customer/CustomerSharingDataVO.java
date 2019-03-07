package com.lifeshs.vo.customer;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 客服共享信息
 * author: wenxian.cai
 * date: 2017/10/23 14:37
 */
@Data
public class CustomerSharingDataVO implements Serializable {

	private Integer id;
	/** 用户编号 */
	private String userNo;
	/** 登录账号 */
	private String userName;
	/** 姓名 */
	private String name;
	/** 创建日期 */
	private Date createDate;
	/** 修改日期 */
	private Date modifyDate;
	/** 状态 */
	private int status;
	/** 头像 */
	private String photo;
	/** 环信code */
	private String userCode;
	/** 代理商类型 1代理商 2业务员 null平台用户*/
	private Integer agentId;
	private Integer agentNum;
	/** 用户拥有的角色 */
	private String[] roles;
	/** 用户拥有的权限 */
	private String[] perms;
}
