package wxx.java.appraise.entity;

import java.util.List;

public class DesignerWorkday {

  private Integer principal;

  private List<PrincipalWorkday> list;

  public Integer getPrincipal() {
    return principal;
  }

  public void setPrincipal(Integer principal) {
    this.principal = principal;
  }

  public List<PrincipalWorkday> getList() {
    return list;
  }

  public void setList(List<PrincipalWorkday> list) {
    this.list = list;
  }
}
