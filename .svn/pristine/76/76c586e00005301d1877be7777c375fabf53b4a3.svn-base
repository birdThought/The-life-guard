package com.lifeshs.service.tool;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * @author Yue.Li
 * @date 3/14/17.
 */
public interface ITokenService {
    String createToken(String key, String salt);
    boolean isTokenOverTime(String key, String salt, String token);

    /**
     * 验证第三方的身份和token
     * dengfeng
     * @param token
     * @param timestamp
     * @param appsecert
     * @return
     */
    boolean validThirdToken(String token, String timestamp, String appsecert);

    /**
     * 检查第三方接口的时效性，目前规则是timestamp不超过三分钟
     * @param timestamp
     * @param token
     * @return
     */
    boolean isThirdTokenOverTime(String token, String timestamp);
}
