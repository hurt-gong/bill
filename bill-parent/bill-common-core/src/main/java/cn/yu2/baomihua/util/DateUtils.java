package cn.yu2.baomihua.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {

	private final static long minute = 60 * 1000;// 1分钟

	private final static long hour = 60 * minute;// 1小时

	private final static long day = 24 * hour;// 1天

	private final static long month = 31 * day;// 月

	private final static long year = 12 * month;// 年


	/**
	 * 根据日期获取月-日的字符串
	 * 
	 * <p>
	 *
	 * @param date
	 * @return
	 */
	public static String getMonthDay( Date date ) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1 + "-" + calendar.get(Calendar.DATE);
	}


	/**
	 * 生成指定两个日期之间的所有日期list
	 * <p>
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static List<Date> generateDate( Date startDate, Date endDate ) throws Exception {
		if ( startDate.after(endDate) ) {
			throw new Exception("开始时间应该在结束时间之后");
		}
		Long spi = endDate.getTime() - startDate.getTime();
		Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

		List<Date> dateList = new ArrayList<Date>();
		dateList.add(endDate);
		for ( int i = 1 ; i <= step ; i++ ) {
			dateList.add(new Date(dateList.get(i - 1).getTime() - (24 * 60 * 60 * 1000)));// 比上一天减一
		}
		return dateList;
	}


	public static Date addCalendarDate( int amount ) {
		Calendar startCal = Calendar.getInstance();
		startCal.add(Calendar.DATE, amount);
		return parseyyyyMMddDate(new SimpleDateFormat("yyyy-MM-dd").format(startCal.getTime()));
	}


	/**
	 * Parse date like "yyyy-MM-dd".
	 */
	public static Date parseyyyyMMddDate( String d ) {
		d = "".equals(d) ? new SimpleDateFormat("yyyy-MM-dd").format(new Date()) : d;
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(d);
		} catch ( Exception e ) {}
		return null;
	}


	public static String getTimeFormatText( Date date ) {
		if ( date == null ) {
			return null;
		}
		long diff = new Date().getTime() - date.getTime();
		long r = 0;
		if ( diff > year ) {
			r = diff / year;
			return r + "年前";
		}
		if ( diff > month ) {
			r = diff / month;
			return r + "个月前";
		}
		if ( diff > day ) {
			r = diff / day;
			return r + "天前";
		}
		if ( diff > hour ) {
			r = diff / hour;
			return r + "个小时前";
		}
		if ( diff > minute ) {
			r = diff / minute;
			return r + "分钟前";
		}
		return "刚刚";
	}


	/**
	  * 得到本周周一
	  * 
	  * @return yyyy-MM-dd
	  */
	public static Date getMondayOfThisWeek() {
		Calendar c = Calendar.getInstance();
		int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if ( day_of_week == 0 ) day_of_week = 7;
		c.add(Calendar.DATE, -day_of_week + 1);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
		return parseyyyyMMddDate(date);
	}
	
	/**
	 * 获取当前时间
	 * <p>Title: getCurrentDateTime</p>  
	 * <p>Description: </p>  
	 * @param _dtFormat
	 * @return
	 */
	public static String getCurrentDateTime(String _dtFormat) {
		String currentdatetime = "";
		try {
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat dtFormat = new SimpleDateFormat(_dtFormat);
			currentdatetime = dtFormat.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentdatetime;
	}

}
