package wxx.java.appraise.entity;

import java.util.List;

public class VirtualDesigner {
  private Integer id;

  private String principal;

  private List<Integer> list;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPrincipal() {
    return principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  public List<Integer> getList() {
    return list;
  }

  public void setList(List<Integer> list) {
    this.list = list;
  }
}
