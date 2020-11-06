package wxx.java.appraise.entity;

public class TecScore {

    private Integer id;

    private Integer gradeId;

    private Integer tecId;

    private Double designer;

    private Double personal;

    private Double coordinate;

    private Long date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTecId() {
      return tecId;
    }

    public void setTecId(Integer tecId) {
      this.tecId = tecId;
    }

    public Long getDate() {
      return date;
    }

  public void setDate(Long date) {
    this.date = date;
  }

  public Double getDesigner() {
        return designer;
    }

    public void setDesigner(Double designer) {
        this.designer = designer;
    }

    public Double getPersonal() {
        return personal;
    }

    public void setPersonal(Double personal) {
        this.personal = personal;
    }

    public Double getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Double coordinate) {
        this.coordinate = coordinate;
    }

    public Integer getGradeId() {
      return gradeId;
    }

    public void setGradeId(Integer gradeId) {
      this.gradeId = gradeId;
    }
}
