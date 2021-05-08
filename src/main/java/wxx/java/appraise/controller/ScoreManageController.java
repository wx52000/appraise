package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.ScoreManage;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ScoreManageService;

@RestController
@RequestMapping("scoreManage")
public class ScoreManageController {
  private ScoreManageService scoreManageService;
  @Autowired
  public void  setScoreManageService(ScoreManageService scoreManageService){
    this.scoreManageService = scoreManageService;
  }

  @RequestMapping("handle")
  public Result handle(@RequestBody ScoreManage scoreManage){
    scoreManageService.handle(scoreManage);
    return Result.ok();
  }
}
