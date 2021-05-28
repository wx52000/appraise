package wxx.java.appraise.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wxx.java.appraise.entity.ExcelData;
import wxx.java.appraise.entity.UserExcel;
import wxx.java.appraise.service.DepartmentService;
import wxx.java.appraise.service.TechnologyService;
import wxx.java.appraise.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserExcelListener extends AnalysisEventListener<UserExcel> {
  private UserService userService;
  private DepartmentService departmentService;
  private TechnologyService technologyService;
  @Autowired
  public void setUserService(UserService userService){
    this.userService = userService;
  }
  @Autowired
  public void setDepartmentService(DepartmentService departmentService){
    this.departmentService = departmentService;
  }
  @Autowired
  public void setTechnologyService(TechnologyService technologyService){
    this.technologyService = technologyService;
  }

  private List<ExcelData> list= new ArrayList<>();
  @Override
  public void invoke(UserExcel userExcel, AnalysisContext analysisContext) {
    if (userExcel.getName() != null&& userExcel.getName() != "") {
      ExcelData excelData = new ExcelData();
      excelData.setName(userExcel.getName());
      excelData.setUsername(userExcel.getUsername());
      Integer dep = departmentService.queryName(userExcel.getDepartment());
      if (dep != null)
        excelData.setDepartment(dep.toString());
      else {
        excelData.setDepartment(departmentService.addString(userExcel.getDepartment()).toString());
      }
      Integer tec = technologyService.queryByName(userExcel.getTec());
      if (tec != null)
        excelData.setTechnology(tec.toString());
      else
        excelData.setTechnology(technologyService.addString(userExcel.getTec(), Integer.valueOf(excelData.getDepartment())).toString());
      if (userExcel.getGrade() != null&&userExcel.getGrade()!= "")
        excelData.setGrade(1);
      else excelData.setGrade(0);
      list.add(excelData);
    }
  }

  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext){
    userService.addExcel(list);
    System.out.println(list);
  }
}
