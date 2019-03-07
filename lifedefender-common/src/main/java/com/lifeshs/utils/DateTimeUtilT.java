package com.lifeshs.utils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 *  时间工具类，线程安全
 *  @author yuhang.weng  
 *  @DateTime 2016年8月30日 下午12:07:31
 */
public class DateTimeUtilT {
    
    private static final Logger logger = Logger.getLogger(DateTimeUtilT.class);

    private static final String DATE = "yyyy-MM-dd";
    
    private static final String DATE_CN = "yyyy年MM月dd日";
    
    private static final String DATE_WITHOUT_FORM = "yyyyMMdd";
    
    private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    
    private static final String DATE_TIME_WITHOUT_FORM = "yyyyMMddHHmmss";
    
    private static final String TIME = "HH:mm:ss";
    
    private static final String TIME_WITHOUT_FORM = "HHmmss";
    
    private static final String MONTH_DAY = "MM-dd";
    
    private static final String HOUR_MIN = "HH:mm";
    
    private static final String DATE_GST = "yyyy-MM-dd'T'HH:mm:ss.SSS Z";
    
    private static final String YEAR_MONTH_WITHOUT_FORM = "yyyyMM";
    
    private static ThreadLocal<SimpleDateFormat> t_Date = new ThreadLocal<>();
    private static SimpleDateFormat format_Date() {
        SimpleDateFormat format = t_Date.get();
        if(format == null) {
            format = new SimpleDateFormat(DATE);
            t_Date.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 yyyy-MM-dd格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午10:47:41
     *
     *  @param date
     *  @return
     */
    public static String date(Date date) {
        return format_Date().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为yyyy-MM-dd
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午10:50:05
     *
     *  @param source
     *  @return
     */
    public static Date date(String source) {
        Date date = null;
        try {
            date = format_Date().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_DateCN = new ThreadLocal<>();
    private static SimpleDateFormat format_DateCN() {
        SimpleDateFormat format = t_DateCN.get();
        if(format == null) {
            format = new SimpleDateFormat(DATE_CN);
            t_DateCN.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 yyyy年MM月dd日格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午1:51:30
     *
     *  @param date
     *  @return
     */
    public static String dateCN(Date date) {
        return format_DateCN().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为yyyy年MM月dd日
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午1:51:50
     *
     *  @param source
     *  @return
     */
    public static Date dateCN(String source) {
        Date date = null;
        try {
            date = format_DateCN().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_DateWithoutForm = new ThreadLocal<>();
    private static SimpleDateFormat format_DateWithoutForm() {
        SimpleDateFormat format = t_DateWithoutForm.get();
        if(format == null) {
            format = new SimpleDateFormat(DATE_WITHOUT_FORM);
            t_DateWithoutForm.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 yyyyMMdd格式类型的字符串(不包含连字符)
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午11:09:04
     *
     *  @param date
     *  @return
     */
    public static String dateWithoutForm(Date date) {
        return format_DateWithoutForm().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为yyyyMMdd
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午11:09:24
     *
     *  @param source
     *  @return
     */
    public static Date dateWithoutForm(String source) {
        Date date = null;
        try {
            date = format_DateWithoutForm().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_DateTime = new ThreadLocal<>();
    private static SimpleDateFormat format_DateTime() {
        SimpleDateFormat format = t_DateTime.get();
        if(format == null) {
            format = new SimpleDateFormat(DATE_TIME);
            t_DateTime.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 yyyy-MM-dd HH:mm:ss格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午11:09:31
     *
     *  @param date
     *  @return
     */
    public static String dateTime(Date date) {
        return format_DateTime().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为yyyy-MM-dd HH:mm:ss
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午11:09:51
     *
     *  @param source
     *  @return
     */
    public static Date dateTime(String source) {
        Date date = null;
        try {
            date = format_DateTime().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_DateTimeWithoutForm = new ThreadLocal<>();
    private static SimpleDateFormat format_DateTimeWithoutForm() {
        SimpleDateFormat format = t_DateTimeWithoutForm.get();
        if (format == null) {
            format = new SimpleDateFormat(DATE_TIME_WITHOUT_FORM);
            t_DateTimeWithoutForm.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 yyyyMMddHHmmss格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午2:27:58
     *
     *  @param date
     *  @return
     */
    public static String dateTimeWithoutForm(Date date) {
        return format_DateTimeWithoutForm().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为yyyyMMddHHmmss
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午2:27:55
     *
     *  @param source
     *  @return
     */
    public static Date dateTimeWithoutForm(String source) {
        Date date = null;
        try {
            date = format_DateTimeWithoutForm().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_MonthDay = new ThreadLocal<>();
    private static SimpleDateFormat format_MonthDay() {
        SimpleDateFormat format = t_MonthDay.get();
        if(format == null) {
            format = new SimpleDateFormat(MONTH_DAY);
            t_MonthDay.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 MM-dd格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午3:15:18
     *
     *  @param date
     *  @return
     */
    public static String monthDay(Date date) {
        return format_MonthDay().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为MM-dd
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午3:15:21
     *
     *  @param source
     *  @return
     */
    public static Date monthDay(String source) {
        Date date = null;
        try {
            date = format_MonthDay().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_HourMin = new ThreadLocal<>();
    private static SimpleDateFormat format_HourMin() {
        SimpleDateFormat format = t_HourMin.get();
        if(format == null) {
            format = new SimpleDateFormat(HOUR_MIN);
            t_HourMin.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 HH:mm格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午11:09:54
     *
     *  @param date
     *  @return
     */
    public static String hourMin(Date date) {
        return format_HourMin().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为HH:mm
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午11:10:06
     *
     *  @param source
     *  @return
     */
    public static Date hourMin(String source) {
        Date date = null;
        try {
            date = format_HourMin().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_Time = new ThreadLocal<>();
    private static SimpleDateFormat format_Time() {
        SimpleDateFormat format = t_Time.get();
        if(format == null) {
            format = new SimpleDateFormat(TIME);
            t_Time.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 HH:mm:ss格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午11:10:11
     *
     *  @param date
     *  @return
     */
    public static String time(Date date) {
        return format_Time().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为HH:mm:ss
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 上午11:10:32
     *
     *  @param source
     *  @return
     */
    public static Date time(String source) {
        Date date = null;
        try {
            date = format_Time().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_TimeWithoutForm = new ThreadLocal<>();
    private static SimpleDateFormat format_TimeWithoutForm() {
        SimpleDateFormat format = t_TimeWithoutForm.get();
        if (format == null) {
            format = new SimpleDateFormat(TIME_WITHOUT_FORM);
            t_TimeWithoutForm.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为 HHmmss格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午4:01:09
     *
     *  @param date
     *  @return
     */
    public static String timeWithoutForm(Date date) {
        return format_TimeWithoutForm().format(date);
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为HHmmss
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午4:01:07
     *
     *  @param source
     *  @return
     */
    public static Date timeWithoutForm(String source) {
        Date date = null;
        try {
            date = format_TimeWithoutForm().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_date_GST = new ThreadLocal<>();
    private static SimpleDateFormat format_dateGst() {
        SimpleDateFormat format = t_date_GST.get();
        if (format == null) {
            format = new SimpleDateFormat(DATE_GST);
            t_date_GST.set(format);
        }
        return format;
    }
    
    private static ThreadLocal<SimpleDateFormat> t_YearMonthWithoutForm = new ThreadLocal<>();
    private static SimpleDateFormat format_YearMonthWithoutForm() {
        SimpleDateFormat format = t_YearMonthWithoutForm.get();
        if (format == null) {
            format = new SimpleDateFormat(YEAR_MONTH_WITHOUT_FORM);
            t_YearMonthWithoutForm.set(format);
        }
        return format;
    }
    
    /**
     *  Date对象转换为yyyyMM格式类型的字符串
     *  @author yuhang.weng 
     *  @DateTime 2017年1月19日 上午11:07:07
     *
     *  @param source
     *  @return
     */
    public static Date yearMonthWithoutForm(String source) {
        Date date = null;
        try {
            date = format_YearMonthWithoutForm().parse(source);
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }
    
    /**
     *  字符串转换为Date对象，要求字符串的格式为yyyyMM
     *  @author yuhang.weng 
     *  @DateTime 2017年1月19日 上午11:07:21
     *
     *  @param date
     *  @return
     */
    public static String yearMonthWithoutForm(Date date) {
        return format_YearMonthWithoutForm().format(date);
    }
    
    /**
     *  GST类型日期转换UTC类型日期
     *  @author yuhang.weng 
     *  @DateTime 2016年10月11日 上午11:48:20
     *
     *  @param gstDate
     *  @return
     */
    public static Date convertGST2UTC(String gstDate) {
        Date date = null;
        try {
            date = format_dateGst().parse(gstDate.replace("Z", " UTC"));
        } catch (ParseException e) {
            logger.error("字符串转换Date对象失败:"+e.getMessage());
        }
        return date;
    }

    /**
     *  计算年龄
     *  @author yuhang.weng 
     *  @DateTime 2016年9月1日 下午1:36:05
     *
     *  @param birthday
     *  @return
     */
    public static Integer calculateAge(Date birthday) {
        
        /*Calendar calendar = Calendar.getInstance();
        int yearNow = calendar.get(Calendar.YEAR);
        
        calendar.setTime(birthday);
        int yearBirth = calendar.get(Calendar.YEAR);
        
        Integer age = yearNow - yearBirth;
        return age;*/
        
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            return 0;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthday);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        age = age > 0 ? age : 0;
        return age;
    }
    
    /**
     *  计算日期间隔
     *  @author yuhang.weng 
     *  @DateTime 2016年10月22日 下午5:15:07
     *
     *  @param start
     *  @param end
     *  @return
     */
    public static int calculateDayInterval(Date start, Date end) {
        long interval = 0;
        
        long diff = end.getTime() - start.getTime();
        interval = diff / (1000 * 60 * 60 * 24);
        
        return (int)interval;
    }
    
    /**
     *  计算秒间隔
     *  @author yuhang.weng 
     *  @DateTime 2016年11月25日 下午4:01:06
     *
     *  @param start
     *  @param end
     *  @return
     */
    public static int calculateSecondInterval(Date start, Date end) {
        long interval = 0;
        long diff = end.getTime() - start.getTime();
        interval = diff / (1000);
        return (int) interval;
    }
    
    /**
     *  @author duosheng.mo 
     *  @DateTime 2016年6月13日 上午10:02:26
     *  @serverCode 是否在时间内 
     *
     *  @param pushTime_  (HH:ss)
     *  @param startTime_ (HH:ss)
     *  @param endTime_ (HH:ss)
     *  @return
     */
    @SuppressWarnings("deprecation")
    public static boolean inTime(Date pushTime ,Time startTime, Time endTime) {

        if(pushTime.getHours() < startTime.getHours() || pushTime.getHours() > endTime.getHours())
            return false;
        if(pushTime.getHours() == startTime.getHours() && pushTime.getMinutes() < startTime.getMinutes())
            return false;
        if(pushTime.getHours() == endTime.getHours() && pushTime.getMinutes() > endTime.getMinutes())
            return false;
//      Date pushTime = timeWithoutForm(pushTime_);
//      Date startTime = timeWithoutForm(startTime_);
//      Date endTime = timeWithoutForm(endTime_);
//      if(pushTime.after(startTime) && pushTime.before(endTime)){
//          bool = true;
//      }
        return true;
    }
    
    /**
     * 判断时间格式是正确，且为"2015-01"格式
     * @author wenxian.cai
     * @DateTime 2016年9月23日下午2:30:13
     * @serverComment 
     * @param 
     */
    public static boolean valiDateTimeWithShortFormat(String timeStr) {
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m-1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * 判断时间格式是正确，且为"2015-01-01"格式
     * @author wenxian.cai
     * @DateTime 2016年9月18日下午7:57:39
     * @serverComment 
     * @param 
     */
    public static boolean valiDateTimeWithFormat(String timeStr) {
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m-1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * 判断时间格式是正确，且为"2015-01-01 09:09:09"格式
     * @author wenxian.cai
     * @DateTime 2016年9月18日下午7:58:42
     * @serverComment 
     * @param 
     */
    public static boolean valiDateTimeWithLongFormat(String timeStr) {
        String format = "((19|20)[0-9]{2})-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01]) "
                + "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";
        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(timeStr);
        if (matcher.matches()) {
            pattern = Pattern.compile("(\\d{4})-(\\d+)-(\\d+).*");
            matcher = pattern.matcher(timeStr);
            if (matcher.matches()) {
                int y = Integer.valueOf(matcher.group(1));
                int m = Integer.valueOf(matcher.group(2));
                int d = Integer.valueOf(matcher.group(3));
                if (d > 28) {
                    Calendar c = Calendar.getInstance();
                    c.set(y, m-1, 1);
                    int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
                    return (lastDay >= d);
                }
            }
            return true;
        }
        return false;
    }
    
    /**
     * Timestamp转String（格式：yyyy-MM-dd）
     * @author wenxian.cai
     * @DateTime 2016年9月19日下午4:47:45
     * @serverComment 
     * @param 
     */
    public static String TimestampToString(Timestamp timestamp) {
        String str = "";   
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
        try {   
            str = sdf.format(timestamp);   
        } catch (Exception e) {   
            e.printStackTrace();   
        } 
        return str;
    }
    
    /**
     * @author wenxian.cai
     * @DateTime 2017年1月5日上午11:55:25
     * @serverComment String转Time
     * @param timestamp: 日期字符串
     * @param format: 转换格式(yyyy-MM-dd,hh:mm:ss ...)
     */
    public static Time StringToTime(String timestamp, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Time time = null;
        try {
            time = new Time(sdf.parse(timestamp).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
    
    /**
     *  为日期增加天数
     *  @author yuhang.weng 
     *  @DateTime 2016年11月9日 下午4:24:04
     *
     *  @param date
     *  @param day
     *  @return
     */
    public static Date addDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    public static void main(String [] agrs){
        Date now = new Date();
        Date end = date("2017-06-21");
        long hour = calculateHourInterval(now, end);
        System.out.println("相差小时:" + hour);
    }
    
    /**
     *  计算两个日期的小时间隔数
     *  @author yuhang.weng 
     *	@DateTime 2017年6月19日 下午3:28:57
     *
     *  @param start 开始时间
     *  @param end 结束时间
     *  @return
     */
    public static long calculateHourInterval(Date start, Date end) {
        long diff = end.getTime() - start.getTime();
        // 计算差多少小时
        long hour = diff / (1000 * 60 * 60);
        return hour;
    }
}
