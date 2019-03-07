package com.lifeshs.service.alipay.config;

import com.lifeshs.utils.PropertiesUtil;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.4
 *修改日期：2016-03-08
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
    
    static {
        PropertiesUtil p = new PropertiesUtil("environment.properties");
        notify_url = p.readProperty("alipayNotifyUrl");
        return_url = p.readProperty("alipayReturnUrl");
    }
    
	public static String partner = "2088901849463260";
	public static String seller_id = partner;
	public static String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMmnPgJUIqMQhDpls76kUgGYFzni8J9YCZHE2vv4+7c1lQ8ElzDXqzlJWb5FmSanNHQplmuQWkQMCdTsrkoH4VYArVr8cryeAfQlvbagazMQ6APZsAGKvEdXCK18wlanSmyPBGcoWN9vsax14jmmXw4Ssokf3/QRN13EZqxHRkEPAgMBAAECgYBYtH39jj8yVgikm6pm+WetldbZuu+4WITVH7Bo2Qz5edOZKJ7NeIG0kISuw3TsYO8dDlJB/N0IQ/drN7YvkKnZX/BbymV05DGwbSFdkEZOqiZ2kUWQ42LvO0ELAfiEbznQXtdXa7qXG44AkWczvwcgKj5QhK2PXDvp/RGe6NFu4QJBAPEyOOdAFb/dCxT1NwtKjbGxWW0ScSBY/dL3thXoICfp9YF/VB5sx9QLy+FpT0IwFPrb5Uf2VSeRv8qaUzCwC98CQQDWB7SBpX5ekdgWtT2QNYzHf53zR1joTpvyXtvmg8WZpn7zPNw9b0k4DvGXISUV88lVX0lo+8wzxjjnhJSZBXDRAkAC/9vjTFG47BfKL+56TngKoKj7CUKs405sxDXYeeWEeY1bCelS44lsoqTrbbLVoOFx6TAlv/N5JX/cFiU4ssfDAkB/xv3W4Mx+/Sc6910XuUCg4emUkiXcGwIzQrqZ9lWEE8h6wvzhmIzn8NP+3ATeBDtQwqB7EsioqFDUt2xXps3hAkBlQVBfFE3qhbAm4JzyglfhXcqph+jibx0y76JxCmHmnBvbg+2u2qIyCih20pVtI1StPRlghZ2zCQ7+E5405rxM";
	public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url;
	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url;
	// 签名方式
	public static String sign_type = "RSA";
	// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
	public static String log_path = "C:\\";
	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	// 支付类型 ，无需修改
	public static String payment_type = "1";
	// 调用的接口名，无需修改
	public static String service = "create_direct_pay_by_user";
	// 防钓鱼时间戳  若要使用请调用类文件submit中的query_timestamp函数
	public static String anti_phishing_key = "";
	// 客户端的IP地址 非局域网的外网IP地址，如：221.0.0.1
	public static String exter_invoke_ip = "";
}

