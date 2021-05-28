package wxx.java.appraise.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelOutData {

  public static List<List<String>> dataTec(List<Map<String,String>> list){
    List<List<String>> lists = new ArrayList<>();
    list.stream().forEach((Map<String,String> o) ->{
      List<String> list1 = new ArrayList<>();
      list1.add(o.get("tec"));
      list1.add(o.get("count"));
      list1.add(o.get("have"));
      list1.add(o.get("backup"));
      list1.add(o.get("used"));
      list1.add(o.get("ratio"));
      lists.add(list1);
    });
    return lists;
  }



  public static List<List<String>> dataVolume(List<Map<String,String>> list,Integer mode){
    List<List<String>> lists = new ArrayList<>();
    list.stream().forEach((Map<String,String> o) ->{
      List<String> list1 = new ArrayList<>();
      if (mode == 1){
        list1.add(o.get("pNumber"));
        list1.add(o.get("pName"));
      }
      list1.add(o.get("number"));
      list1.add(o.get("name"));
      list1.add(o.get("dep"));
      list1.add(o.get("tec"));
      list1.add(o.get("state"));
      list1.add(o.get("workday"));
      list1.add(o.get("principal"));
      list1.add(o.get("designer"));
      list1.add(o.get("checker"));
      list1.add(o.get("planned_start_date"));
      list1.add(o.get("start_date"));
      list1.add(o.get("planned_shot_date"));
      list1.add(o.get("shot_date"));
      list1.add(o.get("proofreading_date"));
      list1.add(o.get("planned_publication_date"));
      list1.add(o.get("actual_publication_date"));
      list1.add(o.get("complete_time"));
      lists.add(list1);
    });
    return lists;
  }

  public static List<List<String>> everyone(List<Map<String,String>> list,Integer mode){
    List<List<String>> lists = new ArrayList<>();
    list.stream().forEach((Map<String,String> o) ->{
      List<String> list1 = new ArrayList<>();
      if (mode == 0 || mode == 1){
      list1.add(o.get("username"));
      list1.add(o.get("name"));
      }else if (mode == 2){
        list1.add(o.get("number"));
        list1.add(o.get("name"));
      }
      if (mode == 0) {
        list1.add(o.get("amount_p2"));
        list1.add(o.get("amount_d2"));
        list1.add(o.get("amount_c2"));
        list1.add(o.get("workday_p2"));
        list1.add(o.get("workday_d2"));
        list1.add(o.get("workday_c2"));
        list1.add(o.get("workday_sum2"));
        list1.add(o.get("amount_p1"));
        list1.add(o.get("amount_d1"));
        list1.add(o.get("amount_c1"));
        list1.add(o.get("workday_p1"));
        list1.add(o.get("workday_d1"));
        list1.add(o.get("workday_c1"));
        list1.add(o.get("workday_sum1"));
      }
      list1.add(o.get("amount_p"));
      list1.add(o.get("amount_d"));
      list1.add(o.get("amount_c"));
      list1.add(o.get("workday_p"));
      list1.add(o.get("workday_d"));
      list1.add(o.get("workday_c"));
      list1.add(o.get("workday_sum"));
      lists.add(list1);
    });
    return lists;
  }
}
