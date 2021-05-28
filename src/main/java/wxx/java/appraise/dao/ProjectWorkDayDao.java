package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.ProjectExcelTec;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectWorkDayDao {

  //图表
  //同上 && 用于修改
  Map queryProWorkDay(Integer id);
  //同上
  List<Map> queryTecWorkDay(Integer id);
  //查询各专业已用工时
  List<Map> queryUsedTecWorkDay(Integer id);
  //查询已使用工时
  List<Map> queryUsedWorkDay(Integer id);
  //查询分配的工时，用于修改
  List<Map> queryTecWorkDayRatio(Integer id);
  List<Map> queryReserveWorkDayRatio(@Param("id") Integer id);
  //修改项目分配工时
  void setProWorkDay(Map map);
  void setTecWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list);
  void setBackupWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list);
  void setUserWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list,
                     @Param("type") Integer type);

  List<ProjectExcelTec> statisticAll(@Param("min") String min, @Param("max") String max);
  List<Map<String,String>> statisticByDate(@Param("id") Integer id,
                                       @Param("min") String min, @Param("max") String max);

  List<Map<String,String>> statistic(Integer id);

  List<Map<String,String>> everyoneAll(Map map);

  List<Map<String,String>> everyone(Map map);

  List<Map<String,String>> personal(Map map);

  Map queryTecVolumeRatio(Integer id);

  void setTecVolumeRatio(Map map);
}
