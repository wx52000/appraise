package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.GradeTecDao;
import wxx.java.appraise.entity.GradeTec;
import wxx.java.appraise.service.GradeTecService;

import java.util.List;
import java.util.Set;

@Service
public class GradeTecServiceImpl implements GradeTecService {
    private GradeTecDao gradeTecDao;
    @Autowired
    public void  setGradeTecDao(GradeTecDao gradeTecDao){
        this.gradeTecDao = gradeTecDao;
    }
    @Override
    public void add(GradeTec gradeTec) {
        gradeTecDao.add(gradeTec);
    }

    @Override
    public void del(Integer id) {
        gradeTecDao.del(id);
    }

    @Override
    public void addExcel(List<Integer> list, Set<Integer> set) {
        gradeTecDao.addExcel(list,set);
    }

    @Override
    public void resetState() {
        gradeTecDao.resetState();
    }
}
