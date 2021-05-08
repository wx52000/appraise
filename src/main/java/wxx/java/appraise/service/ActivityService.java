package wxx.java.appraise.service;

import wxx.java.appraise.entity.Activity;
import wxx.java.appraise.result.Result;

public interface ActivityService {

  Result  setProject(Activity activity);

  Result query();

  Result queryById(Integer id);

  Result state(Activity activity);
}
