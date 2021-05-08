package wxx.java.appraise.tools;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static wxx.java.appraise.config.ScheduledConfig.setDay;

public class AppraiseDate {

  public static Map appDate() {
    Map<Object, Integer> map = new HashMap();
    Calendar calendar = Calendar.getInstance();
    //获取当前年份
    map.put("year", calendar.get(Calendar.YEAR));
    //如果当年月份不为1月
    if (calendar.get(Calendar.MONTH) != 0) {
      //当前日期大约评价开始日期
      if (calendar.get(Calendar.DAY_OF_MONTH) >= setDay)
        map.put("month", calendar.get(Calendar.MONTH) + 1);
//      否则赋值月份为上月
      else map.put("month", calendar.get(Calendar.MONTH));
    } else {
      if (calendar.get(Calendar.DAY_OF_MONTH) >= setDay)
        map.put("month", calendar.get(Calendar.MONTH) + 1);
      else {
        map.put("month", 12);
        map.replace("year", calendar.get(Calendar.YEAR) - 1);
      }
    }
    return map;
  }

  public static Boolean quarter(Integer month) {
    Integer nowMonth;
    Integer nowDay;
    Integer monthAdd3;
    Calendar calendar = Calendar.getInstance();
    nowMonth = calendar.get(Calendar.MONTH)+1;
    nowDay = calendar.get(Calendar.DAY_OF_MONTH);
    if (nowMonth < month)
      nowMonth = nowMonth + 12;
    if (Math.abs(nowMonth - month) < 3){
      return  true;
    }else if (Math.abs(nowMonth - month) == 3){
      if (nowDay >= 25)
        return false;
      else
        return true;
    }else
      return  false;
  }

  public static Integer year(Integer month) {
    Integer year;
    Integer nowMonth;
    Calendar calendar = Calendar.getInstance();
    nowMonth = calendar.get(Calendar.MONTH) + 1;
    year = calendar.get(Calendar.YEAR);
    if (nowMonth <= 3) {
      if (month > 3)
        return --year;
      else
        return year;
    } else if (nowMonth <= 6) {
      if (month > 6)
        return --year;
      else
        return year;
    } else if (nowMonth <= 9) {
      if (month > 9)
        return --year;
      else
        return year;
    } else
      return year;
  }
}
