package com.lifeshs.thirdservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.lifeshs.common.constants.app.Order;
import com.lifeshs.common.constants.common.jianKe.JianKe;

import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.component.weChat.UnifiedOrderUtils;
import com.lifeshs.component.weChat.WXPayUtil;
import com.lifeshs.entity.weChat.CloseOrderInfo;
import com.lifeshs.entity.weChat.RefundInfo;
import com.lifeshs.entity.weChat.UnifiedOrderInfo;
import com.lifeshs.service.terminal.app.impl.AppNormalService;
import com.lifeshs.thirdservice.IWeChatService;

@Service("IWeChatService")
public class WeChatServiceImpl extends AppNormalService implements IWeChatService{
    private final Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
    
    //获取预支付订单
    @Override
    public JSONObject unifiedOrder(UnifiedOrderInfo info) {
        
        SortedMap<String, Object> params = new TreeMap<String, Object>();
        String appid = UnifiedOrderUtils.getAppid();
        String mch_id = UnifiedOrderUtils.getMch_id();
        String apiKey = UnifiedOrderUtils.getApi_key();
        String notify_url = UnifiedOrderUtils.getNotify_url();
        String unifiedorder = UnifiedOrderUtils.getUnifiedorder();
        String nonce_str = WXPayUtil.generateNonceStr();
        String body = info.getBody();
        String out_trade_no = info.getOut_trade_no();
        String total_fee = info.getTotal_fee();
        String trade_type = info.getTrade_type();
        String spbill_create_ip = info.getSpbill_create_ip();
        String attach = info.getAttach();
        
        params.put("appid", appid); //应用ID
        params.put("mch_id", mch_id); //商户号
        params.put("device_info", "WEB"); //设备号
        params.put("nonce_str", nonce_str); //随机字符串
        params.put("body", body); //商品描述
        params.put("out_trade_no", out_trade_no);//商户订单号
        params.put("total_fee", total_fee); //总金额
        params.put("trade_type", trade_type);//交易类型
        params.put("notify_url", notify_url); //通知地址
        params.put("spbill_create_ip", spbill_create_ip); //终端IP
        params.put("attach", attach); //附加数据  自定义字段数据
        
        String sign = "";//签名(该签名本应使用微信商户平台的API证书中的密匙key,但此处使用的是微信公众号的密匙APP_SECRET)
        sign = WeChatHttpUtils.createSign(params,apiKey);
        params.put("sign", sign); 
        System.out.println("**sign:"+sign);
        
        String requestXML = WeChatHttpUtils.getRequestXml(params);
        System.out.println("requestXML:"+requestXML);
        logger.info(requestXML);
        String result = WeChatHttpUtils.httpsRequest(unifiedorder, "POST", requestXML);

        org.json.JSONObject resultJson = XML.toJSONObject(result);
        org.json.JSONObject jsonObj = (org.json.JSONObject)resultJson.get("xml");
        String result_code  = jsonObj.getString("result_code");
        SortedMap<String,Object> submitMap = new TreeMap<String,Object>();
        if("SUCCESS".equals(result_code)) {
            //appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay
            submitMap.put("appid", appid);
            submitMap.put("partnerid", mch_id);
            submitMap.put("prepayid", jsonObj.getString("prepay_id"));
            submitMap.put("noncestr", jsonObj.getString("nonce_str"));
            Long time = (System.currentTimeMillis() / 1000);
            submitMap.put("timestamp", time.toString());
            submitMap.put("package", "Sign=WXPay");
            //第二次生成签名
            sign = WeChatHttpUtils.createSign(submitMap, apiKey);
            submitMap.put("sign", sign);
        }
        Map<String, Object> orderInfoMap = new HashMap<>();
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(submitMap);
        orderInfoMap.put(Order.INFO, jsonObject.toString());
        return success(orderInfoMap);
    }

