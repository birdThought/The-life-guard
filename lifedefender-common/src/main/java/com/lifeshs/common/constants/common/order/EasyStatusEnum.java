package com.lifeshs.common.constants.common.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lifeshs.common.constants.common.OrderStatus;
import com.lifeshs.common.constants.common.ProjectType;

/**
 * 简易的订单状态枚举类
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年7月12日 下午4:36:14
 */
public enum EasyStatusEnum {

    ALL(1, "全部",
            Arrays.asList(OrderStatus.PAYABLE.getStatus(), OrderStatus.VALID.getStatus(),
                    OrderStatus.COMPLETED.getStatus(), OrderStatus.REFUNDING.getStatus(),
                    OrderStatus.REFUNDED.getStatus()),
            new ArrayList<Integer>(), null, null),

    PAYABLE(2, "待付款", Arrays.asList(OrderStatus.PAYABLE.getStatus()), new ArrayList<Integer>(), null, null),

    USEABLE(3, "待使用", Arrays.asList(OrderStatus.VALID.getStatus()),
            Arrays.asList(ProjectType.PROJECT_TOSTORE.getValue(), ProjectType.PROJECT_VISIT.getValue()), null, null),

    COMMENTABLE(4, "待评价", Arrays.asList(OrderStatus.COMPLETED.getStatus()),
            Arrays.asList(ProjectType.PROJECT_CONSULT.getValue(), ProjectType.PROJECT_TOSTORE.getValue(),
                    ProjectType.PROJECT_VISIT.getValue()),
            false, null),

    REFUND(5, "退款/售后", Arrays.asList(OrderStatus.REFUNDING.getStatus(), OrderStatus.REFUNDED.getStatus()),
            Arrays.asList(ProjectType.PROJECT_TOSTORE.getValue(), ProjectType.PROJECT_VISIT.getValue()), null, true);

    /**
     *  查找value对应的EasyStatusEnum
     *  @author yuhang.weng 
     *	@DateTime 2017年7月13日 上午11:31:00
     *
     *  @param value
     *  @return
     */
    public static EasyStatusEnum getEasyStatusEnum(int value) {
        for (EasyStatusEnum e : EasyStatusEnum.values()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        return null;
    }
    
    private int value;
    /** 描述 */
    private String remark;
    /** orderStatus */
    private List<Integer> statusList;
    /** 服务code */
    private List<Integer> projectType;
    /** 评论不为空 */
    private Boolean commentNotNull;
    /** 退款不为空 */
    private Boolean refundNotNull;

    private EasyStatusEnum(int value, String remark, List<Integer> statusList, List<Integer> projectType,
            Boolean commentNotNull, Boolean refundNotNull) {
        this.value = value;
        this.remark = remark;
        this.statusList = statusList;
        this.projectType = projectType;
        this.commentNotNull = commentNotNull;
        this.refundNotNull = refundNotNull;
    }

    public int getValue() {
        return this.value;
    }

    public String getRemark() {
        return this.remark;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public List<Integer> getProjectType() {
        return this.projectType;
    }

    public Boolean getCommentNotNull() {
        return this.commentNotNull;
    }

    public Boolean getRefundNotNull() {
        return this.refundNotNull;
    }
}
