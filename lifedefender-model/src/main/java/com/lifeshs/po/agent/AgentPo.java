package com.lifeshs.po.agent;

import java.sql.Date;

import lombok.Data;

/**
 * 代理商PO
 * 
 * @author shiqiang.zeng
 * @date 2018.1.18 17:11
 */
@Data
public class AgentPo {

	private Integer id;
	/** 代理商名称 */
	private String agentName;
	/** 真实姓名 */
	private String name;
	/** 状态 正常_0,禁用_1 */
	private int status;
	/** 邮箱 */
	private String contactMan;
	/** 联系电话 */
	private String phone;
	/** 邮箱 */
	private String email;
	/** 省码 */
	private String provinceCode;
	/** 市码 */
	private String cityCode;
	/** 区码 */
    private String areaCode;
    /** 详细地址 */
    private String address;
	/** 创建日期 */
	private Date createDate;
	/** 修改日期 */
	private Date modifyDate;
}
