package com.lifeshs.thirdservice.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by Administrator on 2017/6/18.
 */
public class AlipayUtils {
    private static Logger logger = LoggerFactory.getLogger(AlipayUtils.class);

    //唯一的AlipayClient
    private static AlipayClient alipayClient;

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    private static String app_id;

    // 商户私钥，您的PKCS8格式RSA2私钥
    private static String merchant_private_key;

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    private static String alipay_public_key;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static String notify_url;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private static String return_url;

    // 签名方式
    private static String sign_type;

    // 字符编码格式
    private static String charset;

    // 支付宝网关
    private static String gatewayUrl;

    // 支付宝网关
    private static String log_path;

    private static Properties properties;

    // 单例模式
    private AlipayUtils() {
    }

    static {
        try {
            properties = new Properties();
            InputStreamReader isr = new InputStreamReader(AlipayUtils.class.getClassLoader().getResourceAsStream("alibaba/alipayConfig.properties"));
            properties.load(isr);

            gatewayUrl = properties.getProperty("AlipayConfig.gatewayUrl");
            app_id = properties.getProperty("AlipayConfig.app_id").trim();
            merchant_private_key = properties.getProperty("AlipayConfig.merchant_private_key").trim();
            charset = properties.getProperty("AlipayConfig.charset").trim();
            alipay_public_key = properties.getProperty("AlipayConfig.alipay_public_key").trim();
            sign_type = properties.getProperty("AlipayConfig.sign_type").trim();
            log_path = properties.getProperty("AlipayConfig.log_path").trim();
            notify_url = properties.getProperty("AlipayConfig.notify_url").trim();
            return_url = properties.getProperty("AlipayConfig.return_url").trim();
            
            System.out.println(gatewayUrl);

            //初始化客户端
            alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, "json", charset, alipay_public_key, sign_type);
        } catch (IOException e) {
            logger.error("支付宝客户端初始化失败", e);
        }
    }

    public static AlipayClient getAlipayClient() {
        return alipayClient;
    }


    public static String getApp_id() {
        return app_id;
    }


    public static String getMerchant_private_key() {
        return merchant_private_key;
    }


    public static String getAlipay_public_key() {
        return alipay_public_key;
    }


    public static String getNotify_url() {
        return notify_url;
    }


    public static String getReturn_url() {
        return return_url;
    }


    public static String getSign_type() {
        return sign_type;
    }


    public static String getCharset() {
        return charset;
    }


    public static String getGatewayUrl() {
        return gatewayUrl;
    }


    public static String getLog_path() {
        return log_path;
    }


}
