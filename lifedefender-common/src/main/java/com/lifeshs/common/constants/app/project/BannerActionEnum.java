package com.lifeshs.common.constants.app.project;

/**
 *  Banner动作
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年7月21日 下午5:07:13
 */
public enum BannerActionEnum {

    SERVE_HEALTH("serveHealthUI", "健康养生服务搜索页"),

    SERVE_CONSULT("serveConsultUI", "咨询服务搜索页"),

    SERVE_LESSON("serveLessonUI", "课堂服务搜索页"),

    SERVE_HOME_BASE_CARE("serveHomeBasedCareUI", "居家养老搜索页"),

    MEASURE_SITE("measureSiteUI", "免费测量点页面");

    private String custom;
    private String remark;

    private BannerActionEnum(String custom, String remark) {
        this.custom = custom;
        this.remark = remark;
    }

    public String getCustom() {
        return custom;
    }

    public String getRemark() {
        return remark;
    }
}
