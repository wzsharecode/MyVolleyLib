package cn.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class TimeTool {

	/**
	 * 英文简写（默认）如：2010-12-01
	 */
	public static String FORMAT_SHORT = "yyyy-MM-dd";

	/**
	 * 英文全称 如：2010-12-01 23:15:06
	 */
	public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 精确到毫秒的完整时间 如：yyyy-MM-dd HH:mm:ss.S
	 */
	public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

	/**
	 * 中文简写 如：2010年12月01日
	 */
	public static String FORMAT_SHORT_CN = "yyyy年MM月dd日";

	/**
	 * 中文简写 如：2010年12月01日
	 */
	public static String FORMAT_YEAR_MONTH_CN = "yyyy年MM月";

	/**
	 * 中文全称 如：2010年12月01日 23时15分06秒
	 */
	public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	/**
	 * 精确到毫秒的完整中文时间
	 */
	public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	/**
	 * 功能描述：返回年
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getYear(String seconds) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(seconds));
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * 功能描述：返回月
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回月份
	 */
	public static int getMonth(String seconds) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(seconds));
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 功能描述：返回日
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回日份
	 */
	public static int getDay(String seconds) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(seconds));
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 功能描述：返回小
	 * 
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(String seconds) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(seconds));
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 功能描述：返回分
	 * 
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(String seconds) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(seconds));
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 * 
	 * @param date
	 *            Date 日期
	 * @return 返回秒钟
	 */
	public static int getSecond(String seconds) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(seconds));
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 功能描述：返回毫
	 * 
	 * @param date
	 *            日期
	 * @return 返回毫
	 */
	public static long getMillis(String seconds) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return 0;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(seconds));
		return calendar.getTimeInMillis();
	}

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds
	 *            精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 字符串转换 Date
	 * 
	 * @param seconds
	 *            时间戳
	 * @return
	 */
	private static Date getDate(String seconds) {

		// 时间戳转化为Sting或Date
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_LONG);
		String d = sdf.format(new Date(Long.valueOf(seconds + "000")));
		Date date = null;
		try {
			date = sdf.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 获取当前 时间戳
	 * 
	 * @return
	 */
	public static String getSystemTime(){
		
		Long tsLong = System.currentTimeMillis() / 1000;
		String timestamp = tsLong.toString();
		return timestamp;
	}
}
