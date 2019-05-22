package com.ecxls.integral.util;

import java.io.Closeable;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecxls.integral.util.date.DateTimeUtil;
import com.ecxls.integral.util.exception.EcxlsException;
import com.ecxls.integral.util.properties.PropertiesUtil;


/**
 * <p>ClassName: Formula<p>
 * <p>Description: 常用工具调用方法类<p>
 * <p>Company:广东鑫零售投资有限公司 <p>	
 * @author chenluning
 * @createTime 2018年5月31日 下午3:04:26
 */
public class Formula {
	
	//时间帮助类对象
	static DateTimeUtil lDateTime = new DateTimeUtil();
	
	//格式化对象
	static SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	//properties帮助类对象
	static PropertiesUtil properties;
	
	private static String[] str = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	
	/**
	 * 返回 int 值的绝对值。如果参数为非负数，则返回该参数。如果参数为负数，则返回该参数的相反数 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:23:59
	 * @param anyNumber
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getAbsolute(int anyNumber) throws EcxlsException {
		return Math.abs(anyNumber);
	}
	
	/**
	 * 返回一个值的反余弦 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:24:20
	 * @param cosine
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getACos(double cosine) throws EcxlsException {
		return Math.acos(cosine);
	}
	/**
	 * 返回一个值的反正弦		
	 * @param sine
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getASin(double sine) throws EcxlsException {

		return Math.asin(sine);
	}

	/**
	 * 返回一个值的反正切 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午2:40:31
	 * @param tangent
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getATan(double tangent) throws EcxlsException {
		return Math.atan(tangent);
	}
	
	/**
	 * 计算卡迪尔直角坐标x,y的正切值对应的角度		
	 * @param x 横坐标值
	 * @param y 纵坐标值
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getATanDouble(double x, double y) throws EcxlsException {

		return Math.atan2(y, x);
	}
	
	/**
	 * 返回角的三角正弦		
	 * @param angle 以弧度表示的角
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getSin(double angle) throws EcxlsException {

		return Math.sin(angle);
	}
	
	/**
	 * 返回角的三角余弦		
	 * @param angle 以弧度表示的角
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getCos(double angle) throws EcxlsException {

		return Math.cos(angle);
	}
	
	/**
	 * 返回角的三角正切		
	 * @param angle 以弧度表示的角
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getTan(double angle) throws EcxlsException {

		return Math.tan(angle);
	}
	
	/**
	 * 返回欧拉数 e 的 double 次幂的值		
	 * @param power e 的指数
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getExp(double power) throws EcxlsException {

		return Math.exp(power);
	}
	
	/**
	 * 返回 double 值的自然对数（底数是 e）		
	 * @param number 一个值
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getLn(double number) throws EcxlsException {

		return Math.log(number);
	}
	
	/**
	 * 返回 double 值的底数为 10 的对数		
	 * @param number 一个值
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getLog(double number) throws EcxlsException {

		return Math.log10(number);
	}
	
	/**
	 * 返回两个 int 值中较大的一个		
	 * @param num1 参数1
	 * @param num2 参数2
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getMax(int num1, int num2) throws EcxlsException {

		return Math.max(num1, num2);
	}
	
	/**
	 * 返回两个 int 值中较小的一个		
	 * @param num1 参数1
	 * @param num2 参数2
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getMin(int num1, int num2) throws EcxlsException {

		return Math.min(num1, num2);
	}
	
	/**
	 * 返回 int数组中最大的一个值		
	 * @param nums 数组
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getMax(int[] nums) throws EcxlsException {
		int max = nums[0];
		for(int num : nums){
			max = getMax(max, num);
		}
		return max;
	}
	
	/**
	 * 返回 int数组中最小的一个值		
	 * @param nums 数组
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getMin(int[] nums) throws EcxlsException {
		int min = nums[0];
		for(int num : nums){
			min = getMin(min, num);
		}
		return min;
	}
	
	/**
	 * 返回number1除number2后的余数		
	 * @param num1
	 * @param num2
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getModulo(int num1,int num2) throws EcxlsException {
		
		return num1%num2;
	}
	
	/**
	 * 返回圆周率值		
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getPi()throws EcxlsException {
		
		return Math.PI;
	}
	
	/**
	 * 返回第一个参数的第二个参数次幂的值		
	 * @param x 底数
	 * @param y 指数
	 * @return double 
	 * @throws EcxlsException
	 */
	public static double getPower(double x,double y)throws EcxlsException {
		
		return Math.pow(x, y);
	}
	
