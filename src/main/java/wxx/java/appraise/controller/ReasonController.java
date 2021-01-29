package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ReasonService;

@RestController
@RequestMapping("reason")
public class ReasonController {
  private ReasonService reasonService;
  @Autowired
  private void setReasonService(ReasonService reasonService){
    this.reasonService = reasonService;
  }

  @RequestMapping("queryByType")
  public Result queryByType(@RequestHeader Integer type){
    return Result.ok(reasonService.queryByType(type));
  }
}
