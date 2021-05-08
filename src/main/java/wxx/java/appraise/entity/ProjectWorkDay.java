package wxx.java.appraise.entity;


public class ProjectWorkDay {
  private Integer id;

  private  Integer projectId;

  private double workDay;

  private Integer type;

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

  public double getWorkDay() {
    return workDay;
  }

  public void setWorkDay(double workDay) {
    this.workDay = workDay;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
