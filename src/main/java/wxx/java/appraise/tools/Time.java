package wxx.java.appraise.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Time {

    public static String timeUtilByTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time1 = time * 1000;
        String creatTime = format.format(time1);
        return creatTime;
    }

    public static  Long getLongDate(){

        Date date = new Date();
        return date.getTime();
    }

    public static String getDate(){
        Calendar calendar = Calendar.getInstance();
        String date = String.valueOf(calendar.get(Calendar.YEAR)) +
                String.valueOf(calendar.get(Calendar.MONTH)) +
                String.valueOf(calendar.get(Calendar.DAY_OF_YEAR));
        return date;
    }
    public static Integer getNowWeek(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
    //string 转date

    public static Date stringToDate(String strTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = formatter.parse(strTime);
        return date;
    }

    public static Date stringToDate1(String strTime) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = formatter.parse(strTime);
        return date;
    }

    //date 转long
    public static long dateToLong(Date date) {
        return date.getTime() / 1000;
    }

    //string 转 long
    public static long stringToLong(String strTime) throws ParseException {
        Date date = stringToDate1(strTime); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }
    
    public static String longToString(String longTime){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(Long.valueOf(longTime));
    }

    /**
     * @param time poi中导出的时间（数字）
     * @return
     */
    public static String timeToDate(String time) {
//        poi工具类中默认获取到的是1900年到现在的天数，那么就将获取到的天数加上1900年的天数，再转化为日期
        Calendar calendar = new GregorianCalendar(1900, 0, -1);
        Date d = calendar.getTime();
        Date dd = DateUtils.addDay(d, Integer.valueOf(time));
        //dateformat 格式化类型
//        return DateUtils.dateToString(dd, dateformat);
        return String.valueOf(dateToLong(dd)*1000);
    }

}
