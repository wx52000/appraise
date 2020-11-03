package wxx.java.appraise.entity;

import java.util.List;

public class GradeScore {

    private Integer id;

    private Integer gradeId;

    private Integer scoreId;

    private List<Integer> scoreIds;

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

  public List<Integer> getScoreIds() {
    return scoreIds;
  }

  public void setScoreIds(List<Integer> scoreIds) {
    this.scoreIds = scoreIds;
  }
}
