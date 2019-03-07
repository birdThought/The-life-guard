package com.lifeshs.po.workOrder;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;

/**
 * 用户提交工单
 * 
 * @author zizhen.huang
 * @DateTime 2017年12月28日09:50:12
 *
 */
@Data
public class WorkOrderPO {
	/** 主键 */
	private Integer id;
	/** 用户id */
	private Integer userId;
	/** vip套餐id */
	private Integer vipComboId;
	/** vip套餐项id */
	private Integer vipComboItemId;
	/** 预约时间 */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
	/** 状态:已提交待审核0,审核成功1,审核失败2 */
	private Integer status;
	/** 创建日期 */
	private Date createDate;
	/** 修改时间 */
	private Date modifyDate;
}
