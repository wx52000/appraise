package wxx.java.appraise.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wxx.java.appraise.dao.GradeScoreDao;
import wxx.java.appraise.entity.GradeScore;
import wxx.java.appraise.service.GradeScoreService;

import java.util.List;
import java.util.Map;

@Service
public class GradeScoreServiceImpl implements GradeScoreService {
    private GradeScoreDao gradeScoreDao;
    @Autowired
    public  void  setGradeScoreDao(GradeScoreDao gradeScoreDao){
        this.gradeScoreDao = gradeScoreDao;
    }

    @Override
    public void manage(GradeScore gradeScore) {
//        System.out.println(gradeScore);
        if (gradeScore.getAddScoreId().size() != 0)
          gradeScoreDao.add(gradeScore);
        if (gradeScore.getDelScoreId().size() != 0)
          gradeScoreDao.del(gradeScore);
    }

    @Override
    public void del(GradeScore gradeScore) {
        gradeScoreDao.del(gradeScore);
    }

    @Override
    public void addExcel(List<Integer> grade , List<Integer> score) {
        gradeScoreDao.addExcel(grade,score);
    }

    @Override
    public void amongExcel(List<Integer> list) {
        gradeScoreDao.amongExcel(list);
    }

    @Override
    public void resetState() {
        gradeScoreDao.resetState();
    }

    @Override
    public List<Map> queryTec(Integer id) {
        return gradeScoreDao.queryTec(id);
    }

    @Override
    public List<Map> queryDep(Integer id) {
        return gradeScoreDao.queryDep(id);
    }
}
