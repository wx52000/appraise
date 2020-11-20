package wxx.java.appraise.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class PartExcel extends BaseRowModel {

  @ExcelProperty(value = "打分人" , index = 0)
  private String grade;

  @ExcelProperty(value = "被打分人" , index = 1)
  private String score;

  @ExcelProperty(value = "部门" , index = 5)
  private String department;

  @ExcelProperty(value = "专业" , index = 6)
  private String technology;

  @ExcelProperty(value = "专业" , index = 2)
  private String personal;

  @ExcelProperty(value = "专业" , index = 3)
  private String designer;

  @ExcelProperty(value = "专业" , index = 4)
  private String coordinate;

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getScore() {
    return score;
  }

  public void setScore(String score) {
    this.score = score;
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

  public String getPersonal() {
    return personal;
  }

  public void setPersonal(String personal) {
    this.personal = personal;
  }

  public String getDesigner() {
    return designer;
  }

  public void setDesigner(String designer) {
    this.designer = designer;
  }

  public String getCoordinate() {
    return coordinate;
  }

  public void setCoordinate(String coordinate) {
    this.coordinate = coordinate;
  }
}
