package com.lifeshs.vo.electronicCoupons;

import com.lifeshs.po.electronicCoupons.ElectronicCouponsTempletPO;

/**
 *  电子券模板VO
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年12月20日 下午3:28:47
 */
public class CouponsTempletVO extends ElectronicCouponsTempletPO {

    /** 机构名称 */
    private String orgName;
    /** 服务项目名称 */
    private String projectName;
    /** 服务具体项目名称 */
    private String serveItemName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getServeItemName() {
        return serveItemName;
    }

    public void setServeItemName(String serveItemName) {
        this.serveItemName = serveItemName;
    }
}
