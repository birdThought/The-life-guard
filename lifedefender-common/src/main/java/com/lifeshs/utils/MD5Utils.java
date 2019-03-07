package com.lifeshs.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 版权归
 * TODO 密码加密处理
 *
 * @author duosheng.mo
 * @DateTime 2016年4月29日 下午5:23:49
 */
public class MD5Utils {

    private static final Logger logger = Logger.getLogger(MD5Utils.class);

    /**
     * @param username
     * @param password
     * @return
     * @author duosheng.mo
     * @DateTime 2016年4月29日 下午5:24:42
     * @serverCode 服务代码
     * @serverComment 用户名密码进行加密
     */
    public static String encryptPassword(String username, String password) {
        String inStr = username + password;
        if (StringUtils.isNotBlank(inStr)) {
            return encrypt(inStr);
        }
        return "";
    }


    /**
     * @param password
     * @return
     * @author duosheng.mo
     * @DateTime 2016年4月29日 下午5:26:25
     * @serverCode 服务代码
     * @serverComment 对密码加密
     */
    public static String encryptPassword(String password) {
        if (StringUtils.isNotBlank(password)) {
            return encrypt(password);
        }
        return "";
    }

    /**
     * @param param
     * @return
     * @author duosheng.mo
     * @DateTime 2016年5月3日 下午2:11:19
     * @serverCode 服务代码
     * @serverComment 加密
     */
    private static String encrypt(String param) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return "";
        }
        char[] charArray = param.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String encrypt(String param, String charset) {
        try {
            byte[] btInput = param.getBytes(charset);
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < md.length; i++) {
                int val = ((int) md[i]) & 0xff;
                if (val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * md5 加密
     *
     * @param str 字符串
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5(String str) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    public static String encryptStringWithUTF8(String string) {
        return encrypt(string, "utf-8");
    }
}
