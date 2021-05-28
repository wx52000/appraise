package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Activity;
import wxx.java.appraise.entity.PrincipalWorkday;
import wxx.java.appraise.entity.VirtualDesigner;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ActivityService;

import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController {
  private ActivityService activityService;
  @Autowired
  public void setActivityService(ActivityService activityService){
    this.activityService = activityService;
  }

  @RequestMapping("setProject")
  public Result setProject(@RequestBody Activity activity){
    return activityService.setProject(activity);
  }

  @RequestMapping("query")
  public Result query(){
    return activityService.query();
  }

  @RequestMapping("queryById")
  public Result queryById(@RequestHeader Integer id){
    return activityService.queryById(id);
  }

  @RequestMapping("state")
  public Result state(@RequestBody Activity activity){
    return activityService.state(activity);
  }

  @RequestMapping("principalWorkday")
  public Result principalWorkday(@RequestBody VirtualDesigner virtualDesigner){
    return activityService.queryPrincipalWorkday(virtualDesigner);
  }

  @RequestMapping("designerWorkday")
  public Result designerWorkday(@RequestBody VirtualDesigner virtualDesigner){
    return activityService.queryDesignerWorkday(virtualDesigner);
  }

  @RequestMapping("workdayByGroup")
  public Result workdayByGroup(@RequestHeader Integer id,@RequestHeader Integer uid  ){
    return activityService.workdayByGroup(id,uid);
  }

  @RequestMapping("setDesignerWorkday")
  public Result setDesignerWorkday(@RequestBody List<PrincipalWorkday> list, @RequestHeader Integer id){
    return activityService.setDesignerWorkday(list,id);
  }

  @RequestMapping("homepage")
  public Result homepage(@RequestHeader Integer id){
    return  activityService.homepage(id);
  }
}