    //退款
    @Override
    public JSONObject refund(RefundInfo info) {
        SortedMap<String, Object> params = new TreeMap<String, Object>();
        String nonce_str = WXPayUtil.generateNonceStr();
        String transaction_id = info.getTransaction_id();
        String out_refund_no = info.getOut_refund_no();
        Double total_fee = info.getTotal_fee();
        Double refund_fee = info.getRefund_fee();
        
        params.put("appid", UnifiedOrderUtils.getAppid());//公众账号ID
        params.put("mch_id", UnifiedOrderUtils.getMch_id());//商户号
        params.put("nonce_str", nonce_str);//随机字符串
        params.put("transaction_id", transaction_id); //微信订单号
        params.put("out_refund_no", out_refund_no); //商户退款单号
        params.put("total_fee", total_fee);//订单金额
        params.put("refund_fee", refund_fee);//退款金额
        String sign = "";//签名(该签名本应使用微信商户平台的API证书中的密匙key,但此处使用的是微信公众号的密匙APP_SECRET)
        sign = WeChatHttpUtils.createSign(params, UnifiedOrderUtils.getApi_key());
        params.put("sign", sign); 
        
        String requestXML = WeChatHttpUtils.getRequestXml(params);
        logger.info(requestXML);
        String result = WeChatHttpUtils.httpsRequest(UnifiedOrderUtils.getRefund(), "POST", requestXML);
        logger.info(result);
        
        return success(result);
    }

    //关闭订单
    @Override
    public JSONObject closeorder(CloseOrderInfo info) {
        SortedMap<String,Object> params =new TreeMap<String,Object>();
        String out_trade_no = info.getOut_trade_no();
        String nonce_str = info.getNonce_str(); //随机数
        params.put("appid",UnifiedOrderUtils.getAppid());//公众账号ID
        params.put("mch_id",UnifiedOrderUtils.getMch_id());//商户号
        params.put("out_trade_no",out_trade_no);//商户订单号，要唯一
        params.put("nonce_str",nonce_str);//随机字符串
        String sign =WeChatHttpUtils.createSign(params, UnifiedOrderUtils.getApi_key());
        params.put("sign", sign);

        //请求xml组装  
        String requestXML = WeChatHttpUtils.getRequestXml(params);
        logger.info(requestXML);
        String result = WeChatHttpUtils.httpsRequest(UnifiedOrderUtils.getCloseorder(), "POST", requestXML);
        logger.info(result);
        
//        Map<String, String> map =XMLUtil.doXMLParse(result);
//        String returnCode =map.get("return_code");
//        String resultCode =map.get("result_code");
//        if(returnCode.equalsIgnoreCase("SUCCESS")&&resultCode.equalsIgnoreCase("SUCCESS")){
//            //TODO 关闭订单成功的操作
//            logger.info("订单关闭成功");
//        }else {
//            //TODO 关闭订单失败的操作
//            logger.info("订单关闭失败");
//       }
        return success(result);
    }

    
    
