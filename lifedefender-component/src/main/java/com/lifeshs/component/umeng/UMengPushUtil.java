package com.lifeshs.component.umeng;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.exception.code.ErrorCodeEnum;
import com.lifeshs.common.exception.common.ParamException;
import com.lifeshs.component.umeng.util.CallBackDTO;
import com.lifeshs.component.umeng.util.Key;
import com.lifeshs.component.umeng.util.SendDataAndroidDTO;
import com.lifeshs.component.umeng.util.SendDataBaseDTO;
import com.lifeshs.component.umeng.util.SendDataIosDTO;
import com.lifeshs.component.umeng.util.UMengOpenTypeEnum;
import com.lifeshs.component.umeng.util.UMengTypeEnum;
import com.lifeshs.utils.HttpUtils;

/**
 * 友盟消息推送
 * 
 * @author yuhang.weng
 * @version 1.0
 * @DateTime 2017年9月8日 上午11:06:54
 */
public class UMengPushUtil {

    final Key appAndroidKey;

    private Key appIOSKey;

    private Key mappAndroidKey;

    private Key mappIOSKey;

    private String productionMode;
    
    public UMengPushUtil(Key appAndroidKey, Key appIOSKey, Key mappAndroidKey, Key mappIOSKey, String productionMode) {
        this.appAndroidKey = appAndroidKey;
        this.appIOSKey = appIOSKey;
        this.mappAndroidKey = mappAndroidKey;
        this.mappIOSKey = mappIOSKey;
        this.productionMode = productionMode;
    }

    /**
     * APP信息推送_IOS
     * 
     * @param isIos 是否发ios机型，否的话发android
     * @param isManager 是否是管理APP，否的话应用APP
     * @param deviceToken   终端的友盟设备token列表
     * @param title   通知标题
     * @param text     通知文字描述
     * @param openType   APP打开类型：1查看消息,2打开指定APP页面,3打开指定URL
     * @param openTarget APP打开目标，由openType决定是activity地址或url地址
     * @throws ParamException
     * @return
     */
    public CallBackDTO pushMessage(boolean isIos, boolean isManager, String deviceToken, String title, String text, UMengOpenTypeEnum openType, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS)  {
        CallBackDTO c = push(isIos, isManager, UMengTypeEnum.UNICAST, deviceToken, title, text, openType, openTarget, openAttach, openTargetIOS, openAttachIOS);
        return c;
    }

    public CallBackDTO pushMessage(boolean isIos, boolean isManager, List<String> deviceTokenList, String title, String text, UMengOpenTypeEnum openType, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS) throws ParamException {
        StringBuffer deviceTokenBuffer = getStringByList(deviceTokenList);
        CallBackDTO c = push(isIos, isManager, UMengTypeEnum.LISTCAST, deviceTokenBuffer.toString(), title, text, openType, openTarget, openAttach, openTargetIOS, openAttachIOS);
        return c;
    }

