package com.peas.springboot.thread.pool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	/**
	 * 一天的毫秒数
	 */
	public static final long ONE_DAY = 24*60*60*1000;
	
	/**
	 * 格式化成指定的格式
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 格式化成： yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	public static Date getDate() {
		Date currentTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(sdf.format(currentTime));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 格式化成 yyyy-MM-dd HH:mm:ss
	 */
	public static String formatTime(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 日期相加减
	 * 
	 * @param amount
	 *            天数
	 * @return
	 */
	public static Date add(int amount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, amount);

		return cal.getTime();
	}

	/**
	 * 将当前日期相加减
	 * 
	 * @param date
	 *            当前日期
	 * @param amount
	 *            天数
	 * @return
	 */
	public static Date add(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, amount);

		return cal.getTime();
	}

	/**
	 * 转化成日期
	 * 
	 * @param text
	 * @param pattern
	 * @return
	 */
	public static Date parse(String text, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(text);
		} catch (ParseException e) {
			throw new RuntimeException(String.format("%s to %s error", text,
					pattern), e);
		}
	}

	/**
	 * 计算日期间的间隔天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int betweenDays(Date startDate, Date endDate) {
		startDate = parse(format(startDate), "yyyy-MM-dd");
		endDate = parse(format(endDate), "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();    
        cal.setTime(startDate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(endDate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1) / (1000 * 3600 * 24);  
            
       return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 转化成日期
	 * 
	 * @param mill
	 * @param pattern
	 * @return
	 */
	public static String long2dateStr(long timeMillis, String pattern) {
		return format(new Date(timeMillis), pattern);
	}

	/**
	 * 将字符串格式yyyyMMdd的字符串转为日期，格式"yyyy-MM-dd"
	 *
	 * @param date
	 *            日期字符串
	 * @return 返回格式化的日期
	 * @throws ParseException
	 *             分析时意外地出现了错误异常
	 */
	public static String strToDateFormat(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		formatter.setLenient(false);
		Date newDate = formatter.parse(date);
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(newDate);
	}

	
	/**
	 * 将日期增加一天
	 *
	 * @param date日期
	 * @return 返回增加一天后的日期
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, +1);// +1今天的时间加一天
		date = calendar.getTime();
		return date;
	}

	/**
	 * 计算日期间的间隔小时-活期专用计算公式
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long betweenHour(String startDate, Date endDate) {
		return (parse(startDate, "yyyy-MM-dd").getTime() - endDate.getTime())
				/ (60 * 60 * 1000);
	}
	
	/**
    * 日期加n年
    * @param date 日期
    * @param n 年数
    * @return
    */
    public static Date addByYear(Date date, int n) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.YEAR, n);// 日期加n年
	    return cal.getTime();
    }
    
    /**
     * 判断一个时间点距离今天当前时刻是否超过n天
     * @param date 时间点
     * @param n 天数
     * @return true是超过，false是未超过
     */
    public static boolean isTimeout(Date date, int n) {
    	Date now = new Date();
    	return (now.getTime() - date.getTime()) >= n * ONE_DAY;
    }
    
    /**
     * 判断该时间点是否小于当前时间（注：按天比较，不计时分秒）
     * @param date 需要比较的时间
     * @return true表示小于当前时间
     */
    public static boolean isLessThanCurrentTime(Date date) {
    	Date nowDate = new Date();
		String nowDateStr = format(nowDate);
		String dateStr = format(date);
		nowDate = parse(nowDateStr, "yyyy-MM-dd");
		date = parse(dateStr, "yyyy-MM-dd");
		return date.getTime() < nowDate.getTime();
    }
    /**
     * 判断该时间点是否小于等于当前时间（注：按天比较，不计时分秒）
     * @param date 需要比较的时间
     * @return true表示小于等于当前时间
     */
    public static boolean isLessOrEqualToCurrentTime(Date date) {
    	Date nowDate = new Date();
		String nowDateStr = format(nowDate);
		String dateStr = format(date);
		nowDate = parse(nowDateStr, "yyyy-MM-dd");
		date = parse(dateStr, "yyyy-MM-dd");
		return date.getTime() <= nowDate.getTime();
    }
    /**
     * 时间日加减
     * @param date
     * @param days
     * @return
     */
    public static Date getYesterdate(Date date,int days){
    	Calendar ca = Calendar.getInstance();
    	ca.setTime(date);
    	ca.add(Calendar.DATE, days);
    	return ca.getTime();
    }

	public static void main(String[] args) throws ParseException {
		Date date=null;
		String time="2019-10-16 10:30:10";
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date=formatter.parse(time);
		Date endDate = DateUtils.add(date, 90);
		boolean lessOrEqualToCurrentTime = DateUtils.isLessOrEqualToCurrentTime(endDate);
		System.out.println(lessOrEqualToCurrentTime);
	}
}
