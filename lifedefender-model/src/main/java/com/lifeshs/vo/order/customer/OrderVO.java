package com.lifeshs.vo.order.customer;

import java.util.Date;

import lombok.Data;

/**
 *  客服系统 - 订单详情VO
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2018年1月30日 下午1:59:42
 */
@Data
public class OrderVO {

    /** 订单编号 */
    private String orderNumber;
    /** 订单状态 */
    private Integer status;
    /** 消费金额（分） */
    private Integer charge;
    /** 平台分成比例 */
    private Integer profitShare;
    /** 平台收入 */
    private Integer sysIncome;
    /** 创建日期 */
    private Date createDate;
    /** 用户名 */
    private String userName;
    /** 用户姓名 */
    private String realName;
    /** 机构名 */
    private String orgName;
    /** 服务名称 */
    private String serveName;
    /** 平台编号 */
    private String sysUserNo;
    /** 代理商编号 */
    private String agentUserNo;
    /** 代理商分成 */
    private Integer agentIncome;
    /** 业务员编号 */
    private String salesmanUserNo;
    /** 业务员分成  */
    private Integer salesmanIncome;
    /** 引入机构编号 */
    private String introduceOrgUserNo;
    /** 引入机构分成 */
    private Integer introduceOrgIncome;
    /** 服务机构编号 */
    private String serviceOrgUserNo;
    /** 服务机构分成 */
    private Integer serviceOrgIncome;
    
}
