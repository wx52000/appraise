package wxx.java.appraise.entity;

public class WorkDayTec {

  private Integer id;

  private Integer projectId;

  private String tec;

  private Double ratio;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getProjectId() {
    return projectId;
  }

  public void setProjectId(Integer projectId) {
    this.projectId = projectId;
  }

  public String getTec() {
    return tec;
  }

  public void setTec(String tec) {
    this.tec = tec;
  }

  public Double getRatio() {
    return ratio;
  }

  public void setRatio(Double ratio) {
    this.ratio = ratio;
  }
}
