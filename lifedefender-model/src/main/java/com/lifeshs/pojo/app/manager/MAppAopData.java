package com.lifeshs.pojo.app.manager;

import com.lifeshs.entity.org.user.TOrgUser;

/**
 * 管理app切面新增数据
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月3日 上午11:34:36
 */
public class MAppAopData {

    private TOrgUser orgUser;

    public TOrgUser getOrgUser() {
        return orgUser;
    }

    public void setOrgUser(TOrgUser orgUser) {
        this.orgUser = orgUser;
    }

    @Override
    public String toString() {
        return "MAppAopData [orgUser=" + orgUser + "]";
    }

}
