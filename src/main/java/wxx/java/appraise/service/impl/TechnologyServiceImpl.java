package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.TechnologyDao;
import wxx.java.appraise.dao.UserDao;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Technology;
import wxx.java.appraise.entity.User;
import wxx.java.appraise.service.TechnologyService;

import java.util.List;
import java.util.Map;

@Service
public class TechnologyServiceImpl implements TechnologyService {
    private TechnologyDao technologyDao;
    private UserDao userDao;
    @Autowired
    public  void  setTechnologyDao(TechnologyDao technologyDao ){

        this.technologyDao = technologyDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao){
      this.userDao = userDao;
    }

    @Override
    public List<Technology> query(Integer id) {
        return technologyDao.query(id);
    }

  @Override
  public List<Technology> queryNotUser() {
    return technologyDao.queryNotUser();
  }

  @Override
  public List<Map> evaluate(Integer id) {
    User user1 = userDao.queryById(id);
    if (user1.getGrade() == 1) {
      return technologyDao.evaluate(id);
    }else
      return null;
  }


  @Override
    public Integer queryByName(String name) {
        return technologyDao.queryByName(name);
    }

    @Override
    public void add(Technology technology) {
        technologyDao.add(technology);
    }

  @Override
  public Integer addString(String name, Integer d) {
      Technology technology = new Technology();
      technology.setName(name);
      technology.setDid(d);
      technologyDao.add(technology);
    return technology.getId();
  }

  @Override
    public void del(Integer id) {
        technologyDao.del(id);
    }

    @Override
    public List<Technology> queryToSelected() {
        return technologyDao.queryToSelected();
    }

    @Override
    public List<Map> queryById(Project project) {
        return technologyDao.queryById(project);
    }
}
