package wxx.java.appraise.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class ExcelData extends BaseRowModel {

    @ExcelProperty(value = "姓名" , index = 0)
    private String name;

    @ExcelProperty(value = "部门" , index = 1)
    private String department;

    @ExcelProperty(value = "专业" , index = 2)
    private String technology;

    @ExcelProperty(value = "工号", index = 3)
    private String username;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
