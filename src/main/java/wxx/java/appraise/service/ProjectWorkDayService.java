package wxx.java.appraise.service;

import wxx.java.appraise.result.Result;

import java.util.Map;

public interface ProjectWorkDayService {

  Result drawLine(Integer id);

  Result queryUsedTecWorkDay(Integer id);
  //用于工时修改表单
  Result queryWorkDay(Integer id);

  Result queryReserveWorkday(Integer id);

  Result setProWorkDay(Map map);

  Result setTecWorkDay(Map map);

  Result setUserWorkDay(Map map);
}
