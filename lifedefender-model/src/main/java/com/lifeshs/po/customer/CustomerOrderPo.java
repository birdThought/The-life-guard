package com.lifeshs.po.customer;

import java.util.Date;

import lombok.Data;

/**
 * 客服工单
 * 
 * @author shiqiang.zeng
 * @Date 2017年12月20日18:00
 */

@Data
public class CustomerOrderPo {
	/** 主键 */
	private Integer id;
	/**用户Id*/
	private int userId;
	/** 用户名 */
	private String userName;
	/** 性别 女_0,男_1 */
	private Boolean gender;
	/** 手机号码 */
	private String mobile;
	/** 服务名称 */
	private String serveName;
	/** 预约时间 */
	private Date appoinDate;
	/** 用户备注 */
	private String userRemark;
	/** 客服备注 */
	private String customerRemark;
	/** 确定时间 */
	private Date sureDate;
	/** 机构名称 */
	private String orgName;
	/** 地点 */
	private String address;
	/** 状态 未提交_ 0,成功_1,失败_2 */
	private Integer status;
	/** 创建日期 */
	private Date createDate;
	/** 修改日期 */
	private Date modifyDate;
	/**客服修改填充的东西*/
	private UpdateOrderPo updateOrderPo;
}
