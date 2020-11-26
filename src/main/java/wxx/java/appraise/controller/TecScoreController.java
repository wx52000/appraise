package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.PartParam;
import wxx.java.appraise.entity.TecScore;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.TecScoreService;
import wxx.java.appraise.service.UserService;
import wxx.java.appraise.tools.Download;
import wxx.java.appraise.tools.ExcelProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("tecScore")
public class TecScoreController {

    private TecScoreService tecScoreService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
      this.userService = userService;
    }
    @Autowired
    public void setTecScoreService(TecScoreService tecScoreService){
        this.tecScoreService = tecScoreService;
    }

    @RequestMapping("queryByGradeId")
    public Result queryByGradeId(@RequestHeader Integer id){
        return Result.ok(tecScoreService.queryByGradeId(id));
    }

    @RequestMapping("queryByScoreId")
    public Result queryByScoreId(@RequestBody User user){
      return Result.ok(tecScoreService.queryByScoreId(user));
    }

    @RequestMapping("appraise")
    public Result appraise(@RequestBody List<TecScore> list){
        tecScoreService.appraise(list);
        return Result.ok(0);
    }

    @RequestMapping("queryScore")
    public Result queryScore(@RequestBody User user){
        return Result.ok(tecScoreService.queryScore(user));
    }

    @RequestMapping("query")
    public Result query(@RequestBody User user){
        return Result.ok(tecScoreService.query(user));

    }

    @RequestMapping("detail")
    public Result detail(HttpServletResponse response,HttpServletRequest request){
      String s= "";
      try {
        User user = new User();
        user.setThisMonth(Integer.valueOf(request.getParameter("month")));
        ExcelProperty excelProperty = new ExcelProperty();
        Future<String> future = excelProperty
          .personalDetailsExcel(userService.queryGrade(),tecScoreService.detail(user));
        s = future.get();
        String fileName = user.getThisMonth() + "月个人评分详情表.xlsx";
        Download.downloadFile( response , "excel.xlsx" , fileName);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (ExecutionException e) {
        e.printStackTrace();
      }
      return  Result.ok(s);
    }

  @RequestMapping("part")
  public Result part(@RequestBody PartParam partParam){
    String s= "";
    String fileName = "";
    try {
      fileName = partParam.getMonth() + "月专业评分详情表.xlsx";
      ExcelProperty excelProperty = new ExcelProperty();
      Future<String> future = excelProperty
        .tecPartExcel(tecScoreService.part(partParam.getList(),partParam.getMonth()),fileName);
      s = future.get();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    return  Result.ok(fileName);
  }


  @RequestMapping("excel")
    public Result excel(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String s = "";
        try {
            User user = new User();
            user.setId(Integer.valueOf(request.getParameter("id")));
            user.setThisMonth(Integer.valueOf(request.getParameter("month")));
            ExcelProperty excelProperty = new ExcelProperty();
            Future<String> future = excelProperty.technologyExcel(tecScoreService.excel(user));
            s = future.get();
            String fileName = user.getThisMonth() + "月专业得分汇总表.xlsx";
            Download.downloadFile( response , "excel1.xlsx" , fileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
}
