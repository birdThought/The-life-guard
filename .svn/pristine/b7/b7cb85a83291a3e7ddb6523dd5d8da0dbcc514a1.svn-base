package com.lifeshs.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机字符串生成
 * Created by Administrator on 2017/12/19.
 */
public class RandomCharUtil {

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "1234567890";

    /**
     *  大小写字母数字
     * @param length
     * @return
     */
    public static String getCreatrString(int length){
        StringBuffer sb = new StringBuffer();
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(rd.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     *  返回一个定长的随机纯字母字符串(只包含大小写字母)
     * @param length
     * @return
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     * @param length
     * @return
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     * @param length
     * @return
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 每次生成的len位数都不相同
     * @param param 数字数组
     * @param len
     * @return
     */
    public static int getNotSimple(int[] param, int len) {
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        return result;
    }
}
