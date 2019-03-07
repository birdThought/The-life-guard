package com.lifeshs.common.constants.common;

import com.lifeshs.utils.PropertiesUtil;

/**
 * @author Administrator
 * @create 2018-01-18
 * 11:50
 * @desc
 */
public class Transfer {
    static {
        PropertiesUtil p = new PropertiesUtil("environment.properties");
        APPID = p.readProperty("jztx.appid");
        APPSECERT = p.readProperty("jztx.appsecert");
         JIAZHENG_TIANXIA_APP = p.readProperty("jztx.url");
    }
    public static String APPID;
    public static String APPSECERT;
    public static String JIAZHENG_TIANXIA_APP;
}
