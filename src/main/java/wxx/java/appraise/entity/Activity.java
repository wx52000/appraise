package wxx.java.appraise.entity;

import java.util.List;
import java.util.Map;

public class Activity {
  private Integer id;

  private String name;

  private double workday;

  private List<String> date;

  private String start_date;

  private String end_date;

  private Integer state;

  private Integer checker;

  private List<String> general;

  private Boolean principalState;

  private List<String> principal;

  private List<Map<String,String>> principal_group;

  private List<VirtualDesigner> designer;

  private String createDate;

  private Integer createUser;

  private List<Map> principalWorkday;

  private List<Map> designerWorkday;

  private List<PrincipalWorkday> principalData;

  private List<DesignerWorkday> designerData;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public double getWorkday() {
    return workday;
  }

  public void setWorkday(double workday) {
    this.workday = workday;
  }

  public List<String> getDate() {
    return date;
  }

  public void setDate(List<String> date) {
    this.date = date;
  }

  public String getStart_date() {
    return start_date;
  }

  public void setStart_date(String start_date) {
    this.start_date = start_date;
  }

  public String getEnd_date() {
    return end_date;
  }

  public void setEnd_date(String end_date) {
    this.end_date = end_date;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public Integer getChecker() {
    return checker;
  }

  public void setChecker(Integer checker) {
    this.checker = checker;
  }

  public List<String> getGeneral() {
    return general;
  }

  public void setGeneral(List<String> general) {
    this.general = general;
  }

  public Boolean getPrincipalState() {
    return principalState;
  }

  public void setPrincipalState(Boolean principalState) {
    this.principalState = principalState;
  }

  public List<String> getPrincipal() {
    return principal;
  }

  public void setPrincipal(List<String> principal) {
    this.principal = principal;
  }

  public List<Map<String, String>> getPrincipal_group() {
    return principal_group;
  }

  public void setPrincipal_group(List<Map<String, String>> principal_group) {
    this.principal_group = principal_group;
  }

  public List<VirtualDesigner> getDesigner() {
    return designer;
  }

  public void setDesigner(List<VirtualDesigner> designer) {
    this.designer = designer;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public Integer getCreateUser() {
    return createUser;
  }

  public void setCreateUser(Integer createUser) {
    this.createUser = createUser;
  }

  public List<Map> getPrincipalWorkday() {
    return principalWorkday;
  }

  public void setPrincipalWorkday(List<Map> principalWorkday) {
    this.principalWorkday = principalWorkday;
  }

  public List<Map> getDesignerWorkday() {
    return designerWorkday;
  }

  public void setDesignerWorkday(List<Map> designerWorkday) {
    this.designerWorkday = designerWorkday;
  }

  public List<PrincipalWorkday> getPrincipalData() {
    return principalData;
  }

  public void setPrincipalData(List<PrincipalWorkday> principalData) {
    this.principalData = principalData;
  }

  public List<DesignerWorkday> getDesignerData() {
    return designerData;
  }

  public void setDesignerData(List<DesignerWorkday> designerData) {
    this.designerData = designerData;
  }
}
