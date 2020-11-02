package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Range;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.RangeService;

@RestController
@RequestMapping("range")
public class RangeController {
    private RangeService rangeService;
    @Autowired
    public  void setRangeService(RangeService rangeService){
        this.rangeService = rangeService;
    }

    @RequestMapping("query")
    public Result query(){
        return  Result.ok(rangeService.query());
    }

    @RequestMapping("update")
    public  Result update(@RequestBody Range range){
        rangeService.update(range);
        return  Result.ok();
    }
}
