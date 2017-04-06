package com.example.zdm.horizontaldatedemo.utils;


import com.example.zdm.horizontaldatedemo.constants.Week;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ......................我佛慈悲....................
 * ......................_oo0oo_.....................
 * .....................o8888888o....................
 * .....................88" . "88....................
 * .....................(| -_- |)....................
 * .....................0\  =  /0....................
 * ...................___/`---'\___..................
 * ..................' \\|     |// '.................
 * ................./ \\|||  :  |||// \..............
 * .............../ _||||| -卍-|||||- \..............
 * ..............|   | \\\  -  /// |   |.............
 * ..............| \_|  ''\---/''  |_/ |.............
 * ..............\  .-\__  '-'  ___/-. /.............
 * ............___'. .'  /--.--\  `. .'___...........
 * .........."" '<  `.___\_<|>_/___.' >' ""..........
 * ........| | :  `- \`.;`\ _ /`;.`/ - ` : | |.......
 * ........\  \ `_.   \_ __\ /__ _/   .-` /  /.......
 * ....=====`-.____`.___ \_____/___.-`___.-'=====....
 * ......................`=---='.....................
 * <p>
 * ..................佛祖开光 ,永无BUG................
 * 佛曰:
 * <p>
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 * <p>
 * Created by zdm on 2017/3/9.
 * 描述:
 */
public class TimeUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-M-dd");
    private static SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");

    public static String getToDay() {
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    //是否是闰年
    public static boolean isLeapYear(int year) {
        if (year % 100 == 0 && year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }


    //获取当前月份有几天
    public static int getDaysOfMonth(boolean isLeapyear, int month) {
        int daysOfMonth = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapyear) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }

        }
        return daysOfMonth;
    }

    /**
     * 得到几天前的时间
     *
     * @param data
     * @param day
     * @return
     */
    public static String getDateBefore(String data, int day) {
        Date date;
        try {
            date = simpleDateFormat.parse(data);
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
//            MyLogger.jLog().e("==data==" + data + "====" + simpleDateFormat.format(now.getTime().getTime()));
            return simpleDateFormat.format(now.getTime().getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";

    }

    /**
     * 得到几天后的时间
     *
     * @param data
     * @param day
     * @return
     */
    public static String getDateAfter(String data, int day) {
        Date date;
        try {
            date = simpleDateFormat.parse(data);
            Calendar now = Calendar.getInstance();
            now.setTime(date);
            now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
//            MyLogger.jLog().e("==data==" + data + "====" + simpleDateFormat.format(now.getTime().getTime()));
            return simpleDateFormat.format(now.getTime().getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    //更新当前显示的时间
    public static List<String> getDays(int year, int month, int day) {
        List<String> list = new ArrayList<>();
        int temp_y = year;
        int temp_m = month;
        int temp_d = day;

        //获取当前月有几天
        int dayOfMonth = TimeUtils.getDaysOfMonth(TimeUtils.isLeapYear(year), month);
        //如果是月初 day=1/2/3
        if (dayOfMonth - day == 0) {//月末
            if (month == 12) {
                temp_y++;
                temp_m = 1;
            } else {
                temp_m++;
            }
            list.add(year + "-" + (month) + "-" + (day - 3));
            list.add(year + "-" + (month) + "-" + (day - 2));
            list.add(year + "-" + (month) + "-" + (day - 1));
            list.add(year + "-" + (month) + "-" + day);
            list.add(temp_y + "-" + (temp_m) + "-" + 1);
            list.add(temp_y + "-" + (temp_m) + "-" + 2);
            list.add(temp_y + "-" + (temp_m) + "-" + 3);

        } else if (dayOfMonth - day == 1) {
            if (month == 12) {
                temp_y++;
                temp_m = 1;
            } else {
                temp_m++;
            }
            list.add(year + "-" + (month) + "-" + (day - 3));
            list.add(year + "-" + (month) + "-" + (day - 2));
            list.add(year + "-" + (month) + "-" + (day - 1));
            list.add(year + "-" + (month) + "-" + day);
            list.add(year + "-" + (month) + "-" + (dayOfMonth));
            list.add(temp_y + "-" + (temp_m) + "-" + 1);
            list.add(temp_y + "-" + (temp_m) + "-" + 2);
        } else if (dayOfMonth - day == 2) {
            if (month == 12) {
                temp_y++;
                temp_m = 1;
            } else {
                temp_m++;
            }
            list.add(year + "-" + (month) + "-" + (day - 3));
            list.add(year + "-" + (month) + "-" + (day - 2));
            list.add(year + "-" + (month) + "-" + (day - 1));
            list.add(year + "-" + (month) + "-" + day);
            list.add(year + "-" + (month) + "-" + (day + 1));
            list.add(year + "-" + (month) + "-" + (dayOfMonth));
            list.add(temp_y + "-" + (temp_m) + "-" + 1);
        } else if (day == 1) {
            if (month == 1) {
                temp_y--;
                temp_m = 12;
            } else {
                temp_m--;
            }
            list.add(temp_y + "-" + (temp_m) + "-" + (TimeUtils.getDaysOfMonth(TimeUtils.isLeapYear(temp_y), temp_m) - 2));
            list.add(temp_y + "-" + (temp_m) + "-" + (TimeUtils.getDaysOfMonth(TimeUtils.isLeapYear(temp_y), temp_m) - 1));
            list.add(temp_y + "-" + (temp_m) + "-" + (TimeUtils.getDaysOfMonth(TimeUtils.isLeapYear(temp_y), temp_m)));
            list.add(year + "-" + (month) + "-" + day);
            list.add(year + "-" + (month) + "-" + (day + 1));
            list.add(year + "-" + (month) + "-" + (day + 2));
            list.add(year + "-" + (month) + "-" + (day + 3));
        } else if (day == 2) {
            if (month == 1) {
                temp_y--;
                temp_m = 12;
            } else {
                temp_m--;
            }
            //            list.add(temp_y + "-" + (temp_m) + "-" + (TimeUtils.getDaysOfMonth(TimeUtils.isLeapYear(temp_y), temp_m) - 2));
            list.add(temp_y + "-" + (temp_m) + "-" + (TimeUtils.getDaysOfMonth(TimeUtils.isLeapYear(temp_y), temp_m) - 1));
            list.add(temp_y + "-" + (temp_m) + "-" + (TimeUtils.getDaysOfMonth(TimeUtils.isLeapYear(temp_y), temp_m)));
            list.add(year + "-" + (month) + "-" + (day - 1));
            list.add(year + "-" + (month) + "-" + day);
            list.add(year + "-" + (month) + "-" + (day + 1));
            list.add(year + "-" + (month) + "-" + (day + 2));
            list.add(year + "-" + (month) + "-" + (day + 3));
        } else if (day == 3) {
            if (month == 1) {
                temp_y--;
                temp_m = 12;
            } else {
                temp_m--;
            }
            list.add(temp_y + "-" + (temp_m) + "-" + (TimeUtils.getDaysOfMonth(TimeUtils.isLeapYear(temp_y), temp_m)));
            list.add(year + "-" + (month) + "-" + (day - 2));
            list.add(year + "-" + (month) + "-" + (day - 1));
            list.add(year + "-" + (month) + "-" + day);
            list.add(year + "-" + (month) + "-" + (day + 1));
            list.add(year + "-" + (month) + "-" + (day + 2));
            list.add(year + "-" + (month) + "-" + (day + 3));
        } else {
            list.add(year + "-" + (month) + "-" + (day - 3));
            list.add(year + "-" + (month) + "-" + (day - 2));
            list.add(year + "-" + (month) + "-" + (day - 1));
            list.add(year + "-" + (month) + "-" + day);
            list.add(year + "-" + (month) + "-" + (day + 1));
            list.add(year + "-" + (month) + "-" + (day + 2));
            list.add(year + "-" + (month) + "-" + (day + 3));
        }

        for (String s : list) {
            MyLogger.jLog().e("=======当前显示的日期===========" + s);
        }
        return list;
    }


    //获取星期
    public static String getWeekDay(String data) {
        //        MyLogger.jLog().e("============" + data);
        try {
            long time = simpleDateFormat.parse(data).getTime();
            Date date = new Date(time);
            String format = dateFm.format(date);
            return Week.Weeks.getWeekDay(format) == null ? Week.WeekCN.getWeekDay(format) : Week.Weeks.getWeekDay(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //将日期变成毫秒值
    public static long dateOfMillis(String data) {
        try {
            long time = simpleDateFormat.parse(data).getTime();
            return time;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //秒转成日期  2017-3-27
    public static String millisOfDate(long millis) {
        return simpleDateFormat.format(new Date(millis * 1000));
    }

    //秒转成日期  2017-03-27
    public static String milliOfDate(long millis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date(millis * 1000));
    }

    //计算结束日期与开始日期相差的天数
    public static int daysBetween(long startTime, long endTime) {

        long between_days = (endTime - startTime) / (3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }
}
