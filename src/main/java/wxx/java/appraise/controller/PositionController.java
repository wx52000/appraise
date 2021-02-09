package wxx.java.appraise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.PositionService;

@RestController
@RequestMapping("position")
public class PositionController {
  private PositionService positionService;
  @Autowired
  private void setPositionService(PositionService positionService){
    this.positionService = positionService;
  }

  @RequestMapping("query")
  public Result query(){
    return Result.ok(positionService.query());
  }
}
