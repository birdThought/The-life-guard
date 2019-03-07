package com.lifeshs.po.order;

import lombok.Data;

/**
 *  订单PO
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2018年1月18日 下午6:03:17
 */
@Data
public class OrderPO {

    private Integer id;

    /** 订单号 */
    private String orderNumber;

    /** 用户ID(会员) */
    private Integer userId;

    /** 收费方式:0_免费，1_按次，2_按月，3_按年 */
    private Integer chargeMode;

    /** 交易标题/订单标题 */
    private String subject;

    /** 交易的内容描述 */
    private String body;

    /** 价格 */
    private Integer price;

    /** 数量 */
    private Integer number;

    /** 费用 */
    private Integer charge;

    /** 开始日期 */
    private java.util.Date startDate;

    /** 结束日期 */
    private java.util.Date endDate;

    /** 剩余次数 */
    private Integer timesRemaining;

    /** 设备异常值和 */
    private Integer hasWarning;

    private java.util.Date warningDate;

    /** 状态：待付款_1, 付款失败_2，有效_3，已完成_4，已取消_6，退款中_7，退款成功_8 */
    private Integer status;

    /** 服务记录 */
    private String recard;

    /** 创建时间 */
    private java.util.Date createDate;

    /** 订单类型,1_服务订单,2_短信充值订单,3_其他待扩展订单 */
    private Integer orderType;

    /** 服务师id */
    private Integer orgUserId;

    /** 服务项目唯一code */
    private String projectCode;

    /** 验证码 */
    private String verifyCode;

    /** 服务ID */
    private Integer serveId;

    /** 项目图片 */
    private String projectImage;

    /** （上门服务）收货地址 */
    private String address;

    /** 收货人名字 */
    private String receiverName;

    /** 收货人电话 */
    private String receiverMobile;

    /** 平台分成（百分比数字） */
    private Integer profitShare;

    /** 机构收入 */
    private Integer orgIncome;

    /** 结算id */
    private Integer statementId;

    /** 是否已删除(0：未删除；1：已删除) */
    private Boolean deleted;

    /** 用户备注 */
    private String userRemark;

    /** 病种名称 */
    private String userDiseasesName;

    /** 病种Id */
    private Integer userDiseasesId;

    /** 电子券id */
    private Integer couponsId;

    /** 具体服务项目id */
    private Integer serveItemId;
}
