// /////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (C) 2014 ikamobile.
// All rights reserved
// /////////////////////////////////////////////////////////////////////////////////////////////////
package com.ikamobile.pa.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间对象和字符串的转换工具
 * 
 * @author wanglong(a)ikamobile.com
 * @version 1.0
 * @since 1.0
 */
public class DateTimeUtils {

    // 工具类不允许创建实例
    private DateTimeUtils() {}

    public enum TimeUnit {
        year, month, week, day, hour, minute, secend, millisecond
    }

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String HH_MM_FORMAT = "HH:mm";
    public static final String YYYY_MM_DD_HH_MM_FORMAT = "yyyy-MM-dd HH:mm";

    public static final Long MSEL_PER_SECEND = 1000L;
    public static final Long MSEL_PER_MINUTE = MSEL_PER_SECEND * 60;
    public static final Long MSEL_PER_HOUR = MSEL_PER_MINUTE * 60;
    public static final Long MSEL_PER_DAY = MSEL_PER_HOUR * 24;
    public static final Long MSEL_PER_WEEK = MSEL_PER_DAY * 7;

    public static final String DEFAULT_FIRST_DATE_TIME = "1970-01-01 00:00:00";

    /**
     * 按照yyyy-MM-dd的格式将日期转换为字符串
     * 
     * @param date
     * @return
     */
    public static String convertDateToString(Date date) {
        return convertDateToString(date, DEFAULT_DATE_FORMAT);
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式将日期转换为字符串
     * 
     * @param date
     * @return
     */
    public static String convertTimeToString(Date date) {
        return convertTimeToString(date, DEFAULT_DATE_TIME_FORMAT);
    }

    /**
     * 按照指定格式，将日期转换为字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String convertDateToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        FastDateFormat fdf = FastDateFormat.getInstance(pattern);
        return fdf.format(date);
    }

    /**
     * 按照指定格式，将日期转换为字符串
     * 
     * @param date
     * @param pattern
     * @return
     */
    public static String convertTimeToString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        FastDateFormat fdf = FastDateFormat.getInstance(pattern);
        return fdf.format(date);
    }

    /**
     * 按照格式将字符串转换为date
     * 
     * @param dateString
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String dateString, String pattern) throws ParseException {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }

    /**
     * 按照yyyy-MM-dd的格式将字符串转换为日期
     * 
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String dateString) {
        try {
            if (StringUtils.isBlank(dateString)) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式将字符串转换为日期
     * 
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date convertStringToTime(String dateString) {
        try {
            if (StringUtils.isBlank(dateString)) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME_FORMAT);
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 按照yyyy-MM-dd HH:mm:ss的格式将字符串转换为日期
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date convertStringToTime(String dateString,String pattern) throws ParseException{
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }


    /**
     * 获取当前的系统日期
     * 
     * @return 系统当前日期，格式年(4位)
     */
    public static String getYear() {
        Calendar CD = Calendar.getInstance();
        int YY = CD.get(Calendar.YEAR);
        StringBuffer curDateString = new StringBuffer();
        curDateString.append(YY);
        return curDateString.toString();
    }

    /**
     * 获取当前的系统日期
     * 
     * @return 系统当前日期，格式月（两位）
     */
    public static String getMonth() {
        Calendar CD = Calendar.getInstance();
        int MM = CD.get(Calendar.MONTH) + 1;
        StringBuffer curDateString = new StringBuffer();
        curDateString.append(MM);
        return curDateString.toString();
    }

    /**
     * 获取当前的系统日期
     * 
     * @return 系统当前日期，格式日（两位）
     */
    public static String getDay() {
        Calendar CD = Calendar.getInstance();
        int DD = CD.get(Calendar.DATE);
        StringBuffer curDateString = new StringBuffer();
        curDateString.append(DD);
        return curDateString.toString();
    }

    public static String getDate(String dateFormat) {
        return convertDateToString(new Date(), dateFormat);
    }

    /**
     * 获取当前的系统日期
     * 
     * @return 系统当前日期，格式西元年(4位)+月(2位)+日(2位)
     */
    public static String getDate() {
        return convertDateToString(new Date());
        // Calendar CD = Calendar.getInstance();
        // int YY = CD.get(Calendar.YEAR);
        // int MM = CD.get(Calendar.MONTH) + 1;
        // int DD = CD.get(Calendar.DATE);
        // StringBuffer curDateString = new StringBuffer();
        // curDateString.append(YY);
        // curDateString.append("-");
        // curDateString.append(StringUtils.leftPad(String.valueOf(MM),
        // '0', 2));
        // curDateString.append("-");
        // curDateString.append(StringUtils.leftPad(String.valueOf(DD),
        // '0', 2));
        // return curDateString.toString();
    }

