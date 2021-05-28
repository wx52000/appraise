package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.ProjectDao;
import wxx.java.appraise.dao.ProjectWorkDayDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.entity.ProjectExcelTec;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ProjectWorkDayService;

import java.util.*;

@Service
public class ProjectWorkDayServiceImpl implements ProjectWorkDayService {
  private ProjectWorkDayDao proWorkDayDao;
  private ProjectDao projectDao;
  private UserDao userDao;
  @Autowired
  public void setWorkDayDao(ProjectWorkDayDao projectWorkDayDao){
    this.proWorkDayDao = projectWorkDayDao;
  }
  @Autowired
  public void setProjectDao(ProjectDao projectDao){
    this.projectDao = projectDao;
  }
  @Autowired
  public void setUserDao(UserDao userDao){
    this.userDao = userDao;
  }
  @Override
  public Result drawLine(Integer id) {
    Map<String,Object> map = new HashMap();
    map.put("proWorkDay",proWorkDayDao.queryProWorkDay(id));
    map.put("tecWorkDay",proWorkDayDao.queryTecWorkDay(id));
    return Result.ok(map);
  }

  @Override
  public Result queryUsedTecWorkDay(Integer id) {
    List<Map> list = proWorkDayDao.queryUsedTecWorkDay(id);
    list.removeAll(Collections.singleton(null));
    return Result.ok(list);
  }

  @Override
  public Result queryWorkDay(Integer id) {
    Map map = new HashMap();
    map.put("project",projectDao.queryById(id));
    map.put("workday",proWorkDayDao.queryProWorkDay(id));
    List<Map> list = proWorkDayDao.queryTecWorkDayRatio(id);
    list.removeAll(Collections.singleton(null));
    map.put("tecWorkday",list);
    return Result.ok(map);
  }

  @Override
  public Result queryTecWorkDay(Integer id) {
    List<Map> list = proWorkDayDao.queryTecWorkDayRatio(id);
    list.removeAll(Collections.singleton(null));
    return Result.ok(list);
  }

  @Override
  public Result queryTecVolumeRatio(Integer id) {
    return Result.ok(proWorkDayDao.queryTecVolumeRatio(id));
  }

  @Override
  public Result setTecVolumeRatio(Map map) {
    proWorkDayDao.setTecVolumeRatio(map);
    return Result.ok();
  }

  @Override
  public Result queryReserveWorkday(Integer id) {
    List<Map> list = proWorkDayDao.queryReserveWorkDayRatio(id);
    list.removeAll(Collections.singleton(null));
    return Result.ok(list);
  }

  @Override
  public Result setProWorkDay(Map map) {
    proWorkDayDao.setProWorkDay(map);
    return Result.ok();
  }

  @Override
  public Result setTecWorkDay(Map map) {
    proWorkDayDao.setTecWorkDay(Integer.valueOf(map.get("project_id").toString()),
      (List) Arrays.asList(map.get("list")).get(0));
    return Result.ok();
  }

  @Override
  public Result setBackupWorkDay(Map map) {
    proWorkDayDao.setBackupWorkDay(Integer.valueOf(map.get("project_id").toString()),
      (List) Arrays.asList(map.get("list")).get(0));
    return Result.ok();
  }

  @Override
  public Result setUserWorkDay(Map map) {
    List list = (List) Arrays.asList(map.get("list")).get(0);
    proWorkDayDao.setUserWorkDay(Integer.valueOf(map.get("project_id").toString()),
      (List) Arrays.asList(map.get("list")).get(0),Integer.valueOf(map.get("type").toString()));
    return Result.ok();
  }

  @Override
  public List<ProjectExcelTec> statisticAll(String min,String max) {
    return proWorkDayDao.statisticAll(min,max);
  }

  @Override
  public List<Map<String,String>> statistic(Integer id) {
    List<Map<String,String>> list = proWorkDayDao.statistic(id);
    list.removeAll(Collections.singleton(null));
    return list;
  }

  @Override
  public List<Map<String, String>> everyoneAll(Map map) {
    return proWorkDayDao.everyoneAll(map);
  }

  @Override
  public List<Map<String, String>> everyone(Map map) {
    return proWorkDayDao.everyone(map);
  }

  @Override
  public List<Map<String, String>> personal(Map<String,String> map) {
    User user = userDao.queryById(Integer.valueOf(map.get("id")));
    map.put("name",user.getName());
    return proWorkDayDao.personal(map);
  }
}
