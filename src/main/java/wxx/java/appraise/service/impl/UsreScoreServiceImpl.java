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
import java.util.HashMap;
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
    public List<Map> queryByScoreId(User user) {
      List<Map> list = new ArrayList<>();
      list.add(userScoreDao.queryByScoreId(user));
      list.addAll(userScoreDao.queryByScoreIdPast(user));
      Map map = null;
      list.remove(map);
      Map map1 = new HashMap();
      map1.put("month",0);
      list.remove(map1);
      return list;
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
      Map<Object,Integer> map = appDate();
      Integer month = map.get("month");
      Integer year = map.get("year");
      user.setThisYear(year);
      User user1 = userDao.queryById(user.getId());
      user.setPid(user1.getPid());
      if (month == user.getThisMonth()) {
        return userScoreDao.query(user);
      }else {
        if ( month == 1){
          user.setThisYear(--year);
        }else if (month == 2){
          if (user.getThisMonth() == 12){
            user.setThisYear(--year);
          }
        }
        return userScoreDao.queryPast(user);
      }
    }

  @Override
  public List<List<String>> detail(User user) {
    List<List<String>> data = new ArrayList<>();
    List<Map> users = userDao.queryAll();
    Map<Object,Integer> map = appDate();
    Integer month = map.get("month");
    Integer year = map.get("year");
    user.setThisYear(year);
    if (month == user.getThisMonth()) {
      for (int i = 0; i< users.size() ; i++){
        List<String> list = new ArrayList<>();
        list.add(users.get(i).get("name").toString());
        list.addAll(userScoreDao.detail((Integer) users.get(i).get("id")));
        data.add(list);
      }
    }else {
      if ( month == 1){
        user.setThisYear(--year);
      }else if (month == 2){
        if (user.getThisMonth() == 12){
          user.setThisYear(--year);
        }
      }
      for (int i = 0; i< users.size() ; i++){
        List<String> list = new ArrayList<>();
        list.add(users.get(i).get("name").toString());
        list.addAll(userScoreDao.detailPast(user,(Integer) users.get(i).get("id")));
        data.add(list);
      }
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
  public List<PartExcel> part(Integer mode, List<Map> toData,Integer month) {
      User user = new User();
      List<Integer> users = new ArrayList<>();
      user.setUsers(users);
      user.setThisMonth(month);
      for (int i = 0;i< toData.size();i++){
        user.getUsers()
          .add(Integer.valueOf(toData.get(i).get("id").toString()
            .substring(toData.get(i).get("id").toString().lastIndexOf( "-" )+1)));
      }
      Map<Object,Integer> map = appDate();
      Integer month1 = map.get("month");
      Integer year = map.get("year");
      user.setThisYear(year);
      if (month1.equals(user.getThisMonth())) {
        if (toData.size() > 0) {
          if (mode == 0) {
            return userScoreDao.part0(user);
          } else {
            return userScoreDao.part1(user);
          }
        }else
          return  null;
      }else {
        if ( month1 == 1){
          user.setThisYear(--year);
        }else if (month1 == 2){
          if (user.getThisMonth() == 12){
            user.setThisYear(--year);
          }
        }
        if (toData.size() > 0) {
          if (mode == 0) {
            return userScoreDao.partPast0(user);
          } else {
            return userScoreDao.partPast1(user);
          }
        }else
          return  null;
    }
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
