package com.lifeshs.po.agent;

import java.sql.Date;

import lombok.Data;

/**
 * 代理商或业务员与用户的关联关系PO
 * 
 * @author shiqiang.zeng
 * @date 2018.1.18 17:11
 */
@Data
public class AgentUserRelationPo {

	private Integer id;
	/** 代理商或业务员id  0平台   1代理商  2业务员 */
	private int adminUserId;
	/** 1.代理商    2.业务员 */
	private int adminType;
	/** 线下用户id */
	private int userId;
	/** 线下用户类型   1.业务员   2.机构    3.用户 */
	private int userType;
	/** 创建日期 */
    private Date createDate;
}
