package wxx.java.appraise.service;

import wxx.java.appraise.entity.ProjectExcelTec;
import wxx.java.appraise.result.Result;

import java.util.List;
import java.util.Map;

public interface ProjectWorkDayService {

  Result drawLine(Integer id);

  Result queryUsedTecWorkDay(Integer id);
  //用于工时修改表单
  Result queryWorkDay(Integer id);

  Result queryTecWorkDay(Integer id);

  Result queryTecVolumeRatio(Integer id);

  Result setTecVolumeRatio(Map map);

  Result queryReserveWorkday(Integer id);

  Result setProWorkDay(Map map);

  Result setTecWorkDay(Map map);

  Result setBackupWorkDay(Map map);

  Result setUserWorkDay(Map map);

  //Excel 整体比例 所有项目
  List<ProjectExcelTec> statisticAll(String min , String max);


  //Excel 整体比例
  List<Map<String,String>> statistic(Integer id);

  List<Map<String,String>> everyoneAll(Map map);

  List<Map<String,String>> everyone(Map map);

  List<Map<String,String>> personal(Map<String,String> map);
}
