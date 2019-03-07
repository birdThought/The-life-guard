package com.lifeshs.utils;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 *  版权归
 *  TODO 转换工具类
 *  @author duosheng.mo  
 *  @DateTime 2016年4月19日 下午6:08:50
 */
public class ConvertUtil {
	
	private static final Logger logger = Logger.getLogger(ConvertUtil.class);
	
	public static<T> T getValue(String value,String fieldName,Class<T> clazz){
	
		if (value == null) { // 如果获取参数值为null,则返回null
			return null;
		} else if (!value.equals("")) { // 如果获取参数值不为"",则通过convertGt方法进行类型转换后返回结果
			return convertGt(value, clazz);
		} else if (clazz.getName().equals(String.class.getName())) { // 如果获取参数值为""
			return convertGt(value, clazz);
		} else {// 如果获取参数值为"",并且clazz不是是String类型,则返回null
			return null;
		}
	}
	
	/**
	 * @param <T>
	 * @param value
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertGt(String value, Class<T> clazz) {
		if (value == null) { // 如果值为null,则返回null
			return null;
		} else if (value.equals("")
				&& !clazz.getName().equals(String.class.getName())) { // 如果value值为"",而且要转为的类型不是string类型，那么就统一返回null，也就是空字符串不能转成任何其他类型的实体，只能返回null
			return null;
		} else if (Date.class.getName().equalsIgnoreCase(clazz.getName())) { // 增加对从String类型到Date
			return (T) convertSTD(value);
		}
		return (T) ConvertUtils.convert(value, clazz);
	}

	//日期类型的转换
//	private static SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//日期类型格式为yyyy-MM-dd的用户生日数据类型的转换
//	private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Date convertSTD(String date){
		if(date.length() == 19 || date.length() == 21) {
			return DateTimeUtilT.dateTime(date);	// 处理格式为yyyy-MM-dd HH:mm:ss
		}
		if(date.length() == 24) {
			return DateTimeUtilT.convertGST2UTC(date);
		}
		return DateTimeUtilT.date(date);	// 处理日期格式为yyyy-MM-dd
	}
	
	public static String convertDTS(Date date){
		return DateTimeUtilT.dateTime(date);
	}
	
	/**
	 * 判断字符串是否为空
	 */
	static Map<String, Object> map = new HashMap<String, Object>();

	public static Map<String, Object> getMap() {
		return map;
	}

	public static boolean isEmpty(Object object) {
		if (object == null) {
			return (true);
		}
		if (object.equals("")) {
			return (true);
		}
		if (object.equals("null")) {
			return (true);
		}
		return (false);
	}

	public static String decode(String strIn, String sourceCode, String targetCode) {
		String temp = code2code(strIn, sourceCode, targetCode);
		return temp;
	}

