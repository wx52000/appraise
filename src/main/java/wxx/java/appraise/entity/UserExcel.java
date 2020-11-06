package wxx.java.appraise.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class UserExcel extends BaseRowModel {

  private Integer id;

  @ExcelProperty(value = "姓名", index = 0)
  private String name;
  @ExcelProperty(value = "工号", index = 1)
  private String username;
  @ExcelProperty(value = "职位", index = 2)
  private String position;
  @ExcelProperty(value = "部门", index = 3)
  private String department;
  @ExcelProperty(value = "专业", index = 4)
  private String tec;
  @ExcelProperty(value = "", index = 5)
  private String grade;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getTec() {
    return tec;
  }

  public void setTec(String tec) {
    this.tec = tec;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }
}
