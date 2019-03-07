package com.lifeshs.pojo.order;

/**
 * 交易流水基础信息(DO)
 * 
 * @author yuhang.weng
 * @DateTime 2016年10月20日 下午3:10:43
 */
public class OrderFlowDO {

    private String userRealName;

    private Integer orgId;

    private String orgName;

    private String serveName;

    private Integer profitShare;

    private int orderType;

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getServeName() {
        return serveName;
    }

    public void setServeName(String serveName) {
        this.serveName = serveName;
    }

    public Integer getProfitShare() {
        return profitShare;
    }

    public void setProfitShare(Integer profitShare) {
        this.profitShare = profitShare;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "OrderFlowDO [userRealName=" + userRealName + ", orgId=" + orgId + ", orgName=" + orgName
                + ", serveName=" + serveName + ", profitShare=" + profitShare + ", orderType=" + orderType + "]";
    }

}
