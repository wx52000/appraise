package wxx.java.appraise.entity;

import java.math.BigDecimal;

public class WorkdayTecUsed {

  private Integer id;

  private String tec;

  private BigDecimal have;

  private BigDecimal backup;

  private BigDecimal used;

  private Integer count;

  private BigDecimal ratio;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTec() {
    return tec;
  }

  public void setTec(String tec) {
    this.tec = tec;
  }

  public BigDecimal getHave() {
    return have;
  }

  public void setHave(BigDecimal have) {
    this.have = have;
  }

  public BigDecimal getBackup() {
    return backup;
  }

  public void setBackup(BigDecimal backup) {
    this.backup = backup;
  }

  public BigDecimal getUsed() {
    return used;
  }

  public void setUsed(BigDecimal used) {
    this.used = used;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public BigDecimal getRatio() {
    return ratio;
  }

  public void setRatio(BigDecimal ratio) {
    this.ratio = ratio;
  }
}
