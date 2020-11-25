package wxx.java.appraise.tools;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static wxx.java.appraise.config.ScheduledConfig.setDay;

public class AppraiseDate {

  public static Map appDate(){
    Map<Object,Integer> map = new HashMap();
    Calendar calendar = Calendar.getInstance();
    map.put("year",calendar.get(Calendar.YEAR));
    if (calendar.get(Calendar.MONTH) != 0) {
      if (calendar.get(Calendar.DAY_OF_MONTH) >= setDay)
        map.put("month",calendar.get(Calendar.MONTH) + 1);
      else map.put("month",calendar.get(Calendar.MONTH));
    }else {
      if (calendar.get(Calendar.DAY_OF_MONTH) >= setDay)
        map.put("month", calendar.get(Calendar.MONTH) + 1);
      else {
        map.put("month", 12);
        map.replace("year", calendar.get(Calendar.YEAR) - 1);
      }
    }
      return map;
  }

}
