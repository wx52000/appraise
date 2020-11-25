package wxx.java.appraise.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wxx.java.appraise.entity.TecPartExcel;
import wxx.java.appraise.entity.TecScore;
import wxx.java.appraise.entity.TechnologyExcel;
import wxx.java.appraise.entity.User;

import java.util.List;
import java.util.Map;

@Repository
public interface TecScoreDao {

    List<Map> queryByGradeId(Integer id);

    void appraise(@Param("list") List<TecScore> list,
                  @Param("month") Integer month , @Param("year")Integer year);

    List<Map> queryScore(User user);

    List<Map> queryScorePast(User user);

    List<Map> query(User user);

    List<String> detail(Integer id);

    //本月数据
    List<TechnologyExcel> excel(User user);
    //上月数据
    List<TechnologyExcel> excel1(User user);

    List<TecPartExcel> part(User user);

    void backups();

    void delete();
}
