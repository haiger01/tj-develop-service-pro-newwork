/**
 *
 */
package com.scistor.develop.util;

import java.text.*;
import java.util.*;


public class DateTool {


    public static final SimpleDateFormat SHORT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat SHORT_DATE_FORMAT_DAY = new SimpleDateFormat("yyyyMMdd");

    //获取入账编码
    /**
     * 甲方明确要求只要后六位--20200721修改的需求
     * @return
     */
    public static String getRzCode() {
        /*String dayCode = date2String(new Date());
        dayCode = dayCode.replace("-", "");
        incrBy(dayCode, 1L);
        String code = getRedisVal(dayCode);
        for (int i = code.length(); i < 6; i++) {
            code = "0" + code;
        }
        //return dayCode + code;--20200721修改的需求*/
        return null;
    }


    public static String getLastMonthDate(String dataTime,int month) throws ParseException{
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(SHORT_DATE_FORMAT.parse(dataTime));
        calendar.add(Calendar.MONTH, month);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //System.out.println("一个月以后的时间：" + df.format(calendar.getTime()));
        return df.format(calendar.getTime());
    }


    //获取入账日期
    public static String date2StrChines(Date date) {
        SimpleDateFormat simpledate = new SimpleDateFormat("yyyy年M月dd日");
        return simpledate.format(date);
    }

    public DateTool() {
    }

    public static String longDate2Str(Long l) {
        return dateTimeSec2Str(new Date(l));
    }

    public static Date str2Date(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date);
        } catch (Exception err) {
            return null;
        }
    }


    public static Date str2DateTime(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(date);
        } catch (Exception err) {
            return null;
        }
    }

    public static Date str2DateTimeSec(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(date);
        } catch (Exception err) {
            return null;
        }
    }


    public static String date2Str(Date date) {
        SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
        return simpledate.format(date);
    }

    public static String date2String(Date date) {
        SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM");
        return simpledate.format(date);
    }

    public static String getDateTime2StrByData(Date date,SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(date);
    }


    public static String dateTime2Str(Date date) {
        SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpledate.format(date);
    }

    public static String dateTimeSec2Str(Date date) {
        SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpledate.format(date);
    }

    public static int getYear() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        int year = ca.get(Calendar.YEAR);
        return year;
    }

    public static int getMonth() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        int month = ca.get(Calendar.MONTH);
        return month;
    }

    public static int getDay() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getYear(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int year = ca.get(Calendar.YEAR);
        return year;
    }

    public static int getMonth(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int month = ca.get(Calendar.MONTH);
        return month;
    }

    public static int getDay(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        int day = ca.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static int getHourOfDay() {
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        return hour;
    }

    public static int getDistanceDay(Date date1, Date date2) {
        int distanceDay = 0;
        if (date1 != null && date2 != null) {
            Calendar ca1 = Calendar.getInstance();
            Calendar ca2 = Calendar.getInstance();
            ca1.setTime(date1);
            ca2.setTime(date2);
            int year1 = ca1.get(Calendar.YEAR);
            int year2 = ca2.get(Calendar.YEAR);
            if (year1 != year2) {
                if ((year2 - year1) >= 2) {
                    for (int i = year1 + 1; i < year2; i++) {
                        if (isLeapYear(i)) {
                            distanceDay = distanceDay + 366;
                        } else {
                            distanceDay = distanceDay + 365;
                        }
                    }
                }
                if (isLeapYear(year1)) {
                    distanceDay = distanceDay + 366 - ca1.get(Calendar.DAY_OF_YEAR) + ca2.get(Calendar.DAY_OF_YEAR);
                } else {
                    distanceDay = distanceDay + 365 - ca1.get(Calendar.DAY_OF_YEAR) + ca2.get(Calendar.DAY_OF_YEAR);
                }
            } else {
                distanceDay = ca2.get(Calendar.DAY_OF_YEAR) - ca1.get(Calendar.DAY_OF_YEAR);
            }
        }
        return distanceDay;
    }

    public static int getDistanceHour(Date date1, Date date2) {
        int distanceHour = 0;
        if (date1 != null && date2 != null) {
            Calendar ca1 = Calendar.getInstance();
            Calendar ca2 = Calendar.getInstance();
            ca1.setTime(date1);
            ca2.setTime(date2);
            distanceHour = ca2.get(Calendar.HOUR_OF_DAY) - ca1.get(Calendar.HOUR_OF_DAY);
            int year1 = ca1.get(Calendar.YEAR);
            int year2 = ca2.get(Calendar.YEAR);
            int distanceDay = 0;
            if (year1 != year2) {
                if ((year2 - year1) >= 2) {
                    for (int i = year1 + 1; i < year2; i++) {
                        if (isLeapYear(i)) {
                            distanceDay = distanceDay + 366;
                        } else {
                            distanceDay = distanceDay + 365;
                        }
                    }
                }
                if (isLeapYear(year1)) {
                    distanceDay = distanceDay + 366 - ca1.get(Calendar.DAY_OF_YEAR) + ca2.get(Calendar.DAY_OF_YEAR);
                } else {
                    distanceDay = distanceDay + 365 - ca1.get(Calendar.DAY_OF_YEAR) + ca2.get(Calendar.DAY_OF_YEAR);
                }
            } else {
                distanceDay = ca2.get(Calendar.DAY_OF_YEAR) - ca1.get(Calendar.DAY_OF_YEAR);
            }
            distanceHour = distanceHour + distanceDay * 24;
        }
        return distanceHour;
    }


    public static long getDistanceMinute(Date date1, Date date2) throws Exception {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date begin = dfs.parse(dateTime2Str(date1));
        Date end = dfs.parse(dateTime2Str(date2));
        long between = end.getTime() - begin.getTime();
        between = (between / 1000) / 60;
        return between;
    }

    public static long getDistanceSecond(Date date1, Date date2) throws Exception {
        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = dfs.parse(dateTimeSec2Str(date1));
        Date end = dfs.parse(dateTimeSec2Str(date2));
        long between = end.getTime() - begin.getTime();
        between = between / 1000;
        return between;
    }


    public static boolean isLeapYear(int year) {
        boolean isLeapYear = true;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            isLeapYear = true;
        } else {
            isLeapYear = false;
        }
        return isLeapYear;
    }

    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static Date getHourOfDay(Date date, int hour) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, hour);
        return ca.getTime();
    }


    public static Date getComputeDate(int days) {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.DAY_OF_YEAR, days);
        return ca.getTime();
    }


    public static Date getComputeDate(Date date, int days) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_YEAR, days);
        return ca.getTime();
    }


    public static Date getComputeYear(int years) {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.YEAR, years);
        return ca.getTime();
    }


    public static Date getComputeMonth(int months) {
        Calendar ca = Calendar.getInstance();
        ca.add(Calendar.MONTH, months);
        return ca.getTime();
    }

    public static Date getComputeMonth(Date date, int months) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.MONTH, months);
        return ca.getTime();
    }

    public static int getDayOfWeek(Date date) {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return ca.get(Calendar.DAY_OF_WEEK);
    }
}
