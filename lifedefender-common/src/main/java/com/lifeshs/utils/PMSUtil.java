package com.lifeshs.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import net.sf.json.JSONObject;


/**
 *  版权归   gang of three
 *  @TODO 类说明     
 *  @author duosheng.mo  
 *  @DateTime 2015-7-2 下午09:22:28
 *  @version V1.0
 */
public class PMSUtil {
	private static Logger logger = Logger.getLogger(PMSUtil.class);
	public static final String MACHINE_ID = "CHNBS-NETINVOICE";
	public static final String AES_KEY = "0123456789ABCDEF";
	/**
	 * 去重复(传入二个集合对像，内容结构要相等)
	 * 将listB中存在的数据从listA中去掉，listA中保留listB中没有的数据
	 * @param listA 需要去重复的数据
	 * @param listB 对照数据
	 * @return 去掉重复的集合对像
	 */
	public static List getContains(List listA,List listB){
		if(null!=listA&&null!=listB){
			Iterator it = listB.iterator();  
	           while (it.hasNext()) {  
	        	   Map o = (Map) it.next();  
	                if (listA.contains(o))  
	                    listA.remove(o);  
	            }  
		}
		return listA;
	}
	/**
	 * 取授权码(时间戳取后8位)
	 * @return
	 */
	public static String getAuthorizationCode(){
		String cont=System.currentTimeMillis()+"";
		return cont.substring(5,13);
	}
	/**
	 * 生成文书号
	 * @param seq 数据库seq
	 * @param length 文书长度
	 * @return
	 */
	public static String getInstrumentNumber(String seq,int length){
		if(!isEmpty(isNull(seq))){
			if(seq.length()<length){
				StringBuffer sub=new StringBuffer();
				for(int i=0;i<length-seq.length();i++){
					sub.append("0");
				}
				sub.append(seq);
				return sub.toString();
			}
		}
		return seq;
	}
	/**
	 * 跟据正则规则验证是否数字字符串
	 * 
	 * @param s
	 *            被验证
	 * @param zz
	 *            正则规则
	 * @return
	 */
	public static boolean isNum(String s, String zz) {
		Pattern pattern = Pattern.compile(zz);
		Matcher isNum = pattern.matcher(s);
		return isNum.matches();
	}
	/**
	 * 判定字符串是否在數組中，僅針對純字符串數組
	 * 
	 * @param str
	 * @param arr
	 * @return
	 */
	public static boolean inArray(String str, ArrayList arr) {
		for (int i = 0; i < arr.size(); i++) {
			if (str.equals((String) arr.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判定字符串是否在數組中，僅針對純字符串數組，不区分大小写
	 * 
	 * @param str
	 * @param arr
	 * @return
	 */
	public static boolean inArrayNCase(String str, ArrayList arr) {
		for (int i = 0; i < arr.size(); i++) {
			if (str.equalsIgnoreCase((String) arr.get(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 數組轉換成字符串，通過分隔符進行連接，僅針對純字符串數組
	 * 
	 * @param glue
	 * @param arr
	 * @return
	 */
	public static String implode(String glue, ArrayList arr) {
		String implodeStr = "";
		for (int i = 0; i < arr.size(); i++) {
			if (i == 0) {
				implodeStr += (String) arr.get(i);
			} else {
				implodeStr += glue + (String) arr.get(i);
			}
		}
		return implodeStr;
	}

	/**
	 * 獲取MAP的所有鍵名，返回字符串數組
	 * 
	 * @param mp
	 * @return
	 */
	public static ArrayList<String> mapKeys(Map mp) {
		ArrayList<String> arr = new ArrayList<String>();
		Iterator it = mp.keySet().iterator();
		while (it.hasNext()) {
			// System.out.println("====================="+it.next());
			arr.add((String) it.next());
		}
		return arr;
	}

	/**
	 * 判断在MAP中是否存在此键
	 * 
	 * @param key
	 *            键名
	 * @param mp
	 *            MAP对象
	 * @return
	 */
	public static boolean inMap(String key, Map mp) {
		ArrayList li = PMSUtil.mapKeys(mp);
		boolean flag = false;
		for (int i = 0; i < li.size(); i++) {
			String tmp = (String) li.get(i);
			if (key.equals(tmp)) {
				flag = true;
				break;
			}
		}
		return flag;

	}

	/**
	 * 獲取JSONObject的所有鍵名，返回字符串數組
	 * 
	 * @param mp
	 * @return
	 */
	public static ArrayList jsonObjectKeys(JSONObject object) {
		ArrayList arr = new ArrayList();
		Iterator it = object.keys();
		while (it.hasNext()) {
			arr.add((String) it.next());
		}
		return arr;
	}

	/**
	 * 判断在JSONObject 中是否存在此键
	 * 
	 * @param key
	 *            键名
	 * @param mp
	 *            MAP对象
	 * @return
	 */

	public static boolean inJSON(String key, JSONObject object) {
		ArrayList li = PMSUtil.jsonObjectKeys(object);
		boolean flag = false;
		for (int i = 0; i < li.size(); i++) {
			String tmp = (String) li.get(i);
			if (key.equals(tmp)) {
				flag = true;
				break;
			}
		}
		return flag;

	}

	/**
	 * 生成随机字符串
	 * 
	 * @param length
	 *            字符串长度
	 * @return
	 */
	public static String randomStr(int length) {
		String str = "";
		String source = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
		String[] s = source.split(",");
		for (int i = 0; i < length; i++) {
			int rand = (int) (Math.random() * 35);
			str += s[rand];
		}
		return str;
	}

	/**
	 * 返回空字符串
	 * 
	 * @param str
	 * @return
	 * 
	 */
	public static String isNull(Object str) {
		if (isEmpty(String.valueOf(str))) {
			return "";
		} else {
			return String.valueOf(str);
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return 空 true 非空 false
	 */
	public static boolean isEmpty(String str) {
		if (str != null && !"".equals(str) && !"null".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 去除字符串开头和结尾的空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		if (str.charAt(0) == ' ') {
			str = str.substring(1, str.length());
		}
		if (str.charAt(str.length() - 1) == ' ') {
			str = str.substring(0, str.length() - 1);
		}
		if (str.charAt(0) == ' ' || str.charAt(str.length() - 1) == ' ') {
			str = PMSUtil.trim(str);
		}

		return str;
	}

	/**
	 * 判断是否是字符串数组
	 */
	public static boolean isStringArr(Object obj) {
		if (obj instanceof String[]) {
			return true;
		} else {
			return false;
		}
	}


	public static String addSlashes(String str) {
		str = str.replaceAll("'", "''"); // SQL-92标准
		// str = str.replaceAll("\"", "\\\\\"");
		return str;
	}

	public static String stripSlashes(String str) {
		str = str.replaceAll("''", "'");// SQL-92标准
		// str = str.replaceAll("\\\\\"", "\"");
		return str;
	}


	/**
	 * 按结束符载取字符串前部份
	 * 
	 * @param info
	 *            被载字符串
	 * @param jsf
	 *            结束符
	 * @return
	 */
	public static String getRequestInfo(String info, String jsf) {
		int i = isNull(info).indexOf(jsf);
		if (i > 0) {
			return isNull(info).substring(0, i);
		} else {
			return info;
		}
	}

	public static boolean verifyIDCard(String idCard) {
		boolean ok = false;
		if (idCard.length() == 18) {
			char idCards[] = idCard.toCharArray();
			int iS = 0;
			int iW[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
			char szVerCode[] = new char[] { '1', '0', 'X', '9', '8', '7', '6',
					'5', '4', '3', '2' };
			for (int i = 0; i < 17; i++) {
				iS += (int) (idCards[i] - '0') * iW[i];
			}
			int iY = iS % 11;
			ok = szVerCode[iY] == idCards[17];
		} else if (idCard.length() == 15) {
			ok = true;
		}
		return ok;
	}

	/**
	 * 生成提示下拉JS字符串
	 * @param list
	 * @param jsname
	 * @param name
	 * @return
	 */
	public static String getScript(List list,String jsname,String name){
		StringBuffer sb=new StringBuffer("<script>var ");
		sb.append(jsname).append("=[");
		if(null!=list){
			for(int i=0;i<list.size();i++){
				Map map=(HashMap)list.get(i);
				sb.append("'").append(isNull(map.get(name))).append("'");
				if(i<list.size()-1){
					sb.append(",");
				}
			}
		}
		sb.append("]</script>");
		return sb.toString();
	}
	/**
	 * 生成提示下拉JS字符串
	 * @param list
	 * @param jsname
	 * @param name
	 * @return
	 */
	public static String getScript(String json,String jsname){
		StringBuffer sb=new StringBuffer("<script>var ");
		sb.append(jsname).append("=");
		sb.append(json);
		sb.append("</script>");
		return sb.toString();
	}
	
	public static String getMonthFirstDay(){
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.DATE, 1);
		Date d=cal.getTime();
		return DateTimeUtilT.date(d);
	}
	
	/**
	 * 数字人民币格式化  例如1：输入：4.9881986199999996E7  输出： 49881986.16
	 * @param money 例如2:输入：3433  输出：3433.00
	 * @return
	 */
	public static String formatMoney(String money){
		if(StringUtils.isNotEmpty(money)){
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			money= decimalFormat.format(Double.parseDouble(money));
		}
		return money;
	}
	
	/**
	 * 添加分页信息
	 * @param args
	 */
	
	@SuppressWarnings("unchecked")
	public static Map putPageInfo(Map map) {
		int page = 0;
		int pagesize = 1;
		Object s_page = map.get("page");
		Object s_pagesize = map.get("pagesize");
		if (s_page == null || "".equals(s_page))
			page = 1;
		else
			page = Integer.parseInt(s_page.toString());
		if (s_pagesize == null || "".equals(s_pagesize))
			pagesize = 1;
		else
			pagesize = Integer.parseInt(s_pagesize.toString());
		int start = (page - 1) * pagesize;
		int end = (page - 1) * pagesize + pagesize;
		map.put("BEGIN_NUM", Integer.valueOf(start));
		map.put("END_NUM", Integer.valueOf(end));
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static  Map getResultMap(Map para){
	  	Map res = null;
	  	if(para.isEmpty()){
	  		return  null ;
	  	}
	  	Object  obj = para.get("contents");
	  	if(obj !=null){
	  		res = (Map)obj;
	  	}
	  	return  res ;	  	
	}
	
	@SuppressWarnings("unchecked")
	public static  List getResultList(Map para){
	  	List res = null;
	  	if(para.isEmpty()){
	  		return  null ;
	  	}
	  	Object  obj = para.get("contents");
	  	if(obj !=null){
	  		res = (List)obj;
	  	}
	  	return  res ;	  	
	}
	

	
	/***
	 * 查找 新集合与原集合值不同的项
	 * @param newMap  页面上收集的参数信息
	 * @param oldMap  原始的信息
	 * @param igno	     需要忽略的key
	 * @return list    改变了的值的key集合
	 */
	public static List<String> compareMap(Map newMap , Map oldMap , List<String> igno){
		List<String> changeList = new ArrayList<String>();
		Set<String> keys = newMap.keySet() ;
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()){
			String key = itr.next();
			if(igno != null && igno.contains(key)){
				continue ;
			}
			String newVal = String.valueOf(newMap.get(key));
			String oldVal = String.valueOf(oldMap.get(key));
			if(!newVal.equals(oldVal)){
				changeList.add(key);
			}
		}
		return changeList ;
	}

	/**
	 * 监测密码强度(0:弱，1：弱，2：中，3：强，4：超强)
	 * @param content
	 * @return
	 */
	public static int checkMode(String content){
		if (content.length() <= 4)
			return 0; // 密码太短
		int mode = 0;
		for (int i = 0; i < content.length(); i++) {
			// 测试每一个字符的类别并统计一共有多少种模式.
			char c=content.charAt(i);
			int temp=0;
			if(c>=48&&c<=57){//数字
				temp=1;
			}else if(c>=65&&c<=90){//大写字母
				temp=2;
			}else if(c>=97&&c<=122){//小写
				temp=4;
			}else{
				temp=8;//特殊字符
			}
			mode |= temp;
		}
		int strength = 0;
		for (int j = 0; j < 4; j++) {
			if ((mode & 1)>0)
				strength++;
			mode >>>= 1;
		}
		return strength;
	}

}

