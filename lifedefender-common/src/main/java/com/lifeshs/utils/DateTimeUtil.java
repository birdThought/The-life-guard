package com.lifeshs.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 *  版权归   gang of three
 *  @TODO 类说明     
 *  @author duosheng.mo  
 *  @DateTime 2015-7-2 下午09:22:39
 *  @version V1.0
 */
public class DateTimeUtil {

	private static final int MAJOR_VERSION = 1;
	private static final int MINOR_VERSION = 0;
	private static final int REVISION_VERSION = 1;
	private static final String showFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String storeFormat = "yyyyMMddHHmmss";
	private static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss_en = new SimpleDateFormat(
			showFormat);
	private static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat(
			storeFormat);
	private static final SimpleDateFormat yyyy_MM_dd_HH_mm_ss_zh = new SimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分ss秒");
	private static final SimpleDateFormat yyyy_MM_dd_zh = new SimpleDateFormat(
			"yyyy年MM月dd日");
	private static final SimpleDateFormat yyyy_MM_ddHHmmss = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat(
			"yyyy/MM/dd");
	private static final SimpleDateFormat MM_ddHHmm = new SimpleDateFormat(
			"MM-dd HH:mm");
	private static final SimpleDateFormat yyyyMM_en = new SimpleDateFormat(
			"yyyyMM");
	private static final SimpleDateFormat yyyy = new SimpleDateFormat(
			"yyyy");
	private static final SimpleDateFormat HHmmss = new SimpleDateFormat(
			"HH:mm:ss");
	private static final SimpleDateFormat MM = new SimpleDateFormat(
			"MM");
	private static final SimpleDateFormat yyyyMMdd_en = new SimpleDateFormat(
	"yyyyMMdd");
	private static final SimpleDateFormat HHmm = new SimpleDateFormat(
	"HH:mm");
	private static final SimpleDateFormat yyyyMM = new SimpleDateFormat(
	"yyyy/MM");
	private static final SimpleDateFormat yyyy_MM = new SimpleDateFormat(
	"yyyy-MM");
	private static final SimpleDateFormat solrFormatter = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm s.SSS", Locale.US);
	
	private static final SimpleDateFormat MM_dd = new SimpleDateFormat("MM-dd");
	
	public static TimeZone UTC = TimeZone.getDefault();

	public DateTimeUtil() {
	}

	public static String getNow() {
		return yyyyMMddHHmmss.format(new Date());
	}

	public static String getRelativeDate(int days) {
		Calendar c = Calendar.getInstance();
		c.set(5, c.get(5) + days);
		StringBuffer sb = new StringBuffer(17);
		sb.append(c.get(1));
		int tmp[] = { c.get(2) + 1, c.get(5), c.get(11), c.get(12), c.get(13),
				c.get(14) };
		for (int i = 0; i < tmp.length - 1; i++)
			sb.append(tmp[i] >= 10 ? "" : "0").append(tmp[i]);

		if (tmp[tmp.length - 1] < 10)
			sb.append("0");
		if (tmp[tmp.length - 1] < 100)
			sb.append("0");
		sb.append(tmp[tmp.length - 1]);
		return sb.toString();
	}

	public static String getNow(String string) {
		return (new SimpleDateFormat(string)).format(new Date());
	}

	public static String getDisplayTime(long time) {
		return yyyy_MM_dd_HH_mm_ss_en.format(new Date(time));
	}

	public static String getDisplayTime(long time, String string) {
		return (new SimpleDateFormat(string)).format(new Date(time));
	}

