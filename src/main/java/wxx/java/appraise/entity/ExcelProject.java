package wxx.java.appraise.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;


public class ExcelProject extends BaseRowModel {

    private Integer pid ;

    private Integer vid;


    @ExcelProperty(value = "卷册号" ,index = 0)
    private String number;

    @ExcelProperty(value = "图名" ,index = 1)
    private String volumeName;

    @ExcelProperty(value = "项目名称" ,index = 2)
    private String projectName;

    private Integer tid;
    @ExcelProperty(value = "专业" ,index = 3)
    private String tec;


    @ExcelProperty(value = "计划出版日期" ,index = 4)
    private String plannedPublicationDate;

    @ExcelProperty(value = "实际最终出版日期" ,index = 5)
    private String actualPublicationDate;

    @ExcelProperty(value = "计划出手时间" ,index = 6)
    private String planned_shot_date;


    @ExcelProperty(value = "出手日期" ,index = 7)
    private String shotDate;

    @ExcelProperty(value = "校审完成时间" ,index = 8)
    private String proofreading_date;

    @ExcelProperty(value = "院交出时间" ,index = 9)
    private String complete_time;

    private Integer designerId;
    @ExcelProperty(value = "设计人" ,index = 10)
    private String designer;

    @ExcelProperty(value = "上月底已完成比例" ,index = 11)
    private String designerLastMonth;

    @ExcelProperty(value = "本月第1周完成比例(%)" ,index = 12)
    private String designerOne;

    @ExcelProperty(value = "备注(存在问题、延期原因等)" ,index = 13)
    private String designerOneRemark;


    @ExcelProperty(value = "本月第2周完成比例(%)" ,index = 14)
    private String designerTwo;

    @ExcelProperty(value = "备注(存在问题、延期原因等)" ,index = 15)
    private String DesignerTwoRemark;

    @ExcelProperty(value = "本月第3周完成比例(%)" ,index = 16)
    private String designerThree;

    @ExcelProperty(value = "备注(存在问题、延期原因等)" ,index = 17)
    private String designerThreeRemark;

    @ExcelProperty(value = "本月第4周完成比例(%)" ,index = 18)
    private String designerFour;

    @ExcelProperty(value = "备注(存在问题、延期原因等)" ,index = 19)
    private String designerFourRemark;

    @ExcelProperty(value = "本月第5周完成比例" ,index = 20)
    private String designerFive;

    @ExcelProperty(value = "备注(存在问题、延期原因等)" ,index = 21)
    private String designerFiveRemark;

    private Integer checkerId;
    @ExcelProperty(value = "互校人" ,index = 22)
    private String checker;

    @ExcelProperty(value = "互校完成日期" ,index = 23)
    private String checkerCompletionDate;

    @ExcelProperty(value = "上月底已完成比例" ,index = 24)
    private String checkerLastMonth;

    @ExcelProperty(value = "上周完成比例(%)" ,index = 25)
    private String checkerLastWeek;

    @ExcelProperty(value = "本周完成比例(%)" ,index = 26)
    private String checkerNowWeek;

    @ExcelProperty(value = "延期原因" ,index = 27)
    private String checkerRemark;


    private Integer principalId;
    @ExcelProperty(value = "主设人校" ,index = 28)
    private String principal;

//    @ExcelProperty(value = "完成日期" ,index = 28)
//    private String principalCompletionDate;

    @ExcelProperty(value = "上周完成比例(%)" ,index = 29)
    private String principalLastWeek;

    @ExcelProperty(value = "本周完成比例(%)" ,index = 30)
    private String principalNowWeek;

