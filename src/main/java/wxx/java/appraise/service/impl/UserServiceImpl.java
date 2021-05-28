package wxx.java.appraise.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.DepartmentDao;
import wxx.java.appraise.dao.TechnologyDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.entity.Department;
import wxx.java.appraise.entity.ExcelData;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserOut;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.UserService;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private DepartmentDao departmentDao;
    private TechnologyDao technologyDao;
    @Autowired
    public void setDepartmentDao(DepartmentDao departmentDao){
      this.departmentDao = departmentDao;
    }
    @Autowired
    public void setTechnologyDao(TechnologyDao technologyDao){
      this.technologyDao = technologyDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public Result login(String username, String paw) {
        Map map = userDao.login(username,paw);
        if (map != null)
            return Result.ok(userDao.login(username,paw));
        else return Result.build(10);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void addExcel(List<ExcelData> list) {
        userDao.addExcel(list);
    }

    @Override
    public void del(Integer id) {
        userDao.del(id);
    }

    @Override
    public void upd(User user) {
        userDao.upd(user);
    }

    @Override
    public PageInfo<Map> query(User user) {
        PageHelper.startPage(user.getPageIndex(),user.getPageSize(),true);
        PageInfo<Map> pageInfo = new PageInfo<>(userDao.query(user));
        return pageInfo;
    }

  @Override
  public List<String> queryGrade() {
    return userDao.queryGrade();
  }

  @Override
  public List<Map> queryAll() {
    return userDao.queryAll();
  }

  @Override
  public Map queryToupd(Integer id) {
      Map map = userDao.queryToupd(id);
      map.put("position",userDao.queryPosition(id));
    return map;
  }

  @Override
    public List<Map> queryByTid(Integer id) {
        return userDao.queryByTid(id);
    }

  @Override
  public PageInfo<Map> queryNotSelf(User user) {
      User user1 = userDao.queryById(user.getId());
      if (user1.getGrade() == 1) {
        PageHelper.startPage(user.getPageIndex(), user.getPageSize(), true);
        PageInfo<Map> pageInfo = new PageInfo<>(userDao.queryNotSelf(user));
        return pageInfo;
      }else
        return null;
  }

  @Override
  public Result queryToScore(User user) {
    Map map = userDao.queryLimits(user.getId());
    if (Integer.valueOf(map.get("grade").toString()) == 1) {
      if (map.get("limits") == null) {
        return Result.ok(userDao.queryByGAndP(user));
      } else if (Integer.valueOf(map.get("limits").toString()) == 0 || Integer.valueOf(map.get("limits").toString()) == 1) {
        return Result.ok(userDao.queryNotSelf(user));
      }
      //经理
      else if (Integer.valueOf(map.get("limits").toString()) == 2) {
        return Result.ok(userDao.queryByManager(user));
      }
      //组长
      else if (Integer.valueOf(map.get("limits").toString()) == 3) {
        return Result.ok(userDao.queryByHeadman(user));
      } else
        return Result.ok(userDao.queryByGAndP(user));
    }else
      return Result.build(1001,"暂无打分权限");
  }

  @Override
  public Result queryScoreList(User user) {
    Map map = userDao.queryLimits(user.getId());
    if (Integer.valueOf(map.get("grade").toString()) == 1) {
      if (map.get("limits") == null) {
        return Result.ok(userDao.queryByGAndPList(user));
      } else if (Integer.valueOf(map.get("limits").toString()) == 0 || Integer.valueOf(map.get("limits").toString()) == 1) {
        return Result.ok(userDao.queryNotSelfList(user));
      }
      //经理
      else if (Integer.valueOf(map.get("limits").toString()) == 2) {
        return Result.ok(userDao.queryByManagerList(user));
      }
      //组长
      else if (Integer.valueOf(map.get("limits").toString()) == 3) {
        return Result.ok(userDao.queryByHeadmanList(user));
      } else
        return Result.ok(userDao.queryByGAndP(user));
    }else
      return Result.ok(null);
  }

  @Override
    public PageInfo<Map> queryNotScore(User user) {
        PageHelper.startPage(user.getPageIndex(),user.getPageSize(),true);
        PageInfo<Map> pageInfo = new PageInfo<>(userDao.queryNotScore(user));
        return pageInfo;
    }

  @Override
  public Result queryAppriseAll() {
      User user = new User();
    Calendar calendar = Calendar.getInstance();
    Integer year = calendar.get(Calendar.YEAR);
    Integer month;
    if (calendar.get(Calendar.MONTH)%3 == 2) {
      if (calendar.get(Calendar.DAY_OF_MONTH) >= 25) {
        month = calendar.get(Calendar.MONTH)-2;
      }else
        month = calendar.get(Calendar.MONTH)-3;
    }else
      month = calendar.get(Calendar.MONTH)-3-calendar.get(Calendar.MONTH)%3;
    if (month <=0)
      year--;
    user.setSqlDate(year + "-" + month + "-1");
    return  Result.ok(userDao.queryAppriseAll(user));
  }

  @Override
  public List<UserOut> queryAppraise(User user) {
    return userDao.queryAppraise(user);
  }

  @Override
  public List<UserOut> queryNotAppraise(User user) {
    Calendar calendar = Calendar.getInstance();
    Integer year = calendar.get(Calendar.YEAR);
    Integer month;
    if (calendar.get(Calendar.MONTH)%3 == 2) {
      if (calendar.get(Calendar.DAY_OF_MONTH) >= 25) {
        month = calendar.get(Calendar.MONTH)-2;
      }else
      month = calendar.get(Calendar.MONTH)-3;
    }else
      month = calendar.get(Calendar.MONTH)-3-calendar.get(Calendar.MONTH)%3;
    if (month <=0)
      year--;
    user.setSqlDate(year + "-" + month + "-1");
    return userDao.queryNotAppraise(user);
  }

  @Override
  public List<UserOut> queryNotScored(User user) {
    return userDao.queryNotScored(user);
  }

  @Override
  public List<UserOut> queryNotTecApp(User user) {
    return userDao.queryNotTecApp(user);
  }

  @Override
    public List<Map> queryByTec(Integer id) {
        return userDao.queryByTec(id);
    }

    @Override
    public List<Map> queryByName(User user) {
        return userDao.queryByName(user);
    }

    @Override
    public List<Integer> queryByUsername(List<ExcelData> list) {
        return userDao.queryByUsername(list);
    }

    @Override
    public void paw(User user) {
        userDao.paw(user);
    }

    @Override
    public List<Map> queryPrincipal(Integer id) {
        return userDao.queryPrincipal(id);
    }

  @Override

  public List<Map> userAll(Integer mode) {
      List<Map> list = new ArrayList<>();
      List<Department> dep = departmentDao.queryNotUser();
      for (int i = 0 ; i < dep.size(); i++){
        Map map = new HashMap();
        map.put("id",dep.get(i).getId());
        map.put("pid",0);
        map.put("label",dep.get(i).getName());
        List<Map> tecList = new ArrayList<>();
        List<Map> tec = technologyDao.queryBydepNoU(dep.get(i).getId());
        for (int j = 0; j < tec.size(); j++){
          Map map1 = new HashMap();
          map1.put("id",dep.get(i).getId() + "-" + tec.get(j).get("id"));
          map1.put("pid" , dep.get(i).getId());
          map1.put("label", tec.get(j).get("name"));
          map1.put("children", userDao.queryByT((Integer) tec.get(j).get("id"),mode));
          tecList.add(map1);
        }
        map.put("children" , tecList);
        list.add(map);
      }
    return list;
  }

  @Override
  public List<Map> userAllAndState(Integer id) {
    List<Map> list = new ArrayList<>();
    List<Department> dep = departmentDao.queryNotUser();
    for (int i = 0 ; i < dep.size(); i++){
      Map map = new HashMap();
      map.put("id",dep.get(i).getId());
      map.put("pid",0);
      map.put("label",dep.get(i).getName());
      List<Map> tecList = new ArrayList<>();
      List<Map> tec = technologyDao.queryBydepNoU(dep.get(i).getId());
      for (int j = 0; j < tec.size(); j++){
        Map map1 = new HashMap();
        map1.put("id",dep.get(i).getId() + "-" + tec.get(j).get("id"));
        map1.put("pid" , dep.get(i).getId());
        map1.put("label", tec.get(j).get("name"));
        map1.put("children", userDao.queryByTAndState(id,(Integer) tec.get(j).get("id")));
        tecList.add(map1);
      }
      map.put("children" , tecList);
      list.add(map);
    }
    return list;
  }

  @Override
  public List<Map> userAllAndGroup(Integer id,Integer mode) {
    List<Map> list = new ArrayList<>();
    List<Department> dep = departmentDao.queryNotUser();
    for (int i = 0 ; i < dep.size(); i++){
      Map map = new HashMap();
      map.put("id", "d-" + dep.get(i).getId());
      map.put("label",dep.get(i).getName());
      List<Map> tecList = new ArrayList<>();
      List<Map> tec = technologyDao.queryBydepNoU(dep.get(i).getId());
      for (int j = 0; j < tec.size(); j++){
        Map map1 = new HashMap();
        map1.put("id", "t-" + dep.get(i).getId() + "-" + tec.get(j).get("id"));
        map1.put("label", tec.get(j).get("name"));
        if (mode == 0) {
          //业务
          map1.put("children", userDao.queryByTAndGroup((Integer) tec.get(j).get("id"), id));
        }else if (mode == 1){
          //活动
          map1.put("children", userDao.queryByTAndGroup1((Integer) tec.get(j).get("id"), id));
        }
        tecList.add(map1);
      }
      map.put("children" , tecList);
      list.add(map);
    }
    return list;
  }


}
