package wxx.java.appraise.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Volume;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.VolumeService;

import java.util.Map;

@RestController
@RequestMapping("volume")
public class VolumeController {
    private VolumeService volumeService;
    @Autowired
    public void  setVolumeService(VolumeService volumeService){
        this.volumeService = volumeService;
    }

    @RequestMapping("queryById")
    public Result queryById(@RequestHeader Integer id){
        return Result.ok(volumeService.queryById(id));
    }

    @RequestMapping("queryByProjectId")
    public Result queryByProjectId(@RequestBody Project project){
      return Result.ok(volumeService.queryByProjectId(project));
    }

    @RequestMapping("upd")
    public Result upd(@RequestBody Volume volume){
        volumeService.upd(volume);
        return Result.ok();
    }

    @RequestMapping("add")
    public Result add(@RequestBody Volume volume){
        volumeService.add(volume);
        return Result.ok();
    }

    @RequestMapping("query")
  public Result query(@RequestBody Map<String,String> params){
      return Result.ok(volumeService.queryVolume(params.get("user"),params.get("volume")));
    }

  @RequestMapping("queryByNumber")
  public Result queryByNumber(@RequestBody Volume volume){
    return volumeService.queryByNumber(volume);
  }
}
