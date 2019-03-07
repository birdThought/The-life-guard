package com.lifeshs.component.weChat;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 统一下单
 */
public class UnifiedOrderUtils {

    private static Logger logger = LoggerFactory.getLogger(UnifiedOrderUtils.class);
    
    /**
     * 公众账号ID
     */
    private static String appid;
    /**
     * 商户号
     */
    private static String mch_id;
    /**
     * 公众号密匙
     */
    private static String api_key;
    /**
     * 回调URL
     */
    private static String notify_url;
    
    //统一下单
    private static String unifiedorder;
    
    //关闭
    private static String closeorder;
    
    //退款
    private static String refund;
    
    private static Properties properties;
    
    static {
        try {
            properties = new Properties();
            InputStreamReader isr = new InputStreamReader(UnifiedOrderUtils.class.getClassLoader().getResourceAsStream("weChat/weChatConfig.properties"));
            properties.load(isr);

            appid = properties.getProperty("weChatConfig.app_id");
            mch_id = properties.getProperty("weChatConfig.mch_id");
            api_key = properties.getProperty("weChatConfig.api_key");
            unifiedorder = properties.getProperty("weChatConfig.url.unifiedorder");
            closeorder = properties.getProperty("weChatConfig.url.closeorder");
            refund = properties.getProperty("weChatConfig.url.refund");
        
            
            properties = new Properties();
            isr = new InputStreamReader(UnifiedOrderUtils.class.getClassLoader().getResourceAsStream("environment.properties"));
            properties.load(isr);
            notify_url = properties.getProperty("weChatConfig.notify_url");
            
        } catch (IOException e) {
            logger.error("",e);
        }
    }
    
    public static String getAppid() {
        return appid;
    }

    public static String getMch_id() {
        return mch_id;
    }

    public static String getApi_key() {
        return api_key;
    }

    public static String getUnifiedorder() {
        return unifiedorder;
    }

    public static String getCloseorder() {
        return closeorder;
    }

    public static String getRefund() {
        return refund;
    }

    public static String getNotify_url() {
        return notify_url;
    }
}
