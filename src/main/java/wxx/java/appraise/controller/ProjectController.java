package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ProjectService;
import wxx.java.appraise.tools.Download;
import wxx.java.appraise.tools.ExcelProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("project")
public class ProjectController {
    private ProjectService projectService;
    @Autowired
    public void  setProjectService(ProjectService projectService){
        this.projectService = projectService;
    }

    @RequestMapping("drawLine")
    public Result drawLine(@RequestHeader Integer id){
      return projectService.drawLine(id);
    }

    @RequestMapping("add")
    public Result add(@RequestBody Project project){
        projectService.add(project);
        return Result.ok();
    }

    @RequestMapping("addNumber")
    public Result addNumber(@RequestBody Project project){
      projectService.addNumber(project);
      return Result.ok();
    }

    @RequestMapping("upd")
    public Result upd(@RequestBody Project project){
        projectService.upd(project);
        return Result.ok();
    }

    @RequestMapping("updState")
    public Result updState(@RequestHeader Integer id){
        projectService.updState(id);
        return Result.ok();
    }

  @RequestMapping("spider")
  public Result spider(@RequestBody Project project){
    projectService.spider(project);
    return Result.ok();
  }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader Integer id){
        return Result.ok(projectService.queryById(id));
    }

    @RequestMapping("queryByAdmin")
    public Result queryByAdmin(@RequestBody User user){
        return Result.ok(projectService.queryByAdmin(user));
    }

    @RequestMapping("queryByGeneral")
    public Result queryByGeneral(@RequestBody User user){
        return Result.ok(projectService.queryByGeneral(user));
    }

    @RequestMapping("queryByPrincipal")
    public Result queryByPrincipal(@RequestBody User user){
        return Result.ok(projectService.queryByPrincipal(user));
    }

    @RequestMapping("queryProByPrincipal")
    public Result queryProByPrincipal(@RequestBody User user){
      return Result.ok(projectService.queryProByPrincipal(user));
    }

    @RequestMapping("queryByDesigner")
    public Result queryByDesigner(@RequestBody User user){
        return Result.ok(projectService.queryByDesigner(user));
    }

    @RequestMapping("queryByChecker")
    public Result queryByChecker(@RequestBody User user){
        return Result.ok(projectService.queryByChecker(user));
    }

    @RequestMapping("queryByHeadman")
    public Result queryByHeadman(@RequestBody User user){
        return projectService.queryByHeadman(user);
    }

    @RequestMapping("projectExcel")
    public Result projectExcel(HttpServletResponse response, HttpServletRequest request){
        String s = "";
        Integer month = Integer.valueOf(request.getParameter("month"));
        try {
            ExcelProperty excelProperty = new ExcelProperty();
            excelProperty.ProjectExcel(projectService.queryExcel(month));
            String fileName = month + "月汇总表.xlsx";
            Download.downloadFile( response , "project.xlsx" , fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.ok(s);
    }

    @RequestMapping("queryAll")
    public Result queryAll(){
      return projectService.queryAll();
    }

    @RequestMapping("queryPrincipal")
    public Result queryPrincipal(@RequestHeader Integer id){
      return projectService.queryPrincipal(id);
    }

}
