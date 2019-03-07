package com.lifeshs.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 *  类说明
 *  @author yuhang.weng
 *  @version 1.0
 *  @DateTime 2017年6月22日 上午10:54:42
 */
public class Toolkits {

    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月20日 下午4:36:43
     *  @serverCode 服务代码
     *  @serverComment 数量加 1
     *
     *  @param code
     *  @return
     */
    public static  String codeAdd(String code){
        String result = "";
        result = String.format("%0"+code.length()+"d", Integer.valueOf(code) + 1);  
        return result;
    }
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016-2-2 上午11:36:16
     *  @serverCode 服务代码
     *  @serverComment 验证手机号码
     *
     *  @param number
     *  @return
     */
    public static  boolean verifyPhone(String number){
        String regex = "^((\\+86)|(86))?[1][3,4,5,7,8][0-9]{9}$"; // 验证手机号 
        return match(number, regex);
    }
    
    /**
     *  @author yuhang.weng 
     *  @DateTime 2016年6月25日 上午9:24:38
     *  @serverComment 验证座机号码
     *
     *  @param number
     *  @return
     */
    public static boolean verifyTel(String number){
        String regex = "^([0|4|8][0-9]{2,3}\\-)?([2-9][0-9]{6,7})$";
        return match(number, regex);
    }
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年5月20日 下午4:36:43
     *  @serverComment 验证邮箱
     *
     *  @param email
     *  @return
     */
    public static boolean isEmail(String email){     
        String regex = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        return match(email, regex);
    }
    
    public static boolean isVerifyUserName(String userName) {
        String regex = "^[a-zA-Z0-9]{6,16}$";
        return match(userName, regex);
    }
    
    public static boolean isVerifyPassword(String password) {
        String regex = "^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,16}$";
        return match(password, regex);
    }
    
    public static boolean isVerifyOrgName(String orgName) {
        String regex = "^([a-zA-Z0-9]|[\u4e00-\u9fa5]){1,20}$";
        return match(orgName, regex);
    }
    
    public static boolean isVerifyHH_mm(String HH_mm) {
        String regex = "^(?:[01]\\d|2[0-3])(?::[0-5]\\d)$";
        return match(HH_mm, regex);
    }
    
    /**
     *  长度最大值为20，可以由中文，字母，数字组成
     *  @author yuhang.weng 
     *	@DateTime 2017年6月15日 下午1:47:20
     *
     *  @param receiverName 收货人名字
     *  @return
     */
    public static boolean isVerifyReciverName(String receiverName) {
        String regex = "^([a-zA-Z0-9]|[\u4e00-\u9fa5]){1,20}$";
        return match(receiverName, regex);
    }
    
    /**
     *  长度最大值为15，可以由中文，字母，数字组成
     *  @author yuhang.weng 
     *	@DateTime 2017年8月3日 上午9:27:36
     *
     *  @param realName
     *  @return
     */
    public static boolean isVerifyRealName(String realName) {
        String regex = "^([a-zA-Z0-9]|[\u4e00-\u9fa5]){1,15}$";
        return match(realName, regex);
    }
    
    /**
     *  对公账号
     *  @author yuhang.weng 
     *  @DateTime 2017年8月11日 下午2:38:30
     *
     *  @param bankAccount
     *  @return
     */
    public static boolean isVerifyBankAccount(String bankAccount) {
        int length = bankAccount.length();
        if (length >= 16 && length <= 19) {
            return true;
        }
        return false;
    }
    
    private static boolean match(String arg, String regex) {
        if (StringUtils.isBlank(arg)) {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arg);
        return matcher.matches();
    }
    
    
    /** 
     *  获取project的标题
     *  示例: AA[bb] 结果:AA
     *  @author yuhang.weng 
     *	@DateTime 2017年6月22日 上午10:55:11
     *
     *  @param name
     *  @return
     */
    public static String projectTilte(String name) {
        String projectTilte = "";
        String regex = "\\[.+\\]";
        projectTilte = name.replaceAll(regex, "");
        return projectTilte;
    }
    
    /**
     *  获取project的类型
     *  示例: AA[bb] 结果:bb
     *  @author yuhang.weng 
     *	@DateTime 2017年6月22日 上午10:54:44
     *
     *  @param name
     *  @return
     */
    public static String projectType(String name) {
        String projectType = "";
        String projectTitle = projectTilte(name);
        String typeWithShell = name.replace(projectTitle, "");
        if (typeWithShell.length() >= 2) {
            projectType = typeWithShell.substring(1, typeWithShell.length() - 1);
        }
        return projectType;
    }
    
    public static boolean custom(String name, String regex) {
        return match(name, regex);
    }
}
