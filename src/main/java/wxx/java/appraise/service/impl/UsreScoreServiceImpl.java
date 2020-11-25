package wxx.java.appraise.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.GradeScoreDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.dao.UserScoreDao;
import wxx.java.appraise.entity.PartExcel;
import wxx.java.appraise.entity.PersonalExcel;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserScore;
import wxx.java.appraise.service.UserScoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static wxx.java.appraise.tools.AppraiseDate.appDate;

@Transactional
@Service
public class UsreScoreServiceImpl implements UserScoreService {
    private UserDao userDao;
    private UserScoreDao userScoreDao;
    private GradeScoreDao gradeScoreDao;

    @Autowired
    public void setUserScoreDao(UserScoreDao userScoreDao){
        this.userScoreDao = userScoreDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setGradeScoreDao(GradeScoreDao gradeScoreDao){
        this.gradeScoreDao = gradeScoreDao;
    }


    @Override
    public PageInfo<Map> queryByGradeId(User user) {
        PageHelper.startPage(user.getPageIndex(),user.getPageSize(),true);
        PageInfo<Map> pageInfo = new PageInfo<>(userScoreDao.queryByGradeId(user));
        return pageInfo;
    }

    @Override
    public void appraise(List<UserScore> list) {
        Map<Object,Integer> map = appDate();
        Integer month = map.get("month");
        Integer year = map.get("year");
        userScoreDao.appraise(list,month,year);
    }

    @Override
    public List<Map> queryScore(User user) {
      Map<Object,Integer> map = appDate();
      Integer month = map.get("month");
      Integer year = map.get("year");
      user.setThisYear(year);
      User user1 = userDao.queryById(user.getId());
      user.setPid(user1.getPid());
      if (month == user.getThisMonth()) {
        return userScoreDao.queryScore(user);
      }else {
        if ( month == 1){
          user.setThisYear(--year);
      }else if (month == 2){
          if (user.getThisMonth() == 12){
            user.setThisYear(--year);
          }
        }
        return userScoreDao.queryScorePast(user);
      }
    }

    @Override
    public List<Map> query(User user) {
        return userScoreDao.query(user);
    }

  @Override
  public List<List<String>> detail() {
      List<List<String>> data = new ArrayList<>();
      List<Map> user = userDao.queryAll();
      for (int i = 0; i< user.size() ; i++){
        List<String> list = new ArrayList<>();
        list.add(user.get(i).get("name").toString());
        list.addAll(userScoreDao.detail((Integer) user.get(i).get("id")));
        data.add(list);
      }
    return data;
  }

  @Override
    public void add(List<UserScore> list) {
        userScoreDao.add(list);
    }

    @Override
    public void del(UserScore userScore) {
        userScoreDao.del(userScore);
    }

    @Override
    public List<PersonalExcel> excel1(User user) {
      Map<Object,Integer> map = appDate();
      Integer month = map.get("month");
      Integer year = map.get("year");
      user.setThisYear(year);
      User user1 = userDao.queryById(user.getId());
      user.setPid(user1.getPid());
      if (month.equals(user.getThisMonth())) {
        return userScoreDao.excel1(user);
      }else {
        if ( month == 1){
          user.setThisYear(--year);
        }else if (month == 2){
          if (user.getThisMonth() == 12){
            user.setThisYear(--year);
          }
        }
        return userScoreDao.excel2(user);
      }
    }

    @Override
    public List<Map> selectByGradeId(User user) {
        return userScoreDao.queryByGradeId(user);
    }

  @Override
  public List<PartExcel> part(Integer mode, List<Map> toData) {
      User user = new User();
      List<Integer> users = new ArrayList<>();
      user.setUsers(users);
      for (int i = 0;i< toData.size();i++){
        user.getUsers()
          .add(Integer.valueOf(toData.get(i).get("id").toString()
            .substring(toData.get(i).get("id").toString().lastIndexOf( "-" )+1)));
      }
      if (toData.size() > 0) {
        if (mode == 0) {
          return userScoreDao.part0(user);
        } else {
          return userScoreDao.part1(user);
        }
      }else
        return  null;
  }

  @Override
    public void backups() {
        userScoreDao.backups();
    }

    @Override
    public void delete() {
        userScoreDao.delete();
    }


}