    /**
     * 获取当前的系统时间
     * 
     * @return 系统当前时间，格式时(2位)+分(2位)+秒(2位)
     */
    public static String getDateTime() {
        Calendar CD = Calendar.getInstance();
        int YY = CD.get(Calendar.YEAR);
        int MM = CD.get(Calendar.MONTH) + 1;
        int DD = CD.get(Calendar.DATE);
        int HH = CD.get(Calendar.HOUR_OF_DAY);
        int mm = CD.get(Calendar.MINUTE);
        int ss = CD.get(Calendar.SECOND);
        StringBuffer curDateTimeString = new StringBuffer();
        curDateTimeString.append(String.valueOf(YY));
        curDateTimeString.append("-");
        curDateTimeString.append(StringUtils.leftPad(String.valueOf(MM), 2, '0'));
        curDateTimeString.append("-");
        curDateTimeString.append(StringUtils.leftPad(String.valueOf(DD), 2, '0'));
        curDateTimeString.append(" ");
        curDateTimeString.append(StringUtils.leftPad(String.valueOf(HH), 2, '0'));
        curDateTimeString.append(":");
        curDateTimeString.append(StringUtils.leftPad(String.valueOf(mm), 2, '0'));
        curDateTimeString.append(":");
        curDateTimeString.append(StringUtils.leftPad(String.valueOf(ss), 2, '0'));
        return curDateTimeString.toString();
    }

    /**
     * 获取当前的系统时间
     * 
     * @return 系统当前时间，格式时(2位)+分(2位)+秒(2位)
     */
    public static String getTime() {
        Calendar CD = Calendar.getInstance();
        int HH = CD.get(Calendar.HOUR_OF_DAY);
        int mm = CD.get(Calendar.MINUTE);
        int ss = CD.get(Calendar.SECOND);
        StringBuffer curTimeString = new StringBuffer();
        curTimeString.append(StringUtils.leftPad(String.valueOf(HH), 2, '0'));
        curTimeString.append(":");
        curTimeString.append(StringUtils.leftPad(String.valueOf(mm), 2, '0'));
        curTimeString.append(":");
        curTimeString.append(StringUtils.leftPad(String.valueOf(ss), 2, '0'));
        return curTimeString.toString();
    }

    /**
     * 获取当前的系统时间的小时
     * 
     * @return 系统当前小时
     */
    public static int getHour() {
        Calendar CD = Calendar.getInstance();
        int HH = CD.get(Calendar.HOUR_OF_DAY);
        return HH;
    }

    /**
     * 获取当前的系统时间的分钟
     * 
     * @return 系统当前分钟
     */
    public static int getMinute() {
        Calendar CD = Calendar.getInstance();
        int mm = CD.get(Calendar.MINUTE);
        return mm;
    }

    public static Long getTimeDispersion(Date beginTime, Date endTime, TimeUnit timeUnit) {

        if (beginTime == null || endTime == null) {
            return null;
        }

        Long dis = endTime.getTime() - beginTime.getTime();
        Long result = dis;
        switch (timeUnit) {
        case year:
        case month:
            break;
        case week:
            result = dis / MSEL_PER_WEEK;
            break;
        case day:
            result = dis / MSEL_PER_DAY;
            break;
        case hour:
            result = dis / MSEL_PER_HOUR;
            break;
        case minute:
            result = dis / MSEL_PER_MINUTE;
            break;
        case secend:
            result = dis / MSEL_PER_SECEND;
            break;
        case millisecond:
            break;
        }
        return result;
    }

    /**
     * 获取两个日期的差
     * 
     * @param beginTime
     * @param endTime
     * @param timeUnit
     * @return
     */
    public static Long getTimeDispersion(String beginTime, String endTime,
            TimeUnit timeUnit) {
        if (StringUtils.isEmpty(beginTime) || StringUtils.isEmpty(endTime)) {
            return null;
        }
        return getTimeDispersion(convertStringToTime(endTime),
                convertStringToTime(beginTime), timeUnit);
    }

