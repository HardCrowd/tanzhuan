package com.ecxls.integral.util.date;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class DataConverUtils {
    public static final String TIME_FORMAT_DAY = "yyyy-MM-dd";
    public static final String TIME_FORMAT_DAY_STR = "yyyyMMdd";
    public static final String TIME_FORMAT_MONTH = "yyyyMM";
    public static final String TIME_FORMAT_SECONDS = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMAT_MINUTES = "yyyy-MM-dd HH:mm";


    /**
     * obj转Integer对象
     * @createTime:2018年10月26日 下午1:59:33
     * @param objarr
     * @return
     */
    public static Integer getInt(Object objarr) {
        return objarr != null ? Integer.valueOf(objarr.toString()) : null;
    }

    /**
     * obj转Long对象
     * @createTime:2018年10月26日 下午1:59:33
     * @param objarr
     * @return
     */
    public static Long getLong(Object objarr) {
        return objarr != null ? Long.valueOf(objarr.toString()) : null;
    }

    /**
     * obj转BigDecimal对象
     * @createTime:2018年10月26日 下午1:59:33
     * @param objarr
     * @return
     */
    public static BigDecimal getBigDecimal(Object objarr) {
        return objarr != null ? new BigDecimal(objarr.toString()) : null;
    }

    /**
     * obj转String对象
     * @createTime:2018年10月26日 下午1:59:33
     * @param objarr
     * @return
     */
    public static String getString(Object objarr) {
        return objarr != null ? objarr.toString() : null;
    }

    public static Date getDate(Object objarr, String format) {
        if (!StringUtils.isEmpty(objarr)) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date date = null;
            try {
                date = sdf.parse(objarr.toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }
        return null;
    }

    /**
     * 把对象按照指定的格式进行转成字符串
     * @createTime:2018年10月26日 下午1:58:42
     * @param date
     * @param format
     * @return
     */
    public static String getDateStr(Object objarr, String format) {
        Date date = getDate(objarr,TIME_FORMAT_SECONDS);
        if (date != null) {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(date);
        }
        return null;
    }

    /**
     * 把时间按照指定的格式进行转成字符串
     * @createTime:2018年10月26日 下午1:58:42
     * @param date
     * @param format
     * @return
     */
    public static String getDateStr(Date date, String format) {
        String result = null;
        try {
            DateFormat df = new SimpleDateFormat(format);
            result = df.format(date);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * string列表转long列表
     * @createTime:2018年10月26日 下午1:58:14
     * @param sources
     * @return
     */
    public static List<Long> getLongList(List<String> sources) {
        List<Long> result = new ArrayList<Long>();
        for (String source : sources) {
            Long target = Long.valueOf(source);
            result.add(target);
        }
        return result;
    }
    
    public static long getDateSubtract(Date beginDate, Date endDate) {
		long subtractDate = 0L;
		try {
			subtractDate = (beginDate.getTime() - endDate.getTime()) / 3600000L;
		} catch (Exception e) {
			return 0L;
		}
		return subtractDate; 
	}
    
    /**
     * 在某个时间增加天数
     * @createTime:2018年10月26日 下午1:57:52
     * @param date
     * @param hour
     * @return
     */
    public static Date addDay(Date date,int hour) {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_MONTH, hour);
    	return calendar.getTime();
    }
    /**
     * 在某个时间增加小时数
     * @createTime:2018年10月26日 下午1:57:52
     * @param date
     * @param hour
     * @return
     */
    public static Date addHour(Date date,int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hour);
        return calendar.getTime();
    }
    
    /**
     * 在某个时间增加分钟数
     * @createTime:2018年10月26日 下午1:57:17
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date,int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }
    
    /**
     * 判断某天是否在某天之前
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isBeforeDate(Date startDate,Date endDate){
    	boolean isBefore = false;
    	if(startDate!=null && endDate !=null){
    		isBefore = startDate.before(endDate);
    	}
		return isBefore;
	}
    
    /**
     * 取得某日期days日后的日期
     * @param months int
     * @return Date
     */
    public static Date getAfterDateByDays(Date date, int days) {
        Calendar calValue = Calendar.getInstance();
        calValue.setTime(date);
        calValue.add(Calendar.DATE, days);
        return new Date(calValue.getTime().getTime());
    }
    
    /**
     * 两个日期相差天数
     * @createTime:2018年10月26日 下午1:56:56
     * @param early
     * @param late
     * @return
     */
    public static int daysBetween(Date early, Date late) { 
        java.util.Calendar calst = java.util.Calendar.getInstance();   
        java.util.Calendar caled = java.util.Calendar.getInstance();   
        calst.setTime(early);   
         caled.setTime(late);   
         //设置时间为0时   
         calst.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         calst.set(java.util.Calendar.MINUTE, 0);   
         calst.set(java.util.Calendar.SECOND, 0);   
         caled.set(java.util.Calendar.HOUR_OF_DAY, 0);   
         caled.set(java.util.Calendar.MINUTE, 0);   
         caled.set(java.util.Calendar.SECOND, 0);   
        //得到两个日期相差的天数   
         int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst   
                .getTime().getTime() / 1000)) / 3600 / 24;   
         
        return days;   
   }   
}
