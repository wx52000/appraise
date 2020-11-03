package wxx.java.appraise.service;

import wxx.java.appraise.entity.GradeScore;

import java.util.List;
import java.util.Map;

public interface GradeScoreService {

    void manage(GradeScore gradeScore);

    void del(GradeScore  gradeScore);

    void addExcel(List<Integer> grade , List<Integer> score);

    void amongExcel(List<Integer> list);

    void resetState();

    //用于查询该评价人与哪些专业的被评价人关联
    List<Map> queryTec(Integer id);

    ///用于查询该评价人与哪些部门的被评价人关联
    List<Map> queryDep(Integer id);
}
