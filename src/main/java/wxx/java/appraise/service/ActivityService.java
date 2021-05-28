package wxx.java.appraise.service;

import wxx.java.appraise.entity.Activity;
import wxx.java.appraise.entity.PrincipalWorkday;
import wxx.java.appraise.entity.VirtualDesigner;
import wxx.java.appraise.result.Result;

import java.util.List;

public interface ActivityService {

  Result  setProject(Activity activity);

  Result query();

  Result queryById(Integer id);

  Result state(Activity activity);

  Result queryPrincipalWorkday(VirtualDesigner virtualDesigner);

  Result queryDesignerWorkday(VirtualDesigner virtualDesigner);

  Result homepage(Integer id);

  Result workdayByGroup(Integer id, Integer uid);

  Result setDesignerWorkday(List<PrincipalWorkday> list, Integer id);
}
