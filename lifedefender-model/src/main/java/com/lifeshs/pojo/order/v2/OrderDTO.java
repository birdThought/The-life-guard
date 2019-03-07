package com.lifeshs.pojo.order.v2;

import com.lifeshs.common.constants.common.order.StatusEnum;
import com.lifeshs.po.CommentPO;
import com.lifeshs.pojo.org.employee.EmployeeDTO;
import com.lifeshs.pojo.org.service.ServiceCommonDTO;
import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import com.lifeshs.vo.RefundOrderVO;

import lombok.Data;

/**
 * 订单
 * 
 * @author yuhang.weng
 * @version 2.0
 * @DateTime 2017年6月19日 下午3:13:42
 */
public @Data class OrderDTO {

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

    /** 服务项目类型1：咨询；2：线下；3：上门；4：课堂 */
    private int projectType;

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

    /** 平台编号 */
    private String sysUserNo;
    /** 平台分成 */
    private int sysIncome;
    /** 代理商编号 */
    private String agentUserNo;
    /** 代理商分成 */
    private int agentIncome;
    /** 业务员编号 */
    private String salesmanUserNo;
    /** 业务员分成 */
    private int salesmanIncome;
    /** 引入机构编号 */
    private String introduceOrgUserNo;
    /** 引入机构分成 */
    private int introduceOrgIncome;
    /** 服务机构编号 */
    private String serviceOrgUserNo;
    /** 服务机构分成 */
    private int serviceOrgIncome;
    
    /** 结算id */
    private Integer statementId;

    /** 是否已删除(0：未删除；1：已删除) */
    private Boolean deleted;

    /** 扩展字段 */
    /** 评论 */
    private CommentPO comment;
    /** 退款订单 */
    private RefundOrderVO refund;
    /** 服务师 */
    private EmployeeDTO serveUser;
    /** 通用的项目 */
    private ServiceCommonDTO commonProject;
    /** 服务 */
    private ServeTypeSecondDTO serve;

    /** 自行设定的字段 */
    /** 订单状态 */
    private StatusEnum statusEnum;
    /** 环信账号 */
    private String huanxinUserName;
    /** 课堂项目id */
    private Integer projectLessonId;
    /** 电子券id */
    private Integer couponsId;
    /** 具体服务项目id */
    private Integer serveItemId;
}
