package wxx.java.appraise.entity;

import java.util.List;
import java.util.Map;

public class ProjectExcelTec {
  private Integer id;

  private String number;

  private String name;

  private List<Map<String,String>> list;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Map<String, String>> getList() {
    return list;
  }

  public void setList(List<Map<String, String>> list) {
    this.list = list;
  }
}
