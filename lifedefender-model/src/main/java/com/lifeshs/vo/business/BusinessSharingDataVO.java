package com.lifeshs.vo.business;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 渠道商基础数据
 * author: wenxian.cai
 * date: 2017/10/23 16:19
 */
@Data
public class BusinessSharingDataVO implements Serializable{

	private Integer id;
	/** 登录账号 */
	private String userName;
	/** 名字 */
	private String name;
	/** 头像存放路径 */
	private String photo;
	/** 环信编号 */
	private String userCode;
	/** 创建日期 */
	private Date createDate;
	/** 修改日期 */
	private Date modifyDate;
	/** 状态 */
	private int status;
	/** 用户类型 */
	private Integer type;
}
