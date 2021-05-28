package wxx.java.appraise.service;

import wxx.java.appraise.entity.PrincipalWorkday;
import wxx.java.appraise.entity.Virtual;
import wxx.java.appraise.entity.VirtualDesigner;
import wxx.java.appraise.result.Result;

import java.util.List;

public interface VirtualService {

  Result setProject(Virtual virtual);

  Result query();

  Result queryById(Integer id);

  Result queryDesignerByGroup(Integer id, Integer vid);

  Result queryPrincipalWorkday(VirtualDesigner virtualDesigner);

  Result queryDesignerWorkday(VirtualDesigner virtualDesigner);

  Result setDesignerWorkday(List<PrincipalWorkday> list,Integer id);
  Result workdayByGroup(Integer id, Integer uid);

  Result homepage(Integer id);
}
