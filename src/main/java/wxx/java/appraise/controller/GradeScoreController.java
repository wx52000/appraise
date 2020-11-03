package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.GradeScore;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.GradeScoreService;
import wxx.java.appraise.tools.ExcelDataListener;

@RestController
@RequestMapping("gradeScore")
public class GradeScoreController {
    private GradeScoreService gradeScoreService;
    private ExcelDataListener excelDataListener;
    @Autowired
    public  void  setGradeScoreService(GradeScoreService gradeScoreService){
        this.gradeScoreService = gradeScoreService;
    }

    @Autowired
    public void  setExcelDataListener(ExcelDataListener excelDataListener){
        this.excelDataListener = excelDataListener;
    }

    @RequestMapping("manage")
    public Result manage(@RequestBody GradeScore gradeScore){
        gradeScoreService.manage(gradeScore);
        return Result.ok();
    }


    @RequestMapping("queryTec")
    public Result queryTec(@RequestHeader Integer id){
        return Result.ok(gradeScoreService.queryTec(id));
    }

    @RequestMapping("queryDep")
    public Result queryDep(@RequestHeader Integer id){
        return Result.ok(gradeScoreService.queryDep(id));
    }

}
