package com.ecxls.integral.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.ecxls.integral.util.exception.EcxlsException;

/**
 * <p>ClassName: DateTimeAbstract<p>
 * <p>Description: 时间处理类<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年5月31日 下午2:47:18
 */
public class DateTimeUtil {

	// 时间字符串格式
	private SimpleDateFormat format = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	// 日历类
	private Calendar calendar;

	/**
	 * 
	 * 初始化calendar为当前系统时间
	 */
	public DateTimeUtil() {
		calendar = Calendar.getInstance();
	}

	/**
	 * 
	 * 带参构造方法
	 * @param date
	 *            时间字符串
	 * @throws EcxlsException
	 */
	public DateTimeUtil(String date) throws EcxlsException {
		try {
			calendar = Calendar.getInstance();
			calendar.setTime(format.parse(date));
		} catch (ParseException e) {
			throw new EcxlsException(e);
		}
	}

	/**
	 * 带参构造方法
	 * @param date
	 *            时间对象
	 */
	public DateTimeUtil(Date date) {
		calendar = Calendar.getInstance();
		calendar.setTime(date);
	}

	/**
	 * 带参构造方法
	 * @param date
	 *            日历对象
	 */
	public DateTimeUtil(Calendar date) {
		this.calendar = date;
	}

	/**
	 * 带参构造方法
	 * @param date
	 *            long型数值
	 */
	public DateTimeUtil(long date) {
		calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date);
	}

	/**
	 * 
	 * 带参构造方法
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param day
	 *            日
	 */
	public DateTimeUtil(int year, int month, int day) {
		calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
	}

	/**
	 * 返回当前日期，只返回日期（年月日）
	 * @author chenluning
	 * @return String
	 */
	public String getDateOnly() throws EcxlsException {

		return format.format(calendar.getTime()).substring(0, 10);
	}

	/**
	 * 返回当前时间，只返回时间（时分秒）
	 * @author chenluning
	 * @return String
	 * @throws EcxlsException
	 */
	public String getTimeOnly() throws EcxlsException {

		return format.format(calendar.getTime()).substring(11);
	}

	/**
	 * 返回格林尼治标准时间字符串
	 * @author chenluning
	 * @return String
	 */
	public String getGMTTime() throws EcxlsException {
		Calendar c = Calendar.getInstance();
		// SimpleDateFormat format = new SimpleDateFormat();
		format.setTimeZone(TimeZone.getTimeZone("GMT")); // 设置时区为GMT
		return format.format(c.getTime());
	}

	/**
	 * 返回当前系统本地时间字符串
	 * @author chenluning
	 * @return String
	 * @throws EcxlsException
	 */
	public String getLocalTime() throws EcxlsException {
		Calendar c = Calendar.getInstance();
		return format.format(c.getTime());
	}

	/**
	 * 返回当前系统本地日期
	 * @author chenluning
	 * @return Date
	 * @throws EcxlsException
	 */
	public Date getLocalDate() throws EcxlsException {
		Calendar c = Calendar.getInstance();
		return c.getTime();
	}

	/**
	 * 返回当前类，日历属性的yyyy-MM-dd HH:mm:ss格式字符串
	 * @return
	 * @throws EcxlsException
	 */
	public String getTimeToStr() throws EcxlsException {
		return format.format(calendar.getTime());
	}

	/**
	 * 根据参数设置当前类日历属性并返回其yyyy-MM-dd HH:mm:ss格式字符串
	 * @param date
	 * @return
	 * @throws EcxlsException
	 */
	public String getTimeToStr(Date date) throws EcxlsException {
		calendar.setTime(date);
		return format.format(calendar.getTime());
	}

	/**
	 * 返回当前类日历属性的时间
	 * @return
	 * @throws EcxlsException
	 */
	public Date getStrToDate() throws EcxlsException {
		return calendar.getTime();
	}

	/**
	 * 根据参数设置当前类日历属性并返回其时间
	 * @param str
	 *            yyyy-MM-dd HH:mm:ss格式字符串
	 * @return
	 * @throws EcxlsException
	 */
	public Date getStrToDate(String str) throws EcxlsException {
		try {
			calendar.setTime(format.parse(str));
		} catch (ParseException e) {
			throw new EcxlsException(e);
		}
		return calendar.getTime();
	}

	/**
	 * 返回当前系统本地时间戳格式
	 * @author chenluning
	 * @return long
	 * @throws EcxlsException
	 */
	public long getLocalTimeMillis() throws EcxlsException {
		Calendar c = Calendar.getInstance();
		return c.getTimeInMillis();
	}

	/**
	 * 返回当前系统本地日期（Calendar对象）
	 * @author chenluning
	 * @return Calendar
	 * @throws EcxlsException
	 */
	public Calendar getLocalCalendar() throws EcxlsException {
		Calendar c = Calendar.getInstance();
		return c;
	}

	/**
	 * 返回本地当前时间与 GMT时间之间的时间差
	 * @author chenluning
	 * @return int
	 * @throws EcxlsException
	 */
	public int getTimeZone() throws EcxlsException {

		Calendar cal = Calendar.getInstance();
		return cal.getTimeZone().getOffset(cal.getTimeInMillis());
	}

	/**
	 * 把时间戳转换为字符串
	 * @author chenluning
	 * @return String
	 * @throws EcxlsException
	 */
	public String getTimeMillisToString() throws EcxlsException {

		return String.valueOf(calendar.getTimeInMillis());
	}

	/**
	 * 根据参数时间重新设置默认时间变量值
	 * @author chenluning
	 * @param dateTime
	 * @throws EcxlsException
	 */
	public void setDateTime(String dateTime) throws EcxlsException {
		try {
			calendar.setTime(format.parse(dateTime));
		} catch (ParseException e) {
			throw new EcxlsException(e);
		}
	}

	/**
	 * 根据参数时间重新设置默认时间变量值
	 * @author chenluning
	 * @param dateTime
	 * @throws EcxlsException
	 */
	public void setDateTime(Date dateTime) throws EcxlsException {

		calendar.setTime(dateTime);
	}

	/**
	 * 根据参数时间重新设置默认时间变量值
	 * @author chenluning
	 * @param dateTime
	 * @throws EcxlsException
	 */
	public void setDateTime(Calendar dateTime) throws EcxlsException {
		this.calendar = dateTime;
	}

	/**
	 * 根据参数时间重新设置默认时间变量值
	 * @author chenluning
	 * @param dateTime
	 * @throws EcxlsException
	 */
	public void setDateTime(long dateTime) throws EcxlsException {
		calendar.setTimeInMillis(dateTime);
	}

	/**
	 * 根据参数时间重新设置默认时间变量值
	 * @author chenluning
	 * @param year
	 * @param month
	 * @param day
	 * @throws EcxlsException
	 */
	public void setDateTime(int year, int month, int day) throws EcxlsException {

		calendar.set(year, month - 1, day);
	}

	/**
	 * 设置默认时间变量值为系统当前时间
	 * @author chenluning
	 * @throws EcxlsException
	 */
	public void setNow() throws EcxlsException {
		calendar = Calendar.getInstance();
	}

	/**
	 * 把时间根据指定格式转换
	 * @author chenluning
	 * @param formatter
	 * @throws EcxlsException
	 */
	public void setFormatter(String formatter) throws EcxlsException {

		format.applyPattern(formatter);
	}

	/**
	 * 把时间根据指定格式转换
	 * @author chenluning
	 * @param formatter
	 * @throws EcxlsException
	 */
	public void setFormatter(SimpleDateFormat formatter) throws EcxlsException {

		format = formatter;
	}

	/**
	 * @param cal
	 */
	private void setTimeZero(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
	}

	/**
	 * 返回当前周的第一天日期
	 * @author chenluning
	 * @return Date
	 * @throws EcxlsException
	 */
	public Date getFirstWeekDate() throws EcxlsException {
		Calendar cal = (Calendar) calendar.clone();
		// 每周第一天是 星期日
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		setTimeZero(cal);
		return cal.getTime();
	}

	/**
	 * 返回当前周的最后一天日期
	 * @author chenluning
	 * @return Date
	 * @throws EcxlsException
	 */
	public Date getLastWeekDate() throws EcxlsException {
		Calendar cal = (Calendar) calendar.clone();
		// 每周最后一天是 星期六
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		setTimeZero(cal);
		return cal.getTime();
	}

	/**
	 * 返回当前月的第一天日期
	 * @author chenluning
	 * @return Date
	 * @throws EcxlsException
	 */
	public Date getFirstMonthDate() throws EcxlsException {
		Calendar cal = (Calendar) calendar.clone();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setTimeZero(cal);
		return cal.getTime();
	}

	/**
	 * 返回当前月的最后一天日期
	 * @author chenluning
	 * @return Date
	 * @throws EcxlsException
	 */
	public Date getLastMonthDate() throws EcxlsException {
		Calendar cal = (Calendar) calendar.clone();
		cal.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		setTimeZero(cal);
		return cal.getTime();
	}

	/**
	 * 返回当前年的第一天日期
	 * @author chenluning
	 * @return Date
	 * @throws EcxlsException
	 */
	public Date getFirstYearDate() throws EcxlsException {
		Calendar cal = (Calendar) calendar.clone();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		setTimeZero(cal);
		return cal.getTime();
	}

	/**
	 * 返回当前年的最后一天日期
	 * @author chenluning
	 * @return Date
	 * @throws EcxlsException
	 */
	public Date getLastYearDate() throws EcxlsException {
		Calendar cal = (Calendar) calendar.clone();
		cal.set(Calendar.DAY_OF_YEAR, cal
				.getActualMaximum(Calendar.DAY_OF_YEAR));
		setTimeZero(cal);
		return cal.getTime();
	}

	/**
	 * 增加一个日期时间的天数
	 * @author chenluning
	 * @param day
	 * @throws EcxlsException
	 */
	public void addJustDay(int day) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (day < 0) {
			day = 0;
		}
		calendar.add(Calendar.DATE, day);
	}

	/**
	 * 增加一个日期时间的小时
	 * @author chenluning
	 * @param hour
	 */
	public void addJustHour(int hour) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (hour < 0) {
			hour = 0;
		}
		calendar.add(Calendar.HOUR_OF_DAY, hour);
	}

	/**
	 * 增加一个日期时间的分钟
	 * @author chenluning
	 * @param minute
	 */
	public void addJustMinute(int minute) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (minute < 0) {
			minute = 0;
		}
		calendar.add(Calendar.MINUTE, minute);
	}

	/**
	 * 增加一个日期时间的月份
	 * @author chenluning
	 * @param month
	 */
	public void addJustMonth(int month) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (month < 0) {
			month = 0;
		}
		calendar.add(Calendar.MONTH, month);
	}

	/**
	 * 增加一个日期时间的秒数
	 * @author chenluning
	 * @param second
	 */
	public void addJustSecond(int second) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (second < 0) {
			second = 0;
		}
		calendar.add(Calendar.SECOND, second);
	}

	/**
	 * 增加一个日期时间的年份数
	 * @author chenluning
	 * @param year
	 */
	public void addJustYear(int year) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (year < 0) {
			year = 0;
		}
		calendar.add(Calendar.YEAR, year);
	}

	/**
	 * 减少一个日期时间的天数
	 * @author chenluning
	 * @param day
	 * @throws EcxlsException
	 */
	public void subtractJustDay(int day) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (day < 0) {
			day = 0;
		}
		calendar.add(Calendar.DATE, -day);

	}

	/**
	 * 减少一个日期时间的小时数
	 * @author chenluning
	 * @param hour
	 * @throws EcxlsException
	 */
	public void subtractJustHour(int hour) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (hour < 0) {
			hour = 0;
		}
		calendar.add(Calendar.HOUR_OF_DAY, -hour);

	}

	/**
	 * 减少一个日期时间的分钟数
	 * @author chenluning
	 * @param minute
	 * @throws EcxlsException
	 */
	public void subtractJustMinute(int minute) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (minute < 0) {
			minute = 0;
		}
		calendar.add(Calendar.MINUTE, -minute);

	}

	/**
	 * 减少一个日期时间的月份数
	 * @author chenluning
	 * @param month
	 * @throws EcxlsException
	 */
	public void subtractJustMonth(int month) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (month < 0) {
			month = 0;
		}
		calendar.add(Calendar.MONTH, -month);

	}

	/**
	 * 减少一个日期时间的秒数
	 * @author chenluning
	 * @param Second
	 * @throws EcxlsException
	 */
	public void subtractJustSecond(int second) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (second < 0) {
			second = 0;
		}
		calendar.add(Calendar.SECOND, -second);

	}

	/**
	 * 减少一个日期时间的年份数
	 * @author chenluning
	 * @param year
	 * @throws EcxlsException
	 */
	public void subtractJustYear(int year) throws EcxlsException {
		// 参数不能为负数，如果为负，则等于0
		if (year < 0) {
			year = 0;
		}
		calendar.add(Calendar.YEAR, -year);

	}

	/**
	 * @return the calendar
	 */
	public Calendar getCalendar() {
		return calendar;
	}

	/**
	 * @param calendar
	 *            the calendar to set
	 */
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
}
