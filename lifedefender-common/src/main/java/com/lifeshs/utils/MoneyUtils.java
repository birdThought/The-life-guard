package com.lifeshs.utils;

/**
 * Created by Administrator on 2017/7/18 0018.
 */
public class MoneyUtils {

    /**
     * 将分转换成元
     * example: 99 => 0.99
     *
     * @param money 单位:分
     * @return String 单位:元
     * @author wuj
     * @updateTime none
     * @since 2017-07-17 16:13:12
     */
    public static String unitTransfer(String money) {
        String str = money;
        int len = str.length();

        switch (len) {
            case 1:
                str = "0.0" + str;
                break;
            case 2:
                str = "0." +str;
                break;
            default:
                str = str.substring(0,len-2) + "." + str.substring(len-2);
        }

        return str;
    }
}
