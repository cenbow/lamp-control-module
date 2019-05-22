package com.owen.lamp_control_module_common.utils;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>Title: DateUtil</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018 lzx All rights reserved</p>
 *
 * @author Owen
 * @version 1.0
 * @email 1962864010@qq.com
 * @date 2019/4/19 14:21
 */
public class DateUtil {

    /**
     * 默认日期格式
     */
    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * 默认时间格式
     */
    public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String TIME_DEFAULT_FORMAT = "HH:mm:ss";

    /**
     * 日期格式化
     */
    private static DateFormat dateFormat;

    /**
     * 时间格式化
     */
    private static DateFormat dateTimeFormat;

    private static DateFormat timeFormat;

    private static Calendar gregorianCalendar;

    static {
        dateFormat = new SimpleDateFormat(DATE_DEFAULT_FORMAT);
        dateTimeFormat = new SimpleDateFormat(DATETIME_DEFAULT_FORMAT);
        timeFormat = new SimpleDateFormat(TIME_DEFAULT_FORMAT);
        gregorianCalendar = new GregorianCalendar();
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return 结果
     */
    public static Date formatDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期格式化yyyy-MM-dd
     *
     * @param date
     * @return 结果
     */
    public static String getDateFormat(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 日期格式化yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return 结果
     */
    public static String getDateTimeFormat(Date date) {
        return dateTimeFormat.format(date);

    }

    /**
     * 时间格式化
     *
     * @param date
     * @return 结果
     */
    public static String getTimeFormat(Date date) {
        return timeFormat.format(date);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param formatStr
     * @return 结果
     */
    public static String getDateFormat(Date date, String formatStr) {
        if (!StringUtils.isEmpty(formatStr)) {
            return new SimpleDateFormat(formatStr).format(date);

        }
        return null;
    }

    /**
     * 日期格式化
     *
     * @param date
     * @return 结果
     */
    public static Date getDateFormat(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 时间格式化
     *
     * @param date
     * @return 结果
     */
    public static Date getDateTimeFormat(String date) {
        try {
            return dateTimeFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前日期(yyyy-MM-dd)
     *
     * @return 结果
     */
    public static String getNowDate() {
        return DateUtil.getDateFormat(new Date());
    }

    /**
     * 获取指定日期
     *
     * @param date
     * @return 结果
     */
    public static String getRecentThreeDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        date = calendar.getTime();
        return DateUtil.getDateFormat(date, "yyyy-MM-dd");
    }

    /**
     * 获取指定日期 减一天
     *
     * @param date
     * @return 结果
     */
    public static String getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // -1今天的时间减一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return DateUtil.getDateFormat(date, "MM-dd");
    }

    /**
     * 比较两个日期
     *
     * @param date1
     * @param date2
     * @return 结果 date1 > date2 返回 1， date1 < date2 返回 -1 ,date1 == date2 返回 0
     */
    public static int compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("MM-dd");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2后");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2前");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    private static final String FORMAT_STR = "HH:mm";
    private static SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STR);

    public static long getLong(String timeStr) {
        long result = 0;
        try {
            result = sdf.parse(timeStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static long getLong24(String timeStr) throws ParseException {
        return timeFormat.parse(timeStr).getTime();
    }

    /**
     * 获取今天是周几
     *
     * @return 结果
     */
    public static int getWeek() {
        int[] str = {6, 0, 1, 2, 3, 4, 5};
        Calendar rightNow = Calendar.getInstance();
        //获取时间
        int day = rightNow.get(Calendar.DAY_OF_WEEK);
        return str[day - 1];
    }
}
