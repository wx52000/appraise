package wxx.java.appraise.service;

import wxx.java.appraise.entity.Virtual;
import wxx.java.appraise.entity.VirtualDesigner;
import wxx.java.appraise.result.Result;

public interface VirtualService {

  Result setProject(Virtual virtual);

  Result query();

  Result queryById(Integer id);

  Result queryDesignerByGroup(Integer id, Integer vid);

  Result queryPrincipalWorkday(VirtualDesigner virtualDesigner);

  Result queryDesignerWorkday(VirtualDesigner virtualDesigner);
}