	/**
	 * 返回带正号的 double 值，该值大于等于 0.0 且小于 1.0		
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getRandom()throws EcxlsException {
		
		return Math.random();
	}
	
	/**
	 * 返回带正号的 double 值，该值为大于等于 0.0 且小于 1.0的值乘以num		
	 * @param num 参数
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getRandom(int num)throws EcxlsException {
		
		return Math.random()*num;
	}
	
	/**
	 * 返回最接近参数的 int		
	 * @param num 要舍入为整数的浮点值
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getRound(float num)throws EcxlsException {
		
		return Math.round(num);
	}
	
	/**
	 * 返回正确舍入的 double值的正平方根		
	 * @param x 一个值
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getSqrt(double x) throws EcxlsException {
		
		return Math.sqrt(x);
	}
	
	/**
	 * 指定数数组求和后返回		
	 * @param num 指定数组
	 * @return double
	 * @throws EcxlsException
	 */
	public static double getSum(double[] num) throws EcxlsException {
		double sum = 0;
		for(int i=0;i<num.length;i++){
			sum += num[i];
		}
		return sum;
	}
	
	/**
	 * 返回要匹配的字符串在指定字符串中第一次出现处的索引 		
	 * @param str 指定字符串
	 * @param substr 要匹配的字符串
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getBegins(String str,String substr) throws EcxlsException {
		
		return str.indexOf(substr);
	}
	
	/**
	 * 获取匹配字符串第一次出现在指定字符串倒数第几的位置，如果不存在，则返回-1		
	 * @param str 指定字符串
	 * @param pos 要匹配的字符串
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getEndPosition(String str,String pos) throws EcxlsException {
		
		if(str.indexOf(pos) != -1){
			return str.length() - str.indexOf(pos);
		}else{
			return -1;
		}
	}
	
	/**
	 * 把字符串数组转换为按逗号隔开的字符串
	 * @param listValue
	 * @return
	 * @throws EcxlsException
	 */
	public static String implode(String[] listValue) throws EcxlsException {
		return implode(listValue,",");
	}
	
	/**
	 * 把字符串数组转换为按符号隔开的字符串		
	 * @param listValue 字符串数组
	 * @param separator 指定分隔字符
	 * @return String
	 * @throws EcxlsException
	 */
	public static String implode(String[] listValue,String separator) throws EcxlsException {
		StringBuffer sb = new StringBuffer();
		sb.append(listValue[0]);
		for(int i=1;i<listValue.length;i++){
			sb.append(separator).append(listValue[i]);
		}
		return sb.toString();
	}
	
