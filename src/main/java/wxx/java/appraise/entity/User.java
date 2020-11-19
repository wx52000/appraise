package wxx.java.appraise.entity;

import java.util.List;

public class User {
    private Integer id;

    private Integer pid;

    private Integer did;

    private Integer tid;

    private List<Object> queryByd;

    private List<Object> queryByt;

    private String name;

    private String username;

    private Integer grade;

    private String paw;

    private Integer pageIndex;

    private Integer pageSize;

    private Integer thisMonth;

    private Integer thisDay;

    private List<Integer> tIds;

    private List<Integer> dIds;

    private String selectName;

    private Integer selectType;




    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  public String getPaw() {
        return paw;
    }

    public void setPaw(String paw) {
        this.paw = paw;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(Integer thisMonth) {
        this.thisMonth = thisMonth;
    }

    public Integer getThisDay() {
        return thisDay;
    }

    public void setThisDay(Integer thisDay) {
        this.thisDay = thisDay;
    }

    public List<Object> getQueryByd() {
        return queryByd;
    }

    public void setQueryByd(List<Object> queryByd) {
        this.queryByd = queryByd;
    }

    public List<Object> getQueryByt() {
        return queryByt;
    }

    public void setQueryByt(List<Object> queryByt) {
        this.queryByt = queryByt;
    }

    public List<Integer> gettIds() {
        return tIds;
    }

    public void settIds(List<Integer> tIds) {
        this.tIds = tIds;
    }

    public List<Integer> getdIds() {
        return dIds;
    }

    public void setdIds(List<Integer> dIds) {
        this.dIds = dIds;
    }

  public String getSelectName() {
    return selectName;
  }

  public void setSelectName(String selectName) {
    this.selectName = selectName;
  }

  public Integer getSelectType() {
    return selectType;
  }

  public void setSelectType(Integer selectType) {
    this.selectType = selectType;
  }
}
