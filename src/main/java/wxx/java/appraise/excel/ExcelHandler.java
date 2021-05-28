package wxx.java.appraise.excel;

import com.alibaba.excel.event.WriteHandler;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import wxx.java.appraise.entity.ExcelProject;
import wxx.java.appraise.tools.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class ExcelHandler implements WriteHandler {

    private List<ExcelProject> list = new ArrayList<>();

    private String nowDate = DateUtils.getCurrentDate();
    @Override
    public void sheet(int i, Sheet sheet) {
    }

    @Override
    public void row(int i, Row row) {


    }
    public void setList(List list){
        this.list = list;
    }

    @Override
    public void cell(int i, Cell cell) {
        System.out.println(cell);
        Workbook workbook = cell.getSheet().getWorkbook();
        CellStyle cellStyle = workbook.createCellStyle();
        if (cell.getRowIndex() > 0) {
            if (list.get(cell.getRowIndex() - 1).getPlanned_shot_date() != null) {
                if (list.get(cell.getRowIndex() - 1).getShotDate() != null) {

                    if (DateUtils.compareDate(list.get(cell.getRowIndex() - 1).getPlanned_shot_date(),list.get(cell.getRowIndex() - 1).getShotDate()) == -1) {
                        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置前景填充样式
                        cellStyle.setFillForegroundColor(HSSFColor.RED.index);//前景填充色
                    }
                }else {
                        if (DateUtils.compareDate(list.get(cell.getRowIndex()-1).getPlannedPublicationDate(),nowDate) == -1 ) {
                            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);//设置前景填充样式
                            cellStyle.setFillForegroundColor(HSSFColor.RED.index);//前景填充色
                        }
                    }
            }
        }
        cell.setCellStyle(cellStyle);
    }


}