    //获取预支付订单
    @Override
    public JSONObject unifiedOrderNew(String body, String outTradeNo, String totalFee, String tradeType,
            String spbillCreateIp, String attach) {
        
//      //接入沙箱start
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/json");
//        SortedMap<String, Object> map = new TreeMap<String, Object>();
//        String sx_key = UnifiedOrderUtils.getApi_key();;
//        
//        String sx_url = "https://api.mch.weixin.qq.com/sandboxnew/pay/getsignkey";
//        String sx_mch_id = UnifiedOrderUtils.getMch_id();
//        String sx_nonce_str = WXPayUtil.generateNonceStr().substring(0, 8);
//        
//        map.put("mch_id", sx_mch_id);
//        map.put("nonce_str", sx_nonce_str);
//        System.out.println("sx_key:"+sx_key);
//        String sx_sign = WeChatHttpUtils.createSign(map, sx_key);
//        map.put("sign", sx_sign);
//        
//     // 将Map转换成json
////        net.sf.json.JSONObject sx_jsonObject = net.sf.json.JSONObject.fromObject(map);
//        String sx_jsonObject = WeChatHttpUtils.getRequestXml(map);
//        String sx_josn = sx_jsonObject.toString();
//        System.out.println("沙箱params2:"+ map);
//        System.out.println("沙箱josn2:"+ sx_josn);
//
//        // 接口调用
////        String sx_result = WeChatHttpUtils.httpsRequest(sx_url, "POST", sx_josn, headers);
//        String sx_result = WeChatHttpUtils.httpsRequest(sx_url, "POST", sx_jsonObject);
//        System.out.println("沙箱###result:"+ sx_result);
//        
//        
//        org.json.JSONObject sx_resultJson = XML.toJSONObject(sx_result);
//        System.out.println("沙箱###orderNoJson:"+ sx_resultJson);
//        org.json.JSONObject sx_jsonObj = (org.json.JSONObject)sx_resultJson.get("xml");
//        logger.info("**jsonObj:"+sx_jsonObj);
//        String sandbox_signkey  = sx_jsonObj.getString("sandbox_signkey");
//        System.out.println("沙箱Key:"+sandbox_signkey);
//        
//        //请一定记得放开apiKey
//        String apiKey = sandbox_signkey;
//        
//        //接入沙箱end
        
        
        SortedMap<String, Object> params = new TreeMap<String, Object>();
        String appid = UnifiedOrderUtils.getAppid(); //公众账号ID
        String mch_id = UnifiedOrderUtils.getMch_id(); //商户号
        String apiKey = UnifiedOrderUtils.getApi_key(); //密钥
        String notify_url = UnifiedOrderUtils.getNotify_url(); //通知地址
        String unifiedorder = UnifiedOrderUtils.getUnifiedorder(); //统一下单
        String nonce_str = WXPayUtil.generateNonceStr(); //随机数
        
        params.put("appid", appid); //应用ID
        params.put("mch_id", mch_id); //商户号
        params.put("device_info", "WEB"); //设备号
        params.put("nonce_str", nonce_str); //随机字符串
        params.put("body", body); //商品描述
        params.put("out_trade_no", outTradeNo);//商户订单号
        params.put("total_fee", totalFee); //总金额
        params.put("trade_type", tradeType);//交易类型
        params.put("notify_url", notify_url); //通知地址
        params.put("spbill_create_ip", spbillCreateIp); //终端IP
        params.put("attach", attach); //附加数据  自定义字段数据
        
        String sign = "";//签名(该签名本应使用微信商户平台的API证书中的密匙key,但此处使用的是微信公众号的密匙APP_SECRET)
        sign = WeChatHttpUtils.createSign(params,apiKey);
        params.put("sign", sign); 
        logger.info("**第一次签名sign:"+sign);
        System.out.println("**sign:"+sign);
        
        String requestXML = WeChatHttpUtils.getRequestXml(params);
        logger.info("**requestXML:"+requestXML);
        System.out.println("requestXML:"+requestXML);
        logger.info(requestXML);
        String result = WeChatHttpUtils.httpsRequest(unifiedorder, "POST", requestXML);
        logger.info("result:"+result);
        
        org.json.JSONObject resultJson = XML.toJSONObject(result);
        logger.info("**resultJson:"+resultJson);
        org.json.JSONObject jsonObj = (org.json.JSONObject)resultJson.get("xml");
        logger.info("**jsonObj:"+jsonObj);
        String result_code  = jsonObj.getString("result_code");
        logger.info("**result_code:"+result_code);
        SortedMap<String,Object> submitMap = new TreeMap<String,Object>();
        if("SUCCESS".equals(result_code)) {
            //appId，partnerId，prepayId，nonceStr，timeStamp，package。注意：package的值格式为Sign=WXPay
            submitMap.put("appid", appid);
            submitMap.put("partnerid", mch_id);
            submitMap.put("prepayid", jsonObj.getString("prepay_id"));
            submitMap.put("noncestr", jsonObj.getString("nonce_str"));
            Long time = (System.currentTimeMillis() / 1000);
            submitMap.put("timestamp", time.toString());
            submitMap.put("package", "Sign=WXPay");
            //第二次生成签名
            sign = WeChatHttpUtils.createSign(submitMap, apiKey);
            logger.info("**第二次签名sign:"+sign);
            submitMap.put("sign", sign);
        }
        Map<String, Object> orderInfoMap = new HashMap<>();
        net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(submitMap);
        orderInfoMap.put(Order.INFO, jsonObject.toString());
        logger.info("**预付订单返回值orderInfoMap:"+success(orderInfoMap));
        return success(orderInfoMap);
    }
}