	/**
	 * 判断指定字符串是否在字符串数组中存在		
	 * @param listValue 指定字符串数组
	 * @param textValue 指定字符串
	 * @return boolean 存在返回true
	 * @throws EcxlsException
	 */
	public static boolean isMember(String[] listValue,String textValue)throws EcxlsException {
		//遍历字符串数组
		for(int i=0; i<listValue.length; i++){
			//如果指定字符串等于数组里的某个值
			if(listValue[i].equals(textValue)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断子数组中是否有成员在父数组存在	
	 * @param parent 父字符串数组
	 * @param children 子字符串数组
	 * @return boolean 存在返回true
	 * @throws EcxlsException
	 */
	public static boolean isMember(String[] parent, String[] children)throws EcxlsException {
		//遍历子字符串数组
		for(int i=0; i<children.length; i++){
			if(isMember(parent,children[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 返回子成员中存在于父成员中的成员		
	 * @param parent 父字符串数组
	 * @param children 子字符串数组
	 * @return String[]
	 * @throws EcxlsException
	 */
	public static String[] getMember(String[] parent,String[] children)throws EcxlsException {
		
		List<String> list = new ArrayList<String>();
		for(int i=0; i<children.length; i++){
			if(isMember(parent,children[i])){
				list.add(children[i]);
			}
		}
		return list.toArray(new String[list.size()]); 
	}
	
	/**
	 * 返回子成员中不存在于父成员中的成员		
	 * @param parent 父字符串数组
	 * @param children 子字符串数组
	 * @return String[]
	 * @throws EcxlsException
	 */
	public static String[] getNotMember(String[] parent,String[] children)throws EcxlsException {
		
		List<String> list = new ArrayList<String>();
		for(int i=0; i<children.length; i++){
			if(!isMember(parent,children[i])){
				list.add(children[i]);
			}
		}
		return list.toArray(new String[list.size()]); 
	}
	
	/**
	 * 返回子字符串，该字符串为匹配字符串第一次出现在指定字符串左边的字符串，不包含匹配字符串。如果str中不存在patten则返回空		
	 * @param str 指定字符串
	 * @param patten 要匹配的字符
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getLeftBack(String str,String patten)throws EcxlsException {
		//如果指定字符串包含匹配字符串
		if(str.indexOf(patten) != -1){
			return str.substring(0, str.indexOf(patten));
		}
		return null;
	}
	
	/**
	 * 返回子字符串，该字符串为匹配字符串第一次出现在指定字符串右边的字符串，不包含匹配字符串。如果str中不存在patten则返回空。		
	 * @param str 指定字符串
	 * @param patten 要匹配的字符
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getRightBack(String str,String patten)throws EcxlsException {
	
		if(str.indexOf(patten) != -1){
			return str.substring(str.indexOf(patten)+patten.length());
		}
		return null;
	}

	/**
	 * 返回子字符串，该字符串为指定字符串从起始字符串到结束字符串之间的字符串。		
	 * @param str 指定字符串
	 * @param start 起始字符串
	 * @param end 结束字符串
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getMiddleBack(String str,String start,String end)throws EcxlsException {
		
		String s = str.substring(str.indexOf(start), str.indexOf(end));
		return s.substring(start.length(), s.length());
	}
	
	/**
	 * 返回指定字符串重复拼接指定次数后的结果。		
	 * @param str 指定字符串
	 * @param number 复制次数
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getRepeat(String str,int number)throws EcxlsException {
		String result = str;
		for(int i=0;i<number;i++){
			result = result.concat(str);
		}
		return result;
	}
	
	/**
	 * 把货币对象按照指定样式转为字符串		
	 * @param money 货币对象
	 * @param style 指定样式
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getMoneyToString(Object money, String style)throws EcxlsException {
		
		NumberFormat nf = new DecimalFormat(style);
		return nf.format(money);
	}
	
	/**
	 * 把任意值按照指定格式转字符串		
	 * @param format 指定格式
	 * @param object 任意值
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getText(String format,Object object)throws EcxlsException {
	
		return String.format(format, object);
	}
	
	/**
	 * 去除字符串数组中重复的元素		
	 * @param textlist 指定字符串数组
	 * @return String[] 去重后的数组
	 * @throws EcxlsException
	 */
	public static String[] getUnique(String[] textlist)throws EcxlsException {
		 List<String> list = new LinkedList<String>();
		 	//遍历指定字符串数组
		    for(int i = 0; i < textlist.length; i++) { 
		    	//如果list不包含某个字符串
		        if(!list.contains(textlist[i])) {  
		            list.add(textlist[i]);  
		        }  
		    }  
		 return list.toArray(new String[list.size()]); 
	}
	
	/**
	 * 把整形数组转换成以“,”相隔的字符串		
	 * @param nums 整型数组
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getIntArrayToString(int[] nums)throws EcxlsException {
		StringBuffer sb = new StringBuffer();
		sb.append(nums[0]);
		for(int i=1;i<nums.length;i++){
			sb.append(",").append(nums[i]);
		}
		return sb.toString();
	}
	
	/**
	 * 判断指定字符串是否为空		
	 * @param str 指定字符串
	 * @return boolean 为空返回true
	 * @throws EcxlsException
	 */
	public static boolean isEmpty(String str)throws EcxlsException {
		
		return str == null || str.trim().isEmpty();
	}
	
	/**
	 * 判断指定字符串是否不为空		
	 * @param str 指定字符串
	 * @return boolean 不为空返回true
	 * @throws EcxlsException
	 */
	public static boolean isNotEmpty(String str)throws EcxlsException {
		
		return !isEmpty(str);
	}
	
	/**
	 * 判断指定字符串是否为数字 		
	 * @param str 指定字符串
	 * @return boolean 是数字返回true
	 * @throws EcxlsException
	 */
	public static boolean isNumber(String str)throws EcxlsException {
		
		return str.matches("[0-9]*");
	}
	
	/**
	 * 将指定字符串按UTF-8格式转化并返回
	 * @createTime:2018年10月25日 下午3:43:41
	 * @param str 要转换的字符串
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getUrlDecode(String str)throws EcxlsException {
		
		//"UTF8" 以后要外部引用
		return getUrlDecode(str,"UTF-8");
	}
	
	/**
	 * 
	 * 将字符串按照指定编码格式转码并返回	
	 * @param str 指定的字符串
	 * @param decodeType 指定的编码格式
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getUrlDecode(String str,String decodeType)throws EcxlsException {
		String result = null;
		try {
			result = new String(str.getBytes(),decodeType);
		} catch (UnsupportedEncodingException e) {
			throw new EcxlsException(e);
		}
		return result;
	}
	
	/**
	 * 返回将0-9之间的数字转化为大写中文的值		
	 * @param num 0-9之间的数字
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getNumberToCN(int num)throws EcxlsException {
		
		switch (num) {
			case 0:
				return str[0];
			case 1:
				return str[1];
			case 2:
				return str[2];
			case 3:
				return str[3];
			case 4:
				return str[4];
			case 5:
				return str[5];
			case 6:
				return str[6];
			case 7:
				return str[7];
			case 8:
				return str[8];
			case 9:
				return str[9];
			default:
				return null;
		}
	}
	
	/**
	 * 
	 * 将字符串转为ascii码字符串并返回 		
	 * @param str 指定字符串
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getAscii(String str)throws EcxlsException {
		StringBuffer sb = new StringBuffer();  
	    char[] chars = str.toCharArray();   
	    for (int i = 0; i < chars.length; i++) {  
	        if(i != chars.length - 1){  
	            sb.append((int)chars[i]).append(" ");  
	        }  
	        else {  
	            sb.append((int)chars[i]);  
	        }  
	    }  
	    return sb.toString(); 
	}
	
	/**
	 * 
	 * 把Ascii码转为字符		
	 * @param codeNumber ascii码值
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getAsciiToChar(int codeNumber)throws EcxlsException {
		
		return String.valueOf((char)codeNumber);
	}
	
	/**
	 * 
	 * 调整指定的日期时间值，如年，月，日，小时，分钟，秒，调整量可以是正的或负的。		
	 * @param date 要调整的时间
	 * @param years 增减年数
	 * @param months 增减月份数
	 * @param days 增减天数
	 * @param hours 增减小时数
	 * @param minutes 增减分钟数
	 * @param seconds 增减秒数
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getAdjust(Date date,int years,int months,int days,
			int hours,int minutes,int seconds)throws EcxlsException {
		
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(date); 
		
	    lDateTime.getCalendar().add(Calendar.YEAR, years);
	    lDateTime.getCalendar().add(Calendar.MONTH, months);
	    lDateTime.getCalendar().add(Calendar.DATE, days);
	    lDateTime.getCalendar().add(Calendar.HOUR_OF_DAY, hours);
	    lDateTime.getCalendar().add(Calendar.MINUTE, minutes);
	    lDateTime.getCalendar().add(Calendar.SECOND, seconds);
	    
		return lDateTime.getCalendar().getTime();
	}
	
	/**
	 * 
	 * 获取指定日期内属于工作日的天数		
	 * @param startDate 开始日期（包含当天）
	 * @param endDate 结束日期（包含当天）
	 * @param startWork 一周的起始工作日
	 * @param endWork 一周的结束工作日
	 * @return int
	 * @throws EcxlsException
	 */
	@SuppressWarnings("deprecation")
	public static int getBusinessDays(Date startDate,Date endDate,int startWork,int endWork)throws EcxlsException {
		int result = 0;  
		//当开始日期小于结束日期
		while (startDate.compareTo(endDate) <= 0) { 
			//如果起始工作日 小于等于 结束工作日
			if(startWork <= endWork){
				if (startDate.getDay() >= startWork && startDate.getDay() <= endWork){
					result++;  
				}
			}else{
				if (startDate.getDay() >= startWork || startDate.getDay() <= endWork){
					result++;  
				}
			}
			startDate.setDate(startDate.getDate() + 1); 
		}
		return result; 
	}
	
	/**
	 * 获取当前操作系统的时区设置		
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getCurrentTimeZone()throws EcxlsException {
		
		lDateTime.setCalendar(Calendar.getInstance());
		return lDateTime.getCalendar().getTimeZone().toString();
	}
	
	/**
	 * 把指定的时间字符串转为时间格式输出		
	 * @param dateTime 时间格式字符串,格式是系统时间格式
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getTime(String dateTime)throws EcxlsException {
		return getTime(dateTime,null);
	}
	/**
	 * 把指定的时间字符串转为指定时间格式输出			
	 * @param dateTime 时间格式字符串
	 * @param format 指定时间格式
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getTime(String dateTime,String format)throws EcxlsException {
		Date date;
		
		if(format!=null && !"".equals(format)){
			fmt.applyPattern(format);
		}
		try {
			date = fmt.parse(dateTime);
		} catch (ParseException e) {
			throw new EcxlsException(e);
		}
		return date;
	}
	
	
	/**
	 * 根据参数返回时间对象		
	 * @param year 年份
	 * @param month 月份
	 * @param day 天数
	 * @param hour 小时数
	 * @param minute 分钟数
	 * @param second 秒数
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getTime(int year,int month,int day, int hour ,int minute ,int second )throws EcxlsException {
		
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().set(year, month, day, hour, minute, second);
		return lDateTime.getCalendar().getTime();
	}
	
	/**
	 * 根据参数返回日期对象		
	 * @param year 年份
	 * @param month 月份
	 * @param day 天数
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getDate(int year,int month,int day)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().set(year, month, day);
		return lDateTime.getCalendar().getTime();
	}
	
	/**
	 * getDateNow
	 * @Description: 返回当前系统日期		
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getDate()throws EcxlsException {
		
		return lDateTime.getLocalDate();
	}

	/**
	 * 返回当前系统日历对象		
	 * @return Calendar
	 * @throws EcxlsException
	 */
	public static Calendar getCalendar()throws EcxlsException {
		
		return lDateTime.getLocalCalendar();
	}
	/**
	 * 根据参数返回日历对象		
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @param hour 时
	 * @param minute 分
	 * @param second 秒
	 * @return Calendar
	 * @throws EcxlsException
	 */
	public static Calendar getCalendar(int year,int month,int day, int hour ,int minute ,int second)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().set(year, month, day, hour, minute, second);
		return lDateTime.getCalendar();
	}
	/**
	 * 返回当前系统日期的昨天日期对象		
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getYesterday()throws EcxlsException {
		
		return getYesterday(new Date());
	}
	/**
	 * 返回指定日期的昨天日期对象		
	 * @param date
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getYesterday(Date date)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(date);
		lDateTime.getCalendar().add(Calendar.DAY_OF_WEEK, -1);
		return lDateTime.getCalendar().getTime();
	}
	/**
	 * 返回当前系统日期的明天日期对象		
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getTomorrow()throws EcxlsException {
		
		return getTomorrow(new Date());
	}
	/**
	 * 返回指定日期的明天日期对象		
	 * @param date
	 * @return Date
	 * @throws EcxlsException
	 */
	public static Date getTomorrow(Date date)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(date);
		lDateTime.getCalendar().add(Calendar.DAY_OF_WEEK, 1);
		return lDateTime.getCalendar().getTime();
	}
	/**
	 * 返回当前日期是中文星期几，如星期一、星期二等。		
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getWeekdayToCN()throws EcxlsException {
		
		return getWeekdayToCN(new Date());
	}
	/**
	 * 返回指定日期是中文星期几，如：星期一、星期二等。		
	 * @param date 指定日期
	 * @return String
	 * @throws EcxlsException
	 */
	@SuppressWarnings("deprecation")
	public static String getWeekdayToCN(Date date)throws EcxlsException {
		int day = date.getDay();
		switch (day) {
		case 0:
			return "星期日";
		case 1:
			return "星期一";
		case 2:
			return "星期二";
		case 3:
			return "星期三";
		case 4:
			return "星期四";
		case 5:
			return "星期五";
		case 6:
			return "星期六";
		default:
			return "";
		}
	}
	/**
	 * 返回当前日期中年份的值		
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getYear()throws EcxlsException {
		
		return getYear(new Date());
	}
	/**
	 * 返回指定日期中年份的值		
	 * @param dateTime 指定日期
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getYear(Date dateTime)throws EcxlsException {
		
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(dateTime);
		return lDateTime.getCalendar().get(Calendar.YEAR);
	}
	/**
	 * 返回当前日中月份的值。为显示直观,月数加1。 加1之后：1代表一月,2代表二月。	
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getMonth()throws EcxlsException {
		
		return getMonth(new Date());
	}
	/**
	 * 返回指定日期中月份的值。为显示直观,月数加1。 加1之后：1代表一月,2代表二月。		
	 * @param dateTime 指定日期
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getMonth(Date dateTime)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(dateTime);
		//为显示直观,月数加1。 加1之后：1代表一月,2代表二月。	
		return lDateTime.getCalendar().get(Calendar.MONTH)+1;
	}
	
	/**
	 * 返回当前日期中天数的值		
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getDay()throws EcxlsException {
		
		return getDay(new Date());
	}
	/**
	 * 返回指定日期的天数		
	 * @param dateTime 指定日期
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getDay(Date dateTime)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(dateTime);
		return lDateTime.getCalendar().get(Calendar.DAY_OF_MONTH);
	}
	/**
	 * 返回当前日期是星期几。为显示直观，星期数减1。减1之后：0=星期日 1=星期一...6=星期六。	
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getWeekday()throws EcxlsException {
		
		return getWeekday(new Date());
	}
	/**
	 * 返回指定日期是星期几。为显示直观，星期数减1。减1之后：0=星期日 1=星期一...6=星期六。		
	 * @param date 指定日期
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getWeekday(Date date)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(date);
		//为显示直观，星期数减1。减1之后：0=星期日 1=星期一...6=星期六。	
		return lDateTime.getCalendar().get(Calendar.DAY_OF_WEEK)-1;
	}
	
	/**
	 *  返回当前日期时间中小时的值		
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getHour()throws EcxlsException {
		
		return getHour(new Date());
	}
	/**
	 * 返回指定日期中小时的值		
	 * @param dateTime 指定日期时间
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getHour(Date dateTime)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(dateTime);
		return lDateTime.getCalendar().get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 返回当前日期中分钟的值		
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getMinute()throws EcxlsException {
		
		return getMinute(new Date());
	}

	/**
	 * 返回指定日期中分钟的值 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:20:14
	 * @param dateTime 指定日期时间
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getMinute(Date dateTime)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(dateTime);
		return lDateTime.getCalendar().get(Calendar.MINUTE);
	}

	/**
	 * 返回当前日期时间的秒数	 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:19:56
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getSecond()throws EcxlsException {
		return getSecond(new Date());
	}

	/**
	 * 返回指定日期中秒数的值 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:15:51
	 * @param dateTime 指定日期时间
	 * @return int
	 * @throws EcxlsException
	 */
	public static int getSecond(Date dateTime)throws EcxlsException {
		lDateTime.setCalendar(Calendar.getInstance());
		lDateTime.getCalendar().setTime(dateTime);
		return lDateTime.getCalendar().get(Calendar.SECOND);
	}
	
	/**
	 * 获得该文件夹里的文件（不包含子文件夹里面的文件） 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:15:07
	 * @param dir 指定文件夹路径
	 * @return File[] 文件对象数组
	 * @throws EcxlsException
	 */
	public static File[] getFiles(String dir)throws EcxlsException {
		File f = new File(dir);
		if(f.isDirectory()){
			return f.listFiles();
		}else{
			return null;
		}
	}
	
	/**
	 * 取页面的head中内容 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:14:40
	 * @param request servlet请求对象
	 * @param requestHeader 页面header属性名称
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getHttpHeader(HttpServletRequest request,String requestHeader)throws EcxlsException {
		return request.getHeader(requestHeader);
	}

	/**
	 * 设置页面head 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:14:09
	 * @param response servlet响应对象
	 * @param responseHeader 页面head属性名称
	 * @param value 属性名称对应的值
	 * @throws EcxlsException
	 */
	public static void setHttpHeader( HttpServletResponse response,String responseHeader,String value)throws EcxlsException {
		response.setHeader(responseHeader, value);
	}
	
	/**
	 * 获取url地址中所有的参数及对应的值	 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:13:51
	 * @param url 指定url地址
	 * @return Map<String,String> 所有参数map对象
	 * @throws EcxlsException
	 */
	public static Map<String,String> getUrlQueryString(String url)throws EcxlsException {

		// 匹配参数名和参数值的正则表达式
	    String regEx="(\\?|&+)(.+?)=([^&]*)";
        Matcher m = Pattern.compile(regEx).matcher(url);
        // LinkedHashMap是有序的Map集合，遍历时会按照加入的顺序遍历输出
        Map<String, String> mapParam = new LinkedHashMap<String, String>();
        while(m.find()){
        	// 参数名和参数值
	        mapParam.put(m.group(2), m.group(3));
        }
        return mapParam;
	}
	
	/**
	 * 获取url地址中指定参数对应的值 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:12:57
	 * @param url 指定url地址
	 * @param parameterName 指定参数
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getUrlQueryString(String url,String parameterName)throws EcxlsException {
		
		return getUrlQueryString(url).get(parameterName);
	}
	
	/**
	 * 关闭流公共方法 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:12:45
	 * @param closeObj
	 * @throws EcxlsException
	 */
	public static void closeIO(Closeable closeObj) throws EcxlsException {
		try {
			if (closeObj != null) {
				closeObj.close();
			}
		} catch (Exception e) {
			throw new EcxlsException(e);
		}
	}
	
	/**
	 * 比较值大于0返回true,反之返回false 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:12:27
	 * @param compare 要比较的值
	 * @return boolean
	 * @throws EcxlsException
	 */
	public static boolean getReturn(Integer compare)throws EcxlsException{
		return compare>0?true:false;
	}

	/**
	 * 比较值不为空返回比较值的字符串，反之返回""  
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:12:06
	 * @param compare 比较值
	 * @return String
	 * @throws EcxlsException
	 */
	public static String getReturn(String compare)throws EcxlsException{
		return compare==null?"":compare;
	}
	
	/**
	 * 把Object类型转字符串  
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:11:42
	 * @param obj object对象
	 * @return String
	 */
	public static String getReturnValue(Object obj){
		return obj==null?"":obj.toString();
	}
	
	/**
	 * 计算两个日期之间相差的天数  
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:11:18
	 * @param smdate 开始日期
	 * @param bdate 结束日期
	 * @return int 相差天数 
	 * @throws EcxlsException
	 */
    public static int daysBetween(Date smdate,Date bdate) throws EcxlsException    
    {    
        long between_days = 0;;
		try {
			smdate=sdf.parse(sdf.format(smdate));  
			bdate=sdf.parse(sdf.format(bdate));  
			Calendar cal = Calendar.getInstance();    
			cal.setTime(smdate);    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(bdate);    
			long time2 = cal.getTimeInMillis();         
			between_days = (time2-time1)/(1000*3600*24);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
      
    /**
     * 计算两个日期之间相差的天数 
     * @author chenluning	
     * @createTime 2018年10月25日 下午3:09:41
     * @param smdate 开始日期
     * @param bdate 结束日期
     * @return int 相差天数 
     * @throws EcxlsException
     */
    public static int daysBetween(String smdate,String bdate) throws EcxlsException{  
        long between_days = 0;
		try {
			Calendar cal = Calendar.getInstance();    
			cal.setTime(sdf.parse(smdate));    
			long time1 = cal.getTimeInMillis();                 
			cal.setTime(sdf.parse(bdate));    
			long time2 = cal.getTimeInMillis();         
			between_days = (time2-time1)/(1000*3600*24);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
    
    /**
     * 把number类型转int	 
     * @author chenluning	
     * @createTime 2018年10月25日 下午3:09:15
     * @param o Number类型的数据
     * @return int
     * @throws EcxlsException
     */
	public static int getNumberToInt(Object o)throws EcxlsException{
		Number num=(Number)o;
		return null==num?0:num.intValue();
	}
	
	/**
	 * 判断对象是空 
	 * @author chenluning	
	 * @createTime 2018年10月25日 下午3:08:45
	 * @param pObj
	 * @return boolean
	 */
    @SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object pObj) {
    	if (pObj == null)
			return true;
		if (pObj == "")
			return true;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0 || ((String) pObj).equals("undefined") || ((String) pObj).equals("null")) {
				return true;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return true;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return true;
			}
		}
		return false;
    }
    
    /**
     * 判断对象不是空 
     * @author chenluning	
     * @createTime 2018年10月25日 下午3:08:16
     * @param pObj
     * @return boolean
     */
    @SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Object pObj) {
    	if (pObj == null)
			return false;
		if (pObj == "")
			return false;
		if (pObj == "null")
			return false;
		if (pObj instanceof String) {
			if (((String) pObj).length() == 0) {
				return false;
			}
		} else if (pObj instanceof Collection) {
			if (((Collection) pObj).size() == 0) {
				return false;
			}
		} else if (pObj instanceof Map) {
			if (((Map) pObj).size() == 0) {
				return false;
			}
		}
		return true;
    }
}
