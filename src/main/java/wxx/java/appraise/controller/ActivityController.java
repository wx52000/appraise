package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Activity;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ActivityService;

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
}
