package com.lifeshs.dto.thirdparty;

/**
 * 第三方接口的提交数据，公共数据类
 * Created by dengfeng on 2018/1/26 0026.
 */
public class ClientDTO {
    /** 第三方ID */
    private String appid;
    /** 版本 */
    private String ver;
    /** 原始数据 */
    private String json;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
