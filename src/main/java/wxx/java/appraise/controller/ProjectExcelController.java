package wxx.java.appraise.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.ProjectExcelTec;
import wxx.java.appraise.excel.ExcelOutData;
import wxx.java.appraise.excel.ExcelOutputHead;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ProjectService;
import wxx.java.appraise.service.ProjectTecService;
import wxx.java.appraise.service.ProjectWorkDayService;
import wxx.java.appraise.service.VolumeService;
import wxx.java.appraise.tools.Download;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("projectExcel")
public class ProjectExcelController {
  private ProjectService projectService;
  private ProjectTecService projectTecService;
  private ProjectWorkDayService projectWorkDayService;
  private VolumeService volumeService;
  @Autowired
  public void setProjectService(ProjectService projectService){
    this.projectService = projectService;
  }
  @Autowired
  public void setProjectTecService(ProjectTecService projectTecService){
    this.projectTecService =projectTecService;
  }
  @Autowired
  public void setProjectWorkDayService(ProjectWorkDayService projectWorkDayService){
    this.projectWorkDayService = projectWorkDayService;
  }
  @Autowired
  public void setVolumeService(VolumeService volumeService){
    this.volumeService = volumeService;
  }
  /*
  all为项目管理页面下载；其余为项目页面下载
  */
  @RequestMapping("statisticAll")
  public Result statisticAll(HttpServletResponse response, HttpServletRequest request){
    String min = request.getParameter("minDay");
    String max = request.getParameter("maxDay");
    List<String> listHeader = Arrays.asList("专业","卷册总数","专业工时","备用工时","已用工时","已用比例");
    List<ProjectExcelTec> list = projectWorkDayService.statisticAll(min,max);
    String fileName = "D:/excel/statisticAll.xlsx";
    ExcelWriter excelWriter = null;
    try {
      excelWriter = EasyExcel.write(fileName).build();
      WriteSheet writeSheet = EasyExcel.writerSheet().sheetName("sheet").needHead(Boolean.FALSE).build();
      for(int i = 0; i < list.size(); i ++ ){
        System.out.println(list.get(i).getList());
        if (list.get(i).getList().size() > 0) {
          WriteTable writeTable = EasyExcel.writerTable(i).needHead(Boolean.TRUE)
            .head(ExcelOutputHead.ComplexList(list.get(i).getName(),listHeader))
            .build();
          excelWriter.write(ExcelOutData.dataTec(list.get(i).getList()),writeSheet,writeTable);
        }
      }
    }finally {
      if (excelWriter != null)
        excelWriter.finish();
    }
    Download.downloadFile(response, "statisticAll.xlsx", min + "-" + max + "专业完成卷册工时汇总.xlsx");
    return Result.ok();
  }


  @RequestMapping("statistic")
  public Result statistic(HttpServletResponse response, HttpServletRequest request){
    Integer id = Integer.valueOf(request.getParameter("id"));
    List<String> listHeader = Arrays.asList("专业","卷册总数","专业工时","备用工时","已用工时","已用比例");
    Map map = projectService.queryById(id);

    List<Map<String,String>> list = projectWorkDayService.statistic(id);
      String fileName = "D:/excel/statistic.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("工时使用情况汇总").doWrite(ExcelOutData.dataTec(list));
      Download.downloadFile( response , "statistic.xlsx", map.get("name").toString()+".xlsx");
    return Result.ok();
  }

