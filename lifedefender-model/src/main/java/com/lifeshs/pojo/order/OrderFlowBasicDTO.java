package com.lifeshs.pojo.order;

import lombok.Data;

/**
 * 交易流水基础信息
 * 
 * @author yuhang.weng
 * @DateTime 2016年10月20日 下午3:10:43
 */
public @Data class OrderFlowBasicDTO {

    private String userRealName;

    private Integer orgId;

    private String orgName;

    private String serveName;

    private Integer profitShare;

    private int orderType;

    private int total;
}
