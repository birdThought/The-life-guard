package com.lifeshs.dto.manager.member;

import java.util.List;

/**
 * 通过环信CODE得到用户信息的提交数据
 * Created by dengfeng on 2017/7/22 0022.
 */
public class UpMemberbyHXData {
    private List<String> huanxinUserNames;
    private List<String> huanxinGroupIds;

    public List<String> getHuanxinUserNames() {
        return huanxinUserNames;
    }

    public void setHuanxinUserNames(List<String> huanxinUserNames) {
        this.huanxinUserNames = huanxinUserNames;
    }

    public List<String> getHuanxinGroupIds() {
        return huanxinGroupIds;
    }

    public void setHuanxinGroupIds(List<String> huanxinGroupIds) {
        this.huanxinGroupIds = huanxinGroupIds;
    }
}
