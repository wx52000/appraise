package wxx.java.appraise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wxx.java.appraise.entity.Virtual;
import wxx.java.appraise.entity.VirtualDesigner;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.VirtualService;

@RestController
@RequestMapping("virtual")
public class VirtualController {
  private VirtualService virtualService;
  @Autowired
  public void setVirtualService(VirtualService virtualService){
    this.virtualService = virtualService;
  }

  @RequestMapping("setProject")
  public Result setProject(@RequestBody Virtual virtual){
    return virtualService.setProject(virtual);
  }

  @RequestMapping("query")
  public Result query(){
    return virtualService.query();
  }

  @RequestMapping("queryById")
  public Result queryById(@RequestHeader Integer id){
    return virtualService.queryById(id);
  }

  @RequestMapping("principalWorkday")
  public Result principalWorkday(@RequestBody VirtualDesigner virtualDesigner){
    return virtualService.queryPrincipalWorkday(virtualDesigner);
  }

  @RequestMapping("designerWorkday")
  public Result designerWorkday(@RequestBody VirtualDesigner virtualDesigner){
    return virtualService.queryDesignerWorkday(virtualDesigner);
  }
}
