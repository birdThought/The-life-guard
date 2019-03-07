package com.lifeshs.dto.member;

import com.lifeshs.pojo.member.UserDTO;

/**
 * 应用APP发送的字符串转换对象
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年3月3日 上午10:59:39
 */
public class SumbitDTO {

    /** 终端设备类型, android: APP_A, ios: APP_I */
    private String type;
    /** 版本 */
    private String ver;
    /** 用户ID */
    private UserDTO user;
    /** 原始数据 */
    private String json;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "SumbitDTO [type=" + type + ", ver=" + ver + ", user=" + user + "]";
    }

}
