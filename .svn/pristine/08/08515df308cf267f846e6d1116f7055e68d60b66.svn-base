package com.lifeshs.pojo.app.member;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 *  APP字符串转换的data对象
 *  @author yuhang.weng  
 *  @DateTime 2017年2月21日 下午2:45:57
 */
public class AppJSONDataDTO {

    private Integer userId;

    private String token;

    private JSONArray msg;

    private Integer size;

    /**
     *  获取msg对象的第一个JSONObject
     *  @author yuhang.weng 
     *	@DateTime 2017年2月22日 下午1:52:46
     *
     *  @return
     */
    public JSONObject getFirstJSONObject() {
        JSONObject jsonObject = new JSONObject();
        if (msg != null && msg.size() > 0) {
            jsonObject = msg.getJSONObject(0);
        }
        return jsonObject;
    }
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONArray getMsg() {
        return msg;
    }

    public void setMsg(JSONArray msg) {
        this.msg = msg;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "AppJSONDataDTO [userId=" + userId + ", token=" + token + ", msg=" + msg + ", size=" + size + "]";
    }

}
