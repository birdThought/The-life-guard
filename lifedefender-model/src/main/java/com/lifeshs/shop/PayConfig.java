package com.lifeshs.shop;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class PayConfig {
	
		private static Properties properties;				
		static {
			properties = new Properties();
            InputStreamReader isr = new InputStreamReader(PayConfig.class.getClassLoader().getResourceAsStream("weChat/weChatConfig.properties"));
            try {
				properties.load(isr);
				 APP_ID = properties.getProperty("weChatConfig.app_id");
		         MCH_ID = properties.getProperty("weChatConfig.mch_id");
		         API_KEY = properties.getProperty("weChatConfig.api_key");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}          
		}
	
		// 微信号
		public static String APP_ID;
		// 应用对应的凭证
		public static String APP_SECRET = "138xxxxxx";
		// 商户密钥
		public static String API_KEY;
		// 商业号
		public static String MCH_ID;
		// 回调地址
		public static String NOTIFY_URL = "http://www.lifekeepers.cn/shop/pay/wxPayNotify?userid=";
		//微信支付h5 回调地址
		public static String NOTIFY_URL_H5 = "http://www.lifekeepers.cn/shop/pay/wxPayNotify?userid=";
		// 商品名称
		public static String BODY = "生命守护-商品购买 ";
		// 请求地址
		public static String UFDODER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		// 微信支付V2账单查询接口
		public static String ORDERQUERY = "https://api.mch.weixin.qq.com/pay/orderquery";
		
		public static String getAppid() {
	        return APP_ID;
	    }

	    public static String getMch_id() {
	        return MCH_ID;
	    }

	    public static String getApi_key() {
	        return API_KEY;
	    }
}