	public static String getShowFormat(String time) {
		try {
			if (time == null || time.equals(""))
				time = yyyy_MM_dd_HH_mm_ss_en.format(new Date());
			else
				time = yyyy_MM_dd_HH_mm_ss_en.format(yyyyMMddHHmmss.parse(time));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
	
	public static Date strFormatDate(String time) {
		try {
			return yyyyMMddHHmmss.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static Date strFormatDate2(String dateTime) {
		try {
			return yyyy_MM_dd_HH_mm_ss_en.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	
	public static Date strFormatDate3(String dateTime) {
		try {
			return yyyy_MM_dd.parse(dateTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static long compare(String t1, String t2) {
		return Long.valueOf(t1).longValue() - Long.valueOf(t2).longValue();
	}

	public static String getYear(String time) {
		return time.substring(0, 4);
	}

	public static String getMonth(String time) {
		return time.substring(4, 6);
	}

	public static String getDate(String time) {
		return time.substring(6, 8);
	}

	public static String getHour(String time) {
		return time.substring(8, 10);
	}

	public static String getMinute(String time) {
		return time.substring(10, 12);
	}

	public static String getSecond(String time) {
		return time.substring(12, 14);
	}

	public static int getMilliSencond(String time) {
		return Integer.valueOf(time.substring(14, 17)).intValue();
	}
	
	public static long getTimeStamp(String time) throws ParseException {
		return yyyyMMddHHmmss.parse(time).getTime();
	}

	public static String yyyy_MM_dd_HH_mm_ss_zh(Date myDate) {
		return yyyy_MM_dd_HH_mm_ss_zh.format(myDate);
	}

	public static String yyyy_MM_dd_zh(Date myDate) {
		return yyyy_MM_dd_zh.format(myDate);
	}

	public static String yyyy_MM_ddHHmmss(Date myDate) {
		return yyyy_MM_ddHHmmss.format(myDate);
	}

	public static String yyyy_MM_dd(Date myDate) {
		return yyyy_MM_dd.format(myDate);
	}

	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:42:22
	 *  @serverCode yyyy/MM/dd
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String yyyyMMdd(Date myDate) {
		return yyyyMMdd.format(myDate);
	}

	public static String MM_ddHHmm(Date myDate) {
		return MM_ddHHmm.format(myDate);
	}

	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:43:00
	 *  @serverCode yyyyMM
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String yyyyMM_en(Date myDate) {
		return yyyyMM_en.format(myDate);
	}
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:43:21
	 *  @serverCode yyyy
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String yyyy(Date myDate) {
		return yyyy.format(myDate);
	}
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:43:47
	 *  @serverCode HH:mm:ss
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String HHmmss(Date myDate) {
		return HHmmss.format(myDate);
	}
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:44:09
	 *  @serverCode MM
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String MM(Date myDate) {
		return MM.format(myDate);
	}
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:45:02
	 *  @serverCode yyyyMMdd
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String yyyyMMdd_en(Date myDate) {
		return yyyyMMdd_en.format(myDate);
	}
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:45:25
	 *  @serverCode HHmm
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String HHmm(Date myDate) {
		return HHmm.format(myDate);
	}
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:45:50
	 *  @serverCode yyyy/MM
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String yyyyMM(Date myDate) {
		return yyyyMM.format(myDate);
	}
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午9:47:28
	 *  @serverCode yyyy-MM
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String yyyy_MM(Date myDate) {
		return yyyy_MM.format(myDate);
	}
	
	/**
	 *  @author yuhang.weng 
	 *	@DateTime 2016年6月18日 上午9:30:55
	 *  @serverComment MM-dd
	 *
	 *  @param myDate
	 *  @return
	 */
	public static String MM_DD(Date myDate) {
		return MM_dd.format(myDate);
	}
	
	public static String solrFormatter(long d) {
		Date myDate = new java.util.Date(d);
		solrFormatter.setTimeZone(UTC);
		return solrFormatter.format(myDate);
	}

	public static long getLongTime(String time) {
		try {
			return yyyy_MM_dd_HH_mm_ss_en.parse(time).getTime();
		} catch (ParseException ex) {
			return 0L;
		}
	}

	public static long getDateTime(String time) {
		try {
			return yyyy_MM_ddHHmmss.parse(time).getTime();
		} catch (ParseException ex) {
			return 0L;
		}
	}
	
	public static long getTime(String time) {
		try {
			return yyyy_MM_dd.parse(time).getTime();
		} catch (ParseException ex) {
			return 0L;
		}
	}
	
	public static String getyyyyMMdd(String time){
		try {
			long l4 = yyyy_MM_dd.parse(time).getTime();
			return yyyy_MM_dd.format(new Date(l4));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static long getTime(String time, String flag){
		StringBuffer sb = new StringBuffer();
		sb.append(time);
		if(flag.equalsIgnoreCase("start")){
			sb.append(" ").append("00:00:00");
		}else if(flag.equalsIgnoreCase("end")){
			sb.append(" ").append("23:59:59");
		}
		return getLongTime(sb.toString());
	}
	
	public static String getTimeDate(String time, String flag){
		StringBuffer sb = new StringBuffer();
		sb.append(time);
		if(flag.equalsIgnoreCase("start")){
			sb.append(" ").append("00:00:00");
		}else if(flag.equalsIgnoreCase("end")){
			sb.append(" ").append("23:59:59");
		}
		return sb.toString();
	}
	
	public static long getYearTime(String time, String flag){
		StringBuffer sb = new StringBuffer();
		sb.append(time);
		if(flag.equalsIgnoreCase("start")){
			sb.append("-01-01 00:00:00");
		}else if(flag.equalsIgnoreCase("end")){
			sb.append("-12-30 23:59:59");
		}
		return getLongTime(sb.toString());
	}
	/**  
	 * 得到本月的第一天  
	 * @return  
	 */
	public static long getMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime().getTime();
	}

	public static long getMonthFirstDay(long l) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(l));
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime().getTime();
	}
	/**
	 * 取某个时间后某天的时间
	 * @param strTime 某个时间
	 * @param i 后几天数（向前为负数）
	 * @return
	 */
	 public static String printNextTime(String strTime,int i){ 
         Calendar cal = Calendar.getInstance(); 
         Date date = new Date(); 
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			date = yyyy_MM_dd_HH_mm_ss_zh.parse(strTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         cal.setTime(date); 
         cal.add(cal.DATE, i); 
         //System.out.println("下一天的时间是：" + formatter3.format(cal.getTime())); 
         return yyyy_MM_ddHHmmss.format(cal.getTime());
        
     } 
	 
	 public static String printNextTime2(String strTime,int i){
         Calendar cal = Calendar.getInstance(); 
         Date date = new Date(); 
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			date = yyyy_MM_ddHHmmss.parse(strTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         cal.setTime(date); 
         cal.add(cal.DATE, i); 
         //System.out.println("下一天的时间是：" + formatter3.format(cal.getTime())); 
         return yyyy_MM_dd.format(cal.getTime());
        
     } 
	 
	 


	/**  
	 * 得到本月的最后一天  
	 *   
	 * @return  
	 */
	public static long getMonthLastDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime().getTime();
	}

	public static long getMonthLastDay(long l) {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(l));
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime().getTime();
	}
	/**
	 * 返回某月多少天
	 * @param strDate（yyyyMM）
	 * @return
	 * @throws ParseException
	 */
	public static int getMonthDay(String strDate) throws ParseException{
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyyMM");     
		Calendar   calendar   =   new   GregorianCalendar();      
		Date date = sdf.parse(strDate);
		calendar.setTime(date);      
		int   day   =   calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return day;
	}
	public static String getVersion() {
		return "1.0.1";
	}

	public static int getMajor() {
		return MAJOR_VERSION;
	}

	public static int getMinor() {
		return MINOR_VERSION;
	}

	public static int getRevision() {
		return REVISION_VERSION;
	}

	public static String getSomeDay(String source,int day){
		 String pattern = "yyyy-MM-dd";
		 SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		 Date date=null;
		 try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 Calendar c = Calendar.getInstance();
		 c.setTime(date);
		 c.add(Calendar.DAY_OF_YEAR, day);
		 Date today = c.getTime();
		return today.toLocaleString();
	}
	
	/**
	 *  @author duosheng.mo 
	 *	@DateTime 2016年6月13日 上午10:02:26
	 *  @serverCode 是否在时间内 
	 *
	 *  @param pushTime_  (HH:ss)
	 *  @param startTime_ (HH:ss)
	 *  @param endTime_ (HH:ss)
	 *  @return
	 */
	public static boolean inTime(String pushTime_ ,String startTime_, String endTime_){
		boolean bool = false;
		try {
			Date pushTime = HHmmss.parse(pushTime_);
			Date startTime = HHmmss.parse(startTime_);
			Date endTime = HHmmss.parse(endTime_);
			if(pushTime.after(startTime) && pushTime.before(endTime)){
				bool = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			bool = false;
		}
		return bool;
	}

	public static Date getDBDate() {
		return new java.sql.Date(new Date().getTime());
	}
}
