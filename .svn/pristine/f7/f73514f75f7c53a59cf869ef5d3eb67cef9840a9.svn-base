package com.lifeshs.app.api.store;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lifeshs.common.constants.common.ReturnStatus;
import com.lifeshs.dto.manager.ReturnDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 对管理APP的返回数据进行统一处理
 * Created by dengfeng on 2017/6/23 0023.
 */
public class ReturnDataAgent {
    public static final String SUCCESS_STATUS = "0";
    public static final String ERROR_STATUS_NORMAL = "1";

    /**
     * 服务器响应正常请求(无返回数据)
     * dengfeng
     * @return
     */
    public static JSONObject success() {
        return fillDataDTO(SUCCESS_STATUS, null, null);
    }

    /**
     * 服务器响应正常请求(有返回数据)
     * @param data 返回对象
     * @return
     */
    public static JSONObject success(Object data) {
        return fillDataDTO(SUCCESS_STATUS, null, data);
    }

    /**
     * 服务器响应成功(返回消息和数据)
     *
     * @author wuj
     * @since 2017-07-18 10:17:05
     * @updateTime none
     *
     * @param message
     * @param data
     * @return
     */
    public static JSONObject success(String message,Object data) {
        return fillDataDTO(SUCCESS_STATUS, message, data);
    }


    private static JSONObject fillDataDTO(String status, String message, Object data){
        ReturnDTO returnData = new ReturnDTO();
        returnData.setStatus(status);
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
        //JSONObject.DEFFAULT_DATE_FORMAT="yyyy-MM-dd hh:mm";
        JSONObject jsonObject = (JSONObject)JSON.toJSON(returnData); //, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
        return jsonObject;
    }

    /**
     * 服务器错误响应请求(默认错误状态码=1)
     * @param message 错误信息
     * @return
     */
    public static JSONObject error(String message) {
        return fillDataDTO(ERROR_STATUS_NORMAL, message, null);
    }

    /**
     * 服务器错误响应请求(默认错误状态码=1)
     * @param returnStatus 响应状态
     * @return
     */
    public static JSONObject error(ReturnStatus returnStatus) {
        return fillDataDTO(String.valueOf(returnStatus.value()), returnStatus.getName(), null);
    }
    
}
