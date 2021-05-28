package wxx.java.appraise.excel;

import java.util.ArrayList;
import java.util.List;

public class ExcelOutputHead {

    public static List<List<String>> head(List<String> list){
        List<List<String>> header = new ArrayList<>();
        List<String> first = new ArrayList<>();
        first.add("");
        header.add(first);
//        for (int i = 0 ; i < list.size() ; i++) {
//          List<String> cell = new ArrayList<>();
//          cell.add(list.get(i));
//          header.add(cell);
//        }
      list.stream().forEach( o -> {
          List<String> cell = new ArrayList<>();
          cell.add(o);
          header.add(cell);
        }
      );

        return header;
    }

  public static List<List<String>> headList(List<String> list ){
    List<List<String>> header = new ArrayList<>();
//    for (int i = 0 ; i < list.size() ; i++) {
//      List<String> cell = new ArrayList<>();
//        cell.add(list.get(i));
//        header.add(cell);
//    }
    list.stream().forEach( o -> {
      List<String> cell = new ArrayList<>();
      cell.add(o);
      header.add(cell);
      }
    );
    return header;
  }

  public static List<List<String>> ComplexList(String name ,List<String> list ){
    List<List<String>> header = new ArrayList<>();
//    for (int i = 0 ; i < list.size() ; i++) {
//      List<String> cell = new ArrayList<>();
//      cell.add(name);
//      cell.add(list.get(i));
//      header.add(cell);
//    }
    list.stream().forEach( o -> {
        List<String> cell = new ArrayList<>();
        cell.add(name);
        cell.add(o);
        header.add(cell);
      }
    );
    return header;
  }
}