    private CallBackDTO push(boolean isIos, boolean isManager, UMengTypeEnum umengType, String deviceToken, String title, String text, UMengOpenTypeEnum openType, String openTarget, String openAttach, String openTargetIOS, String openAttachIOS) {
        String url = "https://msgapi.umeng.com/api/send";
        String timestamp = String.valueOf(System.currentTimeMillis());
        Key key;
        JSONObject json = null;

        SendDataBaseDTO sendBaseDTO = new SendDataBaseDTO();
        sendBaseDTO.setTimestamp(timestamp);
        sendBaseDTO.setType(umengType.value());
        sendBaseDTO.setDeviceTokens(deviceToken);
        sendBaseDTO.setProductionMode(productionMode);

        if (isIos) {
            if(isManager)
                key = mappIOSKey;
            else
                key = appIOSKey;
            SendDataIosDTO sendDTO = new SendDataIosDTO();
            BeanUtils.copyProperties(sendBaseDTO, sendDTO);
            sendDTO.setAppkey(key.getAppKey());
            sendDTO.getPayload().getAps().getAlert().setTitle(title);
            sendDTO.getPayload().getAps().getAlert().setBody(text);
            sendDTO.getPayload().getAps().setSound("default");

            json = (JSONObject) JSONObject.toJSON(sendDTO);
            JSONObject payLoadJ = json.getJSONObject("payload");
            payLoadJ.put("openType",String.valueOf(openType.value()));  //打开类型：1查看消息,2打开指定APP页面,3打开指定URL
            if(openType == UMengOpenTypeEnum.URL) {
                payLoadJ.put("openTarget", openTargetIOS);
            }
            if(openType == UMengOpenTypeEnum.Activity){
                payLoadJ.put("openTarget", openTargetIOS);
                payLoadJ.put("openAttach", openAttachIOS);
            }
        } else {
            String miActivity;  //友盟小米、华为推送离线弹窗消息的包名
            if(isManager) {
                key = mappAndroidKey;
                miActivity = "com.example.tzdq.lifeshsmanager.umeng.MipushTestActivity";
            } else {
                key = appAndroidKey;
                miActivity = "com.mgc.lifeguardian.umeng.MipushTestActivity";
            }
            SendDataAndroidDTO sendDTO = new SendDataAndroidDTO();
            BeanUtils.copyProperties(sendBaseDTO, sendDTO);
            sendDTO.setMiActivity(miActivity);
            sendDTO.setAppkey(key.getAppKey());
            sendDTO.getPayload().setDisplayType("notification");
            sendDTO.getPayload().getBody().setTicker("生命守护");
            sendDTO.getPayload().getBody().setTitle(title);
            sendDTO.getPayload().getBody().setText(text);
            sendDTO.getPayload().getBody().setSound("default");
            sendDTO.getPayload().getBody().setPlayVibrate("true");
            sendDTO.getPayload().getBody().setPlayLights("true");
            sendDTO.getPayload().getBody().setPlaySound("true");
            sendDTO.getPayload().getBody().setAfterOpen(openType.sname());
            
            if(openType == UMengOpenTypeEnum.Activity){
                //添加扩展参数，处理扩展数据:   key:value,key:value
                if(org.apache.commons.lang.StringUtils.isNotBlank(openAttach)){
                        String[] strs = openAttach.split(",");
                        for(String str : strs){
                            String[] ss = str.split(":");
                            if(ss.length == 2)
                                sendDTO.getPayload().getExtra().put(ss[0],ss[1]);
                        }
                }
                sendDTO.getPayload().getBody().setActivity(openTarget);
            }
            if(UMengOpenTypeEnum.URL == openType){
                sendDTO.getPayload().getBody().setUrl(openTarget);
            }
            
            json = (JSONObject) JSONObject.toJSON(sendDTO);
        }


        String sign = sign(json.toString(), url, key.getMasterSecret());
        url = url + "?sign=" + sign;
        System.out.println(json);

        String result = HttpUtils.getResultEntity(url, header(), json, HttpUtils.POST, true, null);
        JSONObject r = JSONObject.parseObject(result);
        CallBackDTO c = JSONObject.toJavaObject(r, CallBackDTO.class);
        return c;
    }

    private StringBuffer getStringByList(List<String> deviceTokenList) throws ParamException {
        if (deviceTokenList == null || deviceTokenList.size() == 0) {
            throw new ParamException("没有给入要发送的用户", ErrorCodeEnum.FORMAT);
        }
        if (deviceTokenList.size() > 500) {
            throw new ParamException("单次推送不能超过500个用户", ErrorCodeEnum.FORMAT);
        }
        StringBuffer deviceTokenBuffer = new StringBuffer();
        for (String deviceToken : deviceTokenList) {
            if (deviceTokenBuffer.length() == 0) {
                deviceTokenBuffer.append(deviceToken);
            } else {
                deviceTokenBuffer.append("," + deviceToken);
            }
        }
        return deviceTokenBuffer;
    }

