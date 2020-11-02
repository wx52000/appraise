package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserScore;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.UserScoreService;
import wxx.java.appraise.tools.Download;
import wxx.java.appraise.tools.ExcelProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("userScore")
public class UserScoreController {

    private UserScoreService userScoreService;
    @Autowired
    public void setUserScoreService(UserScoreService userScoreService){
        this.userScoreService = userScoreService;
    }

    @RequestMapping("queryByGradeId")
    public Result queryByGradeId(@RequestBody User user){
        return Result.ok(userScoreService.queryByGradeId(user));
    }

    @RequestMapping("selectByGradeId")
    public Result selectByGradeId(@RequestBody User user){
        return Result.ok(userScoreService.selectByGradeId(user));
    }

    @RequestMapping("appraise")
    public Result appraise(@RequestBody List<UserScore> list){
        userScoreService.appraise(list);
        return Result.ok(0);
    }

    @RequestMapping("queryScore")
    public Result queryScore(@RequestHeader Integer id){
        return Result.ok(userScoreService.queryScore(id));
    }

    @RequestMapping("query")
    public Result query(@RequestHeader Integer id){
        return Result.ok(userScoreService.query(id));
    }

    @RequestMapping("add")
    public Result add(@RequestBody List<UserScore> list){
        userScoreService.add(list);
        return Result.ok();
    }

    @RequestMapping("del")
    public Result del(@RequestBody UserScore userScore){
        userScoreService.del(userScore);
        return Result.ok();
    }

    @RequestMapping("personExcel")
    public Result personExcel(HttpServletRequest request , HttpServletResponse response) {
        String s = "";
        try {
            User user = new User();
            user.setId(Integer.valueOf(request.getParameter("id")));
            Calendar calendar = Calendar.getInstance();
            user.setThisMonth(calendar.get(Calendar.MONTH)+1);
            user.setThisDay(calendar.get(Calendar.DATE));
            ExcelProperty excelProperty = new ExcelProperty();
            Future<String> future = excelProperty.personalExcel(userScoreService.excel1(user));
            s = future.get();
            String fileName = calendar.get(Calendar.MONTH)+1 + "月个人得分汇总表.xlsx";
            Download.downloadFile( response , "excel.xlsx" , fileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return  Result.ok(s);
    }

}
