package com.example.zdm.horizontaldatedemo.constants;

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
 * <p>
 * <p>
 * <p>
 * Created by zdm on 2017/4/6/0006.
 * <p>
 * 描述:
 */

public class Week {
    public enum Weeks {

        MON("Monday", "周一"),
        TUE("Tuesday", "周二"),
        WED("Wednesday", "周三"),
        THU("Thursday", "周四"),
        FRI("Friday", "周五"),
        SAT("Saturday", "周六"),
        SUN("Sunday", "周日");

        private String week;
        private String weekDay;

        Weeks(String week, String weekDay) {
            this.week = week;
            this.weekDay = weekDay;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDayc(String weekDay) {
            this.weekDay = weekDay;
        }


        // 普通方法
        public static String getWeekDay(String str) {
            for (Weeks weeks : Weeks.values()) {
                if (weeks.getWeek().equals(str)) {
                    return weeks.weekDay;
                }
            }
            return null;
        }

    }


    public enum WeekCN {
        MON("星期一", "周一"),
        TUE("星期二", "周二"),
        WED("星期三", "周三"),
        THU("星期四", "周四"),
        FRI("星期五", "周五"),
        SAT("星期六", "周六"),
        SUN("星期日", "周日");

        private String week;
        private String weekDay;

        WeekCN(String week, String weekDay) {
            this.week = week;
            this.weekDay = weekDay;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDayc(String weekDay) {
            this.weekDay = weekDay;
        }


        // 普通方法
        public static String getWeekDay(String str) {
            for (WeekCN weeks : WeekCN.values()) {
                if (weeks.getWeek().equals(str)) {
                    return weeks.weekDay;
                }
            }
            return null;
        }

    }

}
