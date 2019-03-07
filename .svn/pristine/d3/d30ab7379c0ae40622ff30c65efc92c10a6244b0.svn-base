package com.lifeshs.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/** 
 * 数字处理工具类 
 *  
 * @author Johnson 
 * @version Monday October 25th, 2010 
 */  
public class NumberUtils {  
  
    /** 
     * 判断当前值是否为整数 
     *  
     * @param value 
     * @return 
     */  
    public static boolean isInteger(Object value) {  
        if (StringUtils.isEmpty(value)) {  
            return false;  
        }  
        String mstr = value.toString();  
        Pattern pattern = Pattern.compile("^-?\\d+{1}");  
        return pattern.matcher(mstr).matches();  
    }  
  
    /** 
     * 判断当前值是否为数字(包括小数) 
     *  
     * @param value 
     * @return 
     */  
    public static boolean isDigit(Object value) {  
        if (StringUtils.isEmpty(value)) {  
            return false;  
        }  
        String mstr = value.toString();  
        Pattern pattern = Pattern.compile("^-?[0-9]*.?[0-9]*{1}");  
        return pattern.matcher(mstr).matches();  
    }  
  
    /** 
     * 将数字格式化输出 
     *  
     * @param value 
     *            需要格式化的值 
     * @param precision 
     *            精度(小数点后的位数) 
     * @return 
     */  
    public static String format(Object value, Integer precision) {  
        Double number = 0.0;  
        if (NumberUtils.isDigit(value)) {  
            number = new Double(value.toString());  
        }  
        precision = (precision == null || precision < 0) ? 2 : precision;  
        BigDecimal bigDecimal = new BigDecimal(number);  
        return bigDecimal.setScale(precision, BigDecimal.ROUND_HALF_UP)  
                .toString();  
    }  
  
    /** 
     * 将数字格式化输出 
     *  
     * @param value 
     * @return 
     */  
    public static String format(Object value) {  
        return NumberUtils.format(value, 2);  
    }  
  
    /** 
     * 将值转成Integer型，如果不是整数，则返回0 
     *  
     * @param value 
     * @param replace 
     *            如果为0或者null，替换值 
     * @return 
     */  
    public static Integer parseInteger(Object value, Integer replace) {  
        if (!NumberUtils.isInteger(value)) {  
            return replace;  
        }  
        return new Integer(value.toString());  
    }  
  
    /** 
     * 将值转成Integer型，如果不是整数，则返回0 
     *  
     * @param value 
     * @return 
     */  
    public static Integer parseInteger(Object value) {  
        return NumberUtils.parseInteger(value, 0);  
    }  
  
    /** 
     * 将值转成Long型 
     *  
     * @param value 
     * @param replace 
     *            如果为0或者null，替换值 
     * @return 
     */  
    public static Long parseLong(Object value, Long replace) {  
        if (!NumberUtils.isInteger(value)) {  
            return replace;  
        }  
        return new Long(value.toString());  
    }  
  
    /** 
     * 将值转成Long型，如果不是整数，则返回0 
     *  
     * @param value 
     * @return 
     */  
    public static Long parseLong(Object value) {  
        return NumberUtils.parseLong(value, 0L);  
    }  
  
    /** 
     * 将值转成Double型 
     *  
     * @param value 
     * @param replace 
     *            replace 如果为0或者null，替换值 
     * @return 
     */  
    public static Double parseDouble(Object value, Double replace) {  
        if (!NumberUtils.isDigit(value)) {  
            return replace;  
        }  
        return new Double(value.toString());  
    }  
  
    /** 
     * 将值转成Double型，如果不是整数，则返回0 
     *  
     * @param value 
     * @return 
     */  
    public static Double parseDouble(Object value) {  
        return NumberUtils.parseDouble(value, 0.0);  
    }  
  
    /** 
     * 将char型数据转成字节数组 
     *  
     * @param value 
     * @return 
     */  
    public static byte[] toBytes(char value) {  
        byte[] bt = new byte[2];  
        for (int i = 0; i < bt.length; i++) {  
            bt[i] = (byte) (value >>> (i * 8));  
        }  
        return bt;  
    }  
  
    /** 
     * 将short型数据转成字节数组 
     *  
     * @param value 
     * @return 
     */  
    public static byte[] toBytes(short value) {  
        byte[] bt = new byte[2];  
        for (int i = 0; i < bt.length; i++) {  
            bt[i] = (byte) (value >>> (i * 8));  
        }  
        return bt;  
    }  
  
