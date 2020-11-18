package wxx.java.appraise.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wxx.java.appraise.dao.GradeScoreDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.dao.UserScoreDao;
import wxx.java.appraise.entity.PersonalExcel;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.entity.UserScore;
import wxx.java.appraise.service.UserScoreService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
        Calendar calendar = Calendar.getInstance();
        Integer month = 0;
        if (calendar.get(Calendar.DAY_OF_MONTH) >= 20)
            month = calendar.get(Calendar.MONTH)+1;
        else month = calendar.get(Calendar.MONTH);
        userScoreDao.appraise(list,month);
//        gradeScoreDao.updState(list);

    }

    @Override
    public List<Map> queryScore(User user) {
        User user1 = userDao.queryById(user.getId());
        user.setPid(user1.getPid());
        Calendar calendar = Calendar.getInstance();
        user.setThisMonth(calendar.get(Calendar.MONTH)+1);
        user.setThisDay(calendar.get(Calendar.DATE));
        return userScoreDao.queryScore(user);
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

        return userScoreDao.excel1(user);
    }

    @Override
    public List<Map> selectByGradeId(User user) {
        return userScoreDao.queryByGradeId(user);
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