    /**
     * 头部信息
     * 
     * @author yuhang.weng
     * @DateTime 2017年5月22日 下午5:33:40
     *
     * @return
     */
    private Map<String, String> header() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("User-Agent", "Mozilla/5.0");
        return map;
    }

    /**
     * 签名
     * 
     * @author yuhang.weng
     * @DateTime 2017年5月22日 下午5:32:11
     *
     * @param body
     *            消息内容
     * @param url
     *            请求url
     * @param appMasterSecret
     *            主密钥
     * @return
     */
    private String sign(String body, String url, String appMasterSecret) {
        String sign = null;
        try {
            sign = DigestUtils.md5Hex(("POST" + url + body + appMasterSecret).getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    //测试
    public static void main(String[] args) {
        String url = "https://msgapi.umeng.com/api/send";
        String timestamp = String.valueOf(System.currentTimeMillis());
        //测试android手机的推送
        SendDataAndroidDTO sendBaseDTO = new SendDataAndroidDTO();
        sendBaseDTO.setTimestamp(timestamp);
        sendBaseDTO.setType("unicast");
        sendBaseDTO.setDeviceTokens("Ag7kJFsPDzl1jAAFYCpRtdREbkKbQdGsCsGMKfswTq21");  //Ag7kJFsPDzl1jAAFYCpRtdSTL1x8_h_6aPY87kZUkm1O 自己手机app  Ag7kJFsPDzl1jAAFYCpRtdREbkKbQdGsCsGMKfswTq21 mapp
        sendBaseDTO.setDescription("这是个测试的正文信息");

        String appMasterSecret = "go3yo1emetxzv5yyxjtmpcithzwhsbsr";  //fgofh9zuxsqxnbm1ezsbamcxdlglbsp3 app  go3yo1emetxzv5yyxjtmpcithzwhsbsr  mapp
        sendBaseDTO.setAppkey("595b7af3677baa520700091f");  // 58f7288e65b6d6305f001094  app   595b7af3677baa520700091f  mapp
        sendBaseDTO.getPayload().setDisplayType("notification");

        sendBaseDTO.getPayload().getBody().setTicker("测试通知");
        sendBaseDTO.getPayload().getBody().setTitle("测试标题");
        sendBaseDTO.getPayload().getBody().setText("这是测试的内容");
        sendBaseDTO.getPayload().getBody().setSound("default");
        sendBaseDTO.getPayload().getBody().setPlayVibrate("true");
        sendBaseDTO.getPayload().getBody().setPlayLights("true");
        sendBaseDTO.getPayload().getBody().setPlaySound("true");
//        sendBaseDTO.getPayload().getBody().setAfterOpen("go_url");
//        sendBaseDTO.getPayload().getBody().setUrl("http://www.lifekeepers.cn/app/appweb/operate-band");
        sendBaseDTO.getPayload().getBody().setAfterOpen("go_activity");
        sendBaseDTO.getPayload().getBody().setActivity("com.example.tzdq.lifeshsmanager.view.activity.MemDetailActivity");  //com.mgc.lifeguardian.business.service.serviceview.ServiceMainActivity  app

        JSONObject json = (JSONObject) JSONObject.toJSON(sendBaseDTO);

        Map<String, Object> extra = new HashMap<>();
        extra.put("date", "2018-05-06");
        extra.put("userId", "1231");
        json.getJSONObject("payload").put("extra", extra);

        String sign = null;
        try {
            sign = DigestUtils.md5Hex(("POST" + url + json.toString() + appMasterSecret).getBytes("utf8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        url = url + "?sign=" + sign;

        System.out.println(json);
        Map<String, String> map = new HashMap<String, String>();
        map.put("Content-Type", "application/json");
        map.put("User-Agent", "Mozilla/5.0");

        String result = HttpUtils.getResultEntity(url, map, json, HttpUtils.POST, true, null);
        JSONObject r = JSONObject.parseObject(result);
        CallBackDTO c = JSONObject.toJavaObject(r, CallBackDTO.class);
        System.out.println(result);
    }
}
