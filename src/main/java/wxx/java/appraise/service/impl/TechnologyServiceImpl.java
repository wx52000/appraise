package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.TechnologyDao;
import wxx.java.appraise.entity.Project;
import wxx.java.appraise.entity.Technology;
import wxx.java.appraise.service.TechnologyService;

import java.util.List;
import java.util.Map;

@Service
public class TechnologyServiceImpl implements TechnologyService {
    private TechnologyDao technologyDao;
    @Autowired
    public  void  setTechnologyDao(TechnologyDao technologyDao ){

        this.technologyDao = technologyDao;
    }

    @Override
    public List<Technology> query(Integer id) {
        return technologyDao.query(id);
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
