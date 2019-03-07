package com.lifeshs.service.alipay.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lifeshs.utils.DateTimeUtilT;

public class OrderInfoUtil2_0 {

	/**
	 * 构造授权参数列表
	 * 
	 * @param pid
	 * @param app_id
	 * @param target_id
	 * @return
	 */
	public static Map<String, String> buildAuthInfoMap(String pid, String app_id, String target_id) {
		Map<String, String> keyValues = new HashMap<String, String>();

		// 商户签约拿到的app_id，如：2013081700024223
		keyValues.put("app_id", app_id);

		// 商户签约拿到的pid，如：2088102123816631
		keyValues.put("pid", pid);

		// 服务接口名称， 固定值
		keyValues.put("apiname", "com.alipay.account.auth");

		// 商户类型标识， 固定值
		keyValues.put("app_name", "mc");

		// 业务类型， 固定值
		keyValues.put("biz_type", "openservice");

		// 产品码， 固定值
		keyValues.put("product_id", "APP_FAST_LOGIN");

		// 授权范围， 固定值
		keyValues.put("scope", "kuaijie");

		// 商户唯一标识，如：kkkkk091125
		keyValues.put("target_id", target_id);

		// 授权类型， 固定值
		keyValues.put("auth_type", "AUTHACCOUNT");

		// 签名类型
		keyValues.put("sign_type", "RSA");

		return keyValues;
	}

	/**
	 * 构造支付订单参数列表
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Map<String, String> buildOrderParamMap(Double totalAmount_y, String subject, String body,
			String orderNumber, String appId, String notify_url, Map<String, String> extraData)
			throws UnsupportedEncodingException {

		JSONObject biz_content = new JSONObject();
		biz_content.put("timeout_express", "30m");
		biz_content.put("product_code", "QUICK_MSECURITY_PAY");
		biz_content.put("total_amount", totalAmount_y);
		biz_content.put("subject", subject);
		biz_content.put("body", body);
		biz_content.put("out_trade_no", orderNumber);
		if (extraData != null && !extraData.isEmpty()) {
			for (String key : extraData.keySet()) {
				String value = URLEncoder.encode(extraData.get(key), "UTF-8");
//				value = URLEncoder.encode(value, "UTF-8"); 第二次Encode
				extraData.put(key, value);
			}
			biz_content.put("passback_params", extraData);
		}
		biz_content.toString();

		Map<String, String> keyValues = new HashMap<String, String>();

		keyValues.put("app_id", appId);

		keyValues.put("biz_content", biz_content.toString());

		keyValues.put("charset", "utf-8");

		keyValues.put("method", "alipay.trade.app.pay");

		keyValues.put("sign_type", "RSA");

		keyValues.put("timestamp", DateTimeUtilT.dateTime(new Date()));

		keyValues.put("version", "1.0");

		keyValues.put("notify_url", notify_url);

		return keyValues;
	}

	/**
	 * 构造支付订单参数信息
	 * 
	 * @param map
	 *            支付订单参数
	 * @return
	 */
	public static String buildOrderParam(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append(buildKeyValue(key, value, true));
			sb.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		sb.append(buildKeyValue(tailKey, tailValue, true));

		return sb.toString();
	}

	/**
	 * 拼接键值对
	 * 
	 * @param key
	 * @param value
	 * @param isEncode
	 * @return
	 */
	private static String buildKeyValue(String key, String value, boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}

	/**
	 * 对支付参数信息进行签名
	 * 
	 * @param map
	 *            待签名授权信息
	 * 
	 * @return
	 */
	public static String getSign(Map<String, String> map, String rsaKey) {
		List<String> keys = new ArrayList<String>(map.keySet());
		// key排序
		Collections.sort(keys);

		StringBuilder authInfo = new StringBuilder();
		for (int i = 0; i < keys.size() - 1; i++) {
			String key = keys.get(i);
			String value = map.get(key);
			authInfo.append(buildKeyValue(key, value, false));
			authInfo.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		authInfo.append(buildKeyValue(tailKey, tailValue, false));

		String oriSign = SignUtils.sign(authInfo.toString(), rsaKey);
		String encodedSign = "";

		try {
			encodedSign = URLEncoder.encode(oriSign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "sign=" + encodedSign;
	}

}
