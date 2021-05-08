package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.ActivityDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.entity.Activity;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.ActivityService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class ActivityServiceImpl implements ActivityService {
  private ActivityDao activityDao;
  private UserDao userDao;
  @Autowired
  public void setActivityDao(ActivityDao activityDao){
    this.activityDao = activityDao;
  }
  @Autowired
  public void setUserService(UserDao userDao){
    this.userDao = userDao;
  }
  @Override
  public Result setProject(Activity activity) {
    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
//    Pattern pattern = Pattern.compile("[^0-9]");
    activityDao.setProject(activity);
    activity.setStart_date(activity.getDate().get(0));
    activity.setEnd_date(activity.getDate().get(1));
    //人员添加
    if (activity.getGeneral().size()>0) {
      //若不为数字类型则删除
      List<Map<String,String>> list = activityDao.queryRole(activity.getId(),1);
      activity.getGeneral().forEach(integer -> {
        for (int i=0; i < list.size();i++){
          if (integer.equals(list.get(i).get("id"))||
            integer.equals(list.get(i).get("name"))){
            list.remove(i);
            break;
          }
        }
      });
      //删除修改设总
      activity.getGeneral().removeIf(o -> !(pattern.matcher(o).matches()));
      if (activity.getGeneral().size() >0)
        activityDao.addGeneral(activity.getId(), activity.getGeneral());
      if (list.size()>0)
        activityDao.deleteRole(activity.getId(),list);
    }

    if (activity.getPrincipalState()){
      for (int i = 0; i < activity.getPrincipal().size();i++){
        boolean exist = false;
        for (int j = 0 ;j < activity.getPrincipal_group().size(); j ++) {
          if (activity.getPrincipal().get(i).equals(activity.getPrincipal_group().get(j).get("id"))) {
            exist = true;
          }
        }
          if (!exist) {
            Map map = new HashMap();
            map.put("id", activity.getPrincipal().get(i));
            map.put("group", activity.getPrincipal().get(i));
            activity.getPrincipal_group().add(map);
          }
      }
        List<Map<String,String>> list = activityDao.queryRole(activity.getId(),2);
        activity.getPrincipal().forEach(integer -> {
          for (int i=0; i < list.size();i++){
            if (integer.equals(String.valueOf(list.get(i).get("id")))){
              list.remove(i);
              break;
            }
          }
        });
        if (activity.getPrincipal_group().size() > 0)
          activityDao.addPrincipal(activity.getId(),activity.getPrincipal_group());
      if (list.size()>0)
        activityDao.deleteRole(activity.getId(),list);
    }
    if (activity.getDesigner()  .size()>0){
      List<Integer> list1 = activityDao.queryDesignerId(activity.getId());
      activity.getDesigner().forEach(integer -> {
        integer.getList().forEach(item -> {
        for (int i = 0; i < list1.size(); i++) {
          if (item.equals(list1.get(i))) {
            list1.remove(i);
            break;
          }
        }
        });
      });
      if (activity.getDesigner().size() > 0)
        activityDao.addDesigner(activity.getId(),activity.getDesigner());
      if (list1.size()>0)
        activityDao.deleteDesigner(activity.getId(),list1);
    }
    return Result.ok();
  }

  @Override
  public Result query() {
    List<Map> list = activityDao.query();
    list.forEach(item ->{
      List<String> list1 = new ArrayList<>();
      if (item.get("start_date") != null){
          list1.add(item.get("start_date").toString());
          list1.add(item.get("end_date").toString());
          item.put("date",list1);
      }
    });
    return Result.ok(list);
  }

  @Override
  public Result queryById(Integer id) {
    Map<String,Object> map = new HashMap();
    map.put("general",activityDao.queryByRole(id,1));
    map.put("principal",activityDao.queryByRole1(id,2));
    map.put("principal_group",activityDao.queryGroup(id,2));
    return Result.ok(map);
  }

  @Override
  public Result state(Activity activity) {
    User user = userDao.queryById(activity.getChecker());
    if (user.getPid() == 1){
      try {
        activityDao.state(activity);
        return Result.ok();
      }catch (Exception e){
        return Result.build(56545,"审核异常");
      }
    }else
      return Result.build(56,"暂无此权限");
  }


}
