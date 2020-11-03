package wxx.java.appraise.entity;

import java.util.List;

public class GradeScore {

    private Integer id;

    private Integer gradeId;

    private Integer scoreId;

    private List<Integer> addScoreId;

    private List<Integer> delScoreId;

    private List<Integer> tec;

    private List<Integer> dep;

    private Integer  stateAll;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

  public List<Integer> getAddScoreId() {
    return addScoreId;
  }

  public void setAddScoreId(List<Integer> addScoreId) {
    this.addScoreId = addScoreId;
  }

  public List<Integer> getDelScoreId() {
    return delScoreId;
  }

  public void setDelScoreId(List<Integer> delScoreId) {
    this.delScoreId = delScoreId;
  }

  public List<Integer> getTec() {
    return tec;
  }

  public void setTec(List<Integer> tec) {
    this.tec = tec;
  }

  public List<Integer> getDep() {
    return dep;
  }

  public void setDep(List<Integer> dep) {
    this.dep = dep;
  }

  public Integer getStateAll() {
    return stateAll;
  }

  public void setStateAll(Integer stateAll) {
    this.stateAll = stateAll;
  }
}
