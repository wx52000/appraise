package wxx.java.appraise.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class TechnologyExcel extends BaseRowModel {

    @ExcelProperty(value = "部门" ,index = 2)
    private  String department;

    @ExcelProperty(value = "专业" ,index = 3)
    private  String technology;

    @ExcelProperty(value = "得分" ,index = 4)
    private  Double score;


    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
