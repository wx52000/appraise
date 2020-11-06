package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Technology;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.TechnologyService;

@RestController
@RequestMapping("technology")
public class TechnologyController {
    private TechnologyService technologyService;
    @Autowired
    public void setTechnologyService(TechnologyService technologyService){
        this.technologyService = technologyService;
    }

    @RequestMapping("query")
    public Result query(@RequestHeader Integer id){
        return Result.ok(technologyService.query(id));
    }

    @RequestMapping("queryNotUser")
    public Result queryNotUser() {
      return Result.ok(technologyService.queryNotUser());
    }

    @RequestMapping("evaluate")
    public Result evaluate(@RequestHeader Integer id) {
      return Result.ok(technologyService.evaluate(id));
    }

    @RequestMapping("add")
    public Result add(@RequestBody Technology technology){
        technologyService.add(technology);
        return Result.ok();
    }

    @RequestMapping("del")
    public Result del(@RequestHeader Integer id){
        technologyService.del(id);
        return Result.ok();
    }

    @RequestMapping("queryToSelected")
    public Result queryToSelected(){
        return Result.ok(technologyService.queryToSelected());
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestBody Project project){
        return Result.ok(technologyService.queryById(project));
    }
}
