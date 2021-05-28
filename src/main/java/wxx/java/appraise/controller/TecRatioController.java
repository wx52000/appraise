package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.TecRatioService;

@RestController
@RequestMapping("tecRatio")
public class TecRatioController {
  private TecRatioService tecRatioService;
  @Autowired
  public  void setTecRatioService(TecRatioService tecRatioService){
    this.tecRatioService = tecRatioService;
  }

  @RequestMapping("queryByTec")
  public Result queryByTec(@RequestHeader Integer id){
    return tecRatioService.queryByTec(id);
  }
}
