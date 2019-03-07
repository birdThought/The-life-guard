package com.lifeshs.utils;

import jodd.util.CharUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isBlank(String string) {
        return string == null || containsOnlyWhitespaces(string);
    }

    private static boolean containsOnlyWhitespaces(String string) {
        int size = string.length();

        for(int i = 0; i < size; ++i) {
            char c = string.charAt(i);
            if(!CharUtil.isWhitespace(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     *  @author duosheng.mo 
     *  @DateTime 2016-4-7 下午10:26:32
     *  @serverCode 服务代码
     *  @serverComment 首字母大写
     *
     *  @param str
     *  @return
     */
    public static String firstUpperCase(String str){
          StringBuffer strSb = new StringBuffer();
          Pattern pattern = Pattern.compile("([a-z])([a-z]*)",Pattern.CASE_INSENSITIVE);
          Matcher m = pattern.matcher(str);
          while (m.find()) {
             m.appendReplacement(strSb, m.group(1).toUpperCase() + m.group(2));
          }
          return m.appendTail(strSb).toString();
    }
    
    /**
     * 判断对象是否为空
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str) {
        boolean flag = true;
        if (str != null && !str.equals("")) {
            if (str.toString().length() > 0) {
                flag = true;
            }
        } else {
            flag = false;
        }
        return flag;
    }
    
    /**
     * 对特殊字符串进行还原转义
     * @author zhansi.Xu
     * @DateTime 2016年9月27日
     * @serverComment
     */
    public static String decodeStr(String str){
        return str.replace("＆", "&");
    }
    
    
    /**
     * 过滤掉脚本返回干净的html
     * @author zhansi.Xu
     * @DateTime 2016年9月30日
     * @serverComment
     */
    public static String filterHtml(String html){
        return  html.replaceAll("<script[^>]*>[\\d\\D]*?</script>","");
    }

    public static String joinLine(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
    
    /**
     *  给字符串打码
     *  @author yuhang.weng 
     *	@DateTime 2017年7月6日 下午1:48:24
     *
     *  @param origin 需要修改的字符串
     *  @param headLength 保留的头部长度
     *  @param tailLength 保留的尾部长度
     *  @param veil 遮蔽物
     *  @return
     */
    public static String cover(String origin, int headLength, int tailLength, String veil) {
        int length = origin.length();
        
        // 如果头部长度或者尾部长度超出，就直接返回遮蔽物
        if ((headLength >= length) || (tailLength >= length)) {
            return veil;
        }
        // 如果保留的长度和仍然超过总长度，就只保留头部内容
        if (headLength + tailLength > length) {
            tailLength = 0;
        }
        String result = "";

        int beginIndex = headLength;
        int endIndex = length - tailLength;
        // 如果长度刚好等于preLength+endLength，就取前一段内容
        if ((headLength + tailLength == length) || (headLength == length)) {
            result = origin.substring(0, beginIndex) + veil;
        } else {
            result = origin.substring(0, beginIndex) + veil + origin.substring(endIndex);
        }
        return result;
    }
}
