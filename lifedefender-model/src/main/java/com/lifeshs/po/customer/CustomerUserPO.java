package com.lifeshs.po.customer;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 客服用户
 * author: wenxian.cai
 * date: 2017/10/10 10:24
 */
@Data
public class CustomerUserPO implements Serializable{

	private Integer id;
	/** 用户编号 */
	private String userNo;
	/** 登录账号 */
	private String userName;
	/** 登录密码 */
	private String password;
	/** 姓名 */
	private String name;
	/** 创建日期 */
	private Date createDate;
	/** 修改日期 */
	private Date modifyDate;
	/** 状态 */
	private int status;
	/** 用户code */
	private String userCode;
	/** 头像 */
	private String photo;
	/** 引荐人id*/
	private String parentId;
	/** 用户类型 (1.管理员 2.普通用户 3.财务 4.其它) */
	private Integer type;
	/** 代理商类型 1代理商 2业务员 null平台用户*/
	private Integer agentId;
	/** 代理商编号  */
	private Integer agentNum;
	/** 手机号码 */
	private String moblie;
	/** 最后登录时间 */
	private Date LastLoginTime;
	/** 省 */
	private String provinceCode;
	/** 市 */
	private String cityCode;
	/** 区 */
	private String areaCode;
	/** 详细地址 */
	private String address;
}
