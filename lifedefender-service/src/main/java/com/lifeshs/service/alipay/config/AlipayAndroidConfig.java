package com.lifeshs.service.alipay.config;

import com.lifeshs.utils.PropertiesUtil;

public class AlipayAndroidConfig {

	public static String appId = "";
	public static String pId = "";
	public static String targetId = "";
	public static String rsaPrivate = "";
	public static String publicKey = "";
	
	static {
		// 读取配置文件
		PropertiesUtil pu = new PropertiesUtil("sysConfig.properties");
		
		appId = pu.readProperty("pay.android.appId");
		pId = pu.readProperty("pay.android.pId");
		targetId = pu.readProperty("pay.android.targetId");
		rsaPrivate = pu.readProperty("pay.android.rsaPrivate");
		publicKey = pu.readProperty("pay.android.publicKey");
	}
	
}
