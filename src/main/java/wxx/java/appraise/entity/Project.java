package wxx.java.appraise.entity;

import java.util.List;
import java.util.Set;

public class Project {

    private Integer id;

    private String number;

    private String name;

    private Integer days;

    private Integer manageDays;

    private Integer designDays;

    private Integer spareDays;

    private Integer creator;

    private Long creatorTime;

    private List<Volume> volumes;

    private Set<Integer> projectUsers;

    private Set<Integer> projectTec;

    private Integer powerId;

    private Set<Integer> general;

    private Integer spider;

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

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getManageDays() {
        return manageDays;
    }

    public void setManageDays(Integer manageDays) {
        this.manageDays = manageDays;
    }

    public Integer getDesignDays() {
        return designDays;
    }

    public void setDesignDays(Integer designDays) {
        this.designDays = designDays;
    }

    public Integer getSpareDays() {
        return spareDays;
    }

    public void setSpareDays(Integer spareDays) {
        this.spareDays = spareDays;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Long getCreatorTime() {
        return creatorTime;
    }

    public void setCreatorTime(Long creatorTime) {
        this.creatorTime = creatorTime;
    }

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Volume> volumes) {
        this.volumes = volumes;
    }

    public Set<Integer> getProjectUsers() {
        return projectUsers;
    }

    public void setProjectUsers(Set<Integer> projectUsers) {
        this.projectUsers = projectUsers;
    }

    public Set<Integer> getProjectTec() {
        return projectTec;
    }

    public void setProjectTec(Set<Integer> projectTec) {
        this.projectTec = projectTec;
    }

    public Integer getPowerId() {
        return powerId;
    }

    public void setPowerId(Integer powerId) {
        this.powerId = powerId;
    }


    public Set<Integer> getGeneral() {
        return general;
    }

    public void setGeneral(Set<Integer> general) {
        this.general = general;
    }

  public Integer getSpider() {
    return spider;
  }

  public void setSpider(Integer spider) {
    this.spider = spider;
  }
}
