package wxx.java.appraise.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.entity.ExcelData;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.result.Result;
import wxx.java.appraise.service.UserService;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
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
  public Map queryToupd(Integer id) {
    return userDao.queryToupd(id);
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
    public PageInfo<Map> queryNotScore(User user) {
        PageHelper.startPage(user.getPageIndex(),user.getPageSize(),true);
        PageInfo<Map> pageInfo = new PageInfo<>(userDao.queryNotScore(user));
        return pageInfo;
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
}
