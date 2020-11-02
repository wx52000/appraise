package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Department;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.DepartmentService;

@Transactional(rollbackFor = Exception.class,isolation = Isolation.READ_UNCOMMITTED)
@RestController
@RequestMapping("department")
public class DepartmentController {
    private DepartmentService departmentService;
    @Autowired
    public void setDepartmentService(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @RequestMapping("query")
    public Result query(){
        return Result.ok(departmentService.query());
    }
    @RequestMapping("add")
    public Result add(@RequestBody Department department){
        departmentService.add(department);
        return Result.ok();
    }
    @RequestMapping("del")
    public Result del(@RequestHeader Integer id){
        departmentService.del(id);
        return Result.ok();
    }

}
