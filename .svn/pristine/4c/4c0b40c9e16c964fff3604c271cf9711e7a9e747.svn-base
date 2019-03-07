package com.lifeshs.thirdservice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.common.exception.common.OperationException;
import com.lifeshs.pojo.weixin.CallBackDTO;
import com.lifeshs.pojo.weixin.menu.MenuDTO;
import com.lifeshs.service.alipay.util.WechatMessageUtil;
import com.lifeshs.utils.HttpUtils;

/**
 *  微信公众平台api
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年11月13日 上午9:18:45
 */
public class WeiXinGZPTApi {

    private static Logger log = Logger.getLogger(WeiXinGZPTApi.class);
    
    /** 授权类型 */
    private String grantType;
    /** 第三方用户唯一凭证 */
    private String appid;
    /** 第三方用户唯一凭证密钥，即appsecret */
    private String secret;
    /** 公众号的全局唯一接口调用凭据 */
    private String accessToken;
    /** token过期时间戳 */
    private Instant expireTokenTime;
    /** 基础url */
    private String baseUrl = "https://api.weixin.qq.com/cgi-bin";
    
    public WeiXinGZPTApi(String grantType, String appid, String secret) {
        this.grantType = grantType;
        this.appid = appid;
        this.secret = secret;
    }
    
    /**
     *  获取接口调用凭据
     *  @author yuhang.weng 
     *  @DateTime 2017年11月13日 上午10:48:38
     *
     *  @return
     */
    private String getAccessToken() {
        // 如果token为空或者已经过期，就重新查询token
        if (StringUtils.isBlank(this.accessToken) || Instant.now().isAfter(this.expireTokenTime)) {
            queryToken();
        }
        return this.accessToken;
    }
    
    /**
     *  查询接口调用凭据
     *  <p>这里加个synchronized，限制为单通道获取token
     *  @author yuhang.weng 
     *  @DateTime 2017年11月13日 上午10:49:03
     *
     */
    private synchronized void queryToken() {
        // 防止高并发状态下继续获取
        if (StringUtils.isNotBlank(this.accessToken) && expireTokenTime.isAfter(Instant.now())) {
            return;
        }
        // 查询新的access_token
        String result = HttpUtils.getResultEntity(this.baseUrl + "/token?grant_type=" + this.grantType + "&appid=" + this.appid + "&secret=" + this.secret, null, null, HttpUtils.GET, true, null);
        JSONObject resultJson = JSONObject.parseObject(result);
        this.accessToken = resultJson.getString("access_token");
        long expiresIn = resultJson.getLongValue("expires_in");
        this.expireTokenTime = Instant.now().plusSeconds(expiresIn);
    }
    
    /**
     *  获取自定义菜单
     *  @author yuhang.weng 
     *  @DateTime 2017年11月13日 上午11:39:43
     *
     *  @return
     */
    public MenuDTO getMenu() {
        Map<String, String> header = new HashMap<>();
        header.put("Accept", "text/plain;charset=utf-8");
        String result = HttpUtils.getResultEntity(baseUrl + "/menu/get?access_token=" + getAccessToken(), header, null, HttpUtils.GET, true, null);
        JSONObject resultJson = JSONObject.parseObject(result);
        MenuDTO menu = JSONObject.parseObject(resultJson.getString("menu"), MenuDTO.class);
        return menu;
    }
    
    /**
     *  创建自定义菜单
     *  @author yuhang.weng 
     *  @DateTime 2017年11月13日 上午11:56:35
     *
     *  @param menu
     *  @throws OperationException
     */
    public void createMenu(MenuDTO menu) throws Exception {
        JSONObject body = (JSONObject) JSONObject.toJSON(menu);
        String result = HttpUtils.getResultEntity(baseUrl + "/menu/create?access_token=" + getAccessToken(), null, body, HttpUtils.POST, true, null);
        JSONObject resultJson = JSONObject.parseObject(result);
        CallBackDTO callBack = JSONObject.toJavaObject(resultJson, CallBackDTO.class);
        int errorCode = callBack.getErrorCode();
        if (errorCode != 0) {
            throw new Exception(callBack.getErrorMessage());
        }
    }
    
    /**
     *  删除自定义菜单
     *  @author yuhang.weng 
     *  @DateTime 2017年11月13日 上午11:58:24
     *
     *  @throws OperationException
     */
    public void deleteMenu() throws Exception {
        String result = HttpUtils.getResultEntity(baseUrl + "/menu/delete?access_token=" + getAccessToken(), null, null, HttpUtils.GET, true, null);
        JSONObject resultJson = JSONObject.parseObject(result);
        CallBackDTO callBack = JSONObject.toJavaObject(resultJson, CallBackDTO.class);
        int errorCode = callBack.getErrorCode();
        if (errorCode != 0) {
            throw new Exception(callBack.getErrorMessage());
        }
    }
    
    public String processRequest(HttpServletRequest request) {
        Map<String, String> map = WechatMessageUtil.xmlToMap(request);
        log.info(map);
        // 发送方帐号（一个OpenID）
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        // 消息类型
        String msgType = map.get("MsgType");
        
        // 默认回复一个"success"
        String responseMessage = "success";
//        // 对消息进行处理
//        if (WechatMessageUtil.MESSAGE_TEXT.equals(msgType)) {// 文本消息
//            TextMessage textMessage = new TextMessage();
//            textMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
//            textMessage.setToUserName(fromUserName);
//            textMessage.setFromUserName(toUserName);
//            textMessage.setCreateTime(System.currentTimeMillis());
//            textMessage.setContent("/::)您好[Smile]，这里是“生命守护”小助手，请问有什么可以帮到您？");
//            responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
//        }
        log.info(responseMessage);
        return responseMessage;

    }
}
