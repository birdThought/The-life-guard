package com.lifeshs.po.user;

import lombok.Data;

import java.util.Date;

/**
 * 用户服务记录
 * author: wenxian.cai
 * date: 2017/11/3 10:01
 */
@Data
public class UserServeRecordPO {

	/** 主键 */
	private int id;

	/** 用户id */
	private int userId;

	/** 客服id */
	private int customerId;

	/** 服务日期 */
	private Date serveDate;

	/** 服务内容 */
	private String serveContent;

	/** 备注 */
	private String remark;

	/** 创建日期 */
	private Date createDate;

	/** 修改日期 */
	private Date modifyDate;

}
