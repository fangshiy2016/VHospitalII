package com.yuyang.VRHospital.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 处理日期类型的工具类
 */
public class DateUtils {
	
	/** 日期的默认格式，1990-09-22 12:59:59 */
	public static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 日期的默认格式，1990-09-22 12:59 */
	public static final String DATE_PATTERN_MINUTE = "yyyy-MM-dd HH:mm";
    /** 日期格式，19900922*/
    public static final String DATE_PATTERN_NO_APART = "yyyyMMdd";
	/** 日期的月，1990-09 */
	public static final String DATE_PATTERN_MONTH = "yyyy-MM";
	/** 日期的天数，1990-09-22 */
	public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";
	/** 日期的时分，00:00 */
	public static final String TIME_PATTERN = "HH:mm";
	/** 日期的中文格式，1990年09月22日 12点59分59秒 */
	public static final String DATE_PATTERN_CN = "yyyy年MM月dd日 HH点mm分ss秒";

	public static long getCurrentTime(){
		return System.currentTimeMillis();
	}
	/**
	 * 格式化日期对象
	 * @param date		需要格式化的日期
	 * @param pattern	日期的格式：形如：yyyy-MM-dd HH:mm:ss
	 * @return			返回格式化后的日期字符串
	 */
	public static String formatDate(Date date, String pattern){
		if(date == null) return "";
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat(pattern);
	    return formatter.format(date);
	}

	public static String getCurrentDate(String pattern){
		return formatDate(new Date(), pattern);
	}

	/**
	 * 将日期格式化为形如：1990-09-22 的样式
	 * @param date	需要格式化的日期
	 * @return		返回格式化后的日期字符串
	 * @author jiangx
	 */
	public static String formatDay(Date date){
		return formatDate(date, DATE_PATTERN_MONTH);
	}
	
	/**
	 * 从用户输入的日期字符串中构造日期对象
	 * @param dateStr	日期字符串，形如：1990-09
	 * @return			返回构造好的日期对象
	 */
	public static Date generateDate(String dateStr,String formate){
		try {
			return new SimpleDateFormat(formate).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	
	/**
	 * 从用户输入的日期字符串中构造日期对象
	 * @param dateStr	日期字符串，形如：1990-09-22 12:59
	 * @return			返回构造好的日期对象
	 */
	public static Date generateDateToMinuteFrom(String dateStr){
		try {
			return new SimpleDateFormat(DATE_PATTERN_MINUTE).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	/**
	 * 获得目标日期的时间参数
	 * @return	返回目标日期的时间参数，一次为：年、月、日、时、分、秒
	 */
	private static int[] getDateParams(Date date){
		int[] dateParams = new int[6];
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		dateParams[0] = c.get(Calendar.YEAR);
		dateParams[1] = c.get(Calendar.MONTH)+1;
		dateParams[2] = c.get(Calendar.DAY_OF_MONTH);
		dateParams[3] = c.get(Calendar.HOUR_OF_DAY);
		dateParams[4] = c.get(Calendar.MINUTE);
		dateParams[5] = c.get(Calendar.SECOND);
		return dateParams;
	}
	
	/**
	 * 获得目标日期的年份
	 * @param date	目标日期
	 * @return		返回目标日期的年份
	 */
	public static int getYear(Date date){
		return getDateParams(date)[0];
	}
	
	/**
	 * 获得目标日期的月份
	 * @param date	目标日期
	 * @return		返回目标日期的月份
	 */
	public static String getMonth(Date date){
		DecimalFormat format = new DecimalFormat("00");
		return format.format(getDateParams(date)[1]);
	}
	
	/**
	 * 获得目标日期的天数
	 * @param date	目标日期
	 * @return		返回目标日期的天数
	 */
	public static int getDay(Date date){
		return getDateParams(date)[2];
	}
	
	/**
	 * 获得目标日期的小时
	 * @param date	目标日期
	 * @return		返回目标日期的小时
	 */
	public static int getHour(Date date){
		return getDateParams(date)[3];
	}
	
	/**
	 * 获得目标日期的分钟
	 * @param date	目标日期
	 * @return		返回目标日期的分钟
	 */
	public static int getMinute(Date date){
		return getDateParams(date)[4];
	}
	
	/**
	 * 获得目标日期的秒数
	 * @param date	目标日期
	 * @return		返回目标日期的秒数
	 */
	public static int getSecond(Date date){
		return getDateParams(date)[5];
	}
	
	/**
	 * 获得现在的年份
	 * @return		返回现在的年份
	 */
	public static int getYear(){
		return getDateParams(new Date())[0];
	}
	
	/**
	 * 获得现在的月份
	 * @return		返回现在的月份
	 */
	public static String getMonthStr(){
		DecimalFormat format = new DecimalFormat("00");
		return format.format(getDateParams(new Date())[1]);
	}

    /**
     * 获得现在的月份
     * @return
     */
    public static int getMonth(){
        return getDateParams(new Date())[1];
    }
	
	/**
	 * 获得现在的天数
	 * @return		返回现在的天数
	 */
	public static int getDay(){
		return getDateParams(new Date())[2];
	}
	
	/**
	 * 获得现在的小时
	 * @return		返回现在的小时
	 */
	public static int getHour(){
		return getDateParams(new Date())[3];
	}
	
	/**
	 * 获得现在的分钟
	 * @return		返回现在的分钟
	 */
	public static int getMinute(){
		return getDateParams(new Date())[4];
	}
	
	/**
	 * 获得现在的秒数
	 * @return		返回现在的秒数
	 */
	public static int getSecond(){
		return getDateParams(new Date())[5];
	}
	
	/**
     * 获取当前日期是星期几 
     * 
     * @param dt 
     * @return 当前日期是星期几 
     */ 
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt); 
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)  w = 0; 
        return weekDays[w]; 
    }
    
    /**
	 * 是否是今天
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isToday(final Date date) {
	        return isTheDay(date, new Date());
	}
	/**
	 * 是否是指定日期
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static boolean isTheDay(final Date date, final Date day) {
	        return date.getTime() >= dayBegin(day).getTime()
	                        && date.getTime() <= dayEnd(day).getTime();
	}
	/**
	 * 获取指定时间的那天 00:00:00.000 的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date dayBegin(final Date date) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.set(Calendar.HOUR_OF_DAY, 0);
	        c.set(Calendar.MINUTE, 0);
	        c.set(Calendar.SECOND, 0);
	        c.set(Calendar.MILLISECOND, 0);
	        return c.getTime();
	}
	/**
	 * 获取指定时间的那天 23:59:59.999 的时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date dayEnd(final Date date) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.set(Calendar.HOUR_OF_DAY, 23);
	        c.set(Calendar.MINUTE, 59);
	        c.set(Calendar.SECOND, 59);
	        c.set(Calendar.MILLISECOND, 999);
	        return c.getTime();
	}


	public static boolean limitTime(long bt,long limitTime) {
		boolean limit = false;
		if(bt - getCurrentTime() > limitTime){
			limit = true;
		}
		return limit;
	}


}
