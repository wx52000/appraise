package wxx.java.appraise.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class PartExcel extends BaseRowModel {

  @ExcelProperty(value = "打分人" , index = 0)
  private String grade;

  @ExcelProperty(value = "被打分人" , index = 1)
  private String score;

  @ExcelProperty(value = "部门" , index = 5)
  private String dep;

  @ExcelProperty(value = "专业" , index = 6)
  private String tec;

  @ExcelProperty(value = "进度得分" , index = 3)
  private String personal;

  @ExcelProperty(value = "质量得分" , index = 2)
  private String designer;

  @ExcelProperty(value = "配合得分" , index = 4)
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

  public String getDep() {
    return dep;
  }

  public void setDep(String dep) {
    this.dep = dep;
  }

  public String getTec() {
    return tec;
  }

  public void setTec(String tec) {
    this.tec = tec;
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
