package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.GradeTecDao;
import wxx.java.appraise.dao.TecScoreDao;
import wxx.java.appraise.dao.TechnologyDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.entity.TecScore;
import wxx.java.appraise.entity.Technology;
import wxx.java.appraise.entity.TechnologyExcel;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.service.TecScoreService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
    public void appraise(List<TecScore> list) {
        Calendar calendar = Calendar.getInstance();
        Integer month = 0;
        if (calendar.get(Calendar.DAY_OF_MONTH) >= 20)
            month = calendar.get(Calendar.MONTH)+1;
        else month = calendar.get(Calendar.MONTH);
        tecScoreDao.appraise(list,month);
//        gradeTecDao.updState(list);
    }

    @Override
    public List<Map> queryScore(Integer id) {
        User user = userDao.queryById(id);
        Calendar calendar = Calendar.getInstance();
        user.setThisMonth(calendar.get(Calendar.MONTH)+1);
        user.setThisDay(calendar.get(Calendar.DATE));
        return tecScoreDao.queryScore(user);
    }

    @Override
    public List<Map> query(User user) {
        return tecScoreDao.query(user);
    }

  @Override
  public List<List<String>> detail() {
      List<List<String>> data = new ArrayList<>();
      List<Technology> tec = technologyDao.queryNotUser();
      for (int i = 0; i< tec.size() ; i++){
        List<String> list = new ArrayList<>();
        list.add(tec.get(i).getName());
        list.addAll(tecScoreDao.detail((Integer) tec.get(i).getId()));
        data.add(list);
      }
      return data;
  }

  @Override
    public List<TechnologyExcel> excel(User user) {
        user = userDao.queryById(user.getId());
        return tecScoreDao.excel(user);
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