  @RequestMapping("everyoneAll")
  public Result everyoneAll(HttpServletResponse response, HttpServletRequest request){
    String min = request.getParameter("minDay");
    String max = request.getParameter("maxDay");
    Map<String,String> map = new HashMap<>();
    map.put("min",min);
    map.put("max",max);
    List<String> listHeader = Arrays.asList("姓名","工号",
      "共管理卷册数量","共设计完成卷册数量","共校核完成卷册数量",
      "共管理卷册工日","共设计完成卷册工日","共校核完成卷册工日","共获得工日");
    List<Map<String,String>> list = projectWorkDayService.everyoneAll(map);
    String fileName = "D:/excel/everyone.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人本项目工时获得情况汇总").doWrite(ExcelOutData.everyone(list,1));
    Download.downloadFile( response , "everyone.xlsx", min + "-" + max +"个人卷册完成状况统计.xlsx");
    return Result.ok();
  }

  @RequestMapping("everyone")
  public Result everyone(HttpServletResponse response, HttpServletRequest request){
    Integer id = Integer.valueOf(request.getParameter("id"));
    Calendar calendar = Calendar.getInstance();
    Integer year = calendar.get(Calendar.YEAR);
    Integer month = calendar.get(Calendar.MONTH)+1;
    List<String> listHeader = Arrays.asList("姓名","工号",
      month+"月管理卷册数量",month+"月设计完成卷册数量",month+"月校核完成卷册数量",
      month+"月管理卷册工日",month+"月设计完成卷册工日",month+"月校核完成卷册工日",month + "月共获得工日",
      month-1+"月管理卷册数量",month-1+"月设计完成卷册数量",month-1+"月完成校核卷册数量",
      month-1+"月管理卷册工日",month-1+"月设计完成卷册工日",month+-1+"月完成校核卷册工日",month-1 + "月共获得工日",
      "共管理卷册数量","共设计完成卷册数量","共校核完成卷册数量",
      "共管理卷册工日","共设计完成卷册工日","共校核完成卷册工日","共获得工日");
    Map map = projectService.queryById(id);
    map.put("thisMonth",year + "-" + month + "-1");
    map.put("nextMonth",year + "-" + month+1 + "-1");
    if (month != 1)
      map.put("lastMonth",year + "-" + --month + "-1");
    else
      map.put("lastMonth",year-1 + "-12-1");
    List<Map<String,String>> list = projectWorkDayService.everyone(map);
    String fileName = "D:/excel/everyone.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人本项目工时获得情况汇总").doWrite(ExcelOutData.everyone(list,0));
    Download.downloadFile( response , "everyone.xlsx", map.get("name").toString()+".xlsx");
    return Result.ok();
  }


  @RequestMapping("volumeAll")
  public Result volumeAll(HttpServletResponse response, HttpServletRequest request){
    String min = request.getParameter("minDay");
    String max = request.getParameter("maxDay");
    Map map = new HashMap();
    map.put("min",min);
    map.put("max",max);
    List<String> listHeader = Arrays.asList("项目号","项目名","卷册号","卷册名","部门","专业","状态","工时","主设人","设计人",
      "互校人","计划开始日期","开始日期","计划出手日期","实际出手日期","互交完成日期","计划出版时间","实际出版时间","完成时间");
    List<Map<String,String>> list = volumeService.queryByDate(map);
    String fileName = "D:/excel/volumeAll.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人工时获得情况汇总").doWrite(ExcelOutData.dataVolume(list,1));
    Download.downloadFile( response , "volumeAll.xlsx", min + "-" + max+"完成卷册汇总.xlsx");
    return Result.ok();
  }

  @RequestMapping("volume")
  public Result volume(HttpServletResponse response, HttpServletRequest request){
    Integer id = Integer.valueOf(request.getParameter("id"));
    List<String> listHeader = Arrays.asList("卷册号","卷册名","部门","专业","状态","工时","主设人","设计人",
      "互校人","计划开始日期","开始日期","计划出手日期","实际出手日期","互交完成日期","计划出版时间","实际出版时间","完成时间");
    Map map = projectService.queryById(id);
    Project project = new Project();
    project.setId(id);
    List<Map<String,String>> list = volumeService.queryByProjectId(project);
    String fileName = "D:/excel/volume.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人工时获得情况汇总").doWrite(ExcelOutData.dataVolume(list,0));
    Download.downloadFile( response , "volume.xlsx", map.get("name").toString()+".xlsx");
    return Result.ok();
  }
  /*
  <=============================================>
   */

  @RequestMapping("personal")
  public Result personal(HttpServletResponse response, HttpServletRequest request){
    String min = request.getParameter("minDay");
    String max = request.getParameter("maxDay");
    String id = request.getParameter("id");
    Map<String,String> map = new HashMap();
    map.put("min",min);
    map.put("max",max);
    map.put("id",id);
    List<String> listHeader = Arrays.asList("项目号","项目名","共管理卷册数量","共设计完成卷册数量","共校核完成卷册数量",
      "共管理卷册工日","共设计完成卷册工日","共校核完成卷册工日","共获得工日");
    List<Map<String,String>> list = projectWorkDayService.personal(map);
    String fileName = "D:/excel/personal.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人工时获得情况汇总").doWrite(ExcelOutData.everyone(list,2));
    Download.downloadFile( response , "personal.xlsx", min + "-" + max+"施工图完成数量.xlsx");
    return Result.ok();
  }
  @RequestMapping("personalVolume")
  public Result personalVolume(HttpServletResponse response, HttpServletRequest request){
    String min = request.getParameter("minDay");
    String max = request.getParameter("maxDay");
    String id = request.getParameter("id");
    Map<String,String> map = new HashMap();
    map.put("min",min);
    map.put("max",max);
    map.put("id",id);
    List<String> listHeader = Arrays.asList("项目号","项目名","卷册号","卷册名","部门","专业","状态","工时","主设人","设计人",
      "互校人","计划开始日期","开始日期","计划出手日期","实际出手日期","互交完成日期","计划出版时间","实际出版时间","完成时间");
    List<Map<String,String>> list = volumeService.personalVolume(map);
    String fileName = "D:/excel/personalVolume.xlsx";
    EasyExcel.write(fileName).head(ExcelOutputHead.headList(listHeader))
      .sheet("个人工时获得情况汇总").doWrite(ExcelOutData.dataVolume(list,1));
    Download.downloadFile( response , "personalVolume.xlsx", min + "-" + max+"完成施工图汇总.xlsx");
    return Result.ok();
  }
}