    @ExcelProperty(value = "延期原因" ,index = 31)
    private String principalRemark;
//
//    private Integer headmanId;
//    @ExcelProperty(value = "组长校" ,index = 32)
//    private String headman;
//
//    @ExcelProperty(value = "完成日期" ,index = 33)
//    private String headmanCompletionDate;
//
//    @ExcelProperty(value = "上周完成比例(%)" ,index = 34)
//    private String headmanLastWeek;
//
//    @ExcelProperty(value = "本周完成比例(%)" ,index = 35)
//    private String headmanNowWeek;
//
//    @ExcelProperty(value = "延期原因" ,index = 36)
//    private String headmanRemark;
//
//    @ExcelProperty(value = "备注" ,index = 37)
//    private String remark;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getVolumeName() {
        return volumeName;
    }

    public void setVolumeName(String volumeName) {
        this.volumeName = volumeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName.trim();
    }

    public String getTec() {
        return tec;
    }

    public void setTec(String tec) {
        this.tec = tec;
    }


    public String getPlannedPublicationDate() {
        return plannedPublicationDate;
    }

    public void setPlannedPublicationDate(String plannedPublicationDate) {
        this.plannedPublicationDate = plannedPublicationDate;
    }

    public String getActualPublicationDate() {
        return actualPublicationDate;
    }

    public void setActualPublicationDate(String actualPublicationDate) {
        this.actualPublicationDate = actualPublicationDate;
    }

    public String getPlanned_shot_date() {
      return planned_shot_date;
    }

    public void setPlanned_shot_date(String planned_shot_date) {
      this.planned_shot_date = planned_shot_date;
    }

    public String getProofreading_date() {
      return proofreading_date;
    }

    public void setProofreading_date(String proofreading_date) {
      this.proofreading_date = proofreading_date;
    }

    public String getComplete_time() {
      return complete_time;
    }

    public void setComplete_time(String complete_time) {
      this.complete_time = complete_time;
    }

   public String getDesigner() {
        return designer;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public String getShotDate() {
        return shotDate;
    }

    public void setShotDate(String shotDate) {
        this.shotDate = shotDate;
    }

    public String getDesignerLastMonth() {
        return designerLastMonth;
    }

    public void setDesignerLastMonth(String designerLastMonth) {
        this.designerLastMonth = designerLastMonth;
    }

    public String getDesignerOne() {
        return designerOne;
    }

    public void setDesignerOne(String designerOne) {
        this.designerOne = designerOne;
    }

    public String getDesignerOneRemark() {
        return designerOneRemark;
    }

    public void setDesignerOneRemark(String designerOneRemark) {
        this.designerOneRemark = designerOneRemark;
    }

    public String getDesignerTwo() {
        return designerTwo;
    }

    public void setDesignerTwo(String designerTwo) {
        this.designerTwo = designerTwo;
    }

    public String getDesignerTwoRemark() {
        return DesignerTwoRemark;
    }

    public void setDesignerTwoRemark(String designerTwoRemark) {
        DesignerTwoRemark = designerTwoRemark;
    }

    public String getDesignerThree() {
        return designerThree;
    }

    public void setDesignerThree(String designerThree) {
        this.designerThree = designerThree;
    }

    public String getDesignerThreeRemark() {
        return designerThreeRemark;
    }

    public void setDesignerThreeRemark(String designerThreeRemark) {
        this.designerThreeRemark = designerThreeRemark;
    }

    public String getDesignerFour() {
        return designerFour;
    }

    public void setDesignerFour(String designerFour) {
        this.designerFour = designerFour;
    }

    public String getDesignerFourRemark() {
        return designerFourRemark;
    }

    public void setDesignerFourRemark(String designerFourRemark) {
        this.designerFourRemark = designerFourRemark;
    }

    public String getDesignerFive() {
        return designerFive;
    }

    public void setDesignerFive(String designerFive) {
        this.designerFive = designerFive;
    }

    public String getDesignerFiveRemark() {
        return designerFiveRemark;
    }

    public void setDesignerFiveRemark(String designerFiveRemark) {
        this.designerFiveRemark = designerFiveRemark;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getCheckerCompletionDate() {
        return checkerCompletionDate;
    }

    public void setCheckerCompletionDate(String checkerCompletionDate) {
        this.checkerCompletionDate = checkerCompletionDate;
    }

    public String getCheckerLastMonth() {
        return checkerLastMonth;
    }

    public void setCheckerLastMonth(String checkerLastMonth) {
        this.checkerLastMonth = checkerLastMonth;
    }

    public String getCheckerLastWeek() {
        return checkerLastWeek;
    }

    public void setCheckerLastWeek(String checkerLastWeek) {
        this.checkerLastWeek = checkerLastWeek;
    }

    public String getCheckerNowWeek() {
        return checkerNowWeek;
    }

    public void setCheckerNowWeek(String checkerNowWeek) {
        this.checkerNowWeek = checkerNowWeek;
    }

    public String getCheckerRemark() {
        return checkerRemark;
    }

    public void setCheckerRemark(String checkerRemark) {
        this.checkerRemark = checkerRemark;
    }


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public Integer getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Integer checkerId) {
        this.checkerId = checkerId;
    }

  public Integer getPrincipalId() {
    return principalId;
  }

  public void setPrincipalId(Integer principalId) {
    this.principalId = principalId;
  }

  public String getPrincipal() {
    return principal;
  }

  public void setPrincipal(String principal) {
    this.principal = principal;
  }

  public String getPrincipalLastWeek() {
    return principalLastWeek;
  }

  public void setPrincipalLastWeek(String principalLastWeek) {
    this.principalLastWeek = principalLastWeek;
  }

  public String getPrincipalNowWeek() {
    return principalNowWeek;
  }

  public void setPrincipalNowWeek(String principalNowWeek) {
    this.principalNowWeek = principalNowWeek;
  }

  public String getPrincipalRemark() {
    return principalRemark;
  }

  public void setPrincipalRemark(String principalRemark) {
    this.principalRemark = principalRemark;
  }
}
