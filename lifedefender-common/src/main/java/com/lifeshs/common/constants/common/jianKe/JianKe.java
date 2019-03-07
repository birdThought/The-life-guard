package com.lifeshs.common.constants.common.jianKe;

import com.lifeshs.utils.PropertiesUtil;

/**
 * 
 *  健客基本信息获取
 *  @author liaoguo
 *  @version 1.0
 *  @DateTime 2018年6月7日 上午11:14:00
 */
public class JianKe {
    static {
        PropertiesUtil p = new PropertiesUtil("environment.properties");
        INTERFACE_URL = p.readProperty("jianke.interface_url");
        LOGIN_NAME = p.readProperty("jianke.loginName");
        LOGIN_PWD = p.readProperty("jianke.loginPwd");
    }
    public static String INTERFACE_URL;
    public static String LOGIN_NAME;
    public static String LOGIN_PWD;
}
