package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ProjectWorkDayService;

import java.util.Map;

@RestController
@RequestMapping("projectWorkDay")
public class ProjectWorkDayController {
  private ProjectWorkDayService proWorkDayService;
  @Autowired
  public void setWorkDayService(ProjectWorkDayService proWorkDayService){
    this.proWorkDayService = proWorkDayService;
  }

  @RequestMapping("drawLine")
  public Result drawLine(@RequestHeader Integer id){
    return proWorkDayService.drawLine(id);
  }

  @RequestMapping("queryUsedTecWorkDay")
  public Result queryUsedTecWorkDay(@RequestHeader Integer id) {
    return proWorkDayService.queryUsedTecWorkDay(id);
  }
  @RequestMapping("queryWorkDay")
  public Result queryWorkDay(@RequestHeader Integer id){
    return proWorkDayService.queryWorkDay(id);
  }

  @RequestMapping("queryTecWorkDay")
  public Result queryTecWorkDay(@RequestHeader Integer id){
    return proWorkDayService.queryTecWorkDay(id);
  }

  @RequestMapping("queryTecVolumeRatio")
  public Result queryTecVolumeRatio(@RequestHeader Integer id){
    return proWorkDayService.queryTecVolumeRatio(id);
  }

  @RequestMapping("setTecVolumeRatio")
  public Result setTecVolumeRatio(@RequestBody Map map){
    return proWorkDayService.setTecVolumeRatio(map);
  }

  @RequestMapping("queryReserveWorkday")
  public Result queryReserveWorkDay(@RequestHeader Integer id){
    return proWorkDayService.queryReserveWorkday(id);
  }

  @RequestMapping("setProWorkDay")
  public Result setProWorkDay(@RequestBody Map map){
    return proWorkDayService.setProWorkDay(map);
  }

  @RequestMapping("setTecWorkDay")
  public Result setTecWorkDay(@RequestBody Map map){
    return proWorkDayService.setTecWorkDay(map);
  }

  @RequestMapping("setBackupWorkDay")
  public Result setBackupWorkDay(@RequestBody Map map){
    return proWorkDayService.setBackupWorkDay(map);
  }

  @RequestMapping("setUserWorkDay")
  public Result setUserWorkDay(@RequestBody Map map){
    return proWorkDayService.setUserWorkDay(map);
  }


}
