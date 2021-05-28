package wxx.java.appraise.excel;

import com.alibaba.excel.EasyExcel;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import wxx.java.appraise.entity.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

public class ExcelProperty {
    //指定文件输出位置
    @Async
    public Future<String> personalExcel(List<PersonalExcel> list) {
          String fileName = "D:/excel/excel.xlsx";
          EasyExcel.write(fileName,PersonalExcel.class)
            .sheet("个人得分表")
            .doWrite(list);
        /**
         * 写数据到Write上下文中
         * 第一个参数：要写入的内容
         * 第二个参数：要写入的sheet目标
         */
        return new AsyncResult<>("Excel生成成功");
    }

    @Async
    public Future<String> personalDetailsExcel(List<String> grade, List<List<String>> list) {
        String fileName = "D:/excel/excel.xlsx";
        EasyExcel.write(fileName)
          .head(ExcelOutputHead.head(grade))
          .sheet("个人得分表").doWrite(list);
        /**
         * 写数据到Write上下文中
         * 第一个参数：要写入的内容
         * 第二个参数：要写入的sheet目标
         */
      return new AsyncResult<>("Excel生成成功");
    }

  @Async
  public Future<String> personalPartExcel(List<PartExcel> list,String name) {
    String fileName = "D:/excel/"+name;
    EasyExcel.write(fileName)
      .head(PartExcel.class)
      .sheet("详细信息表").doWrite(list);
    return new AsyncResult<>("Excel生成成功");
  }


    @Async
    public Future<String> technologyExcel(List list) throws IOException {
      String fileName = "D:/excel/excel1.xlsx";
      EasyExcel.write(fileName)
        .head(TechnologyExcel.class)
        .sheet("专业得分表").doWrite(list);
        return new AsyncResult<>("Excel生成成功");
    }

  @Async
  public Future<String> tecPartExcel(List<TecPartExcel> list,String name) {
    String fileName = "D:/excel/"+name;
    EasyExcel.write(fileName)
      .head(TecPartExcel.class)
      .sheet("详细信息表").doWrite(list);
    return new AsyncResult<>("Excel生成成功");
  }


  @Async
    public Future<String> ProjectExcel(List list) throws IOException {
    String fileName = "D:/excel/project.xlsx";
    EasyExcel.write(fileName)
      .head(ExcelProject.class)
      .sheet("卷册列表").doWrite(list);
        return new AsyncResult<>("Excel生成成功");
    }

  @Async
  public Future<String> userExcel(List list ,String name) throws IOException {
    String fileName = "D:/excel/" + name;
    EasyExcel.write(fileName)
      .head(UserOut.class)
      .sheet(name.substring(0,name.indexOf("."))).doWrite(list);
    return new AsyncResult<>("Excel生成成功");
  }
}
