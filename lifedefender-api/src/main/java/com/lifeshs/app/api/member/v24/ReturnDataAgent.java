package com.lifeshs.app.api.member.v24;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.common.ReturnStatus;
import com.lifeshs.dto.ReturnDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 对应用APP的返回数据进行统一处理
 * Created by dengfeng on 2017/6/23 0023.
 */
public class ReturnDataAgent {

    /**
     * 服务器响应正常请求(无返回数据)
     * dengfeng
     * @return
     */
    public static JSONObject success() {
        return fillDataDTO(ReturnStatus.Normal.value(), null, null);
    }

    /**
     * 服务器响应正常请求(有返回数据)
     * @param data 返回对象
     * @return
     */
    public static JSONObject success(Object data) {
        return fillDataDTO(ReturnStatus.Normal.value(), null, data);
    }

    /**
     * 服务器错误响应请求(默认错误状态码=1)
     * @param message 错误信息
     * @return
     */
    public static JSONObject error(String message) {
        return fillDataDTO(ReturnStatus.Error.value(), message, null);
    }

    /**
     * 服务器错误响应请求(默认错误状态码=1)
     * @param returnStatus 响应状态
     * @return
     */
    public static JSONObject error(ReturnStatus returnStatus) {
        return fillDataDTO(returnStatus.value(), returnStatus.getName(), null);
    }
    /**
     * 服务器错误响应请求(指定错误状态码)
     * @param status
     * @param message
     * @return
     */
    public static JSONObject error(int status, String message) {
        return fillDataDTO(status, message, null);
    }

    private static JSONObject fillDataDTO(int status, String message, Object data){
        ReturnDTO returnData = new ReturnDTO();
        returnData.setStatus(String.valueOf(status));
        returnData.setMsg(message);
        if(data != null) {
            //如果不是列表，自动加上列表
            if (data instanceof List) {
                returnData.setData((List)data);
            }else {
                List dataList = new ArrayList<>();
                dataList.add(data);
                returnData.setData(dataList);
            }
        }

        JSONObject jsonObject = (JSONObject)JSON.toJSON(returnData); //, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
        return jsonObject;
    }

}
