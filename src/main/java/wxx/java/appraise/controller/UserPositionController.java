package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.UserPositionService;

import java.util.Map;

@RestController
@RequestMapping("userPosition")
public class UserPositionController {
    private UserPositionService userPositionService;
    @Autowired
    public void setUserPositionService(UserPositionService userPositionService){
        this.userPositionService = userPositionService;
    }

    @RequestMapping("add")
  public Result add(@RequestBody Map map){
      userPositionService.add(map);
      return Result.ok();
    }

  @RequestMapping("del")
  public Result del(@RequestHeader Integer id){
    userPositionService.del(id);
    return Result.ok();
  }

}
