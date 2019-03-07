package com.lifeshs.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *  版权归
 *  TODO 验证码生成工具类
 *  @author duosheng.mo  
 *  @DateTime 2016年5月17日 下午3:53:30
 */
public class RandCodeUtil {

	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月17日 下午3:47:39
	 *  @serverComment 获取数字验证码
	 *
	 *  @return
	 */
	public static String randNumberCode(){
        int randCodeLength = Integer.parseInt(ResourceUtil.getRandCodeLength());
		return extractRandCode("1",randCodeLength);
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月3日 上午11:19:42
	 *  @serverComment 自定义随机码生成函数
	 *
	 *  @param randCodeType '1'数字 '2'小写字母 '3'大写字母 '4'字符 '5'混合字符串
	 *  @param length 随机码长度
	 *  @return
	 */
	public static String randNumberCodeByCustom(String randCodeType, int length){
		return extractRandCode(randCodeType, length);
	}
	
	public static void b() {
	    int iMax = 100;
        
        List<Map<String, Object>> rl = new ArrayList<>();
        System.out.print("waiting...");
        for (int i = 0; i < iMax; i++) {
            if (i % 20 == 0) {
                System.out.println();
            }
            System.out.print(".");
            Map<String, Object> r = c(5, 4);
            if (!r.isEmpty()) {
                rl.add(r);
            }
        }
        System.out.println();
        for (Map<String, Object> m : rl) {
            System.out.println("[interval:" + m.get("interval") + ", size:" + m.get("size") + "]");
        }
        
        BigDecimal b0 = new BigDecimal(rl.size());
        BigDecimal b1 = new BigDecimal(iMax);
        
        double rate = b0.divide(b1, 5, RoundingMode.HALF_UP).doubleValue();
        
        System.out.println("repeat rate: " + rate);
	}
	
	public static Map<String, Object> c(int length, int xzcs) {
	    Map<String, Object> result = new HashMap<>();
	    
	    List<String> list = new ArrayList<>();
	    
	    LocalDateTime now1 = LocalDateTime.now();
        LocalDateTime now = now1;
        LocalDateTime end = now.plusSeconds(1);
        while (now.isBefore(end)) {
            now = LocalDateTime.now();
            String code = randNumberCodeByCustom("1", length);
            // 修正次数
            for (int j = 0; j < xzcs; j++) {
                if (list.contains(code)) {
                    code = randNumberCodeByCustom("1", length);
                } else {
                    break;
                }
            }
            if (list.contains(code)) {
                result.put("interval", now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - now1.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                result.put("size", list.size());
                return result;
            }
            list.add(code);
        }
        return result;
	}
	
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年5月17日 下午3:47:39
	 *  @serverComment 根据配置生成验证码
	 *
	 *  @return
	 */
	public static String randCode(){
		String randCodeType = ResourceUtil.getRandCodeType();
        int randCodeLength = Integer.parseInt(ResourceUtil.getRandCodeLength());
		return extractRandCode(randCodeType,randCodeLength);
	}
	
	/**
	 * @return 随机码
	 */            
	private static String extractRandCode(String randCodeType,int randCodeLength) {
        if (randCodeType == null) {
			return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
		} else {
			switch (randCodeType.charAt(0)) {
			case '1':
				return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
			case '2':
				return RandCodeImageEnum.LOWER_CHAR.generateStr(randCodeLength);
			case '3':
				return RandCodeImageEnum.UPPER_CHAR.generateStr(randCodeLength);
			case '4':
				return RandCodeImageEnum.LETTER_CHAR.generateStr(randCodeLength);
			case '5':
				return RandCodeImageEnum.ALL_CHAR.generateStr(randCodeLength);
			default:
				return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
			}
		}
	}
	
}

/**
 *  版权归
 *  TODO 验证码辅助类
 *  @author duosheng.mo  
 *  @DateTime 2016年5月17日 下午3:26:41
 */
enum RandCodeImageEnum {
	/**
	 * 混合字符串
	 */
	ALL_CHAR("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
	/**
	 * 字符
	 */
	LETTER_CHAR("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
	/**
	 * 小写字母
	 */
	LOWER_CHAR("abcdefghijklmnopqrstuvwxyz"),
	/**
	 * 数字
	 */
	NUMBER_CHAR("0123456789"),
	/**
	 * 大写字符
	 */
	UPPER_CHAR("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	/**
	 * 待生成的字符串
	 */
	private String charStr;

	/**
	 * @param charStr
	 */
	private RandCodeImageEnum(final String charStr) {
		this.charStr = charStr;
	}

	/**
	 * 生产随机验证码
	 * 
	 * @param codeLength
	 *            验证码的长度
	 * @return 验证码
	 */
	public String generateStr(final int codeLength) {
		final StringBuffer sb = new StringBuffer();
		final Random random = new Random();
		final String sourseStr = getCharStr();
		for (int i = 0; i < codeLength; i++) {
			sb.append(sourseStr.charAt(random.nextInt(sourseStr.length())));
		}
		return sb.toString();
	}

	/**
	 * @return the {@link #charStr}
	 */
	public String getCharStr() {
		return charStr;
	}

}
