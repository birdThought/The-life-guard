package com.lifeshs.po;

import java.util.Date;

import com.lifeshs.common.constants.common.order.StatusEnum;
import com.lifeshs.pojo.org.service.ServeTypeDTO;
import com.lifeshs.pojo.serve.ServeTypeSecondDTO;
import com.lifeshs.vo.RefundOrderVO;

import lombok.Data;

/**
 * 订单实体类 Created by dengfeng on 2017/6/27 0027.
 */
public @Data class OrderPO {
    OrgPO org;// 机构
    ProjectComboPO projectCombo;// 套餐

    int id;
    /** 订单号 */
    String orderNumber;
    /** 会员 */
    UserPO user;
    /** 支付模式（按次按时间） */
    int chargeMode;
    /** 订单subject */
    String subject;
    /** 订单body */
    String body;
    /** 价格 */
    int price;
    /** 数量 */
    int number;
    /** 费用 */
    int charge;
    /** 开始日期 */
    Date startDate;
    /** 结束日期 */
    Date endDate;
    /** 剩余次数 */
    int timesRemaining;
    Long hasWarning;
    Date warningDate;
    int status;
    String recard;
    Date createDate;
    /** 订单类型 */
    int orderType;
    /** 服务师 */
    EmployeePO employee;
    String projectCode; // 服务ID
    /** 券码 */
    String verifyCode;
    /** 项目图片 */
    String projectImage;
    /** 通用项目 */
    ProjectPO project;
    /** 服务 */
    ServeTypeSecondDTO serveType;
    /** 用户地址 */
    UserAddressPO userAddress;
    /** 评价 */
    CommentPO comment;
    /** 门店 */
    StorePO store;

    Integer serveId;
    String address; // 收货人地址
    String receiverName; // 收获人名字
    String receiverMobile; // 收货人手机

    StatusEnum statusEnum;
    RefundOrderVO refund; // 退款

    String huanxinUserName; // 环信用户名(可能是群组，也可能是服务师)
    LessonPO lesson; //课堂
    
    /** 电子券id */
    private Integer couponsId;
    /** 具体服务项目id */
    private Integer serveItemId;
}
