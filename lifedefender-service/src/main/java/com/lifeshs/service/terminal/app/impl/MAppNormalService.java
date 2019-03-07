package com.lifeshs.service.terminal.app.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.constants.app.Normal;
import com.lifeshs.pojo.app.manager.MAppJSON;

@Service(value = "mappNormalService")
public class MAppNormalService extends AppBaseService {

    public static final String SUCCESS_STATUS = "0";

    public static final String ERROR_STATUS_NORMAL = "1";

    /**
     * 服务器响应正常请求
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月16日 下午3:55:54
     *
     * @return
     */
    public static JSONObject success() {
        JSONObject root = getFastJSONRoot(SUCCESS_STATUS);
        root.put(Normal.DATA, new ArrayList<>());
        return root;
    }

    /**
     * 服务器响应正常请求，返回时携带提示信息
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月16日 下午3:56:35
     *
     * @param message
     * @return
     */
    public static JSONObject success(String message) {
        JSONObject root = getFastJSONRoot(SUCCESS_STATUS);
        root.put(Normal.MESSAGE, message);
        return root;
    }

    /**
     * 服务器响应正常请求
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月26日 上午11:30:15
     *
     * @param data
     * @return
     */
    public static JSONObject success(Object data) {
        return returnSuccess(data);
    }

    /**
     * 服务器响应正常请求，返回类型为map，并在root层追加内容
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月16日 下午3:59:49
     *
     * @param data
     * @param extraRootData
     * @return
     */
    public static JSONObject success(Object data, Map<String, String> extraRootData) {
        JSONObject root = success(data);
        root = insertExtraMeta(root, extraRootData);
        return root;
    }

    /**
     * 服务器响应正常请求，返回时携带获取的数据
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月16日 下午3:58:21
     *
     * @param data
     * @return
     */
    @SuppressWarnings("unchecked")
    private static JSONObject returnSuccess(Object object) {
        JSONObject root = getFastJSONRoot(SUCCESS_STATUS);

        JSONArray array = new JSONArray();

        if (object instanceof Map) {
            Map<String, Object> data = (Map<String, Object>) object;
            array.add(value2String(data));
        }
        if (object instanceof List) {
            List<Object> datas = (List<Object>) object;
            array.addAll(value2String(datas));
        }

        root.put(Normal.DATA, array);

        return root;
    }

    /**
     * 插入额外的元素
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月26日 上午11:35:34
     *
     * @param root
     * @param extraRootData
     * @return
     */
    private static JSONObject insertExtraMeta(JSONObject root, Map<String, String> extraRootData) {
        String value = null;
        for (String key : extraRootData.keySet()) {
            if (!root.containsKey(key)) {
                value = extraRootData.get(key);
                root.put(key, value);
            }
        }
        return root;
    }

    /**
     * 服务器错误响应请求
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月16日 下午4:32:45
     *
     * @param message
     * @return
     */
    public static JSONObject error(String message) {
        JSONObject root = getFastJSONRoot(ERROR_STATUS_NORMAL);
        root.put(Normal.MESSAGE, message);
        return root;
    }

    /**
     * 服务器错误响应请求，自定义状态码
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月16日 下午4:40:40
     *
     * @param message
     * @param status
     * @return
     */
    public static JSONObject error(String message, int status) {
        JSONObject root = getFastJSONRoot(String.valueOf(status));
        root.put(Normal.MESSAGE, message);
        return root;
    }

    /**
     * 获取一个fastJson对象
     * 
     * @author yuhang.weng
     * @DateTime 2016年11月16日 下午4:03:30
     *
     * @param status
     * @return
     */
    private static JSONObject getFastJSONRoot(String status) {
        JSONObject root = new JSONObject();
        root.put(Normal.STATUS, status);
        return root;
    }

    /**
     *  一周的天数转换为字符串
     *  示例：1（代表周一）转换为1000000，2（代表周二）转换为0100000
     *  @author yuhang.weng 
     *  @DateTime 2017年3月2日 上午10:50:19
     *
     *  @return
     */
    public static String weekdayToString(int weekday) {
        StringBuffer weeks = new StringBuffer();
        for (int i = 1; i <= 7; i++) {
            if (weekday == i) {
                weeks.append("1");
            } else {
                weeks.append("0");
            }
        }
        return weeks.toString();
    }
    
    /**
     *  字符串转换为一周的某一天
     *  示例：1000000转换为1（代表周一）,0100000转换为2(代表周二)
     *  @author yuhang.weng 
     *  @DateTime 2017年3月2日 上午10:53:32
     *
     *  @param weeks
     *  @return
     */
    public static int weeksToInt(String weeks) {
        char[] week = weeks.toCharArray();
        for (int i = 1; i <= 7; i++) {
            if (week[i - 1] == '1') {
                return i;
            }
        }
        return 0;
    }
    
    public static List<Integer> weeksToIntList(String weeks) {
        List<Integer> result = new ArrayList<>();
        char[] week = weeks.toCharArray();
        for (int i = 1; i <= 7; i++) {
            if (week[i - 1] == '1') {
                result.add(i);
            }
        }
        return result;
    }
    
    /**
     *  将接收到的string转换为MAppJSON对象
     *  @author yuhang.weng 
     *  @DateTime 2017年2月21日 下午7:33:12
     *
     *  @param json
     *  @return
     */
    public static MAppJSON parseAppJSON(String json) {
        JSONObject root = JSONObject.parseObject(json);
        MAppJSON appJSON = JSONObject.toJavaObject(root, MAppJSON.class);
        return appJSON;
    }
}
