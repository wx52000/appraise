package wxx.java.appraise.tools;

import java.util.ArrayList;
import java.util.List;

public class ExcelOutputHead {

    public static List<List<String>> head(List<String> list){

        List<List<String>> header = new ArrayList<>();

        List<String> first = new ArrayList<>();
        first.add("");
        header.add(first);
        for (int i = 0 ; i < list.size() ; i++) {
          List<String> cell = new ArrayList<>();
          cell.add(list.get(i));
          header.add(cell);
        }

        return header;
    }

}
