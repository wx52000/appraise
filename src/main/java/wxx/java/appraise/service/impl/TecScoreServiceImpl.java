package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.GradeTecDao;
import wxx.java.appraise.dao.TecScoreDao;
import wxx.java.appraise.dao.TechnologyDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.entity.*;
import wxx.java.appraise.service.TecScoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static wxx.java.appraise.tools.AppraiseDate.*;

@Service
public class TecScoreServiceImpl implements TecScoreService {
    private TecScoreDao tecScoreDao;
    private UserDao userDao;
    private GradeTecDao gradeTecDao;
    private TechnologyDao technologyDao;
    @Autowired
    public  void  setTechnologyDao(TechnologyDao technologyDao){
      this.technologyDao = technologyDao;
    }
    @Autowired
    public void  setTecScoreDao(TecScoreDao tecScoreDao){
        this.tecScoreDao = tecScoreDao;
    }
    @Autowired
    public void  setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    public void setGradeTecDao(GradeTecDao gradeTecDao){
        this.gradeTecDao = gradeTecDao;
    }

    @Override
    public List<Map> queryByGradeId(Integer id) {
        return tecScoreDao.queryByGradeId(id);
    }

  @Override
  public Map queryByScoreId(User user) {
//      list.addAll(tecScoreDao.queryByScoreIdPast(user));
//      Map map = null;
//      list.remove(map);
//      Map map1 = new HashMap();
//      map1.put("month",0);
//      list.remove(map1);
    return tecScoreDao.queryByScoreId(user);
  }

  @Override
    public void appraise(List<TecScore> list) {
        Map<Object,Integer> map = appDate();
        Integer month = map.get("month");
        Integer year = map.get("year");
        tecScoreDao.appraise(list,month,year);
    }

    @Override
    public List<Map> queryScore(User user) {
//      Map<Object,Integer> map = appDate();
//      Integer month = map.get("month");
//      Integer year = map.get("year");
//      user.setThisYear(year);
//      User user1 = userDao.queryById(user.getId());
//      user.setPid(user1.getPid());
//      if (month == user.getThisMonth()) {
//        return tecScoreDao.queryScore(user);
//      }else {
//        if ( month == 1){
//          user.setThisYear(--year);
//        }else if (month == 2){
//          if (user.getThisMonth() == 12){
//            user.setThisYear(--year);
//          }
//        }
//        return tecScoreDao.queryScorePast(user);
//      }
      if (quarter(user.getThisMonth())){
        return tecScoreDao.queryScore(user);
      }else{
        user.setThisYear(year(user.getThisMonth()));
        return tecScoreDao.queryScorePast(user);
      }
    }

    @Override
    public List<Map> query(User user) {
//      Map<Object,Integer> map = appDate();
//      Integer month = map.get("month");
//      Integer year = map.get("year");
//      user.setThisYear(year);
//      User user1 = userDao.queryById(user.getId());
//      user.setPid(user1.getPid());
//      if (month == user.getThisMonth()) {
//        return tecScoreDao.query(user);
//      }else {
//        if ( month == 1){
//          user.setThisYear(--year);
//        }else if (month == 2){
//          if (user.getThisMonth() == 12){
//            user.setThisYear(--year);
//          }
//        }
//        return tecScoreDao.queryPast(user);
//      }
      if (quarter(user.getThisMonth())){
        return tecScoreDao.query(user);
      }else{
        user.setThisYear(year(user.getThisMonth()));
        return tecScoreDao.queryPast(user);
      }
    }

  @Override
  public List<List<String>> detail(User user,List<String> userName) {
    List<List<String>> data = new ArrayList<>();
    List<Technology> tec = technologyDao.queryNotUser();
    if (quarter(user.getThisMonth())) {
      for (int i = 0; i< tec.size() ; i++){
        List<String> list = new ArrayList<>();
        list.add(tec.get(i).getName());
        list.addAll(tecScoreDao.detail(tec.get(i).getId(),userName));
        data.add(list);
      }
    }else {
      user.setThisYear(year(user.getThisMonth()));
      for (int i = 0; i< tec.size() ; i++){
        List<String> list = new ArrayList<>();
        list.add(tec.get(i).getName());
        list.addAll(tecScoreDao.detailPast(tec.get(i).getId(),user,userName));
        data.add(list);
      }
    }
      return data;
  }

  @Override
    public List<TechnologyExcel> excel(User user) {
    User user1 = userDao.queryById(user.getId());
    user.setPid(user1.getPid());
    if (quarter(user.getThisMonth())) {
      return tecScoreDao.excel(user);
    }else {
      user.setThisYear(year(user.getThisMonth()));
      return tecScoreDao.excel1(user);
    }
    }

  @Override
  public List<TecPartExcel> part(List<Map> toData,Integer month ) {
    User user = new User();
    List<Integer> users = new ArrayList<>();
    user.setUsers(users);
    user.setThisMonth(month);
    for (int i = 0;i< toData.size();i++){
      user.getUsers()
        .add(Integer.valueOf(toData.get(i).get("id").toString()
          .substring(toData.get(i).get("id").toString().lastIndexOf( "-" )+1)));
    }
    if (quarter(user.getThisMonth())) {
      return tecScoreDao.part(user);
    }else {
      user.setThisYear(year(user.getThisMonth()));
      return tecScoreDao.partPast(user);
    }
  }

  @Override
    public void backups() {
        tecScoreDao.backups();
    }

    @Override
    public void delete() {
        tecScoreDao.delete();
    }


}
