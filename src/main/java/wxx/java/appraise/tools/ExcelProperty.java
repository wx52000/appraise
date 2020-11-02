package wxx.java.appraise.tools;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.entity.PersonalExcel;
import wxx.java.appraise.entity.TechnologyExcel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Future;

public class ExcelProperty {
    //指定文件输出位置
    @Async
    public Future<String> personalExcel(List<PersonalExcel> list) {
        OutputStream outputStream = null;
        ExcelWriter excelWriter = null;
        try {
        outputStream = new FileOutputStream("D:/excel/excel.xlsx");
        excelWriter = EasyExcelFactory.getWriter(outputStream);
        //将要输出的内容填充到Sheet里
        Sheet sheet = new Sheet(1, 0, PersonalExcel.class);
        //设置sheet表名
        sheet.setSheetName("个人得分表");
        /**
         * 写数据到Write上下文中
         * 第一个参数：要写入的内容
         * 第二个参数：要写入的sheet目标
         */
        excelWriter.write(list, sheet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            excelWriter.finish();
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new AsyncResult<>("Excel生成成功");
    }

    @Async
    public Future<String> technologyExcel(List list) throws IOException {
        OutputStream outputStream = null;
        ExcelWriter excelWriter = null;
        try {
            outputStream = new FileOutputStream("D:/excel/excel1.xlsx");
            excelWriter = EasyExcelFactory.getWriter(outputStream);
            //将要输出的内容填充到Sheet里
            Sheet sheet = new Sheet(1, 0, TechnologyExcel.class);
            //设置sheet表名
            sheet.setSheetName("专业得分表");
            /**
             * 写数据到Write上下文中
             * 第一个参数：要写入的内容
             * 第二个参数：要写入的sheet目标
             */
            excelWriter.write(list, sheet);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            excelWriter.finish();
            outputStream.close();
        }
        return new AsyncResult<>("Excel生成成功");
    }

    @Async
    public Future<String> ProjectExcel(List list) throws IOException {
        OutputStream outputStream = null;
        ExcelWriter excelWriter = null;
        ExcelHandler handler = new ExcelHandler();
        handler.setList(list);
        try {
            outputStream = new FileOutputStream("D:/excel/project.xlsx");
            excelWriter =  new ExcelWriter(null, outputStream, ExcelTypeEnum.XLSX, true, handler);
            //将要输出的内容填充到Sheet里
            Sheet sheet = new Sheet(1, 0, ExcelProject.class);
            //设置sheet表名
            sheet.setSheetName("卷册列表");
            /**
             * 写数据到Write上下文中
             * 第一个参数：要写入的内容
             * 第二个参数：要写入的sheet目标
             */
            excelWriter.write(list, sheet );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            excelWriter.finish();
            outputStream.close();
        }
        return new AsyncResult<>("Excel生成成功");
    }
}