    /** 
     * 将int型数据转成字节数组 
     *  
     * @param value 
     * @return 
     */  
    public static byte[] toBytes(int value) {  
        byte[] bt = new byte[4];  
        for (int i = 0; i < bt.length; i++) {  
            bt[i] = (byte) (value >>> (i * 8));  
        }  
        return bt;  
    }  
  
    /** 
     * 将long型数据转成字节数组 
     *  
     * @param value 
     * @return 
     */  
    public static byte[] toBytes(long value) {  
        byte[] bt = new byte[8];  
        for (int i = 0; i < bt.length; i++) {  
            bt[i] = (byte) (value >>> (i * 8));  
        }  
        return bt;  
    }  
  
    /** 
     * 将short型数据插入到指定索引的字节数组中 
     *  
     * @param index 
     *            索引 
     * @param values 
     *            字节数组 
     * @param value 
     *            需要插入的值 
     */  
    public static void insert(int index, byte[] values, short value) {  
        byte[] bt = NumberUtils.toBytes(value);  
        System.arraycopy(bt, 0, values, index, 2);  
    }  
  
    /** 
     * 将int型数据插入到指定索引的字节数组中 
     *  
     * @param index 
     *            索引 
     * @param values 
     *            字节数组 
     * @param value 
     *            需要插入的值 
     */  
    public static void insert(int index, byte[] values, int value) {  
        byte[] bt = NumberUtils.toBytes(value);  
        System.arraycopy(bt, 0, values, index, 4);  
    }  
  
    /** 
     * 将long型数据插入到指定索引的字节数组中 
     *  
     * @param index 
     *            索引 
     * @param values 
     *            字节数组 
     * @param value 
     *            需要插入的值 
     */  
    public static void insert(int index, byte[] values, long value) {  
        byte[] bt = NumberUtils.toBytes(value);  
        System.arraycopy(bt, 0, values, index, 8);  
    }  
  
    /** 
     * 将字节转换成整型 
     *  
     * @param value 
     *            字节类型值 
     * @return 
     */  
    public static int byteToInt(byte value) {  
        if (value < 0) {  
            return value + 256;  
        }  
        return value;  
    }

    /**
     * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
     *
     * @param amount
     * @return
     */
    public static int changeY2F(String amount){
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
        }
        return Integer.parseInt(amLong.toString());
    }
    /**
     * 将分为单位的转换为元 （除100）
     *
     * @param amount
     * @return
     * @throws Exception
     */
    public static double changeF2Y(String amount){
        if(!amount.matches("\\-?[0-9]+")) {
            return 0;
        }
        BigDecimal b = new BigDecimal(amount).divide(new BigDecimal(100));
        return b.doubleValue();
    }

    /**
     * 四舍五入返回待支付金额（精确到2位小数）
     *
     * @return
     */
    public static double getRoundCash(String cash) {
        BigDecimal b = new BigDecimal(cash);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static Double add(Double a, Double b) {
        BigDecimal a1 = new BigDecimal(a.toString());
        BigDecimal b1 = new BigDecimal(b.toString());
        return new Double(a1.add(b1).doubleValue());
    }
    
    /**
     *  相除保留一位，四舍五入
     *  @author yuhang.weng 
     *  @DateTime 2017年3月21日 下午3:59:47
     *
     *  @param a
     *  @param b
     *  @param decimal 指定长度
     *  @return
     */
    public static Float divide(Float a, Float b, int decimal) {
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return new Float(a1.divide(b1, decimal, BigDecimal.ROUND_HALF_UP).floatValue());
    }
    
    public static Double divide(Double a, Double b, int decimal) {
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.divide(b1, decimal, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static Long divide(Long a, Long b, int decimal) {
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return new Long(a1.divide(b1, decimal, BigDecimal.ROUND_HALF_UP).longValue());
    }
    
    /**
     * 相乘，四舍五入保留指定decimal位的小数点后数字长度
     * @param a
     * @param b
     * @param decimal 指定长度
     * @return
     */
    public static float multiply(Float a, Float b, int decimal) {
        BigDecimal c = new BigDecimal(a * b);
        return c.setScale(decimal, BigDecimal.ROUND_HALF_UP).floatValue();
    }
}