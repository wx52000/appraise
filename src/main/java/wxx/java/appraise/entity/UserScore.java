package wxx.java.appraise.entity;

public class UserScore {

    private Integer id;

    private Integer gsId;

    private Double designer;

    private Double personal;

    private Double coordinate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGsId() {
        return gsId;
    }

    public void setGsId(Integer gsId) {
        this.gsId = gsId;
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
}
