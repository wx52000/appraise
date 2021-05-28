package wxx.java.appraise.entity;

import java.util.List;
import java.util.Map;

public class Virtual {
  private Integer id;

  private String name;

  private String number;

  private double workday;

  private List<String> general;

  private Boolean principalState;

  private List<String> principal;

  private List<Map<String,String>> principal_group;

  private List<VirtualDesigner> designer;

  private String createDate;

  private Integer createUser;

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

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }


  public double getWorkday() {
    return workday;
  }

  public void setWorkday(double workday) {
    this.workday = workday;
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

  public List<VirtualDesigner> getDesigner() {
    return designer;
  }

  public void setDesigner(List<VirtualDesigner> designer) {
    this.designer = designer;
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
