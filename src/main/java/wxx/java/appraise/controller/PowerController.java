package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.PowerService;

@RestController
@RequestMapping("power")
public class PowerController {
    private PowerService powerService;

    @Autowired
    public void setPowerService(PowerService powerService){
        this.powerService = powerService;
    }

    @RequestMapping("query")
    public Result query(){
        return Result.ok(powerService.query());
    }

}
