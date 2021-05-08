package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.Activity;
import wxx.java.appraise.entity.VirtualDesigner;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityDao {

  void setProject(Activity activity);

  List<Map> query();

  void state(Activity activity);

  //返回名字
  List<String> queryByRole(@Param("id") Integer id,@Param("role") Integer role);
  //返回id
  List<String> queryByRole1(@Param("id") Integer id,@Param("role") Integer role);

  List<Map> queryGroup(@Param("id") Integer id,@Param("role") Integer role);
  //用来查询主设人和设计人
  List<String> queryByGroup(@Param("id") Integer id,@Param("role")Integer role);

  void addGeneral(@Param("id")Integer id,@Param("list") List<String> list);

  void addPrincipal(@Param("id")Integer id,@Param("list")List<Map<String,String>> list);

  void addDesigner(@Param("id")Integer id,@Param("list")List<VirtualDesigner> list);

  void deleteRole(@Param("id")Integer id ,@Param("list") List<Map<String,String>> list);

  void deleteDesigner(@Param("id")Integer id ,@Param("list") List<Integer> list);

  List<Map<String,String>> queryRole(@Param("id") Integer id,@Param("role") Integer role);

  List<Integer> queryDesignerId(Integer id);
}
