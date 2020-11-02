package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.TecScore;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.TecScoreService;
import wxx.java.appraise.tools.Download;
import wxx.java.appraise.tools.ExcelProperty;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("tecScore")
public class TecScoreController {

    private TecScoreService tecScoreService;
    @Autowired
    public void setTecScoreService(TecScoreService tecScoreService){
        this.tecScoreService = tecScoreService;
    }

    @RequestMapping("queryByGradeId")
    public Result queryByGradeId(@RequestHeader Integer id){
        return Result.ok(tecScoreService.queryByGradeId(id));
    }

    @RequestMapping("appraise")
    public Result appraise(@RequestBody List<TecScore> list){
        tecScoreService.appraise(list);
        return Result.ok(0);
    }

    @RequestMapping("queryScore")
    public Result queryScore(@RequestHeader Integer id){
        return Result.ok(tecScoreService.queryScore(id));
    }

    @RequestMapping("query")
    public Result query(@RequestHeader Integer id){
        return Result.ok(tecScoreService.query(id));

    }

    @RequestMapping("excel")
    public Result excel(HttpServletRequest request , HttpServletResponse response) throws IOException {
        String s = "";
        try {
            User user = new User();
            user.setId(Integer.valueOf(request.getParameter("id")));
            Calendar calendar = Calendar.getInstance();
            user.setThisMonth(calendar.get(Calendar.MONTH)+1);
            user.setThisDay(calendar.get(Calendar.DATE));
            ExcelProperty excelProperty = new ExcelProperty();
            Future<String> future = excelProperty.technologyExcel(tecScoreService.excel(user));
            s = future.get();
            String fileName = calendar.get(Calendar.MONTH)+1 + "月技术得分汇总表.xlsx";
            Download.downloadFile( response , "excel1.xlsx" , fileName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
}
