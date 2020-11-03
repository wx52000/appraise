package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.GradeScore;
import wxx.java.appraise.entity.UserScore;

import java.util.List;
import java.util.Map;

@Repository
public interface GradeScoreDao {

    void add(GradeScore list);

    void del(GradeScore gradeScore);

    void addExcel(@Param("grade") List<Integer> grade , @Param("score")List<Integer> score);

    void amongExcel(List<Integer> list);

    void updState(List<UserScore> id);

    void resetState();

    void delAll();
    //用于查询该评价人与哪些专业的被评价人关联
    List<Map> queryTec(Integer id);

    ///用于查询该评价人与哪些部门的被评价人关联
    List<Map> queryDep(Integer id);
}
