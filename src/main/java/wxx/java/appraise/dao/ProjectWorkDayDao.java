package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectWorkDayDao {

  // 用于图表数据
  List<String> queryLegend(Integer id);
  //同上 && 用于修改
  List<Map> queryProWorkDay(Integer id);
  //同上
  List<Map> queryTecWorkDay(Integer id);
  //查询各专业已用工时
  List<Map> queryUsedTecWorkDay(Integer id);
  //查询已使用工时
  List<Map> queryUsedWorkDay(Integer id);
  //查询分配的工时，用于修改
  List<Map> queryTecWorkDayRatio(Integer id);
  List<Map> queryReserveWorkDayRatio(Integer id);
  //修改项目分配工时
  void setProWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list);
  void setTecWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list,
                     @Param("type") Integer type);

  void setUserWorkDay(@Param("id")Integer id,
                     @Param("list") List<Map> list,
                     @Param("type") Integer type);
}