    /**
     * 将时间向前（正数），后（负数）移动
     * 
     * @param beMovedTimeString
     * @param movedDays
     * @return
     * @throws ParseException
     */
    public static String moveTime(String beMovedTimeString, int movedDays) throws ParseException {
        if (StringUtils.isEmpty(beMovedTimeString)) {
            beMovedTimeString = getDateTime();
        }
        Date beMovedTime = convertStringToTime(beMovedTimeString);
        Date moveResult = moveDateTime(beMovedTime, movedDays);
        return convertTimeToString(moveResult);
    }

    /**
     * 将日期向前（正数），后（负数）移动
     * 
     * @param beMovedDateString
     * @param movedDays
     * @return
     * @throws ParseException
     */
    public static String moveDate(String beMovedDateString, int movedDays) throws ParseException {
        if (StringUtils.isEmpty(beMovedDateString)) {
            beMovedDateString = getDate();
        }
        Date beMovedDate = convertStringToTime(beMovedDateString);
        Date moveResult = moveDateTime(beMovedDate, movedDays);
        return convertDateToString(moveResult);
    }

    /**
     * 移动日期
     * 
     * @param movedDate
     * @param movedNum
     * @return
     */
    public static Date moveDateTime(Date movedDate, int movedNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(movedDate);
        calendar.add(Calendar.DAY_OF_MONTH, movedNum);
        return calendar.getTime();
    }

    /**
     * 格式化时间间隔
     * 
     * @param timeInterval 时间间隔(单位：毫秒)
     * @return XX天XX小时XX分XX秒
     */
    public static String formatTimeInterval(long timeInterval) {
        timeInterval = timeInterval / 1000;// 秒
        long day = timeInterval / (24 * 3600);// 天
        long hour = timeInterval % (24 * 3600) / 3600;// 小时
        long minute = timeInterval % 3600 / 60;// 分钟
        long second = timeInterval % 60;// 秒
        return day + "天" + hour + "小时" + minute + "分" + second + "秒";
    }

    /**
     * 获取一个月的时间跨度,日期值错误将返回NULL
     * 
     * @param timeValue 格式yyyyMM 201411
     * @return e.g. Date Array 0:2014-11-01 1:2014-12-01
     */
    public static Date[] getRangeOfoneMonth(String timeValue) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        Date date = null;
        try {
            date = format.parse(timeValue);
        } catch (ParseException e) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        Date s = calendar.getTime();
        calendar.add(Calendar.MONTH, +1);
        Date e = calendar.getTime();
        return new Date[] { s, e };
    }

    public static Date getFirstDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal
                .getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }

    public static Date getYesterday(){
        return moveDateTime(new Date(),-1);
    }
    public static Date getTomorrow(){
        return moveDateTime(new Date(),1);
    }

    /**
     * 根据用户生日计算年龄
     */
    public static int getAgeByBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    /**
     * 获取时间差
     */
    public static String getDiffTime(Date before, Date after) {
        // 毫秒ms
        long diff = after.getTime() - before.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);
        StringBuffer str = new StringBuffer();
        if (diffDays > 0) {
            str.append(diffDays);
            str.append("天");
        }
        if (diffHours > 0) {
            str.append(diffHours);
            str.append("小时");
        }
        if (diffMinutes > 0) {
            str.append(diffMinutes);
            str.append("分");
        }
        if (diffSeconds > 0) {
            str.append(diffSeconds);
            str.append("秒");
        }
        return str.toString();
    }

    public static boolean isToday(Date date){
        Date now = new Date();
        return convertDateToString(now).equalsIgnoreCase(convertDateToString(date));
    }

    public static boolean isYesterday(Date date){
        return convertDateToString(getYesterday()).equalsIgnoreCase(convertDateToString(date));
    }

    public static boolean isTomorrow(Date date){
        return convertDateToString(getTomorrow()).equalsIgnoreCase(convertDateToString(date));
    }


    public static void main(String[] args) {
//        String s = DateTimeUtils.convertDateToString(new Date(),YYYY_MM_DD_HH_MM_FORMAT);
        String dateStr = "2016-07-15 15:00:00:00";
        Date date = convertStringToDate(dateStr);
        System.out.println(dateStr+"is today ?"+isToday(date));
        System.out.println(dateStr+"is yesterday ?"+isYesterday(date));
        System.out.println(dateStr+"is tomorrow ?"+isTomorrow(date));
    }

}