	public static String StrToUTF(String strIn, String sourceCode, String targetCode) {
		strIn = "";
		try {
			strIn = new String(strIn.getBytes("ISO-8859-1"), "GBK");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strIn;

	}

	private static String code2code(String strIn, String sourceCode, String targetCode) {
		String strOut = null;
		if (strIn == null || (strIn.trim()).equals(""))
			return strIn;
		try {
			byte[] b = strIn.getBytes(sourceCode);
			for (int i = 0; i < b.length; i++) {
				System.out.print(b[i] + "  ");
			}
			strOut = new String(b, targetCode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return strOut;
	}

	public static int getInt(String s, int defval) {
		if (s == null || s == "") {
			return (defval);
		}
		try {
			return (Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return (defval);
		}
	}

	public static int getInt(String s) {
		if (s == null || s == "") {
			return 0;
		}
		try {
			return (Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static int getInt(String s, Integer df) {
		if (s == null || s == "") {
			return df;
		}
		try {
			return (Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static Integer[] getInts(String[] s) {
		Integer[] integer = new Integer[s.length];
		if (s.length == 0) {
			return null;
		}
		for (int i = 0; i < s.length; i++) {
			integer[i] = Integer.parseInt(s[i]);
		}
		return integer;

	}

	public static double getDouble(String s, double defval) {
		if (s == null || s == "") {
			return (defval);
		}
		try {
			return (Double.parseDouble(s));
		} catch (NumberFormatException e) {
			return (defval);
		}
	}

	public static double getDou(Double s, double defval) {
		if (s == null) {
			return (defval);
		}
		return s;
	}

	public static Short getShort(String s) {
		if (StringUtil.isNotEmpty(s)) {
			return (Short.parseShort(s));
		} else {
			return null;
		}
	}

	public static int getInt(Object object, int defval) {
		if (isEmpty(object)) {
			return (defval);
		}
		try {
			return (Integer.parseInt(object.toString()));
		} catch (NumberFormatException e) {
			return (defval);
		}
	}

	public static int getInt(BigDecimal s, int defval) {
		if (s == null) {
			return (defval);
		}
		return s.intValue();
	}

	public static Integer[] getIntegerArry(String[] object) {
		int len = object.length;
		Integer[] result = new Integer[len];
		try {
			for (int i = 0; i < len; i++) {
				result[i] = new Integer(object[i].trim());
			}
			return result;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static String getString(String s) {
		return (getString(s, ""));
	}

	public static String getString(Object object) {
		if (isEmpty(object)) {
			return "";
		}
		return (object.toString().trim());
	}

	public static String getString(int i) {
		return (String.valueOf(i));
	}

	public static String getString(float i) {
		return (String.valueOf(i));
	}

	public static String getString(String s, String defval) {
		if (isEmpty(s)) {
			return (defval);
		}
		return (s.trim());
	}

	public static String getString(Object s, String defval) {
		if (isEmpty(s)) {
			return (defval);
		}
		return (s.toString().trim());
	}

	public static long stringToLong(String str) {
		Long test = new Long(0);
		try {
			test = Long.valueOf(str);
		} catch (Exception e) {
		}
		return test.longValue();
	}

	/**
	 * 获取本机IP
	 */
	public static String getIp() {
		String ip = null;
		try {
			InetAddress address = InetAddress.getLocalHost();
			ip = address.getHostAddress();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return ip;
	}

	/**
	 * 判断一个类是否为基本数据类型。
	 * 
	 * @param clazz
	 *            要判断的类。
	 * @return true 表示为基本数据类型。
	 */
	private static boolean isBaseDataType(Class clazz) throws Exception {
		return (clazz.equals(String.class) || clazz.equals(Integer.class) || 
				clazz.equals(Byte.class) || clazz.equals(Long.class) || clazz.equals(Double.class) || 
				clazz.equals(Float.class) || clazz.equals(Character.class) || clazz.equals(Short.class) || 
				clazz.equals(BigDecimal.class) || clazz.equals(BigInteger.class) || clazz.equals(Boolean.class) || 
				clazz.equals(Date.class) || clazz.isPrimitive());
	}

	/**
	 * @param request
	 *            IP
	 * @return IP Address
	 */
	public static String getIpAddrByRequest(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * @return 本机IP
	 * @throws SocketException
	 */
	public static String getRealIp() throws SocketException {
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}

		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}

	/**
	 * java去除字符串中的空格、回车、换行符、制表符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;

	}

	/**
	 * 判断元素是否在数组内
	 * 
	 * @param substring
	 * @param source
	 * @return
	 */
	public static boolean isIn(String substring, String[] source) {
		if (source == null || source.length == 0) {
			return false;
		}
		for (int i = 0; i < source.length; i++) {
			String aSource = source[i];
			if (aSource.equals(substring)) {
				return true;
			}
		}
		return false;
	}
	
	public static <T> Boolean isIn(T obj, T[] array) {
		if(obj == null || array.length == 0) {
			return false;
		}
		for(int i = 0; i < array.length; i++) {
			T tmp = array[i];
			if(tmp.equals(obj)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取Map对象
	 */
	public static Map<Object, Object> getHashMap() {
		return new HashMap<Object, Object>();
	}

	/**
	 * SET转换MAP
	 * 
	 * @param str
	 * @return
	 */
	public static Map<Object, Object> SetToMap(Set<Object> setobj) {
		Map<Object, Object> map = getHashMap();
		for (Iterator iterator = setobj.iterator(); iterator.hasNext();) {
			Map.Entry<Object, Object> entry = (Map.Entry<Object, Object>) iterator.next();
			map.put(entry.getKey().toString(), entry.getValue() == null ? "" : entry.getValue().toString().trim());
		}
		return map;

	}
	
	/**
	 *  将实体对象转换为map对象
	 *  @author yuhang.weng 
	 *	@DateTime 2016年8月31日 下午3:57:14
	 *
	 *  @param entity 实体对象
	 *  @return
	 */
	public static <T> Map<String, Object> entityToMap(T entity) {
		Map<String, Object> map = new HashMap<>();
		
		Class<?> objClass = entity.getClass();
		
		if(objClass == null) {
			throw new NullPointerException();
		}
		
		Field[] fields = objClass.getDeclaredFields();
		for(int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);	// 设置true表示可以查看private的属性
			String name = field.getName();
			// 不处理序列化的标识ID
			if(!StringUtils.equals("serialVersionUID", name)) {
				String getMethodName = "get"+StringUtil.firstUpperCase(name);
				Method method = null;
				try {
					method = objClass.getMethod(getMethodName);
				} catch (NoSuchMethodException e) {
					logger.error("找不到该方法:"+e.getMessage());
				} catch (SecurityException e) {
					logger.error("无权限访问资源:"+e.getMessage());
				}
				Object value = null;
				try {
					value = method.invoke(entity);
				} catch (IllegalAccessException e) {
					logger.error("不允许调用该方法（比如private类型的方法）:"+e.getMessage());
				} catch (IllegalArgumentException e) {
					logger.error("传递的参数不正确:"+e.getMessage());
				} catch (InvocationTargetException e) {
					logger.error("调用的方法抛出异常:"+e.getMessage());
				}
				map.put(name, value);
			}
		}
		
		return map;
	}

	public static boolean isInnerIP(String ipAddress) {
		boolean isInnerIp = false;
		long ipNum = getIpNum(ipAddress);
		/**
		 * 私有IP：A类 10.0.0.0-10.255.255.255 B类 172.16.0.0-172.31.255.255 C类 192.168.0.0-192.168.255.255 当然，还有127这个网段是环回地址
		 **/
		long aBegin = getIpNum("10.0.0.0");
		long aEnd = getIpNum("10.255.255.255");
		long bBegin = getIpNum("172.16.0.0");
		long bEnd = getIpNum("172.31.255.255");
		long cBegin = getIpNum("192.168.0.0");
		long cEnd = getIpNum("192.168.255.255");
		isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd) || ipAddress.equals("127.0.0.1");
		return isInnerIp;
	}

	private static long getIpNum(String ipAddress) {
		String[] ip = ipAddress.split("\\.");
		long a = Integer.parseInt(ip[0]);
		long b = Integer.parseInt(ip[1]);
		long c = Integer.parseInt(ip[2]);
		long d = Integer.parseInt(ip[3]);

		long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
		return ipNum;
	}

	private static boolean isInner(long userIp, long begin, long end) {
		return (userIp >= begin) && (userIp <= end);
	}
	
	public static String delHTMLTag(String htmlStr) {
		String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式 
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式 
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式 
         
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE); 
        Matcher m_script=p_script.matcher(htmlStr); 
        htmlStr=m_script.replaceAll(""); //过滤script标签 
         
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE); 
        Matcher m_style=p_style.matcher(htmlStr); 
        htmlStr=m_style.replaceAll(""); //过滤style标签 
         
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE); 
        Matcher m_html=p_html.matcher(htmlStr); 
        htmlStr=m_html.replaceAll(""); //过滤html标签 

        return htmlStr.trim(); //返回文本字符串
	}
}
