package wxx.java.appraise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Position;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.PositionService;

import java.util.List;

@RestController
@RequestMapping("position")
public class PositionController {
  private PositionService positionService;
  @Autowired
  private void setPositionService(PositionService positionService){
    this.positionService = positionService;
  }

  @RequestMapping("queryByWeight")
  public Result queryByWeight(@RequestHeader Integer id){
    return Result.ok(positionService.queryByWeight(id));
  }

  @RequestMapping("query")
  public Result query(){
    return Result.ok(positionService.query());
  }


  @RequestMapping("update")
  public Result update(@RequestBody List<Position>
                        position){
    return positionService.update(position);
  }
}
