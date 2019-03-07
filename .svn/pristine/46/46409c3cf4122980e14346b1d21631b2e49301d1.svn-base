package com.lifeshs.shop;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import com.lifeshs.utils.PropertiesUtil;

public class AliPayConfig {
	
	private static Properties properties;		
	static {
		properties = new Properties();
        InputStreamReader isr = new InputStreamReader(AliPayConfig.class.getClassLoader().getResourceAsStream("alibaba/alipayConfig.properties"));
        try {
			properties.load(isr);
			APPID = properties.getProperty("AlipayConfig.app_id");
			RSA_PRIVATE_KEY = properties.getProperty("AlipayConfig.merchant_private_key");
			ALIPAY_PUBLIC_KEY = properties.getProperty("AlipayConfig.alipay_public_key");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		
	}
		
	// 商户appid
	public static String APPID;
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY;
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/notify_url.jsp";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://商户网关地址/alipay.trade.wap.pay-JAVA-UTF-8/return_url.jsp";
	// 请求网关地址
	public static String URL = "https://openapi.alipay.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY;
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
	
	public static String getAppid() {
        return APPID;
    }

    public static String getPrivate_Key() {
        return RSA_PRIVATE_KEY;
    }

    public static String getAlipayPublic_Key() {
        return ALIPAY_PUBLIC_KEY;
    }
	
}
